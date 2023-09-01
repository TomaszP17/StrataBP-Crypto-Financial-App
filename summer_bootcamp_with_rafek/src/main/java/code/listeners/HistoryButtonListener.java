package code.listeners;

import code.classes.HistoryWindow;
import code.classes.SecondWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistoryButtonListener implements ActionListener {
    private SecondWindow secondWindow;

    public HistoryButtonListener(SecondWindow secondWindow) {
        this.secondWindow = secondWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        HistoryWindow historyWindow = new HistoryWindow(secondWindow);
        secondWindow.changeMainPanelToHistoryPanel(historyWindow.returnMainPanel());
    }
}
