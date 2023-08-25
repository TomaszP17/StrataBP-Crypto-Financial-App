import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TransactionHistoryWindow {

    private JPanel panel1;
    private JButton searchButton;
    private JTable table1;
    private JButton backButton;
    private JPanel buttonPanel;
    private JPanel centerPanel;
    private final AdminPanel adminPanel;
    private List<Transaction> data;
    private String[] columnNames;

    public TransactionHistoryWindow(AdminPanel adminPanel) {
        this.adminPanel = adminPanel;
        searchButton.addActionListener(new AdminSearchButtonListener(this));
        backButton.addActionListener(new BackButtonToAdminPanelListener(adminPanel));
        createAllTransactionList();
    }
    public void createAllTransactionList(){
        Client currentClient = ClientsController.findClientByUser(User.getCurrentUser());
        data = TransactionController.getTransactionList();
        columnNames = new String[]{"ID", "FROM", "TO", "CRYPTO", "AMOUNT", "DATE"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        addUserTransactions(currentClient, tableModel, data);
        table1 = new JTable(tableModel);
        centerPanel.add(new JScrollPane(table1));
    }
    static void addUserTransactions(Client currentClient, DefaultTableModel tableModel, List<Transaction> data) {
        for (Transaction transaction : data) {
                Object[] rowData = {
                        transaction.getId(),
                        transaction.getFrom().getEmail(),
                        transaction.getTo().getEmail(),
                        transaction.getCryptocurrency(),
                        transaction.getAmount(),
                        transaction.getDate()
                };
                tableModel.addRow(rowData);
        }
    }
    public JPanel getMainPanel() {
        return panel1;
    }
}
