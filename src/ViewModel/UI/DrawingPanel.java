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

public class DrawingPanel extends JPanel implements ModeObserver {
    private ModeType currentMode;
    private List<Shapes>componments=new ArrayList<>();
    public DrawingPanel() {
        this.setBackground(Color.WHITE);
        this.addMouseListener(new MouseAdapter() {
            private int startX, startY;

            @Override
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
                startY = e.getY();
                if(currentMode == ModeType.CLASS)
                    componments.add(new ClassShape(startX,startY,100,100));
                else if(currentMode == ModeType.USE_CASE)
                    componments.add(new UseCaseShape(startX,startY,100,50));
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
                //move componment
                //select area
            }
        });
    }
    public void debug(){
        System.out.println(currentMode);
    }
    // 绘制方法
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shapes s : componments)
            s.draw(g);
    }
    @Override
    public void updateSelect(ModeType modeType) {
        setCurrentMode(modeType);
    }
    public void setCurrentMode(ModeType currentMode) {this.currentMode = currentMode;}
}
