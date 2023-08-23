import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DonateWindow extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel upperLabel;
    private JComboBox comboBox;
    private JTextField amountTextField;
    private JPasswordField passwordField;
    private JLabel currencyLabel;
    private JLabel amountLabel;
    private JLabel enterPasswordLabel;
    private JPanel centerPanel;
    private JPanel optionPanel;
    private JPanel horizontalPanel;
    private SecondWindow secondWindow;
    private final Client client = ClientsController.findClientByUser(User.getCurrentUser());

    public DonateWindow(SecondWindow secondWindow) {
        this.secondWindow = secondWindow;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Donate");
        addItemsToComboBox();
        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                                            JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * Doing some things when user clicked OK button
     * array.get(0) - password
     * array.get(1) - crypto
     * array.get(2) - amount
     */
    private void onOK() {
        System.out.println("Clicked OK");
        // add your code here
        //here we need get data from fields and do some functions
        List<String> array = getStringFromFields();
        //now we need to check if fields are correct
        if(isDataCorrect(array)) {
            //now we need to add funds to account
            String stringAmount = array.get(2);
            double amount = Double.parseDouble(stringAmount);

            ClientsController.donateMoney(client.getEmail(), array.get(1).toString(), amount);
            System.out.println("Donated money to user: " + client.getEmail() + " currency: " + array.get(1).toString()
                    + " amount: " + amount);
            secondWindow.updateClientWalletView(client);
            //here if all operations were successful we show window with confirmation about that
            JOptionPane.showMessageDialog(this, "Operation successful!");
        }else {
            JOptionPane.showMessageDialog(this, "Operation failed!");
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        System.out.println("Clicked CANCEL");
        dispose();
    }

    /**
     * Get all data from fields which client eddited
     * @return - array with data
     */
    public List<String> getStringFromFields() {
        Component[] components = contentPane.getComponents();
        Component[] components1 = centerPanel.getComponents();
        Component[] allComponents = new Component[components.length + components1.length];
        System.arraycopy(components, 0, allComponents, 0, components.length);
        System.arraycopy(components1, 0, allComponents, components.length, components1.length);

        List<String> array = new ArrayList<>();
        for (Component x : allComponents) {
            if (x instanceof JTextField) {
                String rowData = ((JTextField) x).getText();
                array.add(rowData);
            } else if (x instanceof JComboBox<?>) {
                JComboBox<Cryptocurrency> comboBox = (JComboBox<Cryptocurrency>) x;
                String data = ((Cryptocurrency) comboBox.getSelectedItem()).getKey();
                array.add(data);
            }
        }
        System.out.println(array);
        return array;
    }

    /**
     * Add items to ComboBox with cryptos
     */
    public void addItemsToComboBox(){
        comboBox.addItem(Cryptocurrency.BTC);
        comboBox.addItem(Cryptocurrency.ETH);
        comboBox.addItem(Cryptocurrency.ADA);
    }

    /**
     * Check if data entered by user are correct
     * @param array - array with data from fields
     * @return - true if correct or false if incorrect
     */
    public boolean isDataCorrect(List<String> array) {
        String password = array.get(0);
        String crypto = array.get(1);
        String amount = array.get(2);

        if (password.equals(client.getPassword())) {
            if (crypto != null) {
                try{
                    Double.parseDouble(amount);
                    return true;
                }catch (Exception e){
                    System.out.println("Problem with parsing");
                }
            }
        }
        return false;
    }
}
