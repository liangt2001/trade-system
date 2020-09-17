package uipack.usermenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.Item;
import entitypack.TradingUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A UI class for users to view their statistics
 */
public class ViewUserStatsMenu {
    private JButton backButton;
    private JPanel mainPanel;
    private JLabel numberBorrowedLabel;
    private JLabel numberBorrowedAmount;
    private JLabel numberLentLabel;
    private JLabel numberLentAmount;
    private JLabel frozenStatusLabel;
    private JLabel frozenStatusBool;
    private JLabel numIncompleteLabel;
    private JLabel numIncompleteAmount;
    private JLabel weeklyTransactionsLabel;
    private JLabel weeklyTransactionsAmount;
    private JLabel mostRecentlyTradedItemsLabel;
    private JLabel mostRecentlyTradedItemsAmount;
    private JLabel mostFrequentTradingPartnersLabel;
    private JLabel mostFrequentTradingPartnersValue;

    /**
     * Constructs the interface to view user stats
     * @param useCases the user case grouper
     * @param cpg the controller presenter grouper
     * @param username the current user's username
     * @param frame the main window displayed to the trading user of the program
     */
    public ViewUserStatsMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg,
                             String username, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        numberBorrowedLabel.setText(cpg.menuPresenter.getText(Frame.VIEWUSERSTATSMENU,0));
        numberLentLabel.setText(cpg.menuPresenter.getText(Frame.VIEWUSERSTATSMENU,1));
        frozenStatusLabel.setText(cpg.menuPresenter.getText(Frame.VIEWUSERSTATSMENU,2));
        numIncompleteLabel.setText(cpg.menuPresenter.getText(Frame.VIEWUSERSTATSMENU,3));
        weeklyTransactionsLabel.setText(cpg.menuPresenter.getText(Frame.VIEWUSERSTATSMENU,4));
        mostRecentlyTradedItemsLabel.setText(cpg.menuPresenter.getText(Frame.VIEWUSERSTATSMENU,5));
        mostFrequentTradingPartnersLabel.setText(cpg.menuPresenter.getText(Frame.VIEWUSERSTATSMENU,6));


        TradingUser user = (TradingUser) useCases.userManager.searchUser(username);
        numberBorrowedAmount.setText(String.valueOf(user.getNumBorrowed()));
        numberLentAmount.setText(String.valueOf(user.getNumLent()));
        if (user.getFrozen()) {
            frozenStatusBool.setText(cpg.menuPresenter.getText(Frame.VIEWUSERSTATSMENU,7));
        }
        else {
            frozenStatusBool.setText(cpg.menuPresenter.getText(Frame.VIEWUSERSTATSMENU,8));
        }

        numIncompleteAmount.setText(String.valueOf(user.getNumIncompleteTrades()));
        weeklyTransactionsAmount.setText(String.valueOf(useCases.tradeCreator.getTradeHistories()
                .getNumTradesThisWeek(username)));

        // last two stats are lists and need to be built as strings
        StringBuilder recentItems = new StringBuilder();
        for (Item item : useCases.tradeCreator.getTradeHistories()
                .getNRecentItems(useCases.itemManager, username, 3)) {
            String item_string = item.getName() + " ";
            recentItems.append(item_string);
        }
        mostRecentlyTradedItemsAmount.setText(recentItems.toString());

        StringBuilder frequentPartners = new StringBuilder();
        for (String partner : useCases.tradeCreator.getTradeHistories()
                .getTopNTradingPartners(username, 3)) {
            String partner_string = partner + " ";
            frequentPartners.append(partner_string);
        }
        mostFrequentTradingPartnersValue.setText(frequentPartners.toString());

        // if recentItmes or frequentPartners is null
        if (useCases.tradeCreator.getTradeHistories()
                .getNRecentItems(useCases.itemManager, username, 3).isEmpty()) {
            mostRecentlyTradedItemsAmount.setText(cpg.menuPresenter.getText(Frame.VIEWUSERSTATSMENU,9));
        }
        if (useCases.tradeCreator.getTradeHistories()
                .getTopNTradingPartners(username, 3).isEmpty()) {
            mostFrequentTradingPartnersValue.setText(cpg.menuPresenter.getText(Frame.VIEWUSERSTATSMENU,9));
        }


        backButton.setText(cpg.menuPresenter.getText(Frame.VIEWUSERSTATSMENU,10));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new TradingUserActionsMenu(useCases, cpg, username, frame);
            }
        });
    }
}
