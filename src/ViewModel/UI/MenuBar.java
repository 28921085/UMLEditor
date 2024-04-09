package ViewModel.UI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuBar {
    private JMenuBar menuBar;

    // 建構子
    public MenuBar() {
        // 建立功能列
        menuBar = new JMenuBar();
        // 建立 File 功能
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(newMenuItem);
        //fileMenu.addSeparator();
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
    }

    // 取得功能列
    public JMenuBar getMenuBar() {
        return menuBar;
    }
    public void setRenameAction(ActionListener listener) {
        // 设置 "Rename" 菜单项的行为函数
        // 获取 Edit 功能菜单
        JMenu editMenu = (JMenu) menuBar.getMenu(1); // Edit 功能菜单在菜单栏中的索引为 1
        // 获取 Rename 菜单项
        JMenuItem renameMenuItem = editMenu.getItem(0); // Rename 菜单项在 Edit 功能菜单中的索引为 0
        // 为 Rename 菜单项添加 ActionListener
        renameMenuItem.addActionListener(listener);
    }
    public void setGroupAction(ActionListener listener){
        JMenu editMenu = (JMenu) menuBar.getMenu(1);
        JMenuItem groupMenuItem = editMenu.getItem(1);
        groupMenuItem.addActionListener(listener);
    }
    public void setUnGroupAction(ActionListener listener){
        JMenu editMenu = (JMenu) menuBar.getMenu(1);
        JMenuItem unGroupMenuItem = editMenu.getItem(2);
        unGroupMenuItem.addActionListener(listener);
    }
    public void setNewAction(ActionListener listener){
        JMenu fileMenu = (JMenu) menuBar.getMenu(0);
        JMenuItem newMenuItem = fileMenu.getItem(0);
        newMenuItem.addActionListener(listener);
    }
    public void setExitAction(ActionListener listener){
        JMenu fileMenu = (JMenu) menuBar.getMenu(0);
        JMenuItem exitMenuItem = fileMenu.getItem(1);
        exitMenuItem.addActionListener(listener);
    }
}
