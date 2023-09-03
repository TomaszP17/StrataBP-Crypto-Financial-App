package code.classes;

import code.classes.enums.Cryptocurrency;
import code.listeners.BackButtonListener;
import code.listeners.MarketBuyButtonListener;
import code.listeners.MarketComboBoxListener;
import code.listeners.MarketSellButtonListener;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Market {
    private JPanel panel1;
    private JLabel upperLabel;
    private JPanel centerPanel;
    private JPanel buttonsPanel;
    private JButton buyButton;
    private JButton sellButton;
    private JButton refreshTimeButton;
    private JButton backButton;
    private JPanel chartCenterPanel;
    private JComboBox<Cryptocurrency> comboBox;
    private JLabel bottomLabel;
    private JLabel amountCryptoLabel;
    private final SecondWindow secondWindow;
    private Cryptocurrency actualCrypto;
    public Market(SecondWindow secondWindow) {
        this.secondWindow = secondWindow;
        addItemsToComboBox();
        setActualCrypto(Cryptocurrency.BTC);
        setBottomLabel();
        setAmountCryptoLabel();
        backButton.addActionListener(new BackButtonListener(secondWindow));
        comboBox.addActionListener(new MarketComboBoxListener(this));
        buyButton.addActionListener(new MarketBuyButtonListener(this));
        sellButton.addActionListener(new MarketSellButtonListener(this));

        LineChart lineChart = new LineChart("Last week " + getCryptoName(comboBox) + " chart");
        JPanel chartPanel = lineChart.createChartPanel(getSelectedItemFromComboBox(comboBox));
        chartCenterPanel.add(chartPanel);

    }

    public JPanel returnMainPanel() {
        return panel1;
    }
    private void setBottomLabel(){
        bottomLabel.setText("Last actualization: " + LocalDate.now() + " " +
                LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" +
                LocalTime.now().getSecond());
    }
    private void addItemsToComboBox(){
        comboBox.addItem(Cryptocurrency.BTC);
        comboBox.addItem(Cryptocurrency.ETH);
        comboBox.addItem(Cryptocurrency.ADA);
        comboBox.addItem(Cryptocurrency.USDT);
    }
    private Cryptocurrency getSelectedItemFromComboBox(JComboBox<Cryptocurrency> comboBox){
        return comboBox.getItemAt(0);
    }
    private String getCryptoName(JComboBox<Cryptocurrency> comboBox){
        return getSelectedItemFromComboBox(comboBox).getKey();
    }
    public JComboBox<Cryptocurrency> getComboBox(){
        return comboBox;
    }
    public void updateChart(JPanel newChartPanel){
        chartCenterPanel.removeAll();
        chartCenterPanel.add(newChartPanel);
    }
    public void setAmountCryptoLabel(){
        amountCryptoLabel.setText("You have: " +
                ClientsController.findClientByUser(User.getCurrentUser()).getWallet().get(getActualCrypto().getKey()) +
                " " +
                getActualCrypto().getKey());
    }
    public void setActualCrypto(Cryptocurrency cryptocurrency){
        actualCrypto = cryptocurrency;
    }
    public Cryptocurrency getActualCrypto(){
        return actualCrypto;
    }
    public JPanel getMainPanel(){
        return panel1;
    }
}
