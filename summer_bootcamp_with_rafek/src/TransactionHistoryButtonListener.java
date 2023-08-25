import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionHistoryButtonListener implements ActionListener {
    public TransactionHistoryButtonListener(FirstWindow firstWindow) {
        this.firstWindow = firstWindow;
    }

    private final FirstWindow firstWindow;
    @Override
    public void actionPerformed(ActionEvent e) {
        TransactionHistoryWindow transactionHistoryWindow = new TransactionHistoryWindow();
        firstWindow.changeWindow(transactionHistoryWindow.getMainPanel());
    }
}
