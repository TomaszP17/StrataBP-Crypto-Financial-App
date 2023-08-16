import javax.swing.*;

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

    public SecondWindow() {

        limitsButton.addActionListener(new LimitButtonListener());
        sendButton.addActionListener(new SendButtonListener());
        System.out.println("sadasdas");




    }
    public JPanel getMainPanel(){
        return panel1;
    }
    public void setCurrentUser(){

            userNameLabel.setText(User.getCurrentUser().getName());

    }
}
