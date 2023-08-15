import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LogInButtonListener implements ActionListener {
    private final FirstWindow firstWindow;
    private final SecondWindow secondWindow;

    public LogInButtonListener(FirstWindow firstWindow, SecondWindow secondWindow) {
        this.firstWindow = firstWindow;
        this.secondWindow = secondWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel panel = createLogInPanel();
        ClientsController clients = new ClientsController();

        int result = JOptionPane.showConfirmDialog(null, panel, "LogIn option", JOptionPane.OK_CANCEL_OPTION);
        List<String> arrayWithParameters;
        if(result == JOptionPane.OK_OPTION){

            System.out.println("Clicked OK!");
            //check if fields are good filled and
            arrayWithParameters = getStringFromFields(panel);
            System.out.println(arrayWithParameters);
            if(arrayWithParameters.get(0).equals("admin") && arrayWithParameters.get(1).equals("admin")){
                firstWindow.changeWindow(secondWindow.getMainPanel());
            }
            Client client = clients.findByEmail(arrayWithParameters.get(0));
            if(client!=null){
                if(client.getPassword().equals(arrayWithParameters.get(1))){
                    System.out.println("You are Logged in ");
                    firstWindow.changeWindow(secondWindow.getMainPanel());

                }else{
                    System.out.println("Wrong password!");
                }
            }
        }else{
            System.out.println("Clicked CANCEL!");
        }
    }
    public JPanel createLogInPanel(){
        JPanel panel = new JPanel();
        JLabel loginLabel = new JLabel("Login: ");
        JTextField textField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField(20);

        panel.add(loginLabel);
        panel.add(textField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        return panel;
    }


    public List<String> getStringFromFields(JPanel panel){
        Component[] components = panel.getComponents();
        List<String> array = new ArrayList<>();
        for(Component x : components){
            if(x instanceof JTextField){
                String rowData = ((JTextField) x).getText();
                array.add(rowData);
            }
        }

        return array;
    }



}
