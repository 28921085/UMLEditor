package ViewModel.UI;

import javax.swing.*;
import java.awt.*;

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
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        // 建立 Edit 功能
        JMenu editMenu = new JMenu("Edit");
        JMenuItem renameMenuItem = new JMenuItem("Rename");
        JMenuItem groupMenuItem = new JMenuItem("Group");
        JMenuItem ungroupMenuItem = new JMenuItem("Ungroup");
        JMenuItem deleteMenuItem = new JMenuItem("Delete");

        editMenu.add(renameMenuItem);
        editMenu.add(groupMenuItem);
        editMenu.add(ungroupMenuItem);
        editMenu.add(deleteMenuItem);

        // 將功能加入功能列
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
    }

    // 取得功能列
    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
