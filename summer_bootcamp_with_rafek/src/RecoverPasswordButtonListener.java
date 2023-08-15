import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecoverPasswordButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel panel = createRecoverPanel();
        //show window with enter login, and you will have a message
        JOptionPane.showMessageDialog(null, panel, "Password Recovery", JOptionPane.OK_CANCEL_OPTION);
    }
    public JPanel createRecoverPanel(){
        JPanel panel = new JPanel();
        JLabel loginLabel = new JLabel("Enter your login: ");
        JTextField textField = new JTextField(20);
        panel.add(loginLabel);
        panel.add(textField);

        return panel;
    }
    /*public checkIfUserExists(){
        //method checks text file where is user data base and
    }*/
}
