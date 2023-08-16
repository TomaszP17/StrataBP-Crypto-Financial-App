import javax.swing.*;
import java.awt.*;
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

    public SecondWindow(FirstWindow firstWindow) {

        this.firstWindow = firstWindow;
        limitsButton.addActionListener(new LimitButtonListener());
        sendButton.addActionListener(new SendButtonListener());
        System.out.println("sadasdas");
        logOutButton.addActionListener(new LogOutButtonListener(firstWindow));
        List<String> array = new ArrayList<>();


        array.add(Cryptocurrency.BTC.toString() + "14" );
        array.add("ETH" + "2" );

        centerList.setListData(array.toArray());



        };





    public JPanel getMainPanel(){
        return panel1;
    }
    public void setCurrentUser(){

            userNameLabel.setText(User.getCurrentUser().getName());

    }
    public void showClientWallet(Client client){

        List<String> array = new ArrayList<>();


        array.add(Cryptocurrency.BTC.toString() + " -> " + client.wallet.get("BTC").toString());
        array.add(Cryptocurrency.ETH.toString() + " -> " + client.wallet.get("ETH").toString());
        array.add(Cryptocurrency.ADA.toString() + " -> " + client.wallet.get("ADA").toString());


        centerList.setListData(array.toArray());


    }

}
