package ViewModel.Shape;

import java.awt.*;

public class ClassShape extends Shapes{
    private void drawRectangleWithStroke(int X,int Y,int width,int height,int stroke,Graphics g){
        g.setColor(Color.GRAY);
        // 绘制填充的正方形
        g.fillRect(X, Y, width, height);
        g.setColor(Color.BLACK);
        // 設置邊框粗細
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(stroke));
        // 绘制边框的正方形
        g.drawRect(X, Y, width, height);
    }
    public void drawShape(Graphics g){
        drawRectangleWithStroke(x,y,width,height/3,3,g);
        drawRectangleWithStroke(x,y+width/3,width,height/3,3,g);
        drawRectangleWithStroke(x,y+width/3*2,width,height/3,3,g);
    }
    public ClassShape(int x, int y,int width,int height){super(x,y,width,height);}
}
