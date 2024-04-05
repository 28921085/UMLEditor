package ViewModel.Shape;

import java.awt.*;

public class UseCaseShape extends Shapes{
    private void drawOvalWithStroke(int X,int Y,int width,int height,int stroke,Graphics g){
        g.setColor(Color.GRAY);
        // 绘制填充的椭圆
        g.fillOval(X, Y, width, height);
        g.setColor(Color.BLACK);
        // 設置邊框粗細
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(stroke));
        // 绘制边框的椭圆
        g.drawOval(X, Y, width, height);
    }
    public void draw(Graphics g){
        drawOvalWithStroke(x,y,width,height,3,g);
    }
    public UseCaseShape(int x, int y,int width,int height){super(x,y,width,height);}
}
