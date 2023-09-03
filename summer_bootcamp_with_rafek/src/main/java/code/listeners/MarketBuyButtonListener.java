package code.listeners;

import code.classes.*;
import code.classes.enums.Cryptocurrency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarketBuyButtonListener implements ActionListener {
    private final Market market;
    private final String amountUSDT = String.valueOf(ClientsController.findClientByUser(User.getCurrentUser()).getWallet().get(Cryptocurrency.USDT.getKey()));

    public MarketBuyButtonListener(Market market) {
        this.market = market;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel panel = createBuyPanel();
        int result = JOptionPane.showConfirmDialog(market.getMainPanel(), panel, "Buy crypto", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION){
            String userAnswer = getDataFromPanel(panel);
            if(hasUserEnoughToBuy(userAnswer)){
                //if user has enough
                //delete usdt from wallet
                Client client = ClientsController.findClientByUser(User.getCurrentUser());

                client.deleteCrypto(String.valueOf(Cryptocurrency.USDT), Double.parseDouble(userAnswer));
                System.out.println("Took money from account");
                //donate money to wallet
                double amountToAddToUserWallet = Double.parseDouble(userAnswer) / CryptoPrices.getCryptoRateFromProgram(market.getActualCrypto());
                ClientsController.donateMoney(client.getEmail(), market.getActualCrypto().getKey(),amountToAddToUserWallet);
            }else{
                System.out.println("show window user hasn't enough money to buy crypto");
            }
        }
    }

    private JPanel createBuyPanel(){
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel amountCryptoLabel = new JLabel("Current price: ");
        JLabel cryptoPriceLabel = new JLabel("[Price]");
        JLabel quantityLabel = new JLabel("Quantity: ");
        JTextField textField = new JTextField(10);
        JLabel usdtLabel = new JLabel("USDT in your account: ");
        JLabel usdtAmountLabel = new JLabel(amountUSDT);
        panel.add(amountCryptoLabel);
        panel.add(cryptoPriceLabel);
        panel.add(quantityLabel);
        panel.add(textField);
        panel.add(usdtLabel);
        panel.add(usdtAmountLabel);
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
    private boolean hasUserEnoughToBuy(String amount){
        double enteredAmountDouble = Double.parseDouble(amount);
        double useramountUSDT = Double.parseDouble(amountUSDT);
        if(enteredAmountDouble < useramountUSDT){
            System.out.println("User has less USDT than he want to buy");
            return true;
        }else {
            System.out.println("User has enough USDT");
            return false;
        }
    }
}
