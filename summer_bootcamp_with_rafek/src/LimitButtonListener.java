import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LimitButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel panel = createLimitsPanel();
        JOptionPane.showConfirmDialog(null, panel, "Limits", JOptionPane.OK_CANCEL_OPTION);
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
}
