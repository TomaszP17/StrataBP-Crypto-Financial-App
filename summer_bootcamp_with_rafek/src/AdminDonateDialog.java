import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDonateDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;
    private JPanel buttonsPanel;
    private JPanel bottomPanel;
    private JPanel centerPanel;

    public AdminDonateDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        addCryptoToComboBox(comboBox1);

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
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        List<String> arrayWithDataString = getDataFromFields(centerPanel);
        String crypto = arrayWithDataString.get(0);
        double amountDouble = parseDouble(arrayWithDataString.get(1));
        String receiverEmail = arrayWithDataString.get(2);
        ClientsController.donateMoney(receiverEmail, crypto, amountDouble);

        TransactionController.addNewTransaction(
                User.getCurrentUser(),
                ClientsController.findByEmail(receiverEmail),
                Cryptocurrency.valueOf(crypto),
                amountDouble
        );
        dispose();
    }


    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void addCryptoToComboBox(JComboBox<Cryptocurrency> comboBox){
        comboBox.addItem(Cryptocurrency.BTC);
        comboBox.addItem(Cryptocurrency.ETH);
        comboBox.addItem(Cryptocurrency.ADA);
        comboBox.addItem(Cryptocurrency.USDT);
    }
    private List<String> getDataFromFields(JPanel panel){
        Component[] components = panel.getComponents();
        List<String> array = new ArrayList<>();
        for (Component x : components) {
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
    private double parseDouble(String stringDouble){
        return Double.parseDouble(stringDouble);
    }
}
