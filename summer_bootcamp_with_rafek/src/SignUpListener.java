import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SignUpListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel panel = createSignUpPanel();
        JOptionPane.showMessageDialog(null, panel, "Sign Up Panel", JOptionPane.OK_CANCEL_OPTION);
    }
    //maybe here we should have interface with this method because I created two similar method
    public JPanel createSignUpPanel(){
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameTextField = new JTextField(20);
        JLabel lastNameLabel = new JLabel("Last Name: ");
        JTextField lastNameTextField = new JTextField(20);
        JLabel dateLabel = new JLabel("Date of Birth: ");
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

}
