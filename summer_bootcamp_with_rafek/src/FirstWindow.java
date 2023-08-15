import javax.swing.*;
import java.awt.*;

public class FirstWindow extends JFrame{
    private JPanel panel1;
    private JLabel upperLabel;
    private JPanel centerPanel;
    private JButton logInButton;
    private JButton signUpButton;
    private JButton recoverPassword;
    private JLabel bottomLabel;

    public FirstWindow() {
        setTitle("STRATA.BP");
        setContentPane(panel1);
        setSize(new Dimension(1000, 1000));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        logInButton.addActionListener(new LogInButtonListener());
        signUpButton.addActionListener(new SignUpListener());
        recoverPassword.addActionListener(new RecoverPasswordButtonListener());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FirstWindow::new);
    }
}
