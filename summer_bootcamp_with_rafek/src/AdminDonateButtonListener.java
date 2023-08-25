import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDonateButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        AdminDonateDialog adminDonateDialog = new AdminDonateDialog();
        adminDonateDialog.setTitle("Donate Money To User");
        String imageUrl = "./src/res/admin.png";
        ImageIcon icon = new ImageIcon(imageUrl);
        adminDonateDialog.setIconImage(icon.getImage());
        adminDonateDialog.pack();
        adminDonateDialog.setVisible(true);
    }
}
