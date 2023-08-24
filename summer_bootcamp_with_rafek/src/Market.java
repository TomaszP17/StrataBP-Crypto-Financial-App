import javax.swing.*;

public class Market {
    private JPanel panel1;
    private JLabel upperLabel;
    private JPanel centerPanel;
    private JPanel buttonsPanel;
    private JButton buyButton;
    private JButton sellButton;
    private JButton refreshTimeButton;
    private JButton backButton;
    private JTable table1;
    private SecondWindow secondWindow;
    public Market(SecondWindow secondWindow) {
        this.secondWindow = secondWindow;
        backButton.addActionListener(new BackButtonListener(secondWindow));
    }

    public JPanel returnMainPanel() {
        return panel1;
    }
}
