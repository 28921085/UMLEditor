package ViewModel.UI;

import Controller.MouseController;
import Model.ModeType;
import Model.ModeObserver;
import ViewModel.Shape.*;
import ViewModel.Shape.Composite;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

public class DrawingPanel extends JPanel implements ModeObserver {
    private ModeType currentMode;
    private List<Shapes> components = new ArrayList<>();
    private List<ConnectionLine> lines = new ArrayList<>();
    private boolean mouseIsDragging;
    private int startX, startY, draggedX, draggedY;
    private Shapes currentSelect = null;
    private List<Shapes> groupSelect = new ArrayList<>();
    private ConnectionPoint currentSelectPoint = null;
    private ConnectionLine currentDrawing = null;

    public DrawingPanel() {
        this.setBackground(Color.WHITE);
        new MouseController(this);
    }
    public List<Shapes> getComponent() { return components; }
    public List<ConnectionLine> getLines() { return lines; }
    public ModeType getCurrentMode() { return currentMode; }
    public Shapes getCurrentSelect() { return currentSelect; }
    public List<Shapes> getGroupSelect() { return groupSelect; }
    public ConnectionPoint getCurrentSelectPoint() { return currentSelectPoint; }
    public ConnectionLine getCurrentDrawing() { return currentDrawing; }
    public boolean isMouseIsDragging() { return mouseIsDragging; }
    public int getStartX() { return startX; }
    public int getStartY() { return startY; }
    public int getDraggedX() { return draggedX; }
    public int getDraggedY() { return draggedY; }
    public void setCurrentSelect(Shapes shape) { currentSelect = shape; }
    public void setCurrentMode(ModeType currentMode) { this.currentMode = currentMode; }
    public void setCurrentSelectPoint(ConnectionPoint point) { currentSelectPoint = point; }
    public void setCurrentDrawing(ConnectionLine line) { currentDrawing = line; }
    public void setMouseIsDragging(boolean isDragging) { mouseIsDragging = isDragging; }
    public void setStartCoordinates(int x, int y) { startX = x; startY = y; }
    public void setDraggedCoordinates(int x, int y) { draggedX = x; draggedY = y; }

    public void group() {
        unSelectAllComponents();
        components.add(new Composite(groupSelect));
        groupSelect.clear();
        repaint();
    }

    public void unGroup(Composite composite) {
        groupSelect.clear();
        for (Shapes shape : composite.composite)
            components.add(0, shape);
        components.remove(composite);
        repaint();
    }

    public Shapes selectShapeAtPoint(int x, int y) {
        unSelectAllComponents();
        for (int i = 0; i < components.size(); i++) {
            if (components.get(i).isInside(x, y)) {
                components.get(i).setSelectedState(true);
                Shapes tmp = components.get(i);
                components.add(0, components.remove(i));
                reorderedComponentDepth();
                return tmp;
            }
        }
        return null;
    }

    public void drawSelectedArea(int x1, int y1, int x2, int y2, Graphics g) {
        int width = Math.abs(x2 - x1), height = Math.abs(y2 - y1);
        int x = Math.min(x1, x2), y = Math.min(y1, y2);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2d.setColor(Color.BLUE);
        g2d.fillRect(x, y, width, height);
        g2d.drawRect(x, y, width, height);
    }

    public void unSelectAllComponents() {
        currentSelect = null;
        for (Shapes shape : components)
            shape.setSelectedState(false);
        for (Shapes shape : groupSelect)
            shape.setSelectedState(false);
    }

    public void reorderedComponentDepth() {
        for (int i = 0; i < components.size(); i++)
            components.get(i).setDepth(i);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = components.size() - 1; i > -1; i--)
            components.get(i).draw(g);
        if (currentMode == ModeType.SELECT && currentSelect == null && mouseIsDragging)
            drawSelectedArea(startX, startY, draggedX, draggedY, g);

        for (Shapes shape : groupSelect)
            shape.draw(g);

        for (ConnectionLine line : lines)
            line.draw(g);

        if (currentDrawing != null)
            currentDrawing.draw(g);
    }

    @Override
    public void updateSelect(ModeType modeType) {
        setCurrentMode(modeType);
    }

    public void clear() {
        components.clear();
        lines.clear();
        currentSelect = null;
        groupSelect.clear();
        currentSelectPoint = null;
        currentDrawing = null;
        repaint();
    }
}
