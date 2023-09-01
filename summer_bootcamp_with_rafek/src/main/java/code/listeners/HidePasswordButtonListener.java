package code.listeners;

import code.classes.UserInfoPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HidePasswordButtonListener implements ActionListener {
    private final UserInfoPanel userInfoPanel;

    public HidePasswordButtonListener(UserInfoPanel userInfoPanel) {
        this.userInfoPanel = userInfoPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       DefaultListModel<String> newListModel = userInfoPanel.getListModel();
       newListModel.setElementAt("Password: ***** ***", newListModel.size() - 1);
       userInfoPanel.setListModel(newListModel);

       userInfoPanel.getHidePasswordButton().setEnabled(false);
       userInfoPanel.getShowPasswordButton().setEnabled(true);

    }
}
