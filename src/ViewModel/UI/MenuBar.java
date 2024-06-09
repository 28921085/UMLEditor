package ViewModel.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ViewModel.Shape.Composite;
import ViewModel.UI.DrawingPanel;

public class MenuBar {
    private JMenuBar menuBar;
    private DrawingPanel drawingPanel;

    // 建構子
    public MenuBar(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;

        // 建立功能列
        menuBar = new JMenuBar();
        // 建立 File 功能
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(newMenuItem);
        fileMenu.add(exitMenuItem);

        // 建立 Edit 功能
        JMenu editMenu = new JMenu("Edit");
        JMenuItem renameMenuItem = new JMenuItem("Rename");
        JMenuItem groupMenuItem = new JMenuItem("Group");
        JMenuItem ungroupMenuItem = new JMenuItem("Ungroup");

        editMenu.add(renameMenuItem);
        editMenu.add(groupMenuItem);
        editMenu.add(ungroupMenuItem);

        // 將功能加入功能列
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        // 註冊監聽器
        setRenameAction(new RenameAction());
        setGroupAction(new GroupAction());
        setUnGroupAction(new UnGroupAction());
        setNewAction(new NewAction());
        setExitAction(new ExitAction());
    }

    // 取得功能列
    public JMenuBar getMenuBar() {
        return menuBar;
    }

    private class RenameAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (drawingPanel.getCurrentSelect() == null) {
                JOptionPane.showMessageDialog(null, "沒有選擇任何物件或選擇過多物件");
                return;
            }
            if (drawingPanel.getCurrentSelect() instanceof Composite) {
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
            } else {
                JOptionPane.getRootFrame().dispose(); // 关闭对话框
            }
        }
    }

    private class GroupAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (drawingPanel.getGroupSelect().size() < 2) {
                JOptionPane.showMessageDialog(null, "至少需要選擇2個物件");
                return;
            }
            drawingPanel.group();
        }
    }

    private class UnGroupAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (drawingPanel.getCurrentSelect() == null) {
                JOptionPane.showMessageDialog(null, "請使用Select模式\"單擊\"一個物件");
                return;
            } else if (!(drawingPanel.getCurrentSelect() instanceof Composite)) {
                JOptionPane.showMessageDialog(null, "選中的物件不是composite物件");
                return;
            }
            drawingPanel.unGroup((Composite) drawingPanel.getCurrentSelect());
            drawingPanel.setCurrentSelect(null);
        }
    }

    private class NewAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            drawingPanel.clear();
        }
    }

    private class ExitAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int confirmed = JOptionPane.showConfirmDialog(null, "確定要退出應用程式嗎？", "退出", JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    private void setRenameAction(ActionListener listener) {
        JMenu editMenu = (JMenu) menuBar.getMenu(1); // Edit 功能菜单在菜单栏中的索引为 1
        JMenuItem renameMenuItem = editMenu.getItem(0); // Rename 菜单项在 Edit 功能菜单中的索引为 0
        renameMenuItem.addActionListener(listener);
    }

    private void setGroupAction(ActionListener listener) {
        JMenu editMenu = (JMenu) menuBar.getMenu(1);
        JMenuItem groupMenuItem = editMenu.getItem(1);
        groupMenuItem.addActionListener(listener);
    }

    private void setUnGroupAction(ActionListener listener) {
        JMenu editMenu = (JMenu) menuBar.getMenu(1);
        JMenuItem unGroupMenuItem = editMenu.getItem(2);
        unGroupMenuItem.addActionListener(listener);
    }

    private void setNewAction(ActionListener listener) {
        JMenu fileMenu = (JMenu) menuBar.getMenu(0);
        JMenuItem newMenuItem = fileMenu.getItem(0);
        newMenuItem.addActionListener(listener);
    }

    private void setExitAction(ActionListener listener) {
        JMenu fileMenu = (JMenu) menuBar.getMenu(0);
        JMenuItem exitMenuItem = fileMenu.getItem(1);
        exitMenuItem.addActionListener(listener);
    }
}
