package ViewModel.Shape;

import Model.ConnectType;
import Model.Direction;

public class ConnectionPoint {
    int x,y;
    ConnectType connectType;
    Direction direction;
    void setConnectType(ConnectType type){connectType=type;}
    public ConnectionPoint(int x,int y,Direction direction){
        this.x=x;
        this.y=y;
        this.direction=direction;
        connectType=ConnectType.NONE;
    }
    public void draw(){

    }
    public void move(int x,int y){
        //TODO
    }
}
