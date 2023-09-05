package code.listeners;

import code.classes.*;
import code.classes.enums.Cryptocurrency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SendButtonListener implements ActionListener {
    private final SecondWindow secondWindow;

    public SendButtonListener(SecondWindow secondWindow) {
        this.secondWindow = secondWindow;
    }

    //private final code.classes.Client client1 = code.classes.ClientsController.findClientByUser(code.classes.User.getCurrentUser());
    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel panel = createSendPanel();
        int result = JOptionPane.showConfirmDialog(null, panel, "Send Money", JOptionPane.OK_CANCEL_OPTION);
        List<String> arrayWithParameters;
        if(result == JOptionPane.OK_OPTION){

            arrayWithParameters = getStringFromFields(panel);

            Client client1 = ClientsController.findClientByUser(User.getCurrentUser());
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
                    //todo create new transaction
                    Cryptocurrency crypto = Cryptocurrency.valueOf(keyCrypto);
                    TransactionController.addNewTransaction(
                            ClientsController.findByEmail(client1.getEmail()),
                            ClientsController.findByEmail(client2.getEmail()),
                            crypto,
                            amount);
                   //secondWindow.updateClientWalletView(client1);
                    secondWindow.updatePieChart();
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
    public boolean isDataCorrect(List<String> array) {
        //we need to check if someone has crypto to send
        String crypto = array.get(0);
        String amountToSend = array.get(1);
        String receiverEmail = array.get(2);
        //TODO need wallet to verify if user has money to send
        Client currClient = ClientsController.findClientByUser(User.getCurrentUser());
        Map<String, Double> clientWallet = currClient.getWallet();

        if (clientWallet.containsKey(crypto)) {
            Double userAmountOnWallet = clientWallet.get(crypto);
            Double amountToSendDouble = Double.parseDouble(amountToSend);
            if (userAmountOnWallet < amountToSendDouble) {
                System.out.println("Incorrect amount to send");
                return false;
            } else {
                System.out.println("Good amount");
                if (ClientsController.findByEmail(receiverEmail) == null) {
                    System.out.println("Someone doesn't exists");
                    return false;
                } else {
                    return true;
                }
            }
        }else{
            System.out.println("Crypto not found!");
            return  false;
        }

    }
}
