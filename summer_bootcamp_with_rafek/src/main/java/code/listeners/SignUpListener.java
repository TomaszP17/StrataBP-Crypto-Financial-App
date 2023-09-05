package code.listeners;

import code.classes.ClientsController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        System.out.println(isDataOk(array));
        return array;
    }
    //TODO Pop up allert that data is invalid
    public boolean isDataOk(List<String> arrayWithParameters){

        //checking length of name
        String name = arrayWithParameters.get(0);
        if(name.length()>20||name.length()<3)
        {

            return false;
        }
        //checking length of last name
        String lastname = arrayWithParameters.get(1);
        if(lastname.length()>20||lastname.length()<3)
        {

            return false;
        }
        //checking if String is in date format
        String dateString = arrayWithParameters.get(2);
        String datePattern = "\\d{2}-\\d{2}-\\d{4}";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(datePattern);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(dateString);

        // Check if the input string matches the date pattern
        if (matcher.matches()) {
            System.out.println("The string is in date format (dd-MM-yyyy).");
        } else {
            System.out.println("The string is not in date format.");
            return false;
        }
        //Checking if is email consist @
        String email = arrayWithParameters.get(3);
        if(email.contains("@")==false){
            return false;
        }

        //checking if is pesel consist only 11 digits
        String pesel = arrayWithParameters.get(4);
        try {
                long peselInInt = Long.parseLong(pesel);
                if(pesel.length() != 11) {
                    System.out.println("asd");
                    return false;
                }
        }
        catch (Exception e){
            return false;
        }

        //Checking length of password
        String password = arrayWithParameters.get(5);
        if(password.length()>20||password.length()<3)
        {

            return false;
        }
        return true;



    }


}