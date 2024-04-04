package ViewModel.UI;

import Model.SelectType;
import Model.SelectObserver;
import ViewModel.Shape.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import java.util.ArrayList;

public class DrawingPanel extends JPanel implements SelectObserver {
    private SelectType currentSelect;
    private List<Shapes>componments=new ArrayList<>();
    public DrawingPanel() {
        this.setBackground(Color.WHITE);
        this.addMouseListener(new MouseAdapter() {
            private int startX, startY;

            @Override
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
                startY = e.getY();
                //System.out.println("滑鼠pressed位置：(" + startX + ", " + startY + ")");
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                componments.add(new MyRectangle(startX,startY));
                repaint();
                //System.out.println("滑鼠放開位置：(" + x + ", " + y + ")");
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                //System.out.println("滑鼠點擊位置：(" + x + ", " + y + ")");
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                //System.out.println("滑鼠當前位置：(" + x + ", " + y + ")");
                //TODO
                //move componment
                //select area
            }
        });
    }
    public void debug(){
        System.out.println(currentSelect);
    }
    // 绘制方法
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shapes s : componments)
            s.draw(g);
    }
    @Override
    public void updateSelect(SelectType selectType) {
        setCurrentSelect(selectType);
    }
    public void setCurrentSelect(SelectType currentSelect) {this.currentSelect = currentSelect;}
}
