package ViewModel.UI;

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
    private boolean mouseIsDragging;
    private int startX, startY,draggedX,draggedY;
    public DrawingPanel() {

        this.setBackground(Color.WHITE);
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
                startY = e.getY();
                if(currentMode == ModeType.SELECT){
                    selectAtPoint(startX,startY);
                }
                else if(currentMode == ModeType.CLASS) {
                    components.add(0,new ClassShape(startX, startY, 101, 101));
                    reorderedComponentDepth();
                }
                else if(currentMode == ModeType.USE_CASE) {
                    components.add(0,new UseCaseShape(startX, startY, 101, 51));
                    reorderedComponentDepth();
                }
                repaint();
                //System.out.println("滑鼠pressed位置：(" + startX + ", " + startY + ")");
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                mouseIsDragging = false;
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
                repaint();
                //System.out.println("滑鼠dragged位置：(" + x + ", " + y + ")");
                //TODO
                //move components

            }
        });
    }
    void selectAtPoint(int x,int y){
        for(int i=0;i<components.size();i++){
            if(components.get(i).isInside(x,y)){//click最上層
                if(components.get(i).getSelectedState())//本來就選取->取消選取
                    unSelectAllComponents();
                else {//本來未選取->選取
                    unSelectAllComponents();
                    components.get(i).setSelectedState(true);
                }
                components.add(0,components.remove(i));
                reorderedComponentDepth();
                break;
            }
        }
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
        for(Shapes shape:components)
            shape.setSelectedState(false);
    }
    void reorderedComponentDepth(){//變相地進行sort
        for(int i=0;i<components.size();i++)
            components.get(i).setDepth(i);
    }
    // 绘制方法
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //較深的先畫
        for (int i=components.size()-1;i>-1;i--)
            components.get(i).draw(g);
        if(currentMode == ModeType.SELECT&&mouseIsDragging)
            drawSelectedArea(startX,startY,draggedX,draggedY,g);
    }
    @Override
    public void updateSelect(ModeType modeType) {
        setCurrentMode(modeType);
    }
    public void setCurrentMode(ModeType currentMode) {this.currentMode = currentMode;}
}
