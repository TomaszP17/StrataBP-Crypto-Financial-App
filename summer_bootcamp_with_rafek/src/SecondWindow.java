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
    private final FirstWindow firstWindow;

    public SecondWindow(FirstWindow firstWindow) {
        this.firstWindow = firstWindow;
        limitsButton.addActionListener(new LimitButtonListener());
        sendButton.addActionListener(new SendButtonListener());
        System.out.println("sadasdas");
        logOutButton.addActionListener(new LogOutButtonListener(firstWindow));
        };





    public JPanel getMainPanel(){
        return panel1;
    }
    public void setCurrentUser(){

            userNameLabel.setText(User.getCurrentUser().getName());

    }
}
