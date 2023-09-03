package code.classes;

import code.listeners.*;

import javax.swing.*;
import java.awt.*;

public class SecondWindow {
    private JPanel panel1;
    private JLabel upperLabel;
    private JList centerList;
    private JButton marketButton;
    private JButton sendButton;
    private JButton historyButton;
    private JButton userPanelButton;
    private JButton limitsButton;
    private JButton donateButton;
    private JLabel bottomLabel;
    private JButton logOutButton;
    private JLabel userNameLabel;
    private JLabel totalValueLabel;
    private JLabel nicknameLabel;
    private JPanel chartCenterPanel;
    private final FirstWindow firstWindow;
    private UserInfoPanel userInfoPanel;
    public SecondWindow(FirstWindow firstWindow) {
        this.firstWindow = firstWindow;

        limitsButton.addActionListener(new LimitButtonListener());
        sendButton.addActionListener(new SendButtonListener(this));
        logOutButton.addActionListener(new LogOutButtonListener(firstWindow));
        userPanelButton.addActionListener(new UserPanelButtonListener(this));
        donateButton.addActionListener(new DonateButtonListener(this));
        historyButton.addActionListener(new HistoryButtonListener(this));
        marketButton.addActionListener(new MarketButtonListener(this));
        CryptoPrices.setAllCryptoRates();
        PieChart pieChart = new PieChart("Wallet Value: " + ClientsController.findClientByUser(User.getCurrentUser()).getWalletValue() + "$"); // Zmodyfikuj tytuł według swoich potrzeb
        JPanel chartPanel = pieChart.createChartPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270)); // Ustaw odpowiednie wymiary
        chartCenterPanel.add(chartPanel);
        //TODO totalValueLabel.setText();

    }
    public JPanel getMainPanel(){
        return panel1;
    }

   public void setCurrentUser(){
           userNameLabel.setText(User.getCurrentUser().getName());
   }

    public void changeMainPanelToHistoryPanel(JPanel panel){
        firstWindow.changeWindow(panel);
        firstWindow.revalidate();
        firstWindow.repaint();
        firstWindow.pack();
    }
    public void changeMainPanelToSecondPanel(){
        firstWindow.changeWindow(panel1);
    }
    public void changeMainPanelToUserInfoPanel(JPanel panel){
        firstWindow.changeWindow(panel);
        firstWindow.revalidate();
        firstWindow.repaint();
        firstWindow.pack();
    }
}
