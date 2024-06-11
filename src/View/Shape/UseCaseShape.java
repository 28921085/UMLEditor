package View.Shape;

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
        //抗鋸齒 看起來會好看點
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 绘制边框的椭圆
        g2d.drawOval(X, Y, width, height);
    }
    public void drawShape(Graphics g){
        drawOvalWithStroke(x,y,width,height,3,g);
        if (name != null) {
            g.setColor(Color.BLACK);
            // 将文本显示在矩形中心
            int textX = x + width / 2 - g.getFontMetrics().stringWidth(name) / 2;
            int textY = y + height / 2 + g.getFontMetrics().getHeight() / 2;
            g.drawString(name, textX, textY);
        }
    }
    public UseCaseShape(int x, int y,int width,int height){super(x,y,width,height);}
}
