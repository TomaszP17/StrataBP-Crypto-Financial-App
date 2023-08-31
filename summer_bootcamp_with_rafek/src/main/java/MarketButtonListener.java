import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarketButtonListener implements ActionListener {
    private final SecondWindow secondWindow;

    public MarketButtonListener(SecondWindow secondWindow) {
        this.secondWindow = secondWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Market market = new Market(secondWindow);
        secondWindow.changeMainPanelToHistoryPanel(market.returnMainPanel());
    }

}
