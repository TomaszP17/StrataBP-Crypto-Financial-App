import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AdminSearchButtonListener implements ActionListener {
    private final TransactionHistoryWindow historyWindow;

    public AdminSearchButtonListener(TransactionHistoryWindow historyWindow) {
        this.historyWindow = historyWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel panel = createSearchPanel();
        int result = JOptionPane.showConfirmDialog(historyWindow.getMainPanel(), panel, "Title", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION){
            List<String> arrayWithDataFromFields = getDataFromFields(panel);
            String param = arrayWithDataFromFields.get(0);
            String searchedValue = arrayWithDataFromFields.get(1);

            //search value by param
            updateTableModel(param, searchedValue);
        }
    }
    private JPanel createSearchPanel(){
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        panel.add(new JLabel("Choose param: "));
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("ID");
        comboBox.addItem("FROM");
        comboBox.addItem("TO");
        comboBox.addItem("CRYPTO");
        comboBox.addItem("AMOUNT");
        comboBox.addItem("DATE");
        panel.add(comboBox);
        panel.add(new JLabel("Searched value: "));
        panel.add(new JTextField(10));

        return panel;
    }

    private List<String> getDataFromFields(JPanel panel) {
        Component[] components = panel.getComponents();
        List<String> array = new ArrayList<>();
        for(Component x : components){
            if(x instanceof JTextField){
                String rowData = ((JTextField) x).getText();
                array.add(rowData);
            } else if (x instanceof JComboBox<?>) {
                JComboBox<String> comboBox = (JComboBox<String>) x;
                String data = comboBox.getSelectedItem().toString();
                array.add(data);
            }
        }
        System.out.println(array);
        return array;
    }

    /**
     * Searching data by entered param
     * @param param - column name
     * @param searchedValue - user entered value
     * @return - array with similar rows
     */
    private List<Transaction> searchForValue(String param, String searchedValue){
        List<Transaction> transactions = TransactionController.getTransactionList();
        List<Transaction> arrayWithResult = new ArrayList<>();
        for(Transaction transaction : transactions){
            switch (param){
                case "ID":
                    String transactionIdString = String.valueOf(transaction.getId());
                    if(searchedValue.equals(transactionIdString)){
                        arrayWithResult.add(transaction);
                    }
                    break;
                case "FROM":
                    if(searchedValue.equals(transaction.getFrom().getEmail())){
                        arrayWithResult.add(transaction);
                    }
                    break;
                case "TO":
                    if(searchedValue.equals(transaction.getTo().getEmail())){
                        arrayWithResult.add(transaction);
                    }
                    break;
                case "CRYPTO":
                    if(searchedValue.equals(transaction.getCryptocurrency().getKey())){
                        arrayWithResult.add(transaction);
                    }
                    break;
                case "AMOUNT":
                    if(searchedValue.equals(String.valueOf(transaction.getAmount()))){
                        arrayWithResult.add(transaction);
                    }
                    break;
                case "DATE":
                    if(searchedValue.equals(transaction.getDate())){
                        arrayWithResult.add(transaction);
                    }
                    break;
                default:
                    System.out.println("Wrong param");
            }
        }
        System.out.println(arrayWithResult);
        return arrayWithResult;
    }
    //set new tableModel
    private void updateTableModel(String param, String searchedValue) {
        String[] columnNames = historyWindow.getColumnNames();
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        List<Transaction> searchResults = searchForValue(param, searchedValue);
        TransactionHistoryWindow.addUserTransactions(tableModel, searchResults);
        historyWindow.updateTable(tableModel);
    }

}
