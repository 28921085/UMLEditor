package ViewModel.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Composite extends Shapes{
    List<Shapes> composite = new ArrayList<>();
    public Composite(List<Shapes> list){
        super();
        composite.addAll(list);
        generateConnectionPoints();
    }
    public void drawShape(Graphics g) {
        //System.out.println("composite");
        for(Shapes shape:composite) {
            //System.out.println(shape.x+" "+shape.y);
            shape.draw(g);
        }
        //System.out.println();
    }
    @Override
    public void generateConnectionPoints(){
        int xmin=composite.get(0).x,ymin=composite.get(0).y,xmax=xmin+composite.get(0).width,ymax=ymin+composite.get(0).height;
        for(int i=1;i<composite.size();i++){
            xmin=Math.min(xmin,composite.get(i).x);
            xmax=Math.max(xmax,composite.get(i).x+composite.get(i).width);
            ymin=Math.min(ymin,composite.get(i).y);
            ymax=Math.max(ymax,composite.get(i).y+composite.get(i).height);
        }
        x=xmin;
        y=ymin;
        width=xmax-xmin;
        height=ymax-ymin;
        //System.out.println(x+" "+y+" "+width+" "+height);
        connectionPoints.add(new ConnectionPoint((xmin+xmax)/2,ymin,11));//top
        connectionPoints.add(new ConnectionPoint(xmin,(ymin+ymax)/2,11));//left
        connectionPoints.add(new ConnectionPoint(xmax,(ymin+ymax)/2,11));//right
        connectionPoints.add(new ConnectionPoint((xmin+xmax)/2,ymax,11));//bottom
        //for(ConnectionPoint point:connectionPoints)
        //    System.out.println(point.x+" "+point.y);
    }
    @Override
    public void move(int x,int y){
        this.x+=x;
        this.y+=y;
        for(ConnectionPoint point:connectionPoints)
            point.move(x,y);
        for(Shapes shape:composite)
            shape.move(x,y);
    }
}
