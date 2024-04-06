package ViewModel.Shape;

import Model.ConnectType;
import Model.Direction;

import java.awt.*;

public class ConnectionPoint {
    int x,y,width;//width odd is better
    private final int ARROW_WIDTH=21;//odd is better
    ConnectType connectType;
    Direction direction;
    void setConnectType(ConnectType type){connectType=type;}
    public ConnectionPoint(int x,int y,int width,Direction direction){
        this.x=x;
        this.y=y;
        this.width=width;
        this.direction=direction;
        connectType=ConnectType.NONE;
    }
    public void draw(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(x-width/2,y-width/2,width,width);
        drawPlug(g);
    }

    public void drawPlug(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create(); // 创建副本以免影响原始 Graphics 对象
        g2d.rotate(Math.toRadians(direction.getDegree()), x, y);

        g2d.setStroke(new BasicStroke(3));
        //抗鋸齒 看起來會好看點
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        connectType.draw(g2d,x,y,ARROW_WIDTH);

        g2d.dispose();
    }
    public void move(int x,int y){
        this.x+=x;
        this.y+=y;
    }
}
