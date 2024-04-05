package ViewModel.Shape;

import Model.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Shapes {
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
        isSelected = true;
        name = "";
        generateConnectionPoints();
    }
    public void generateConnectionPoints(){
        connectionPoints.add(new ConnectionPoint(x+width/2,y,4, Direction.NORTH));
        connectionPoints.add(new ConnectionPoint(x+width,y+height/2,4,Direction.EAST));
        connectionPoints.add(new ConnectionPoint(x+width/2,y+height,4,Direction.SOUTH));
        connectionPoints.add(new ConnectionPoint(x,y+height/2,4,Direction.WEST));
    }
    public void drawConnectionPoints(Graphics g){
        if(!isSelected)
            return;
        for(ConnectionPoint point:connectionPoints)
            point.draw(g);
    }
    public void draw(Graphics g){
        drawShape(g);
        drawConnectionPoints(g);
    }
    public void move(int x,int y){
        //move component
    }
    public void changeSelectedState(){isSelected = !isSelected;}
    public boolean getSelectedState(){return isSelected;}
    public void setSelectedState(boolean state){isSelected = state;}
    //ToDO
    //4 direction connect

}
