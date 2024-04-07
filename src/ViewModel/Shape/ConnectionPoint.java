package ViewModel.Shape;

import Model.ConnectType;

import java.awt.*;

public class ConnectionPoint {
    int x,y,width;//width odd is better
    ConnectType connectType;
    ConnectionLine connectionLine;
    public void setConnectType(ConnectType type){connectType=type;}
    public ConnectionPoint(int x,int y,int width){
        this.x=x;
        this.y=y;
        this.width=width;
        connectType=ConnectType.NONE;
    }
    public void draw(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(x-width/2,y-width/2,width,width);
    }
    public void move(int x,int y){
        this.x+=x;
        this.y+=y;
    }
    public int getX(){return x;}
    public int getY(){return y;}
}
