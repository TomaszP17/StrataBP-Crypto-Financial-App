import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
    //private final Client client;

    public DonateWindow() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Donate");
        addItemsToComboBox();
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        System.out.println("Clicked OK");
        // add your code here
        //here we need get data from fields and do some functions
        List<String> array = getStringFromFields();
        //now we need to check if fields are correct

        //now we need to add funds to account
        String stringAmount = array.get(2);
        Double amount = Double.parseDouble(stringAmount);
        Client client = ClientsController.findClientByUser(User.getCurrentUser());
        ClientsController.sendFromTo(null, client.getEmail(), array.get(1).toString(),amount);
        System.out.println("Donated money to" + client.getEmail() + " currency: " + array.get(1).toString()
                            + " amount: " + amount);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        System.out.println("Clicked CANCEL");
        dispose();
    }

    public static void main(String[] args) {
        DonateWindow dialog = new DonateWindow();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
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
    public void addItemsToComboBox(){
        comboBox.addItem(Cryptocurrency.BTC);
        comboBox.addItem(Cryptocurrency.ETH);
        comboBox.addItem(Cryptocurrency.ADA);
    }
    public void addFundsToUser(){

    }
}
