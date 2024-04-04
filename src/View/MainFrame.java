package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ViewModel.UI.ButtonPanel;
import ViewModel.UI.DrawingPanel;
import ViewModel.UI.MenuBar;

public class MainFrame {
    private JFrame frame;
    private JLabel label;
    private DrawingPanel canvasPanel;
    private ButtonPanel buttonPanel;
    // 建構子
    public MainFrame() {
        frame = new JFrame("UMLEditor");
        label = new JLabel("Hello, World!");

        // 設定視窗大小和關閉動作
        frame.setSize(1500, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 建立功能列
        MenuBar menuBar = new MenuBar();

        // 設定功能列到視窗中
        frame.setJMenuBar(menuBar.getMenuBar());

        //初始化畫布
        canvasPanel = new DrawingPanel();

        // 建立按鈕區域的面板
        buttonPanel = new ButtonPanel();
        buttonPanel.setLayout(new GridLayout(6, 1)); // 使用 GridLayout 讓按鈕垂直排列
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // 添加间距

        // 將 DrawingPanel 註冊為 ButtonPanel 的觀察者
        buttonPanel.addObserver(canvasPanel);

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
