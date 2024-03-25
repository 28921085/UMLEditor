import javax.swing.*;
import java.awt.*;
public class App {
    private JFrame frame;
    private JLabel label;

    // 建構子
    public App() {
        frame = new JFrame("Java Swing App");
        label = new JLabel("Hello, World!");

        // 設定視窗大小和關閉動作
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 將標籤加入到視窗中
        frame.getContentPane().add(label, BorderLayout.CENTER);

        // 顯示視窗
        frame.setVisible(true);
    }

    // 設定標籤文字
    public void setLabelText(String text) {
        label.setText(text);
    }
}
