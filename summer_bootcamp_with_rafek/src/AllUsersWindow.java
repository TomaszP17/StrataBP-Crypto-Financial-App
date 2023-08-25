import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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

    public AllUsersWindow() {
        backButton.addActionListener(e -> {
            System.out.println("Back Button Clicked!");
        });

        searchUserButton.addActionListener(e -> {
            System.out.println("Search Button Clicked!");
        });

        editUserButton.addActionListener(e -> {
            System.out.println("Edit Button Clicked!");
        });

        deleteUserButton.addActionListener(e -> {
            System.out.println("Delete User Clicked!");
        });

        table = new JTable();
        tableModel = new DefaultTableModel();

    }

    public JPanel getMainPanel() {
        return panel1;
    }
}
