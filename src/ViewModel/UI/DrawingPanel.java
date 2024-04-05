package ViewModel.UI;

import Model.ModeType;
import Model.ModeObserver;
import ViewModel.Shape.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class DrawingPanel extends JPanel implements ModeObserver {
    private ModeType currentMode;
    private List<Shapes>components=new ArrayList<>();
    public DrawingPanel() {
        this.setBackground(Color.WHITE);
        this.addMouseListener(new MouseAdapter() {
            private int startX, startY;
            @Override
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
                startY = e.getY();
                if(currentMode == ModeType.SELECT){

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

                repaint();
                //System.out.println("滑鼠放開位置：(" + x + ", " + y + ")");
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
                int x = e.getX();
                int y = e.getY();
                repaint();
                //System.out.println("滑鼠當前位置：(" + x + ", " + y + ")");
                //TODO
                //move components
                //select area
            }
        });
    }
    public void sortComponentByDepth(boolean isAscendingOrder){
        if(isAscendingOrder)
            Collections.sort(components);
        else
            Collections.sort(components,Collections.reverseOrder());
    }
    void reorderedComponentDepth(){
        for(int i=0;i<components.size();i++)
            components.get(i).setDepth(i);
    }
    // 绘制方法
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //較深的先畫
        sortComponentByDepth(false);
        for (Shapes s : components)
            s.draw(g);
    }
    @Override
    public void updateSelect(ModeType modeType) {
        setCurrentMode(modeType);
    }
    public void setCurrentMode(ModeType currentMode) {this.currentMode = currentMode;}
}
