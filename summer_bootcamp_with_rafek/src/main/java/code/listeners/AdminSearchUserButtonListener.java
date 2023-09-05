package code.listeners;

import code.classes.AllUsersWindow;
import code.classes.Client;
import code.classes.ClientsController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AdminSearchUserButtonListener implements ActionListener {
    private final AllUsersWindow allUsersWindow;

    public AdminSearchUserButtonListener(AllUsersWindow allUsersWindow) {
        this.allUsersWindow = allUsersWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel panel = createSearchPanel();

        int result = JOptionPane.showConfirmDialog(allUsersWindow.getMainPanel(), panel, "Search filter", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION){
            List<String> arrayWithDataFromFields = getDataFromFields(panel);
            String param = arrayWithDataFromFields.get(0);
            String searchedValue = arrayWithDataFromFields.get(1);

            //search value by param
            updateTableModel(param, searchedValue);
        }

    }

    private JPanel createSearchPanel(){
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        panel.add(new JLabel("Choose param: "));
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("ID");
        comboBox.addItem("NAME");
        comboBox.addItem("LAST NAME");
        comboBox.addItem("EMAIL");
        comboBox.addItem("DATE OF BIRTH");
        comboBox.addItem("PESEL");
        comboBox.addItem("PASSWORD");
        panel.add(comboBox);
        panel.add(new JLabel("Searched value: "));
        panel.add(new JTextField(10));

        return panel;
    }

    private List<String> getDataFromFields(JPanel panel) {
        Component[] components = panel.getComponents();
        List<String> array = new ArrayList<>();
        for(Component x : components){
            if(x instanceof JTextField){
                String rowData = ((JTextField) x).getText();
                array.add(rowData);
            } else if (x instanceof JComboBox<?>) {
                JComboBox<String> comboBox = (JComboBox<String>) x;
                String data = comboBox.getSelectedItem().toString();
                array.add(data);
            }
        }
        System.out.println(array);
        return array;
    }
    private List<Client> searchForValue(String param, String searchedValue){
        ClientsController clientsController = new ClientsController();
        List<Client> clientList = clientsController.findAll();
        List<Client> arrayWithResult = new ArrayList<>();
        for(Client client : clientList){
            switch (param) {
                case "ID" -> {
                    String clientIdString = String.valueOf(client.getUserId());
                    if (searchedValue.equals(clientIdString)) {
                        arrayWithResult.add(client);
                    }
                }
                case "NAME" -> {
                    String clientNameString = client.getName();
                    if (searchedValue.equals(clientNameString)) {
                        arrayWithResult.add(client);
                    }
                }
                case "LAST NAME" -> {
                    String clientLastname = client.getLastname();
                    if (searchedValue.equals(clientLastname)) {
                        arrayWithResult.add(client);
                    }
                }
                case "EMAIL" -> {
                    String clientEmail = client.getEmail();
                    if (searchedValue.equals(clientEmail)) {
                        arrayWithResult.add(client);
                    }
                }
                case "DATE OF BIRTH" -> {
                    String clientDate = client.getDateOfBirth();
                    if (searchedValue.equals(clientDate)) {
                        arrayWithResult.add(client);
                    }
                }
                case "PESEL" -> {
                    String clientPesel = client.getPesel();
                    if (searchedValue.equals(clientPesel)) {
                        arrayWithResult.add(client);
                    }
                }
                case "PASSWORD" -> {
                    String clientPassword = client.getPassword();
                    if (searchedValue.equals(clientPassword)) {
                        arrayWithResult.add(client);
                    }
                }
                default -> System.out.println("Some problems with switch in search option");
            }
        }
        System.out.println(arrayWithResult.get(0).getEmail());
        return arrayWithResult;
    }
    private void updateTableModel(String param, String searchedValue) {
        String[] columnNames = allUsersWindow.getColumnNames();
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        List<Client> searchResults = searchForValue(param, searchedValue);
        tableModel.setRowCount(0);
        for (Client client : searchResults) {
            Object[] rowData = {
                    client.getUserId(),
                    client.getName(),
                    client.getLastname(),
                    client.getEmail(),
                    client.getDateOfBirth(),
                    client.getPesel(),
                    client.getPassword()
            };
            tableModel.addRow(rowData);
        }
        allUsersWindow.getTable().setModel(tableModel);
    }

}
