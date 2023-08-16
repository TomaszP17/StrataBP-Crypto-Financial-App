import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SendButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel panel = createSendPanel();
        int result = JOptionPane.showConfirmDialog(null, panel, "Send Money", JOptionPane.OK_CANCEL_OPTION);
        List<String> arrayWithParameters;
        if(result == JOptionPane.OK_OPTION){

            arrayWithParameters = getStringFromFields(panel);
            Client client1 = ClientsController.findClientByUser(User.getCurrentUser());
            Client client2 = ClientsController.findByEmail(arrayWithParameters.get(2).toString());
            getStringFromFields(panel);
            String keyCrypto = arrayWithParameters.get(0).toString();
            double amount = Double.parseDouble(arrayWithParameters.get(1).toString());
            ClientsController.sendFromTo(client1.getEmail(), client2.getEmail(), arrayWithParameters.get(0).toString(), amount);

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

    /**
     * It gets data (String) from fields, in order: currency, amount, email
     */
    public List<String> getStringFromFields(JPanel panel){
        Component[] components = panel.getComponents();
        List<String> array = new ArrayList<>();
        for(Component x : components){
            if(x instanceof JTextField){
                String rowData = ((JTextField) x).getText();
                array.add(rowData);
            } else if (x instanceof JComboBox<?>) {
                JComboBox<Cryptocurrency> comboBox = (JComboBox<Cryptocurrency>) x;
                String data = ((Cryptocurrency) comboBox.getSelectedItem()).getKey();
                array.add(data);
            }
        }
        System.out.println(array);
        return array;
    }
}
