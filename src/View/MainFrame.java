package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import ViewModel.UI.MenuBar;
import ViewModel.UI.RoundedButton;

public class MainFrame {
    private JFrame frame;
    private JLabel label;
    private JPanel canvasPanel;

    // 建構子
    public MainFrame() {
        frame = new JFrame("Java Swing View.App");
        label = new JLabel("Hello, World!");
        canvasPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // 繪製外框
                g.setColor(Color.BLACK);
                g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            }
        };

        // 設定視窗大小和關閉動作
        frame.setSize(1500, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 建立功能列
        MenuBar menuBar = new MenuBar();

        // 設定功能列到視窗中
        frame.setJMenuBar(menuBar.getMenuBar());

        // 建立畫布
        canvasPanel.setBackground(Color.WHITE);
        canvasPanel.addMouseListener(new MouseAdapter() {
            private int startX, startY;

            @Override
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
                startY = e.getY();
                //System.out.println("滑鼠pressed位置：(" + startX + ", " + startY + ")");
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                //System.out.println("滑鼠放開位置：(" + x + ", " + y + ")");
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                //System.out.println("滑鼠點擊位置：(" + x + ", " + y + ")");
            }
        });
        canvasPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                //System.out.println("滑鼠當前位置：(" + x + ", " + y + ")");
            }
        });

        // 建立六個按鈕
        RoundedButton button1 = new RoundedButton("",System.getProperty("user.dir")+"\\src\\resources\\select.png");
        RoundedButton button2 = new RoundedButton("",System.getProperty("user.dir")+"\\src\\resources\\association line.png");
        RoundedButton button3 = new RoundedButton("",System.getProperty("user.dir")+"\\src\\resources\\generalization line.png");
        RoundedButton button4 = new RoundedButton("",System.getProperty("user.dir")+"\\src\\resources\\composition line.png");
        RoundedButton button5 = new RoundedButton("",System.getProperty("user.dir")+"\\src\\resources\\class.png");
        RoundedButton button6 = new RoundedButton("",System.getProperty("user.dir")+"\\src\\resources\\use case.png");

        // 建立按鈕區域的面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1)); // 使用 GridLayout 讓按鈕垂直排列
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button6);

        // 設定按鈕區域和畫布到視窗中
        frame.add(canvasPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.WEST); // 放在西側

        // 顯示視窗
        frame.setVisible(true);
    }

    // 設定標籤文字
    public void setLabelText(String text) {
        label.setText(text);
    }

}
