package ViewModel.Shape;

import Model.ConnectType;
import Model.Direction;

import java.awt.*;

public class ConnectionPoint {
    int x,y,width;
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
        g.drawRect(x-width/2,y-width/2,width,width);
    }
    public void move(int x,int y){
        //TODO
    }
}
