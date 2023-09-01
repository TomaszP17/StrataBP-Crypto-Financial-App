package code.listeners;

import code.classes.FirstWindow;
import code.classes.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogOutButtonListener implements ActionListener {
    private final FirstWindow firstWindow;
    public LogOutButtonListener(FirstWindow firstWindow){
        this.firstWindow = firstWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        User.setCurrentUser(null);
        firstWindow.changeWindow(firstWindow.getMainPanel());
    }
}
