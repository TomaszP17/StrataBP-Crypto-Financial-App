import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LogInButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel panel = createLogInPanel();
        JOptionPane.showMessageDialog(null, panel, "LogIn option", JOptionPane.OK_CANCEL_OPTION);
        //here handle client controller and saving to file

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
}
