package code.classes;

import code.classes.enums.Cryptocurrency;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import javax.swing.*;
import java.awt.*;

public class PieChart {
    private final String chartTitle;

    public PieChart(String chartTitle) {
        this.chartTitle = chartTitle;
    }

    /**
     * Creating panel with pie chart
     * @return - pie chart panel
     */
    public JPanel createChartPanel() {
        PieDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 270));
        return chartPanel;
    }

    private PieDataset createDataset() {

        Client client = ClientsController.findClientByUser(User.getCurrentUser());

        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue(Cryptocurrency.BTC, client.getWallet().get("BTC")*CryptoPrices.getCryptoRateFromProgram(Cryptocurrency.BTC));
        result.setValue(Cryptocurrency.ETH, client.getWallet().get("ETH")*CryptoPrices.getCryptoRateFromProgram(Cryptocurrency.ETH));
        result.setValue(Cryptocurrency.ADA, client.getWallet().get("ADA") * CryptoPrices.getCryptoRateFromProgram(Cryptocurrency.ADA));
        result.setValue(Cryptocurrency.USDT, client.getWallet().get("USDT") * CryptoPrices.getCryptoRateFromProgram(Cryptocurrency.USDT));
        return result;
    }

    private JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart3D(
                chartTitle,
                dataset,
                true,
                true,
                false
        );

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;
    }
}