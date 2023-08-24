import javax.swing.*;

public class UserInfoPanel {
    private JList list;
    private JLabel upperLabel;
    private JLabel bottomLabel;
    private JButton backButton;
    private JPanel mainPanel;
    private JPanel upperPanel;
    private SecondWindow secondWindow; //
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private Client currentClient;

    public UserInfoPanel( SecondWindow secondWindow) {
        this.secondWindow = secondWindow; // Przechowywanie referencji do SecondWindow
        backButton.addActionListener(new BackButtonListener(secondWindow));

        User currentUser = User.getCurrentUser();
        System.out.println("curr" + currentUser);
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
        listModel.addElement("BTC: " + currentClient.getWallet().get("BTC"));
        listModel.addElement("ETH: " + currentClient.getWallet().get("ETH"));
        listModel.addElement("ADA: " + currentClient.getWallet().get("ADA"));

        list.setModel(listModel);
    }

    /**
     * Method returns main Panel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }
}