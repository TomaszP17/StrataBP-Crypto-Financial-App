import javax.swing.*;

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
    private SecondWindow secondWindow;
    public HistoryWindow(SecondWindow secondWindow) {
        this.secondWindow = secondWindow;

        backButton.addActionListener(new BackButtonHistoryListener(secondWindow));
    }
    public JPanel returnMainPanel(){
        return panel1;
    }
}
