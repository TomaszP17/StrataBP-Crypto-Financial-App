import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LogInButtonListener implements ActionListener {
    private final FirstWindow firstWindow;

    public LogInButtonListener(FirstWindow firstWindow) {
        this.firstWindow = firstWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel panel = createLogInPanel();

        int result = JOptionPane.showConfirmDialog(null, panel, "LogIn option", JOptionPane.OK_CANCEL_OPTION);
        List<String> arrayWithParameters;
        if (result == JOptionPane.OK_OPTION) {
            arrayWithParameters = getStringFromFields(panel);
            Client client = ClientsController.findByEmail(arrayWithParameters.get(0));
            User.setCurrentUser(client);
            if (isUserAdmin(arrayWithParameters)) {
                setAdminPanel();
            } else {
                if (client != null) {
                    if (client.getPassword().equals(arrayWithParameters.get(1))) {
                        System.out.println("You are Logged in ");
                        SecondWindow secondWindow = new SecondWindow(firstWindow);
                        firstWindow.changeWindow(secondWindow.getMainPanel());
                        secondWindow.setCurrentUser();
                        secondWindow.showClientWallet(client);
                    } else {
                        System.out.println("Wrong password!");
                    }
                } else {
                    System.out.println("Clicked CANCEL!");
                }
            }
        }


    }

    public JPanel createLogInPanel() {
        JPanel panel = new JPanel();
        JLabel loginLabel = new JLabel("Email: ");
        JTextField textField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField(20);

        panel.add(loginLabel);
        panel.add(textField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        return panel;
    }

    public List<String> getStringFromFields(JPanel panel) {
        Component[] components = panel.getComponents();
        List<String> array = new ArrayList<>();
        for (Component x : components) {
            if (x instanceof JTextField) {
                String rowData = ((JTextField) x).getText();
                array.add(rowData);
            }
        }

        return array;
    }
    private boolean isUserAdmin(List<String> array){
        return array.get(0).equals("admin") && array.get(1).equals("admin");
    }
    public void setAdminPanel(){
        AdminPanel adminPanel = new AdminPanel(firstWindow);
        firstWindow.changeWindow(adminPanel.getMainPanel());
    }
}