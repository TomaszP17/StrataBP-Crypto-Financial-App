import javax.swing.*;
import java.awt.*;

public class AdminPanel{
    private JPanel panel1;
    private JLabel upperLabel;
    private JPanel centerPanel;
    private JButton removeUserButton;
    private JButton donateMoneyToUserButton;
    private JButton transactionHistoryButton;
    private JButton allUsersButton;
    private JButton backButton;
    private JPanel panel;
    private FirstWindow firstWindow;
    public AdminPanel(FirstWindow firstWindow) {
        this.firstWindow = firstWindow;

        allUsersButton.addActionListener(e -> {

        });

        transactionHistoryButton.addActionListener(e -> {

        });

        donateMoneyToUserButton.addActionListener(e -> {

        });
        backButton.addActionListener(new BackToFirstWindowListener(firstWindow));
    }

    public JPanel getMainPanel() {
        return panel1;
    }
}
