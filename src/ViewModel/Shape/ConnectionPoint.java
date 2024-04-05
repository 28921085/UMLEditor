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
        int width = 20,height = 10;
        //g.drawOval(x,y,width,height);

        Graphics2D g2d = (Graphics2D) g.create(); // 创建副本以免影响原始 Graphics 对象
        g2d.rotate(Math.toRadians(direction.getDegree()), x, y);

        //g2d.drawOval(x - width / 2, y - height / 2, width, height);
        connectType.draw(g2d);

        g2d.dispose();
    }
    public void move(int x,int y){
        //TODO
    }
}
