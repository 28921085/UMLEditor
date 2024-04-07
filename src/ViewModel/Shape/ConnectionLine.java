package ViewModel.Shape;

public class ConnectionLine {
    int startX,startY,endX,endY,depth;
    Shapes start,end;
    public ConnectionLine(ConnectionPoint point,Shapes start,Shapes end){

    }
    void moveStart(int x,int y){
        startX+=x;
        startY+=y;
    }
    void moveEnd(int x,int y){
        endX+=x;
        endY+=y;
    }
}
