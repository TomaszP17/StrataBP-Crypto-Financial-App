import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel panel = createSendPanel();
        int result = JOptionPane.showConfirmDialog(null, panel, "Send Money", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION){
            //logika przelewu gdy uzytkownik kliknie OK
        }
    }
    public JPanel createSendPanel(){
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        JLabel label = new JLabel("Currency: ");
        JComboBox<Cryptocurrency> cryptoComboBox = new JComboBox<>();
        cryptoComboBox.addItem(Cryptocurrency.BTC);
        cryptoComboBox.addItem(Cryptocurrency.ETH);
        cryptoComboBox.addItem(Cryptocurrency.ADA);
        JLabel numberLabel = new JLabel("Number: ");
        JTextField numberTextField = new JTextField(10);
        JLabel receiverLabel = new JLabel("Receiver Email: ");
        JTextField emailTextField = new JTextField();

        panel.add(label);
        panel.add(cryptoComboBox);
        panel.add(numberLabel);
        panel.add(numberTextField);
        panel.add(receiverLabel);
        panel.add(emailTextField);

        return panel;
    }
}
