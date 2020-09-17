package uipack.usermenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.Trade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A UI class for users to view their pending trades
 */
public class ViewPendingTradesMenu {
    private JComboBox pendingTradesBox;
    private JButton backButton;
    private JButton selectItemButton;
    private JPanel mainPanel;
    private JScrollPane tradeInfoPane;

    /**
     * Constructs the interface to view pending trades
     * @param useCases the user case grouper
     * @param controllerPresenterGrouper the controller presenter grouper
     * @param username the current user's username
     * @param frame the main window displayed to the trading user of the program
     */
    public ViewPendingTradesMenu(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper,
                                 String username, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        backButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.VIEWPENDINGTRADESMENU, 0));
        selectItemButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.VIEWPENDINGTRADESMENU, 1));
        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;



        int n = 0;
        ArrayList<Trade> trades = controllerPresenterGrouper.tradingUserActions.searchPendingTradesUser(username,
                useCases.userManager, useCases.tradeCreator);
        for (Trade trade: trades){
            JLabel generatedLabel = new JLabel();
            generatedLabel.setText(controllerPresenterGrouper.menuPresenter.printTradeToString(useCases.itemManager,
                    trade));
            pendingTradesBox.addItem(trade.getTradeID());
            c.gridy = n;
            n++;
            content.add(generatedLabel, c);
        }

        tradeInfoPane.setViewportView(content);
        selectItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (pendingTradesBox.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(frame,
                            controllerPresenterGrouper.menuPresenter.getText(Frame.VIEWPENDINGTRADESMENU, 2));
                }else{
                    int tradeID = (int) pendingTradesBox.getSelectedItem();
                    Trade trade = useCases.tradeCreator.searchTrades(tradeID);
                    new ConfirmTradeMenu(useCases, controllerPresenterGrouper, username, frame, trade);
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
