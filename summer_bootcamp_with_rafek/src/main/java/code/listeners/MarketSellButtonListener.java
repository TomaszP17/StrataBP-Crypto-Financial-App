package code.listeners;

import code.classes.*;
import code.classes.enums.Cryptocurrency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarketSellButtonListener implements ActionListener {
    private final Market market;

    public MarketSellButtonListener(Market market) {
        this.market = market;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel panel = createSellPanel();
        int result = JOptionPane.showConfirmDialog(market.getMainPanel(), panel, "Sell Crypto", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION){
            String userAnswer = getDataFromPanel(panel);
            if(hasUserEnough(userAnswer)){
                //delete crypto from wallet
                Client client = ClientsController.findClientByUser(User.getCurrentUser());
                client.deleteCrypto(market.getActualCrypto().getKey(), Double.parseDouble(userAnswer));
                //add usdt to wallet
                double amountToAdd = Double.parseDouble(userAnswer) * CryptoPrices.getCryptoRateFromProgram(market.getActualCrypto());
                ClientsController.donateMoney(client.getEmail(), Cryptocurrency.USDT.getKey(), amountToAdd);
            }else {
                System.out.println("User doesnt have enough");
            }
        }
    }
    private JPanel createSellPanel(){
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel amountCryptoLabel = new JLabel("Current price: ");
        JLabel cryptoPriceLabel = new JLabel(String.valueOf(CryptoPrices.getCryptoRateFromProgram(market.getActualCrypto())));
        JLabel quantityLabel = new JLabel("Quantity: ");
        JTextField textField = new JTextField(10);
        panel.add(amountCryptoLabel);
        panel.add(cryptoPriceLabel);
        panel.add(quantityLabel);
        panel.add(textField);

        return panel;
    }
    private String getDataFromPanel(JPanel panel) {
        Component[] components = panel.getComponents();
        String result = null;
        for (Component x : components) {
            if (x instanceof JTextField) {
                result = ((JTextField) x).getText();
            }
        }
        return result;
    }

    private boolean hasUserEnough(String userAmount){
        double enteredUserAmount = Double.parseDouble(userAmount);
        double userCryptoAmountInWallet = ClientsController.findClientByUser(User.getCurrentUser()).getWallet().get(market.getActualCrypto().getKey());
        return enteredUserAmount <= userCryptoAmountInWallet;
    }
}
