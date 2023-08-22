import javax.swing.*;

public class UserInfoPanel {
    private JList list;
    private JLabel upperLabel;
    private JLabel bottomLabel;
    private JButton backButton;
    private JPanel mainPanel;
    private JPanel upperPanel;
    private FirstWindow firstWindow;
    private SecondWindow secondWindow; //
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private Client currentClient;

    public UserInfoPanel(FirstWindow firstWindow, SecondWindow secondWindow) {
        this.firstWindow = firstWindow;
        this.secondWindow = secondWindow; // Przechowywanie referencji do SecondWindow
        backButton.addActionListener(new BackButtonListener(firstWindow, secondWindow));

        User currentUser = User.getCurrentUser();

        if (currentUser instanceof Client) {
            currentClient = (Client) currentUser;
            setNicknameLabel();
            fillListModel();
        }
    }
    public void setNicknameLabel() {
        upperLabel.setText("Welcome, " + currentClient.getName() + "!");
    }
    public void fillListModel() {
        listModel.addElement("BTC: " + currentClient.wallet.get("BTC"));
        listModel.addElement("ETH: " + currentClient.wallet.get("ETH"));
        listModel.addElement("ADA: " + currentClient.wallet.get("ADA"));

        list.setModel(listModel);
    }

    /**
     * Method returns main Panel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }
}