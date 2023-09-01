import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class HistoryWindow extends JFrame{
    private JPanel panel1;
    private JLabel upperLabel;
    private JButton searchButton;
    private JButton receivedButton;
    private JButton backButton;
    private JTable table1;
    private JButton sentButton;
    private JPanel centerPanel;
    private JPanel buttonsPanel;
    private JButton allTransactionsButton;
    private final SecondWindow secondWindow;
    private String[] columnNames;
    private List<Transaction> data;
    public HistoryWindow(SecondWindow secondWindow) {
        this.secondWindow = secondWindow;
        backButton.addActionListener(new BackButtonHistoryListener(secondWindow));

        //todo is it necessary?
        searchButton.addActionListener(e -> System.out.println("we need to do find window which will search transaction by user"));

        sentButton.addActionListener(new SentHistoryButtonListener(this));

        receivedButton.addActionListener(new ReceivedHistoryButtonListener(this));
        allTransactionsButton.addActionListener(new AllTransactionButtonListener(this));
        createAllTransactionList();

    }
    public JPanel returnMainPanel(){
        return panel1;
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
            if(transaction.getTo().getEmail().equals(currentClient.getEmail()) ||
                    transaction.getFrom().getEmail().equals(currentClient.getEmail())){
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
    }

    public JTable getTable(){
        return table1;
    }
    public String[] getColumnNames(){
        return columnNames;
    }
    public List<Transaction> getTransactionList(){
        return data;
    }
    public JButton getReceivedButton(){
        return receivedButton;
    }
    public JButton getSentButton(){
        return sentButton;
    }
    public JButton getAllTransactionsButton(){
        return allTransactionsButton;
    }

}
