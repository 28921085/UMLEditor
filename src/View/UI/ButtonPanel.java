package View.UI;

import Model.ModeObserver;
import Model.ModeType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
public class ButtonPanel extends JPanel{
    private ModeType currentMode;
    private List<RoundedButton> buttons = new ArrayList<>();
    private List<ModeObserver> observers = new ArrayList<>();
    public ButtonPanel(){
        super();
        currentMode = ModeType.NONE;
        for(ModeType type: ModeType.values()){
            if(type == ModeType.NONE)
                continue;
            RoundedButton button = new RoundedButton("",type.getFileName());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //change type
                    setCurrentMode(type);
                    //notify observer
                    notifyObservers(getCurrentMode());
                    //change UI
                    unSelectAllButtons();
                    button.setBorderColorBlack();
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
    //新增觀察者(這邊是在mainFrame再新增)
    public void addObserver(ModeObserver observer) {
        observers.add(observer);
    }
    //當mode type改變時，通知所有觀察者
    private void notifyObservers(ModeType modeType) {
        for (ModeObserver observer : observers)
            observer.updateSelect(modeType);
    }
    public ModeType getCurrentMode(){return currentMode;}
    public void setCurrentMode(ModeType type){this.currentMode = type;}
}
