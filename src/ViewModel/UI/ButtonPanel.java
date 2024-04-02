package ViewModel.UI;

import Model.SelectType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;

public class ButtonPanel extends JPanel{
    public SelectType currentSelect;
    private List<RoundedButton> buttons = new ArrayList<>();
    public ButtonPanel(){
        currentSelect = SelectType.NONE;
        for(SelectType type:SelectType.values()){
            if(type == SelectType.NONE)
                continue;
            RoundedButton button = new RoundedButton("",type.getFileName());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentSelect = type;
                    unSelectAllButtons();
                    button.setBorderColorRed();
                }
            });
            buttons.add(button);
            this.add(button);
        }

    }
    public void unSelectAllButtons(){
        for(RoundedButton button:buttons)
            button.resetButton(button);
    }

}
