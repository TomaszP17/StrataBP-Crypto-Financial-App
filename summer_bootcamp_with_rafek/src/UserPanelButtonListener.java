import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPanelButtonListener implements ActionListener {
    private final FirstWindow firstWindow;
    private final UserInfoPanel userInfoPanel;
    public UserPanelButtonListener(FirstWindow firstWindow, UserInfoPanel userInfoPanel) {
        this.firstWindow = firstWindow;
        this.userInfoPanel = userInfoPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        firstWindow.changeWindow(userInfoPanel.getMainPanel());
    }
}
