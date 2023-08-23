import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LimitButtonListener implements ActionListener, PanelListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel panel = createLimitsPanel();
        int result = JOptionPane.showConfirmDialog(null, panel, "Limits", JOptionPane.OK_CANCEL_OPTION);

        if(result == JOptionPane.OK_OPTION){ //when user clicked OK run onOK() method
            onOK(panel);
        }else {
            onCancel(panel);
        }
    }

    public JPanel createLimitsPanel(){
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel actualLimitLabel = new JLabel("Actual limit: " );// + obecny limit na koncie);
        JLabel changeLimitLabel = new JLabel("Change your daily transfer limit: ");
        JComboBox<Limit> limitJComboBox = new JComboBox<>();
        limitJComboBox.addItem(Limit._10);
        limitJComboBox.addItem(Limit._100);
        limitJComboBox.addItem(Limit._1000);
        limitJComboBox.addItem(Limit._10000);
        JLabel confirmLabel = new JLabel("Confirm your password: ");
        JPasswordField passwordField = new JPasswordField();

        panel.add(actualLimitLabel);
        panel.add(changeLimitLabel);
        panel.add(limitJComboBox);
        panel.add(confirmLabel);
        panel.add(passwordField);

        return panel;
    }

    /**
     * Get Data From Limit Panel
     * @param panel - mainPanel of limit window
     * @return - array with data entered by user
     */
    @Override
    public List<String> getDataFromFields(JPanel panel) {
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

    /**
     * onOk listener
     * array.get(0) = limit
     * array.get(1) = password
     * @param panel
     */
    public void onOK(JPanel panel){
        List<String> array = getDataFromFields(panel);
        //we need to check if fields are correct
        if(isDataCorrect(array)){
            //here we need to change limit for this account
            //TODO
            //here we need to send information about status
            JOptionPane.showMessageDialog(panel, "Limit changed for Your account!");
        }else{
            JOptionPane.showMessageDialog(panel, "An attempt to change the limit failed!");
        }
    }

    /**
     * onCancel listener
     * @param panel
     */
    private void onCancel(JPanel panel) {
        System.out.println("Clicked CANCEL");
    }
    public boolean isDataCorrect(List<String> array){

        String limit = array.get(0);
        String password = array.get(1);
        Client client = ClientsController.findClientByUser(User.getCurrentUser());
        if(limit != null){
            if(password.equals(client.getPassword())){
                System.out.println("Authorization successful");
                return true;
            }else {
                System.out.println("Wrong Password");
            }
        }
        return false;
    }
}
