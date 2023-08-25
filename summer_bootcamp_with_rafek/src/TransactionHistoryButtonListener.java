import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionHistoryButtonListener implements ActionListener {
    private final FirstWindow firstWindow;
    private final AdminPanel adminPanel;
    public TransactionHistoryButtonListener(FirstWindow firstWindow, AdminPanel adminPanel) {
        this.firstWindow = firstWindow;
        this.adminPanel = adminPanel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        TransactionHistoryWindow transactionHistoryWindow = new TransactionHistoryWindow(adminPanel);
        firstWindow.changeWindow(transactionHistoryWindow.getMainPanel());
    }
}
