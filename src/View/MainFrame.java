package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ViewModel.Shape.Composite;
import ViewModel.UI.ButtonPanel;
import ViewModel.UI.DrawingPanel;
import ViewModel.UI.MenuBar;

public class MainFrame {
    private JFrame frame;
    private JLabel label;
    private DrawingPanel drawingPanel;
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
        menuBar.setRenameAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(drawingPanel.getCurrentSelect()==null) {
                    JOptionPane.showMessageDialog(null, "沒有選擇任何物件或選擇過多物件");
                    return;
                }
                if(drawingPanel.getCurrentSelect() instanceof Composite){
                    JOptionPane.showMessageDialog(null, "請不要選擇composite物件");
                    return;
                }
                // 创建一个文本输入框
                JTextArea textArea = new JTextArea(5, 20);
                // 将文本输入框放置在滚动面板中，以便可以滚动显示文本
                JScrollPane scrollPane = new JScrollPane(textArea);
                // 定义对话框的按钮
                Object[] options = {"OK", "Cancel"};
                // 显示对话框，并等待用户的操作
                int result = JOptionPane.showOptionDialog(
                        null,
                        scrollPane,
                        "Enter new name",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);
                // 根据用户的操作结果执行相应的操作
                if (result == JOptionPane.OK_OPTION) {
                    drawingPanel.getCurrentSelect().setName(textArea.getText());
                    drawingPanel.repaint();
                }
                else
                    JOptionPane.getRootFrame().dispose(); // 关闭对话框
            }
        });
        menuBar.setGroupAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(drawingPanel.getGroupSelect().size()<2){
                    JOptionPane.showMessageDialog(null, "至少需要選擇2個物件");
                    return;
                }
                drawingPanel.group();
            }
        });
        menuBar.setUnGroupAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(drawingPanel.getCurrentSelect()==null){
                    JOptionPane.showMessageDialog(null, "請使用Select模式\"單擊\"一個物件");
                    return;
                }
                else if(!(drawingPanel.getCurrentSelect() instanceof Composite)){
                    JOptionPane.showMessageDialog(null, "選中的物件不是composite物件");
                    return;
                }
                drawingPanel.unGroup((Composite) drawingPanel.getCurrentSelect());
                drawingPanel.setCurrentSelect(null);
            }
        });
        menuBar.setNewAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.clear();
            }
        });
        menuBar.setExitAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null, "確定要退出應用程式嗎？", "退出", JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        // 設定功能列到視窗中
        frame.setJMenuBar(menuBar.getMenuBar());

        //初始化畫布
        drawingPanel = new DrawingPanel();

        // 建立按鈕區域的面板
        buttonPanel = new ButtonPanel();
        buttonPanel.setLayout(new GridLayout(6, 1)); // 使用 GridLayout 讓按鈕垂直排列
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // 添加间距

        // 將 DrawingPanel 註冊為 ButtonPanel 的觀察者
        buttonPanel.addObserver(drawingPanel);

        // 設定按鈕區域和畫布到視窗中
        frame.add(drawingPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.WEST); // 放在西側

        // 顯示視窗
        frame.setVisible(true);
    }
    // 設定標籤文字
    public void setLabelText(String text) {
        label.setText(text);
    }
}
