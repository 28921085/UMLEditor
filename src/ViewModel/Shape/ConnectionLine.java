package ViewModel.Shape;

import java.awt.*;

public class ConnectionLine {
    int startX,startY,endX,endY,depth;
    private final int ARROW_WIDTH=21;//odd is better
    ConnectionPoint start,end;
    public ConnectionLine(ConnectionPoint start,ConnectionPoint end){
        this.start = start;
        this.end = end;
        startX=start.getX();
        startY=start.getY();
        endX=end.getX();
        endY=end.getY();
    }
    public ConnectionLine(int startX,int startY,int endX,int endY) {//use for temp object
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }
    void moveStart(int x,int y){
        startX+=x;
        startY+=y;
    }
    public void moveEnd(int x,int y){
        endX+=x;
        endY+=y;
    }
    void drawLine(double len,Graphics g){
        if(len < ARROW_WIDTH)
            return;
        g.drawLine(0,0,(int)len-21,0);
    }
    /*
    public void drawPlug(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create(); // 创建副本以免影响原始 Graphics 对象
        g2d.rotate(Math.toRadians(direction.getDegree()), x, y);

        g2d.setStroke(new BasicStroke(3));
        //抗鋸齒 看起來會好看點
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        connectType.draw(g2d,x,y,ARROW_WIDTH);

        g2d.dispose();
    }
     */
    public double distance(int x1,int y1,int x2,int y2){
        return Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
    }
    public void draw(Graphics g){
        if(distance(startX,startY,endX,endY)<ARROW_WIDTH)
            return;
        g.setColor(Color.BLACK);
        g.drawLine(startX,startY,endX,endY);
    }
}