package ViewModel.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedButton extends JButton {
    private static final int CORNER_RADIUS = 5;
    private Image iconImage;
    private Color borderColor;

    public RoundedButton(String text, String imagePath) {
        super(text);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

        // 載入圖片
        ImageIcon icon = new ImageIcon(imagePath);
        iconImage = icon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();

        // 繪製圓角矩形背景
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, CORNER_RADIUS, CORNER_RADIUS));

        // 繪製圖片
        if (iconImage != null) {
            int iconWidth = iconImage.getWidth(null);
            int iconHeight = iconImage.getHeight(null);
            int x = (width - iconWidth) / 2;
            int y = (height - iconHeight) / 2;
            g2.drawImage(iconImage, x, y, iconWidth, iconHeight, null);
        }

        // 繪製文字
        //super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        if (borderColor != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(2)); // 設置邊框寬度
            g2.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, CORNER_RADIUS, CORNER_RADIUS));
            g2.dispose();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(150, 150); // 設定按鈕大小
    }

    public void setBorderColorBlack() {
        borderColor = Color.BLACK;
        repaint();
    }

    public void setBorderColorRed() {
        borderColor = Color.RED;
        repaint();
    }
}