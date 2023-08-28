import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class AllUsersWindow {
    private JPanel panel1;
    private JPanel upperPanel;
    private JPanel centerPanel;
    private JButton backButton;
    private JLabel upperLabel;
    private JPanel bottomPanel;
    private JPanel rightPanel;
    private JPanel leftPanel;
    private JButton searchUserButton;
    private JButton editUserButton;
    private JButton deleteUserButton;
    private JTable table;
    private JLabel bottomLabel;
    private DefaultTableModel tableModel;
    private final AdminPanel adminPanel;
    private String[] columnNames;
    private List<Client> data;


    public AllUsersWindow(AdminPanel adminPanel) {
        this.adminPanel = adminPanel;
        backButton.addActionListener(new BackButtonToAdminPanelListener(adminPanel));

        searchUserButton.addActionListener(e -> {
            System.out.println("Search Button Clicked!");
        });

        editUserButton.addActionListener(e -> {
            System.out.println("Edit Button Clicked!");
        });

        deleteUserButton.addActionListener(e -> {
            System.out.println("Delete User Clicked!");
        });

        createAllTransactionList();
    }
    private void createAllTransactionList(){
        columnNames = new String[]{"ID", "FROM", "TO", "CRYPTO", "AMOUNT", "DATE"};
        tableModel = new DefaultTableModel(columnNames, 0);
        addUsers(tableModel, data);
        table = new JTable(tableModel);
        centerPanel.add(new JScrollPane(table));
    }

    public static void addUsers(DefaultTableModel tableModel, List<Client> data) {
        ClientsController clientsController = new ClientsController();
        data = clientsController.findAll();
        tableModel.setRowCount(0);
        for (Client client : data) {
            Object[] rowData = {
                    client.getUserId(),
                    client.getName(),
                    client.getLastname(),
                    client.getEmail(),
                    client.getDateOfBirth(),
                    client.getPesel(),
                    client.getPassword()
            };
            tableModel.addRow(rowData);
        }
    }
    public JPanel getMainPanel(){
        return panel1;
    }
}
