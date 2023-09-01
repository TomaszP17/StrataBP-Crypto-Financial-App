package code.classes;

import code.listeners.BackButtonListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.awt.*;

public class Market {
    private JPanel panel1;
    private JLabel upperLabel;
    private JPanel centerPanel;
    private JPanel buttonsPanel;
    private JButton buyButton;
    private JButton sellButton;
    private JButton refreshTimeButton;
    private JButton backButton;
    private JPanel chartPanel;
    private SecondWindow secondWindow;
    public Market(SecondWindow secondWindow) {
        this.secondWindow = secondWindow;
        backButton.addActionListener(new BackButtonListener(secondWindow));
        // Tworzenie i dodawanie wykresu do panelu chartPanel
        PieChart pieChart = new PieChart("Chart Title"); // Zmodyfikuj tytuł według swoich potrzeb
        JPanel chartPanel = pieChart.createChartPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270)); // Ustaw odpowiednie wymiary
        centerPanel.add(chartPanel);
    }

    public JPanel returnMainPanel() {
        return panel1;
    }
}
