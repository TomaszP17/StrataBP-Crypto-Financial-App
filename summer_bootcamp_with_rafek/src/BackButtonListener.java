import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackButtonListener implements ActionListener {
    private final SecondWindow secondWindow;

    public BackButtonListener(SecondWindow secondWindow) {
        this.secondWindow = secondWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        secondWindow.changeMainPanelToSecondPanel();
    }
}
