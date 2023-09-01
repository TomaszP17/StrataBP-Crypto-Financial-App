package code.listeners;

import code.classes.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReceivedHistoryButtonListener implements ActionListener {

    private final HistoryWindow historyWindow;

    public ReceivedHistoryButtonListener(HistoryWindow historyWindow) {
        this.historyWindow = historyWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Client currentClient = ClientsController.findClientByUser(User.getCurrentUser());
        JTable table = historyWindow.getTable();
        DefaultTableModel tableModel = new DefaultTableModel(historyWindow.getColumnNames(), 0);
        List<Transaction> transactionList = historyWindow.getTransactionList();
        for(Transaction x : transactionList){
            if(x.getTo().getEmail().equals(currentClient.getEmail())) {
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
        historyWindow.getReceivedButton().setEnabled(false);
        historyWindow.getAllTransactionsButton().setEnabled(true);
        historyWindow.getSentButton().setEnabled(true);
        table.repaint();
    }
}
