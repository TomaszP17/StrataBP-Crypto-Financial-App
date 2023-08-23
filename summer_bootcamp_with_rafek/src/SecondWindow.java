import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SecondWindow {
    private JPanel panel1;
    private JLabel upperLabel;
    private JList centerList;
    private JButton cou;
    private JButton sendButton;
    private JButton historyButton;
    private JButton userPanelButton;
    private JButton limitsButton;
    private JButton donateButton;
    private JLabel bottomLabel;
    private JButton logOutButton;
    private JLabel userNameLabel;
    private final FirstWindow firstWindow;
    private UserInfoPanel userInfoPanel;
    public SecondWindow(FirstWindow firstWindow) {
        this.firstWindow = firstWindow;
        userInfoPanel = new UserInfoPanel(firstWindow, this); //

        limitsButton.addActionListener(new LimitButtonListener());
        sendButton.addActionListener(new SendButtonListener());
        logOutButton.addActionListener(new LogOutButtonListener(firstWindow));
        userPanelButton.addActionListener(new UserPanelButtonListener(firstWindow, userInfoPanel));
        donateButton.addActionListener(new DonateButtonListener(this));
        historyButton.addActionListener(new HistoryButtonListener(this));
        List<String> array = new ArrayList<>();

        centerList.setListData(array.toArray());
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

        array.add(Cryptocurrency.BTC.toString() + " -> " + client.getWallet().get("BTC").toString() + " -> $" + CryptoPrices.getAllBtcUserInUSD(client));
        array.add(Cryptocurrency.ETH.toString() + " -> " + client.getWallet().get("ETH").toString()+ " -> $"+ CryptoPrices.getAllEthUserInUSD(client) );
        array.add(Cryptocurrency.ADA.toString() + " -> " + client.getWallet().get("ADA").toString()+ " -> $" + CryptoPrices.getAllAdaUserInUSD(client) );
        array.add(Cryptocurrency.USDT.toString() + " -> " + client.getWallet().get("Tether USD").toString());
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
}
