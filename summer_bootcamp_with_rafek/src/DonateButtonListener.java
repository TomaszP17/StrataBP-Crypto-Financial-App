import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DonateButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        //after clicked donate button open the window with donate screen
        DonateWindow donateWindow = new DonateWindow();
        donateWindow.pack();
        donateWindow.setVisible(true);
    }
}
