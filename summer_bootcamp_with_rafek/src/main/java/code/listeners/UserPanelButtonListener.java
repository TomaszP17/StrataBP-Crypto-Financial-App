package code.listeners;

import code.classes.SecondWindow;
import code.classes.UserInfoPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPanelButtonListener implements ActionListener {
    private final SecondWindow secondWindow;
    public UserPanelButtonListener(SecondWindow secondWindow) {
        this.secondWindow = secondWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UserInfoPanel userInfoPanel = new UserInfoPanel(secondWindow);
        secondWindow.changeMainPanelToUserInfoPanel(userInfoPanel.getMainPanel());
    }
}
