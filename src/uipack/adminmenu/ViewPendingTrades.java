package uipack.adminmenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.Trade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A UI class that displays all pending trades and allows the administrative user to select one
 */
public class ViewPendingTrades {
    private JComboBox pendingTrades;
    private JPanel mainPanel;
    private JButton confirmButton;
    private JButton backButton;
    private JLabel TradeID;

    /**
     * Constructs the interface to view all pending trades
     * @param useCases the use case grouper
     * @param controllerPresenterGrouper the controller presenter grouper
     * @param window the main window displayed to the administrative user of the program
     */
    public ViewPendingTrades(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper, JFrame window) {
        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        TradeID.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADMINVIEWTRADE, 0));
        confirmButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADMINVIEWTRADE, 1));
        backButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADMINVIEWTRADE, 2));


        for (Trade pendingTrade : useCases.tradeCreator.getPendingTrades()) {
            pendingTrades.addItem(pendingTrade.getTradeID());
        }

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewConfirmedTradeToUndo(useCases, controllerPresenterGrouper,
                        (Integer) pendingTrades.getSelectedItem(), window);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditTrade(useCases, controllerPresenterGrouper, window);
            }
        });
    }
}
