package code.listeners;

import code.classes.SecondWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackButtonHistoryListener implements ActionListener {
    private SecondWindow secondWindow;

    public BackButtonHistoryListener(SecondWindow secondWindow) {
        this.secondWindow = secondWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        secondWindow.changeMainPanelToSecondPanel();
    }
}
