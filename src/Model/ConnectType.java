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
    public void draw(Graphics g){//all base on NORTH and point to the connection point
        if(this==ASSOCIATION_LINE_START || this==GENERALIZATION_LINE_START || this==COMPOSITION_LINE_START){
            //draw only a line
        }
        else if(this == ASSOCIATION_LINE_END){
            //draw arrow
            //g.drawLine();
        }
        else if(this == GENERALIZATION_LINE_END){
            //triangle arrow
        }
        else if(this == COMPOSITION_LINE_END){
            //菱形 arrow
        }
        else{/*NONE*/}
    }
}
