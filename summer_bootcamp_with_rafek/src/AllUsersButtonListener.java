import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AllUsersButtonListener implements ActionListener {

    private final FirstWindow firstWindow;
    private final AdminPanel adminPanel;
    public AllUsersButtonListener(FirstWindow firstWindow, AdminPanel adminPanel) {
        this.firstWindow = firstWindow;
        this.adminPanel = adminPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AllUsersWindow allUsersWindow = new AllUsersWindow(adminPanel);
        firstWindow.changeWindow(allUsersWindow.getMainPanel());
    }
}
