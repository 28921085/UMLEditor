package ViewModel.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Shapes {
    int x,y,depth;
    List<Shapes> connect = new ArrayList<>();
    public abstract void draw(Graphics g);
    public Shapes(int x,int y){this.x=x;this.y=y;}
}
