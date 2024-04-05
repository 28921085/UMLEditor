package Model;

import java.awt.*;

public enum ConnectType {
    ASSOCIATION_LINE_START,
    ASSOCIATION_LINE_END,
    GENERALIZATION_LINE_START,
    GENERALIZATION_LINE_END,
    COMPOSITION_LINE_START,
    COMPOSITION_LINE_END,
    NONE;
    public void draw(Graphics g,int x,int y,int width){//all base on NORTH and point to the connection point
        g.setColor(Color.BLACK);
        if(this==ASSOCIATION_LINE_START || this==GENERALIZATION_LINE_START || this==COMPOSITION_LINE_START){
            //draw only a line
            g.drawLine(x,y,x,y-width);
        }
        else if(this == ASSOCIATION_LINE_END){
            //draw arrow
            int smallerWidth = width*7/10;//sqrt(7*7+7*7) close to sqrt(10*10)
            g.drawLine(x,y,x,y-width);
            g.drawLine(x,y,x+smallerWidth,y-smallerWidth);
            g.drawLine(x,y,x-smallerWidth,y-smallerWidth);
        }
        else if(this == GENERALIZATION_LINE_END){
            //triangle arrow
            g.drawLine(x-width,y-width,x+width,y-width);
            g.drawLine(x,y,x+width,y-width);
            g.drawLine(x,y,x-width,y-width);
        }
        else if(this == COMPOSITION_LINE_END){
            //菱形 arrow
            int halfWidth=width/2;
            g.drawLine(x,y,x-halfWidth,y-halfWidth);
            g.drawLine(x,y,x+halfWidth,y-halfWidth);
            g.drawLine(x,y-width,x-halfWidth,y-halfWidth);
            g.drawLine(x,y-width,x+halfWidth,y-halfWidth);
        }
        else{/*NONE*/}
    }
}
