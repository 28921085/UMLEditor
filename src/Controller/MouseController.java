package Controller;

import Model.ConnectType;
import Model.ModeType;
import View.Shape.*;
import View.UI.DrawingPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


public class MouseController {
    private DrawingPanel drawingPanel;

    public MouseController(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;

        this.drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                handleMouseReleased(e);
            }
        });

        this.drawingPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                handleMouseDragged(e);
            }
        });
    }

    private void handleMousePressed(MouseEvent e) {
        int startX = e.getX();
        int startY = e.getY();
        drawingPanel.setStartCoordinates(startX, startY);
        if (!drawingPanel.getGroupSelect().isEmpty()) {
            for (int i = drawingPanel.getGroupSelect().size() - 1; i > -1; i--)
                drawingPanel.getComponent().add(0, drawingPanel.getGroupSelect().get(i));
            drawingPanel.getGroupSelect().clear();
        }
        ModeType currentMode = drawingPanel.getCurrentMode();
        if (currentMode == ModeType.SELECT) {
            drawingPanel.setCurrentSelect(drawingPanel.selectShapeAtPoint(startX, startY));
        } else if (currentMode == ModeType.CLASS) {
            drawingPanel.getComponent().add(0, new ClassShape(startX, startY, 101, 101));
            drawingPanel.reorderedComponentDepth();
            drawingPanel.unSelectAllComponents();
        } else if (currentMode == ModeType.USE_CASE) {
            drawingPanel.getComponent().add(0, new UseCaseShape(startX, startY, 101, 51));
            drawingPanel.reorderedComponentDepth();
            drawingPanel.unSelectAllComponents();
        } else if (currentMode.isLine()) {
            Shapes currentSelect = drawingPanel.selectShapeAtPoint(startX, startY);
            if (currentSelect != null) {
                ConnectionPoint currentSelectPoint = currentSelect.assignConnectionPoint(startX, startY);
                drawingPanel.setCurrentSelectPoint(currentSelectPoint);
                ConnectionLine currentDrawing = getConnectionLine(currentSelectPoint, currentMode);
                drawingPanel.setCurrentDrawing(currentDrawing);
            }
        }
        drawingPanel.repaint();
    }

    private ConnectionLine getConnectionLine(ConnectionPoint currentSelectPoint, ModeType currentMode) {
        int x = currentSelectPoint.getX(), y = currentSelectPoint.getY();
        ConnectionLine currentDrawing;
        if (currentMode == ModeType.ASSOCIATION_LINE)
            currentDrawing = new ConnectionLine(x, y, x, y, ConnectType.ASSOCIATION_LINE_END);
        else if (currentMode == ModeType.COMPOSITION_LINE)
            currentDrawing = new ConnectionLine(x, y, x, y, ConnectType.COMPOSITION_LINE_END);
        else
            currentDrawing = new ConnectionLine(x, y, x, y, ConnectType.GENERALIZATION_LINE_END);
        return currentDrawing;
    }

    private void handleMouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        drawingPanel.setMouseIsDragging(false);
        ModeType currentMode = drawingPanel.getCurrentMode();
        if (currentMode == ModeType.SELECT && drawingPanel.getCurrentSelect() == null) {
            drawingPanel.getGroupSelect().clear();
            for (Shapes shape : drawingPanel.getComponent()) {
                if (shape.isInArea(drawingPanel.getStartX(), drawingPanel.getStartY(), x, y)) {
                    drawingPanel.getGroupSelect().add(shape);
                    shape.setSelectedState(true);
                }
            }
            for (Shapes shape : drawingPanel.getGroupSelect())
                drawingPanel.getComponent().remove(shape);
        } else if (currentMode.isLine()) {
            Shapes releasedShape = drawingPanel.selectShapeAtPoint(x, y);
            if (releasedShape != null) {
                ConnectionPoint releasedPoint = releasedShape.assignConnectionPoint(x, y);
                ConnectType type;
                if (currentMode == ModeType.ASSOCIATION_LINE)
                    type = ConnectType.ASSOCIATION_LINE_END;
                else if (currentMode == ModeType.COMPOSITION_LINE)
                    type = ConnectType.COMPOSITION_LINE_END;
                else
                    type = ConnectType.GENERALIZATION_LINE_END;
                ConnectionLine line = new ConnectionLine(drawingPanel.getCurrentSelectPoint(), releasedPoint, type);
                drawingPanel.getCurrentSelectPoint().setConnectionLine(line);
                releasedPoint.setConnectionLine(line);
                drawingPanel.getLines().add(line);
            }
        }

        drawingPanel.setCurrentSelectPoint(null);
        drawingPanel.setCurrentDrawing(null);

        drawingPanel.repaint();
    }

    private void handleMouseDragged(MouseEvent e) {
        int draggedX = e.getX();
        int draggedY = e.getY();
        drawingPanel.setDraggedCoordinates(draggedX, draggedY);
        drawingPanel.setMouseIsDragging(true);
        ModeType currentMode = drawingPanel.getCurrentMode();
        if (currentMode == ModeType.SELECT) {
            Shapes currentSelect = drawingPanel.getCurrentSelect();
            if (currentSelect != null) {
                currentSelect.move(draggedX - drawingPanel.getStartX(), draggedY - drawingPanel.getStartY());
                drawingPanel.setStartCoordinates(draggedX, draggedY);
            }
        } else if (currentMode.isLine() && drawingPanel.getCurrentDrawing() != null) {
            drawingPanel.getCurrentDrawing().moveEnd(draggedX - drawingPanel.getStartX(), draggedY - drawingPanel.getStartY());
            drawingPanel.setStartCoordinates(draggedX, draggedY);
        }
        drawingPanel.repaint();
    }
}

