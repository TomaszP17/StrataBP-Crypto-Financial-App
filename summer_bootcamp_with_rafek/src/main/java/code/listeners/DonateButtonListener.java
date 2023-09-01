package code.listeners;

import code.classes.DonateWindow;
import code.classes.SecondWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DonateButtonListener implements ActionListener {
    private final SecondWindow secondWindow;

    public DonateButtonListener(SecondWindow secondWindow) {
        this.secondWindow = secondWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //after clicked donate button open the window with donate screen
        DonateWindow donateWindow = new DonateWindow(secondWindow);
        donateWindow.pack();
        donateWindow.setVisible(true);
    }
}
