package uipack.usermenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.TemporaryTrade;
import entitypack.Trade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A UI class for user's to view their active temporary trades
 */
public class ViewActiveTemporaryTradesMenu {
    private JComboBox pendingTradesBox;
    private JButton backButton;
    private JButton selectItemButton;
    private JPanel mainPanel;
    private JScrollPane tradeInfoPane;

    /**
     * Constructs the interface to view active temporary trades
     * @param useCases the user case grouper
     * @param controllerPresenterGrouper the controller presenter grouper
     * @param username the current user's username
     * @param frame the main window displayed to the trading user of the program
     */
    public ViewActiveTemporaryTradesMenu(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper,
                                 String username, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        backButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.VIEWPENDINGTRADESMENU, 0));
        selectItemButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.VIEWPENDINGTRADESMENU, 1));


        ArrayList<TemporaryTrade> trades = controllerPresenterGrouper.tradingUserActions.searchCurrentTempTradesUser(username,
                useCases.userManager, useCases.tradeCreator);
        for (Trade trade: trades){
            JLabel generatedLabel = new JLabel();
            generatedLabel.setText(controllerPresenterGrouper.menuPresenter.printTradeToString(useCases.itemManager,trade));
            pendingTradesBox.addItem(trade.getTradeID());
            tradeInfoPane.add(generatedLabel);
        }

        selectItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (pendingTradesBox.getSelectedIndex() == -1) {

                    JOptionPane.showMessageDialog(frame,
                            controllerPresenterGrouper.menuPresenter.getText(Frame.VIEWPENDINGTRADESMENU, 2));
                }else{
                    int tradeID = (int) pendingTradesBox.getSelectedItem();
                    TemporaryTrade temporaryTrade = (TemporaryTrade) controllerPresenterGrouper.tradingUserActions.searchTrade(useCases.tradeCreator, tradeID);
                    new ConfirmReExchangeMenu(useCases, controllerPresenterGrouper, username, frame, temporaryTrade);
                }

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new TradingUserActionsMenu(useCases, controllerPresenterGrouper, username, frame);
            }
        });
    }
}
