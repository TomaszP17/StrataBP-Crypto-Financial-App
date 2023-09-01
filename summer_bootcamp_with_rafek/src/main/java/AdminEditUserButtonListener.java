import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class AdminEditUserButtonListener implements ActionListener {
    private final AllUsersWindow allUsersWindow;

    public AdminEditUserButtonListener(AllUsersWindow allUsersWindow) {
        this.allUsersWindow = allUsersWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel panel = allUsersWindow.getMainPanel();
        JTable table = allUsersWindow.getTable();

        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            JPanel windowPanel = createPanel(table);

            int result = JOptionPane.showConfirmDialog(panel, windowPanel, "Edit User", JOptionPane.OK_CANCEL_OPTION);
            if(result == JOptionPane.OK_OPTION){
                System.out.println("ok!");
                List<String> arrayWithDataPanel = getDataFromPanel(windowPanel);
                List<String> arrayWithDataTable = getSelectedRowData(table);
                //compare these 2 arrays and change table in alluserswindow
                int diff = counterDifference(arrayWithDataPanel,arrayWithDataTable);
                if(diff > 0){
                    allUsersWindow.setTableWithEditedData(arrayWithDataPanel);
                    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                    allUsersWindow.updateTable(tableModel);

                    List<Client> updatedClients = updateClientsListInController(tableModel.getDataVector());
                    ClientsController.updateClientsList(updatedClients);
                }

            }
        } else {
            System.out.println("No row selected.");
        }
    }

    /**
     * Creating Panel with name of column and filled by data from table
     * @param table - table with data from selected row
     * @return - new panel
     */
    private JPanel createPanel(JTable table){
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        List<String> arrayWithData = getSelectedRowData(table);

        JLabel idLabel = new JLabel("ID");
        JTextField idTextField = new JTextField(arrayWithData.get(0),20);
        JLabel nameLabel = new JLabel("NAME");
        JTextField nameTextField = new JTextField(arrayWithData.get(1),20);
        JLabel lastNameLabel = new JLabel("LAST NAME");
        JTextField lastNameTextField = new JTextField(arrayWithData.get(2),20);
        JLabel emailLabel = new JLabel("EMAIL ");
        JTextField emailTextField = new JTextField(arrayWithData.get(3),20);
        JLabel dateOfBirthLabel = new JLabel("DATE OF BIRTH");
        JTextField dateOfBirthTextField = new JTextField(arrayWithData.get(4),20);
        JLabel peselLabel = new JLabel("PESEL");
        JTextField peselTextField = new JTextField(arrayWithData.get(5),20);
        JLabel passwordLabel = new JLabel("PASSWORD");
        JTextField passwordTextField = new JTextField(arrayWithData.get(6),20);

        panel.add(idLabel);
        panel.add(idTextField);
        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(lastNameLabel);
        panel.add(lastNameTextField);
        panel.add(emailLabel);
        panel.add(emailTextField);
        panel.add(dateOfBirthLabel);
        panel.add(dateOfBirthTextField);
        panel.add(peselLabel);
        panel.add(peselTextField);
        panel.add(passwordLabel);
        panel.add(passwordTextField);

        return panel;
    }

    /**
     * Getting data from selected table row
     * @param table - all users info table
     * @return - list of row data String
     */
    private List<String> getSelectedRowData(JTable table){
        return AdminDeleteUserButtonListener.getStrings(table);
    }

    /**
     * Getting data from windowPanel
     * @param panel - windowPanel
     * @return - array with data in String
     */
    private List<String> getDataFromPanel(JPanel panel){
        Component[] components = panel.getComponents();
        List<String> array = new ArrayList<>();
        for(Component x : components){
            if(x instanceof JTextField){
                String rowData = ((JTextField) x).getText();
                array.add(rowData);
            }
        }
        System.out.println(array);
        return array;
    }

    /**
     * Returning number of difference between arrays
     * @param array1 - first Array
     * @param array2 - second Array
     * @return - number of difference
     */
    private int counterDifference(List<String> array1, List<String> array2){
        if(array1.size() != array2.size()){
            return -1;
        }
        int counter = 0;
        for(int i = 0; i < array1.size(); i++){
            if(array1.get(i).equals(array2.get(i))){
                counter++;
            }
        }
        return counter;
    }


    private List<Client> updateClientsListInController(Vector<Vector> newData) {
        List<Client> updatedClients = new ArrayList<>();

        for (Vector<Object> row : newData) {
            String name = row.get(1).toString();
            String lastname = row.get(2).toString();
            String pesel = row.get(5).toString();
            String email = row.get(3).toString();
            String dateOfBirth = row.get(4).toString();
            String password = row.get(6).toString();

            Client client = new Client(name, lastname, dateOfBirth, email, pesel, password);
            updatedClients.add(client);
        }

        return updatedClients;
    }

}
