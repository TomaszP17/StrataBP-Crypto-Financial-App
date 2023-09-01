import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public interface PanelListener {
    List<String> getDataFromFields(JPanel panel);

    default void wyswietlXD(String email){
        System.out.println("xd");
    }
}
