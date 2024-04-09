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
        //抗鋸齒 看起來會好看點
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 绘制边框的正方形
        g2d.drawRect(X, Y, width, height);
    }
    public void drawShape(Graphics g){
        //System.out.println("CLass");
        drawRectangleWithStroke(x,y,width,height/3,3,g);
        drawRectangleWithStroke(x,y+width/3,width,height/3,3,g);
        drawRectangleWithStroke(x,y+width/3*2,width,height/3,3,g);
        // 调整文本位置到矩形内部
        if (name != null) {
            g.setColor(Color.BLACK);
            // 将文本显示在矩形中心
            int textX = x + width / 2 - g.getFontMetrics().stringWidth(name) / 2;
            int textY = y + g.getFontMetrics().getHeight();
            g.drawString(name, textX, textY);
        }
    }
    public ClassShape(int x, int y,int width,int height){super(x,y,width,height);}
}
