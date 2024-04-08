package ViewModel.UI;

import Model.ConnectType;
import Model.ModeType;
import Model.ModeObserver;
import ViewModel.Shape.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

public class DrawingPanel extends JPanel implements ModeObserver {
    private ModeType currentMode;
    private List<Shapes>components=new ArrayList<>();
    private List<ConnectionLine>lines=new ArrayList<>();
    private boolean mouseIsDragging;
    private int startX, startY,draggedX,draggedY;
    private Shapes currentSelect=null;
    private ConnectionPoint currentSelectPoint = null;
    private ConnectionLine currentDrawing = null;
    public DrawingPanel() {

        this.setBackground(Color.WHITE);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
                startY = e.getY();
                if(currentMode == ModeType.SELECT){
                    currentSelect = selectShapeAtPoint(startX,startY);
                }
                else if(currentMode == ModeType.CLASS) {
                    components.add(0,new ClassShape(startX, startY, 101, 101));
                    reorderedComponentDepth();
                    unSelectAllComponents();
                }
                else if(currentMode == ModeType.USE_CASE) {
                    components.add(0,new UseCaseShape(startX, startY, 101, 51));
                    reorderedComponentDepth();
                    unSelectAllComponents();
                }
                else if(currentMode == ModeType.ASSOCIATION_LINE || currentMode == ModeType.COMPOSITION_LINE || currentMode == ModeType.GENERALIZATION_LINE){
                    currentSelect = selectShapeAtPoint(startX,startY);
                    if(currentSelect != null) {//有點到shape上
                        currentSelectPoint = currentSelect.assignConnectionPoint(startX, startY);
                        int x=currentSelectPoint.getX(),y=currentSelectPoint.getY();
                        if(currentMode == ModeType.ASSOCIATION_LINE)
                            currentDrawing = new ConnectionLine(x,y,x,y,ConnectType.ASSOCIATION_LINE_END);
                        else if(currentMode == ModeType.COMPOSITION_LINE)
                            currentDrawing = new ConnectionLine(x,y,x,y,ConnectType.COMPOSITION_LINE_END);
                        else
                            currentDrawing = new ConnectionLine(x,y,x,y,ConnectType.GENERALIZATION_LINE_END);
                    }
                    //if(currentSelectPoint != null)
                    //    currentSelectPoint.setConnectType(ConnectType.ASSOCIATION_LINE_END);
                    //unSelectAllComponents();
                }
                /*else if(currentMode == ModeType.COMPOSITION_LINE){
                    unSelectAllComponents();
                }
                else if(currentMode == ModeType.GENERALIZATION_LINE){
                    unSelectAllComponents();
                }*/
                repaint();
                //System.out.println("滑鼠pressed位置：(" + startX + ", " + startY + ")");
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                mouseIsDragging = false;

                if(currentMode == ModeType.ASSOCIATION_LINE ||
                        currentMode == ModeType.COMPOSITION_LINE ||
                        currentMode == ModeType.GENERALIZATION_LINE){
                    Shapes releasedShape = selectShapeAtPoint(x,y);
                    if(releasedShape != null){
                        ConnectionPoint releasedPoint = releasedShape.assignConnectionPoint(x,y);
                        ConnectType type;
                        if(currentMode == ModeType.ASSOCIATION_LINE)
                            type = ConnectType.ASSOCIATION_LINE_END;
                        else if(currentMode == ModeType.COMPOSITION_LINE)
                            type = ConnectType.COMPOSITION_LINE_END;
                        else
                            type = ConnectType.GENERALIZATION_LINE_END;
                        ConnectionLine line = new ConnectionLine(currentSelectPoint,releasedPoint,type);
                        currentSelectPoint.setConnectionLine(line);
                        releasedPoint.setConnectionLine(line);
                        lines.add(line);
                    }
                }

                currentSelectPoint = null;
                currentDrawing = null;

                repaint();
                //System.out.println("滑鼠放開位置：(" + x + ", " + y + ")");
                //TODO
                //group
            }
            //@Override
            //public void mouseClicked(MouseEvent e) {
            //    int x = e.getX();
            //    int y = e.getY();
            //    repaint();
            //    //System.out.println("滑鼠點擊位置：(" + x + ", " + y + ")");
            //}
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                draggedX = e.getX();
                draggedY = e.getY();
                mouseIsDragging = true;
                if(currentMode == ModeType.SELECT&&currentSelect != null){//每次觸發mouse dragged都視為一個獨立的事件
                    currentSelect.move(draggedX-startX,draggedY-startY);
                    startX=draggedX;
                    startY=draggedY;
                }
                else if((currentMode == ModeType.ASSOCIATION_LINE||
                        currentMode == ModeType.COMPOSITION_LINE||
                        currentMode == ModeType.GENERALIZATION_LINE)&&currentDrawing != null){//畫線
                    currentDrawing.moveEnd(draggedX-startX,draggedY-startY);
                    startX=draggedX;
                    startY=draggedY;
                }
                repaint();
                //System.out.println("滑鼠dragged位置：(" + x + ", " + y + ")");
            }
        });
    }
    Shapes selectShapeAtPoint(int x,int y){
        unSelectAllComponents();
        for(int i=0;i<components.size();i++){
            if(components.get(i).isInside(x,y)){//click最上層
                //不要新增在點一次取消選取，不然會很難做
                components.get(i).setSelectedState(true);
                Shapes tmp = components.get(i);

                components.add(0,components.remove(i));
                reorderedComponentDepth();
                return tmp;
            }
        }
        return null;
    }
    void drawSelectedArea(int x1,int y1,int x2,int y2,Graphics g){
        int width = Math.abs(x2-x1),height = Math.abs(y2-y1);
        int x = Math.min(x1,x2), y = Math.min(y1,y2);
        Graphics2D g2d = (Graphics2D) g;
        // 设置透明度为 50%
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        // 绘制透明的矩形
        g2d.setColor(Color.BLUE);
        g2d.fillRect(x, y, width, height);
        g2d.drawRect(x,y,width,height);
    }
    void unSelectAllComponents(){
        currentSelect = null;
        for(Shapes shape:components)
            shape.setSelectedState(false);
    }
    void reorderedComponentDepth(){//變相地進行sort
        for(int i=0;i<components.size();i++)
            components.get(i).setDepth(i);
    }
    protected void paintComponent(Graphics g) {//JPanel核心繪圖方法
        super.paintComponent(g);
        //較深的先畫
        for (int i=components.size()-1;i>-1;i--)
            components.get(i).draw(g);
        if(currentMode == ModeType.SELECT&&mouseIsDragging)
            drawSelectedArea(startX,startY,draggedX,draggedY,g);

        for(ConnectionLine line:lines)
            line.draw(g);

        if(currentDrawing!=null)
            currentDrawing.draw(g);
    }
    @Override
    public void updateSelect(ModeType modeType) {
        setCurrentMode(modeType);
    }
    public void setCurrentMode(ModeType currentMode) {this.currentMode = currentMode;}
}
