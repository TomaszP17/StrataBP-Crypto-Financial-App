import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AdminDeleteUserButtonListener implements ActionListener {

    private final AllUsersWindow allUsersWindow;

    public AdminDeleteUserButtonListener(AllUsersWindow allUsersWindow) {
        this.allUsersWindow = allUsersWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String result = JOptionPane.showInputDialog("Type \"delete\" below to delete User");
        if(result.equals("delete")){
            List<String> arrayWithData = getSelectedRowData(allUsersWindow.getTable());
            String email = arrayWithData.get(3); // Assuming email is in the 4th column
            ClientsController.deleteClient(email); // Delete the client
            allUsersWindow.updateTable();
        }
    }
    private List<String> getSelectedRowData(JTable table){
        return getStrings(table);
    }

    static List<String> getStrings(JTable table) {
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
