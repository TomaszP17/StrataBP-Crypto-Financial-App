import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminSearchButtonListener implements ActionListener {
    private final TransactionHistoryWindow historyWindow;

    public AdminSearchButtonListener(TransactionHistoryWindow historyWindow) {
        this.historyWindow = historyWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel panel = createSearchPanel();
        int result = JOptionPane.showConfirmDialog(historyWindow.getMainPanel(), panel, "Title", JOptionPane.OK_CANCEL_OPTION);
    }
    public JPanel createSearchPanel(){
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
}
