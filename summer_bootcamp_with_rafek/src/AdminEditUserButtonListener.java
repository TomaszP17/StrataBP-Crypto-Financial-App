import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
            }
        } else {
            System.out.println("No row selected.");
        }
    }
    private JPanel createPanel(JTable table){
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        List<String> arrayWithData = getSelectedRowData(table);

        JLabel idLabel = new JLabel(arrayWithData.get(0));
        JTextField idTextField = new JTextField(20);
        JLabel nameLabel = new JLabel(arrayWithData.get(1));
        JTextField nameTextField = new JTextField(20);
        JLabel lastNameLabel = new JLabel(arrayWithData.get(2));
        JTextField lastNameTextField = new JTextField(20);
        JLabel emailLabel = new JLabel(arrayWithData.get(3));
        JTextField emailTextField = new JTextField(20);
        JLabel dateOfBirthLabel = new JLabel(arrayWithData.get(4));
        JTextField dateOfBirthTextField = new JTextField(20);
        JLabel peselLabel = new JLabel(arrayWithData.get(5));
        JTextField peselTextField = new JTextField(20);
        /*JLabel passwordLabel = new JLabel(arrayWithData.get(6));
        JTextField passwordTextField = new JTextField(20);*/

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
        /*panel.add(passwordLabel);
        panel.add(passwordTextField);*/

        return panel;
    }

    private List<String> getSelectedRowData(JTable table){
        int selectedRow = table.getSelectedRow();
        List<String> arrayList = new ArrayList<>();
        if(selectedRow >= 0) {
            int columnCount = table.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                Object data = table.getValueAt(selectedRow, i);
                arrayList.add(data != null ? data.toString() : "");
            }
        }else{
            System.out.println("No row selected.");
        }
        return arrayList;
    }
}
