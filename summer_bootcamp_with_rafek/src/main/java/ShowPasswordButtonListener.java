import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowPasswordButtonListener implements ActionListener {
    private final UserInfoPanel userInfoPanel;

    public ShowPasswordButtonListener(UserInfoPanel userInfoPanel) {
        this.userInfoPanel = userInfoPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Client client = ClientsController.findClientByUser(User.getCurrentUser());
        String userPassword = client.getPassword();
        DefaultListModel<String> newListModel = userInfoPanel.getListModel();
        newListModel.setElementAt("Password: " + userPassword, newListModel.size() - 1);
        userInfoPanel.setListModel(newListModel);

        userInfoPanel.getHidePasswordButton().setEnabled(true);
        userInfoPanel.getShowPasswordButton().setEnabled(false);
    }
}
