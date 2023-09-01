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
    private String chartTitle;

    public PieChart(String chartTitle) {
        this.chartTitle = chartTitle;
    }

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
        result.setValue(Cryptocurrency.BTC, client.getWallet().get("BTC")*CryptoPrices.getBitcoinRate());
        result.setValue(Cryptocurrency.ETH, client.getWallet().get("ETH")*CryptoPrices.getEtherumRate());
        result.setValue(Cryptocurrency.ADA, client.getWallet().get("ADA") * CryptoPrices.getCardanoRate());
        result.setValue(Cryptocurrency.USDT, client.getWallet().get("USDT") * CryptoPrices.getTetherRate());
        return result;
    }

    private JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart3D(
                chartTitle,             // chart title
                dataset,                // data
                true,                   // include legend
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