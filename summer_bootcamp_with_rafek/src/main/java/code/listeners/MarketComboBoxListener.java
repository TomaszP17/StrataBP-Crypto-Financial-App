package code.listeners;

import code.classes.LineChart;
import code.classes.Market;
import code.classes.enums.Cryptocurrency;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarketComboBoxListener implements ActionListener {
    private final Market market;
    private JComboBox<Cryptocurrency> comboBox;
    private Cryptocurrency selectedCrypto;
    public MarketComboBoxListener(Market market) {
        this.market = market;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        comboBox = market.getComboBox();
        selectedCrypto = getSelectedItem(comboBox);
        System.out.println("Selected: " + selectedCrypto.getKey());
        changeChartPanelInMarket();
    }
    private Cryptocurrency getSelectedItem(JComboBox<Cryptocurrency> comboBox){
        return (Cryptocurrency) comboBox.getSelectedItem();
    }
    private void changeChartPanelInMarket(){
        LineChart lineChart = new LineChart("Last week " + selectedCrypto + " chart");
        JPanel chartPanel = lineChart.createChartPanel(selectedCrypto);

        market.updateChart(chartPanel);
        market.setActualCrypto(selectedCrypto);
        market.setAmountCryptoLabel();
    }
}
