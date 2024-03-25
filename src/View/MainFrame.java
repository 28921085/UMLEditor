package View;

import javax.swing.*;
import java.awt.*;
import ViewModel.MenuBar;

public class MainFrame {
    private JFrame frame;
    private JLabel label;
    private JPanel canvasPanel;

    // 建構子
    public MainFrame() {
        frame = new JFrame("Java Swing View.App");
        label = new JLabel("Hello, World!");
        canvasPanel = new JPanel();

        // 設定視窗大小和關閉動作
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 建立功能列
        MenuBar menuBar = new MenuBar();

        // 設定功能列到視窗中
        frame.setJMenuBar(menuBar.getMenuBar());

        // 建立畫布
        canvasPanel.setBackground(Color.WHITE);

        // 建立六個按鈕
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");
        JButton button4 = new JButton("Button 4");
        JButton button5 = new JButton("Button 5");
        JButton button6 = new JButton("Button 6");

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
