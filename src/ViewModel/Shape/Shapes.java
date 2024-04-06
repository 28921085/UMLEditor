package ViewModel.Shape;

import Model.ConnectType;
import Model.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Shapes{
    int x,y,width,height,depth;
    boolean isSelected;
    String name;
    List<Shapes> connect = new ArrayList<>();
    List<ConnectionPoint>connectionPoints = new ArrayList<>();
    public abstract void drawShape(Graphics g);

    public Shapes(int x,int y,int width,int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        isSelected = false;
        name = "";
        generateConnectionPoints();
    }
    public void generateConnectionPoints(){
        connectionPoints.add(new ConnectionPoint(x+width/2,y,11, Direction.NORTH));
        connectionPoints.add(new ConnectionPoint(x+width,y+height/2,11,Direction.EAST));
        connectionPoints.add(new ConnectionPoint(x+width/2,y+height,11,Direction.SOUTH));
        connectionPoints.add(new ConnectionPoint(x,y+height/2,11,Direction.WEST));
        //TODO check this point position may have bug
    }
    public void drawConnectionPoints(Graphics g){
        if(!isSelected)
            return;
        for(ConnectionPoint point:connectionPoints)
            point.draw(g);
    }
    public void draw(Graphics g){
        //debug
        //debug(g);
        drawShape(g);
        drawConnectionPoints(g);
    }
    public boolean isInside(int x,int y){
        if(x<this.x||y<this.y)
            return false;
        if(x>this.x+width||y>this.y+height)
            return false;
        return true;
    }
    public void move(int x,int y){
        //move component
    }
    public void debug(Graphics g){
        isSelected = true;
        for(ConnectionPoint point:connectionPoints)
            point.setConnectType(ConnectType.ASSOCIATION_LINE_END);
        g.drawString(String.valueOf(depth),x,y);
    }
    public boolean getSelectedState(){return isSelected;}
    public void setSelectedState(boolean state){isSelected = state;}
    public void setDepth(int depth){this.depth=depth;}
    //ToDO
    //4 direction connect

}
