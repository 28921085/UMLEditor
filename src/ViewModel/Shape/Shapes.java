package ViewModel.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Shapes{
    int x,y,width,height,depth;
    boolean isSelected;
    String name;
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
        connectionPoints.add(new ConnectionPoint(x+width/2,y,11));
        connectionPoints.add(new ConnectionPoint(x+width,y+height/2,11));
        connectionPoints.add(new ConnectionPoint(x+width/2,y+height,11));
        connectionPoints.add(new ConnectionPoint(x,y+height/2,11));
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
        this.x+=x;
        this.y+=y;
        for(ConnectionPoint point:connectionPoints)
            point.move(x,y);
    }
    public boolean getSelectedState(){return isSelected;}
    public void setSelectedState(boolean state){isSelected = state;}
    public void setDepth(int depth){this.depth=depth;}
    public void setName(String name){this.name=name;}
    public double distance(int x1,int y1,int x2,int y2){
        return Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
    }
    public ConnectionPoint assignConnectionPoint(int x,int y){
        double min=1e9;//inf
        for(ConnectionPoint point:connectionPoints)
            min = Math.min(min,distance(point.x,point.y,x,y));
        for(ConnectionPoint point:connectionPoints)
            if(distance(point.x,point.y,x,y)==min)
                return point;
        return null;
    }
}
