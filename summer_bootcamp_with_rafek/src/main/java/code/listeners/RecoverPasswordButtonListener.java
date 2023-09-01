package code.listeners;

import code.classes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RecoverPasswordButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel panel = createRecoverPanel();
        //show window with enter login, and you will have a message
        int result = JOptionPane.showConfirmDialog(null, panel, "Password Recovery", JOptionPane.OK_CANCEL_OPTION);
        //is ok option clicked
        if(result == JOptionPane.OK_OPTION){
            String userInput = getInputFromUser(panel);
            //check if user email exists
            ClientsController clientsController = new ClientsController();
            List<Client> clientList = clientsController.findAll();
            if(checkIfUserExists(clientList, userInput)){
                System.out.println("code.classes.User exists");
                GeneratorRecoveryCode generatorRecoveryCode = new GeneratorRecoveryCode();
                String generatedRecoveryCode = generatorRecoveryCode.generateCode();
                //send email with recoveryCode
                sendMailToUser(userInput, generatedRecoveryCode);
                //generate file with the same code
                GeneratorFileWithCode generatorFileWithCode = new GeneratorFileWithCode();
                generatorFileWithCode.generateFile(generatedRecoveryCode);

                //show window with input
                String recoveryCodeWindowInput = JOptionPane.showInputDialog(panel, "Enter recovery code: ");

                //check is recoveryCode correct
                if(checkIsRecoveryCodeCorrect(recoveryCodeWindowInput)){
                    System.out.println("Passwords are the same");

                    //show window with new password and confirm new password
                    boolean flag = false;
                    JPanel confirmPanel = createNewPasswordConfirmWindow();
                    while (!flag){
                        int confirmResult = JOptionPane.showConfirmDialog(panel, confirmPanel, "Enter a new password", JOptionPane.OK_CANCEL_OPTION);
                        if(confirmResult == JOptionPane.OK_OPTION){
                            //userInput is a email
                            List<String> passwordsFromChangeWindowArray = getInputFromChangePasswordWindow(confirmPanel);
                            if(isPasswordsTheSame(passwordsFromChangeWindowArray) && !isPasswordsAreNull(passwordsFromChangeWindowArray)){
                                //is okay so change the password
                                User user = ClientsController.findByEmail(userInput);
                                user.setPassword(passwordsFromChangeWindowArray.get(0));
                                flag = true;
                                JOptionPane.showMessageDialog(confirmPanel, "Password changed succesfully!");
                            }
                        }
                    }
                }else{
                    System.out.println("Passwords are not the same!");
                }
            }else {
                JOptionPane.showMessageDialog(panel, "This users does not exists");
            }
        }

    }
    private JPanel createRecoverPanel(){
        JPanel panel = new JPanel();
        JLabel loginLabel = new JLabel("Enter your login: ");
        JTextField textField = new JTextField(20);
        panel.add(loginLabel);
        panel.add(textField);

        return panel;
    }
    private String getInputFromUser(JPanel panel){
        Component[] components = panel.getComponents();
        String result = null;
        for(Component x : components){
            if(x instanceof JTextField){
                result = ((JTextField) x).getText();
            }
        }
        return result;
    }
    private boolean checkIfUserExists(List<Client> clientList, String userInputEmail){
        for(Client client : clientList){
            if(client.getEmail().equals(userInputEmail)){
                return true;
            }
        }
        return false;
    }
    private void sendMailToUser(String userInputEmail, String recoveryCode){
        GEmailSender gEmailSender = new GEmailSender();
        String from = "stratabp@gmail.com";
        String subject = "RecoveryPassword [STRATA.BP]";
        String text = "Hello " + userInputEmail + "\nWe heard that you lost your Strata.BP password. Sorry about that!\n" +
                        "But don’t worry! You can use the following code to reset your password:\n" +
                        recoveryCode + "\n" +
                        "Thanks,\n" +
                        "The STRATA.BP Team\n\n\n" +
                        "You're receiving this email because a password reset was requested for your account.";
        boolean b = gEmailSender.sendEmail(userInputEmail,from,subject,text);
        if(b){
            System.out.println("Email is sent successfully");
        }else {
            System.out.println("There is problem in sending email");
        }
    }
    private boolean checkIsRecoveryCodeCorrect(String recoveryCode){
        GeneratorFileWithCode generatorFileWithCode = new GeneratorFileWithCode();
        String codeFromFile = generatorFileWithCode.getRecoveryCodeFromFile();
        if(codeFromFile == null){
            System.out.println("Code from file is null");
            return false;
        }
        return recoveryCode.equals(codeFromFile);
    }

    private JPanel createNewPasswordConfirmWindow(){
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel infoLabel = new JLabel("Passwords must be the same!");
        JLabel nothingLabel = new JLabel("");
        JLabel newPasswordLabel = new JLabel("New Password: ");
        JPasswordField newPasswordTextField = new JPasswordField(20);
        JLabel confirmNewPasswordLabel = new JLabel("Confirm Password: ");
        JPasswordField confirmNewPasswordTextField = new JPasswordField(20);
        panel.add(infoLabel);
        panel.add(nothingLabel);
        panel.add(newPasswordLabel);
        panel.add(newPasswordTextField);
        panel.add(confirmNewPasswordLabel);
        panel.add(confirmNewPasswordTextField);
        return panel;
    }

    private List<String> getInputFromChangePasswordWindow(JPanel panel){
        Component[] components = panel.getComponents();
        List<String> arrayWithResult = new ArrayList<>();
        for(Component x : components){
            if(x instanceof JPasswordField){
                String data = ((JPasswordField) x).getText();
                arrayWithResult.add(data);
            }
        }
        return arrayWithResult;
    }
    private boolean isPasswordsTheSame(List<String> arrayWithResult){
        return arrayWithResult.get(0).equals(arrayWithResult.get(1));
    }
    private boolean isPasswordsAreNull(List<String> arrayWithResult){
        return arrayWithResult.get(0) == null || arrayWithResult.get(0).equals("");
    }

}
