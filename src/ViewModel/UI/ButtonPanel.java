package ViewModel.UI;

import Model.SelectObserver;
import Model.SelectType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
public class ButtonPanel extends JPanel{
    private SelectType currentSelect;
    private List<RoundedButton> buttons = new ArrayList<>();
    private List<SelectObserver> observers = new ArrayList<>();
    public ButtonPanel(){
        currentSelect = SelectType.NONE;
        for(SelectType type:SelectType.values()){
            if(type == SelectType.NONE)
                continue;
            RoundedButton button = new RoundedButton("",type.getFileName());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //change type
                    setCurrentSelect(type);
                    //notify observer
                    notifyObservers(getCurrentSelect());
                    //change UI
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
    //新增觀察者(這邊是在mainFrame再新增)
    public void addObserver(SelectObserver observer) {
        observers.add(observer);
    }
    //當select type改變時，通知所有觀察者
    private void notifyObservers(SelectType selectType) {
        for (SelectObserver observer : observers)
            observer.updateSelect(selectType);
    }
    public SelectType getCurrentSelect(){return currentSelect;}
    public void setCurrentSelect(SelectType type){this.currentSelect = type;}
}
