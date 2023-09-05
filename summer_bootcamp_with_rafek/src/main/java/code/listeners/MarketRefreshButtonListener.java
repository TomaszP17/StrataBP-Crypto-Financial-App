package code.listeners;

import code.classes.CryptoPrices;
import code.classes.LineChart;
import code.classes.Market;
import code.classes.enums.Cryptocurrency;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarketRefreshButtonListener implements ActionListener {
    private final Market market;

    public MarketRefreshButtonListener(Market market) {
        this.market = market;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Cryptocurrency actualCrypto = market.getActualCrypto();
        changeChartPanelInMarket(actualCrypto);
    }
    private void changeChartPanelInMarket(Cryptocurrency actualCrypto){
        LineChart lineChart = new LineChart("Last week " + actualCrypto + " chart");
        CryptoPrices.getCryptoRate(actualCrypto);
        JPanel chartPanel = lineChart.createChartPanel(actualCrypto);
        market.updateChart(chartPanel);
    }
}
