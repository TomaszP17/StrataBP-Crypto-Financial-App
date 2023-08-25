import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AllUsersButtonListener implements ActionListener {

    private final FirstWindow firstWindow;
    public AllUsersButtonListener(FirstWindow firstWindow) {
        this.firstWindow = firstWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AllUsersWindow allUsersWindow = new AllUsersWindow();
        firstWindow.changeWindow(allUsersWindow.getMainPanel());
    }
}
