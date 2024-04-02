package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import ViewModel.UI.ButtonPanel;
import ViewModel.UI.DrawingPanel;
import ViewModel.UI.MenuBar;
import ViewModel.UI.RoundedButton;

public class MainFrame {
    private JFrame frame;
    private JLabel label;
    private JPanel canvasPanel;

    // 建構子
    public MainFrame() {
        frame = new JFrame("UMLEditor");
        label = new JLabel("Hello, World!");
        canvasPanel = new DrawingPanel();

        // 設定視窗大小和關閉動作
        frame.setSize(1500, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 建立功能列
        MenuBar menuBar = new MenuBar();

        // 設定功能列到視窗中
        frame.setJMenuBar(menuBar.getMenuBar());

        // 建立按鈕區域的面板
        ButtonPanel buttonPanel = new ButtonPanel();
        buttonPanel.setLayout(new GridLayout(6, 1)); // 使用 GridLayout 讓按鈕垂直排列
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // 添加间距


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
