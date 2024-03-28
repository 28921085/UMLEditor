package ViewModel.UI;

import ViewModel.Shape.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import java.util.ArrayList;

public class DrawingPanel extends JPanel {
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
            }
        });
    }
    // 绘制方法
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.setColor(Color.BLACK);
        //g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        for (Shapes s : componments)
            s.draw(g);

    }
}
