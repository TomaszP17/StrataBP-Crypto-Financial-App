import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
    private JLabel walletValueLabel;
    private JLabel nicknameLabel;
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
        List<String> array = new ArrayList<>();

        centerList.setListData(array.toArray());
        //TODO totalValueLabel.setText();

    }
    public JPanel getMainPanel(){
        return panel1;
    }

    /**
     * todo ?
     */
    public void setCurrentUser(){
            userNameLabel.setText(User.getCurrentUser().getName());
    }

    /**
     * TODO ?
     * @param client
     */
    public void showClientWallet(Client client){

        List<String> array = new ArrayList<>();

        array.add(Cryptocurrency.BTC + ": " + client.getWallet().get("BTC").toString() + " -> $" + CryptoPrices.getAllBtcUserInUSD(client));
        array.add(Cryptocurrency.ETH + ": " + client.getWallet().get("ETH").toString()+ " -> $"+ CryptoPrices.getAllEthUserInUSD(client) );
        array.add(Cryptocurrency.ADA + ": " + client.getWallet().get("ADA").toString()+ " -> $" + CryptoPrices.getAllAdaUserInUSD(client) );
        array.add(Cryptocurrency.USDT + ": " + client.getWallet().get("Tether USD").toString());
        centerList.setListData(array.toArray());
    }

    /**
     * TODO ?
     * @param client
     */
    public void updateClientWalletView(Client client){
        showClientWallet(client);
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
