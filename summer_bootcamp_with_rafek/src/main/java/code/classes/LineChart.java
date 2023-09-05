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
import java.time.LocalTime;

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
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        switch (selectedItemFromComboBox){
            case BTC :
                dataset.addValue(CryptoPrices.getCryptoRateFromProgram(Cryptocurrency.BTC) + 1000.0, "Bitcoin Value", LocalDate.now().minusDays(144));
                dataset.addValue(CryptoPrices.getCryptoRateFromProgram(Cryptocurrency.BTC) - 500.0, "Bitcoin Value", LocalTime.now().minusHours(120));
                dataset.addValue(CryptoPrices.getCryptoRateFromProgram(Cryptocurrency.BTC) + 700.0, "Bitcoin Value", LocalTime.now().minusHours(96));
                dataset.addValue(CryptoPrices.getCryptoRateFromProgram(Cryptocurrency.BTC) - 250.0, "Bitcoin Value", LocalTime.now().minusHours(72));
                dataset.addValue(CryptoPrices.getCryptoRateFromProgram(Cryptocurrency.BTC) - 2000.0, "Bitcoin Value", LocalTime.now().minusHours(48));
                dataset.addValue(CryptoPrices.getCryptoRateFromProgram(Cryptocurrency.BTC), "Bitcoin Value", LocalTime.now().minusHours(24).toString());
                dataset.addValue(CryptoPrices.getCryptoRateFromProgram(Cryptocurrency.BTC), "Bitcoin Value", LocalDate.now());
                break;
            case ETH:
                dataset.addValue(15, "schools", "01.09.2023");
                dataset.addValue(30, "schools", "02.09.2023");
                dataset.addValue(40, "schools", "03.09.2023");
                dataset.addValue(25, "schools", "04.09.2023");
                dataset.addValue(10, "schools", "05.09.2023");
                dataset.addValue(50, "schools", "06.09.2023");
                dataset.addValue(35, "schools", "07.09.2023");
                break;
            case ADA:
                dataset.addValue(15, "schools", "01.09.2023");
                dataset.addValue(30, "schools", "02.09.2023");
                dataset.addValue(40, "schools", "03.09.2023");
                dataset.addValue(25, "schools", "04.09.2023");
                dataset.addValue(10, "schools", "05.09.2023");
                dataset.addValue(50, "schools", "06.09.2023");
                dataset.addValue(35, "schools", "07.09.2023");
                break;
            case USDT:
                dataset.addValue(15, "schools", "01.09.2023");
                dataset.addValue(30, "schools", "02.09.2023");
                dataset.addValue(40, "schools", "03.09.2023");
                dataset.addValue(25, "schools", "04.09.2023");
                dataset.addValue(10, "schools", "05.09.2023");
                dataset.addValue(50, "schools", "06.09.2023");
                dataset.addValue(35, "schools", "07.09.2023");
                break;
            default:
                System.out.println("Problem with cryptocurrency as argument");
        }
        return dataset;
    }

}
