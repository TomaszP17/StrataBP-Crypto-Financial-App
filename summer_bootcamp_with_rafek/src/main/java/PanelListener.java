import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public interface PanelListener {

    /*JPanel changeToFirstWindow();
    JPanel changeToSecondWindow();
    JPanel changeToUserInfoPanel();*/
    /*default List<String> getDataFromFields(JPanel panel){
        Component[] components = panel.getComponents();
        List<String> array = new ArrayList<>();
        for(Component x : components){
            if(x instanceof JTextField){
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
    }*/
    List<String> getDataFromFields(JPanel panel);
}
