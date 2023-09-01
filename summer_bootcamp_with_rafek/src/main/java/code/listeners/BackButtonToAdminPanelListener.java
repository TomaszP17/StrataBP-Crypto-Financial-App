package code.listeners;

import code.classes.AdminPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackButtonToAdminPanelListener implements ActionListener {
    private final AdminPanel adminPanel;

    public BackButtonToAdminPanelListener(AdminPanel adminPanel) {
        this.adminPanel = adminPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adminPanel.changeWindowToAdminPanel();
    }
}
