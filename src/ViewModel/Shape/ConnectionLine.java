package ViewModel.Shape;

import Model.ConnectType;

import java.awt.*;

public class ConnectionLine {
    int startX,startY,endX,endY,depth;
    private final int ARROW_WIDTH=21;//odd is better
    ConnectionPoint start,end;
    ConnectType connectType;
    public ConnectionLine(ConnectionPoint start,ConnectionPoint end,ConnectType type){
        this.start = start;
        this.end = end;
        startX=start.getX();
        startY=start.getY();
        endX=end.getX();
        endY=end.getY();
        connectType = type;
    }
    public ConnectionLine(int startX,int startY,int endX,int endY,ConnectType type) {//use for temp object
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        connectType=type;
    }
    void moveStart(int x,int y){
        startX+=x;
        startY+=y;
    }
    public void moveEnd(int x,int y){
        endX+=x;
        endY+=y;
    }
    void drawLine(Graphics g){
        // 计算线段的长度
        double totalLength = distance(startX, startY, endX, endY);
        if (totalLength < ARROW_WIDTH)
            return;
        // 计算比例
        double ratio = (totalLength - ARROW_WIDTH) / totalLength;
        // 根据比例计算新点的坐标
        int newX = (int) (startX + ratio * (endX - startX));
        int newY = (int) (startY + ratio * (endY - startY));
        // 计算直线的旋转角度
        double angle = Math.atan2(endY - startY, endX - startX);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawLine(startX, startY, newX, newY);
        // 抗锯齿 看起来会好看点
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 根据角度旋转 Graphics2D 上下文
        g2d.rotate(angle, newX, newY); // 绕起点旋转
        connectType.draw(g2d,newX,newY,ARROW_WIDTH);
        g2d.dispose();
    }
    public double distance(int x1,int y1,int x2,int y2){
        return Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
    }
    public void draw(Graphics g){
        if(distance(startX,startY,endX,endY)<ARROW_WIDTH)
            return;
        g.setColor(Color.BLACK);
        //g.drawLine(startX,startY,endX,endY);
        Graphics2D g2d = (Graphics2D) g.create();
        drawLine(g2d);
    }
}