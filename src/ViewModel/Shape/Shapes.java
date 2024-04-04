package ViewModel.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Shapes {
    int x,y,depth;
    boolean isSelected;
    String name;
    List<Shapes> connect = new ArrayList<>();

    public abstract void draw(Graphics g);

    public Shapes(int x,int y){
        this.x=x;
        this.y=y;
        isSelected = false;
        name = "";
    }
    public void move(int x,int y){
        //move component
    }
    public void changeSelectedState(){isSelected = !isSelected;}
    //ToDO
    //4 direction connect

}
