import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SentHistoryButtonListener implements ActionListener {
    private final HistoryWindow historyWindow;

    public SentHistoryButtonListener(HistoryWindow historyWindow) {
        this.historyWindow = historyWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Client currentClient = ClientsController.findClientByUser(User.getCurrentUser());
        JTable table = historyWindow.getTable();
        DefaultTableModel tableModel = new DefaultTableModel(historyWindow.getColumnNames(), 0);
        List<Transaction> transactionList = historyWindow.getTransactionList();
        for(Transaction x : transactionList){
            if(x.getFrom().getEmail().equals(currentClient.getEmail())) {
                Object[] rowData = {
                        x.getId(),
                        x.getFrom().getEmail(),
                        x.getTo().getEmail(),
                        x.getCryptocurrency(),
                        x.getAmount(),
                        x.getDate()
                };
                tableModel.addRow(rowData);
            }
        }
        table.setModel(tableModel);
        historyWindow.getReceivedButton().setEnabled(true);
        historyWindow.getAllTransactionsButton().setEnabled(true);
        historyWindow.getSentButton().setEnabled(false);
        table.repaint();
    }
}
