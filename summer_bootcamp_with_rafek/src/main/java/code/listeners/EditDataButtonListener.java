package code.listeners;

import code.classes.Client;
import code.classes.ClientsController;
import code.classes.User;
import code.classes.UserInfoPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EditDataButtonListener implements ActionListener {
    private final UserInfoPanel userInfoPanel;
    public EditDataButtonListener(UserInfoPanel userInfoPanel) {
        this.userInfoPanel = userInfoPanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Component component = userInfoPanel.getMainPanel();
        JPanel panel = createEditWindowPanel();
        int result = JOptionPane.showConfirmDialog(component, panel, "Edit Data", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION){
            onOK(panel);
        }
    }
    private JPanel createEditWindowPanel(){
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        JLabel dataLabel = new JLabel("Data: ");
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("First Name");
        comboBox.addItem("Last Name");
        comboBox.addItem("Email");
        comboBox.addItem("Password");
        JLabel newDataLabel = new JLabel("New data: ");
        JTextField newDataTextField = new JTextField(10);

        panel.add(dataLabel);
        panel.add(comboBox);
        panel.add(newDataLabel);
        panel.add(newDataTextField);

        return panel;
    }
    public List<String> getStringFromFields(JPanel panel){
        Component[] components = panel.getComponents();
        List<String> array = new ArrayList<>();
        for(Component x : components){
            if(x instanceof JTextField){
                String rowData = ((JTextField) x).getText();
                array.add(rowData);
            } else if (x instanceof JComboBox<?>) {
                JComboBox<String> comboBox = (JComboBox<String>) x;
                String data = (String) comboBox.getSelectedItem();
                array.add(data);
            }
        }
        System.out.println(array);
        return array;
    }

    private void onOK(JPanel panel) {
        List<String> arrayWithData = getStringFromFields(panel);
        String dataType = arrayWithData.get(0);
        String newData = arrayWithData.get(1);

        Client client = ClientsController.findClientByUser(User.getCurrentUser());
        switch (dataType) {
            case "First Name" -> client.setName(newData);
            case "Last Name" -> client.setLastname(newData);
            case "Email" -> client.setEmail(newData);
            case "Password" -> client.setPassword(newData);
        }
        userInfoPanel.updateListView(client);
    }
}
