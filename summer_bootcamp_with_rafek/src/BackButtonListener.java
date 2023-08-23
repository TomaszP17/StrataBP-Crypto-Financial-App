import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackButtonListener implements ActionListener {
    private final FirstWindow firstWindow;
    private final SecondWindow secondWindow;

    public BackButtonListener(FirstWindow firstWindow, SecondWindow secondWindow) {
        this.firstWindow = firstWindow;
        this.secondWindow = secondWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        secondWindow.setCurrentUser();
        // ... (inne operacje)
        firstWindow.changeWindow(secondWindow.getMainPanel());
    }
}
