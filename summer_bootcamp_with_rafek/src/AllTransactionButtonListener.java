import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AllTransactionButtonListener implements ActionListener {
    private final HistoryWindow historyWindow;

    public AllTransactionButtonListener(HistoryWindow historyWindow) {
        this.historyWindow = historyWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Client currentClient = ClientsController.findClientByUser(User.getCurrentUser());
        JTable table = historyWindow.getTable();
        DefaultTableModel tableModel = new DefaultTableModel(historyWindow.getColumnNames(), 0);
        List<Transaction> transactionList = historyWindow.getTransactionList();
        HistoryWindow.addUserTransactions(currentClient, tableModel, transactionList);
        table.setModel(tableModel);
        historyWindow.getReceivedButton().setEnabled(true);
        historyWindow.getAllTransactionsButton().setEnabled(false);
        historyWindow.getSentButton().setEnabled(true);
        table.repaint();
    }
}
