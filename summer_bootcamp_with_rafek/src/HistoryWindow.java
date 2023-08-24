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
        searchButton.addActionListener(e -> {
            System.out.println("we need to do find window which will search transaction by user");
        });
        sentButton.addActionListener(e -> { //use streams
            System.out.println("after click panel shows only transactions by user when he was a senter");
        });
        receivedButton.addActionListener(e -> { //use streams
            System.out.println("panel shows only receive transaction for a user");
        });
    }
    public JPanel returnMainPanel(){
        return panel1;
    }
}
