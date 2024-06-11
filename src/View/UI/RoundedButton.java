package View.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedButton extends JButton {
    private static final int CORNER_RADIUS = 15;
    private Image iconImage;
    private Color borderColor;
    private int width,height;

    public RoundedButton(String text, String imagePath) {
        super(text);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

        // 載入圖片
        ImageIcon tmp = new ImageIcon(imagePath);
        width = 100;
        height = 100;
        iconImage = tmp.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(iconImage);
        setBorderColorWhite();

        setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // 繪製圓角矩形背景
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, CORNER_RADIUS, CORNER_RADIUS));

        // 繪製圖片
        if (iconImage != null)
            g2.drawImage(iconImage, 0,0, width, height, null);
    }

    @Override
    protected void paintBorder(Graphics g) {
        if (borderColor != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(4)); // 設置邊框寬度
            g2.draw(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, CORNER_RADIUS, CORNER_RADIUS));
            g2.dispose();
        }
    }

    public void setBorderColorBlack() {
        borderColor = Color.BLACK;
        repaint();
    }
    public void setBorderColorWhite(){
        borderColor = Color.WHITE;
        repaint();
    }
    public void resetButton(RoundedButton button){
        button.setBorderColorWhite();
    }
}