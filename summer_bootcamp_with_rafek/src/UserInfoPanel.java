import javax.swing.*;

public class UserInfoPanel {
    private JList list;
    private JLabel upperLabel;
    private JLabel bottomLabel;
    private JButton backButton;
    private JPanel mainPanel;
    private JPanel upperPanel;
    private JButton editDataButton;
    private JButton showPasswordButton;
    private JButton hidePasswordButton;
    private SecondWindow secondWindow;
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private Client currentClient;

    public UserInfoPanel( SecondWindow secondWindow) {
        this.secondWindow = secondWindow;
        backButton.addActionListener(new BackButtonListener(secondWindow));
        showPasswordButton.addActionListener(new ShowPasswordButtonListener(this));
        hidePasswordButton.addActionListener(new HidePasswordButtonListener(this));
        editDataButton.addActionListener(new EditDataButtonListener(this));

        User currentUser = User.getCurrentUser();
        System.out.println("curr" + currentUser);
        if (currentUser instanceof Client) {
            currentClient = (Client) currentUser;
            setNicknameLabel();
            fillListModel(currentClient);
        }
    }
    public void setNicknameLabel() {
        upperLabel.setText("Your data panel");
    }
    public void fillListModel(Client client) {

        listModel.addElement("First Name: " + currentClient.getName());
        listModel.addElement("Last Name: " + currentClient.getLastname());
        listModel.addElement("PESEL: " + currentClient.getPesel());
        listModel.addElement("Date Of Birth: " + currentClient.getDateOfBirth());
        listModel.addElement("Email: " + currentClient.getEmail());
        listModel.addElement("Password: ***** ***" );

        list.setModel(listModel);
    }

    /**
     * Method returns main Panel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }
    public DefaultListModel<String> getListModel(){
        return listModel;
    }
    public void setListModel(DefaultListModel<String> listModel){
        this.listModel = listModel;
    }
    public JButton getShowPasswordButton(){
        return showPasswordButton;
    }
    public JButton getHidePasswordButton(){
        return hidePasswordButton;
    }
    public void updateListView(Client client){
        listModel.clear();
        fillListModel(client);
    }
}