package code.classes;

import code.classes.enums.Cryptocurrency;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class LineChart {

    private final String chartTitle;

    public LineChart(String chartTitle) {
        this.chartTitle = chartTitle;
    }

    /**
     * Creating line chart panel  with specific data
     * @param selectedItemFromComboBox - selectedCryptocurrency
     * @return - chart panel
     */
    public JPanel createChartPanel(Cryptocurrency selectedItemFromComboBox){
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Day",
                "Value",
                createDataSet(selectedItemFromComboBox),
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new Dimension(800, 270));
        return chartPanel;
    }

    /**
     * Creating data set for chart panel with crypto values and date
     * @param selectedItemFromComboBox - selectedCryptocurrency
     * @return - dataset
     */
    private DefaultCategoryDataset createDataSet(Cryptocurrency selectedItemFromComboBox){
        ArrayList<Double> cryptoRates = new ArrayList<>();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        switch (selectedItemFromComboBox){

            case BTC :
                cryptoRates = CryptoPrices.getCryptoRatesFromWeek(Cryptocurrency.BTC);
                dataset.addValue(cryptoRates.get(0), "Bitcoin Value", LocalDate.now().minusDays(6));
                dataset.addValue(cryptoRates.get(1), "Bitcoin Value", LocalDate.now().minusDays(5));
                dataset.addValue(cryptoRates.get(2), "Bitcoin Value", LocalDate.now().minusDays(4));
                dataset.addValue(cryptoRates.get(3), "Bitcoin Value", LocalDate.now().minusDays(3));
                dataset.addValue(cryptoRates.get(4), "Bitcoin Value", LocalDate.now().minusDays(2));
                dataset.addValue(cryptoRates.get(5), "Bitcoin Value", LocalDate.now().minusDays(1));
                dataset.addValue(CryptoPrices.getCryptoRateFromProgram(Cryptocurrency.BTC), "Bitcoin Value", LocalDate.now());
                break;
            case ETH:
                cryptoRates = CryptoPrices.getCryptoRatesFromWeek(Cryptocurrency.ETH);
                dataset.addValue(cryptoRates.get(0), "Ethereum Value", LocalDate.now().minusDays(6));
                dataset.addValue(cryptoRates.get(1), "Ethereum Value", LocalDate.now().minusDays(5));
                dataset.addValue(cryptoRates.get(2), "Ethereum Value", LocalDate.now().minusDays(4));
                dataset.addValue(cryptoRates.get(3), "Ethereum Value", LocalDate.now().minusDays(3));
                dataset.addValue(cryptoRates.get(4), "Ethereum Value", LocalDate.now().minusDays(2));
                dataset.addValue(cryptoRates.get(5), "Ethereum Value", LocalDate.now().minusDays(1));

                dataset.addValue(CryptoPrices.getCryptoRateFromProgram(Cryptocurrency.ETH), "Ethereum Value", LocalDate.now());
                break;
            case ADA:
                cryptoRates = CryptoPrices.getCryptoRatesFromWeek(Cryptocurrency.ADA);
                dataset.addValue(cryptoRates.get(0), "Cardano Value", LocalDate.now().minusDays(6));
                dataset.addValue(cryptoRates.get(1), "Cardano Value", LocalDate.now().minusDays(5));
                dataset.addValue(cryptoRates.get(2), "Cardano Value", LocalDate.now().minusDays(4));
                dataset.addValue(cryptoRates.get(3), "Cardano Value", LocalDate.now().minusDays(3));
                dataset.addValue(cryptoRates.get(4), "Cardano Value", LocalDate.now().minusDays(2));
                dataset.addValue(cryptoRates.get(5), "Cardano Value", LocalDate.now().minusDays(1));

                dataset.addValue(CryptoPrices.getCryptoRateFromProgram(Cryptocurrency.ADA), "Cardano Value", LocalDate.now());
                break;
            case USDT:
                cryptoRates = CryptoPrices.getCryptoRatesFromWeek(Cryptocurrency.USDT);

                dataset.addValue(cryptoRates.get(0), "Tether Value", LocalDate.now().minusDays(6));
                dataset.addValue(cryptoRates.get(1), "Tether Value", LocalDate.now().minusDays(5));
                dataset.addValue(cryptoRates.get(2), "Tether Value", LocalDate.now().minusDays(4));
                dataset.addValue(cryptoRates.get(3), "Tether Value", LocalDate.now().minusDays(3));
                dataset.addValue(cryptoRates.get(4), "Tether Value", LocalDate.now().minusDays(2));
                dataset.addValue(cryptoRates.get(5), "Tether Value", LocalDate.now().minusDays(1));

                dataset.addValue(CryptoPrices.getCryptoRateFromProgram(Cryptocurrency.USDT), "Tether Value", LocalDate.now());
                break;
            default:
                System.out.println("Problem with cryptocurrency as argument");
        }
        return dataset;
    }

}
