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
    private JButton seeAllButton;
    private final AdminPanel adminPanel;
    private List<Transaction> data;
    private String[] columnNames;
    private DefaultTableModel tableModel;

    public TransactionHistoryWindow(AdminPanel adminPanel) {
        this.adminPanel = adminPanel;
        searchButton.addActionListener(new AdminSearchButtonListener(this));
        seeAllButton.addActionListener(e -> {
            addUserTransactions(tableModel, data);
            updateTable(tableModel);
        });
        backButton.addActionListener(new BackButtonToAdminPanelListener(adminPanel));
        createAllTransactionList();
    }
    public void createAllTransactionList(){
        //Client currentClient = ClientsController.findClientByUser(User.getCurrentUser());
        data = TransactionController.getTransactionList();
        columnNames = new String[]{"ID", "FROM", "TO", "CRYPTO", "AMOUNT", "DATE"};
        tableModel = new DefaultTableModel(columnNames, 0);
        addUserTransactions(tableModel, data);
        table1 = new JTable(tableModel);
        centerPanel.add(new JScrollPane(table1));
    }
    public static void addUserTransactions(DefaultTableModel tableModel, List<Transaction> data) {
        tableModel.setRowCount(0);
        for (Transaction transaction : data) {
            Object[] rowData = {
                    transaction.getId(),
                    transaction.getFrom() != null ? transaction.getFrom().getEmail() : "N/A",
                    transaction.getTo() != null ? transaction.getTo().getEmail() : "N/A",
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
    public String[] getColumnNames() {
        return columnNames;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public void setCenterPanel(JPanel centerPanel) {
        this.centerPanel = centerPanel;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }
    public void updateTable(DefaultTableModel newModel) {
        table1.setModel(newModel);
    }
}
