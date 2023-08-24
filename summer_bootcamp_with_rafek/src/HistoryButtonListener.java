import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistoryButtonListener implements ActionListener {
    private final SecondWindow secondWindow;

    public HistoryButtonListener(SecondWindow secondWindow) {
        this.secondWindow = secondWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        secondWindow.showHistoryWindow(); // Wywołaj metodę showHistoryWindow z SecondWindow
    }
}
