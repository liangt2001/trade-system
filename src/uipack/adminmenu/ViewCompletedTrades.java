package uipack.adminmenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.Trade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A UI class that allows the administrative user to view all complete trades and select one to view in details
 */
public class ViewCompletedTrades {
    private JComboBox completedTrades;
    private JPanel mainPanel;
    private JButton confirmButton;
    private JButton backButton;
    private JLabel TradeID;

    /**
     * Constructs the interface to view all complete trades
     * @param useCases the use case grouper
     * @param controllerPresenterGrouper the controller presenter grouper
     * @param window the main window displayed to the administrative user of the program
     */
    public ViewCompletedTrades(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper, JFrame window) {
        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        TradeID.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADMINVIEWTRADE, 0));
        confirmButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADMINVIEWTRADE, 1));
        backButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADMINVIEWTRADE, 2));


        for (Trade completedTrade : useCases.tradeCreator.getTradeHistories().getCompletedTrades())
            completedTrades.addItem(completedTrade.getTradeID());

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewCompletedToUndo(useCases, controllerPresenterGrouper,
                        (Integer) completedTrades.getSelectedItem(), window);
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
