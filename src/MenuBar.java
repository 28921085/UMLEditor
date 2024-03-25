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
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        // 建立 Edit 功能
        JMenu editMenu = new JMenu("Edit");
        JMenuItem cutMenuItem = new JMenuItem("Cut");
        JMenuItem copyMenuItem = new JMenuItem("Copy");
        JMenuItem pasteMenuItem = new JMenuItem("Paste");
        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);

        // 將功能加入功能列
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
    }

    // 取得功能列
    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
