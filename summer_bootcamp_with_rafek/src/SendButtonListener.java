import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SendButtonListener implements ActionListener {

    private final Client client1 = ClientsController.findClientByUser(User.getCurrentUser());
    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel panel = createSendPanel();
        int result = JOptionPane.showConfirmDialog(null, panel, "Send Money", JOptionPane.OK_CANCEL_OPTION);
        List<String> arrayWithParameters;
        if(result == JOptionPane.OK_OPTION){

            arrayWithParameters = getStringFromFields(panel);
            //Client client1 = ClientsController.findClientByUser(User.getCurrentUser());
            Client client2 = ClientsController.findByEmail(arrayWithParameters.get(2));
            if(isDataCorrect(arrayWithParameters)){
                if(!isReceiverDifferent(client1, client2)){
                    System.out.println("You try to send yourself money");
                    JOptionPane.showMessageDialog(null, "Operation failed\nYou try to send yourself money");
                }else{
                    System.out.println("Correct");
                    //getStringFromFields(panel); duplicate line
                    String keyCrypto = arrayWithParameters.get(0);
                    double amount = Double.parseDouble(arrayWithParameters.get(1));
                    ClientsController.sendFromTo(client1.getEmail(), client2.getEmail(), keyCrypto, amount);
                    JOptionPane.showMessageDialog(null, "Operation successful!");
                }
            }else {
                System.out.println("Problem with data entered");
                JOptionPane.showMessageDialog(null, "Bad input!");
            }
        }
    }
    public JPanel createSendPanel(){
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        JLabel label = new JLabel("Currency: ");
        JComboBox<Cryptocurrency> cryptoComboBox = new JComboBox<>();
        cryptoComboBox.addItem(Cryptocurrency.BTC);
        cryptoComboBox.addItem(Cryptocurrency.ETH);
        cryptoComboBox.addItem(Cryptocurrency.ADA);
        cryptoComboBox.addItem(Cryptocurrency.USDT);
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

    /**
     * Check if sender and receiver are different people
     * @param receiver
     * @param sender
     * @return - true if they are different
     */
    public boolean isReceiverDifferent(Client receiver, Client sender){
        return !receiver.getPesel().equals(sender.getPesel());
    }
    public boolean isDataCorrect(List<String> array){
        //we need to check if someone has crypto to send
        String amountToSend = array.get(1);
        String receiverEmail = array.get(2);
        //TODO need wallet to verify if user has money to send

        //now we need to check if receiver exists
        if(ClientsController.findByEmail(receiverEmail) == null){
            System.out.println("Someone doesn't exists");
            return false;
        }else{
            return true;
        }
    }
}
