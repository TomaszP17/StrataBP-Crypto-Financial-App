package code.listeners;

import code.classes.ClientsController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SignUpListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        ClientsController clients = new ClientsController();
        JPanel panel = createSignUpPanel();
        List<String> arrayWithParameters;
        int result = JOptionPane.showConfirmDialog(null, panel, "Sign Up Panel", JOptionPane.OK_CANCEL_OPTION);
        //String dateOfBirth = arrayWithParameters.get(2);

        arrayWithParameters = getStringFromFields(panel);

        if(result == JOptionPane.OK_OPTION){
            clients.clientCreate(arrayWithParameters.get(0),arrayWithParameters.get(1), arrayWithParameters.get(2), arrayWithParameters.get(3),arrayWithParameters.get(4),arrayWithParameters.get(5));

            System.out.println("dsadasd");
            clients.findAll();

            //HERE WE NEED CREATE CLIENT AND SAVE IT TO DATABASE
            System.out.println(getStringFromFields(panel));
        }
    }
    //maybe here we should have interface with this method because I created two similar method
    public JPanel createSignUpPanel(){
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameTextField = new JTextField(20);
        JLabel lastNameLabel = new JLabel("Last Name: ");
        JTextField lastNameTextField = new JTextField(20);
        JLabel dateLabel = new JLabel("Date of Birth: (Format: DD-MM-YYYY)");
        JTextField dateTextField = new JTextField(20);
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailTextField = new JTextField(20);
        JLabel peselLabel = new JLabel("Pesel: ");
        JTextField peselTextField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password: ");
        JTextField passwordTextField = new JTextField(20);

        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(lastNameLabel);
        panel.add(lastNameTextField);
        panel.add(dateLabel);
        panel.add(dateTextField);
        panel.add(emailLabel);
        panel.add(emailTextField);
        panel.add(peselLabel);
        panel.add(peselTextField);
        panel.add(passwordLabel);
        panel.add(passwordTextField);

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
        System.out.println(array);
        return array;
    }


}