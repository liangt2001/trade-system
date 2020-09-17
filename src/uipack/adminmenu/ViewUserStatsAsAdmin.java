package uipack.adminmenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.TradingUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A UI class that displays the stats of a User
 */
public class ViewUserStatsAsAdmin {
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

    /** Method which creates the menu and sets it on the frame
     *
     * @param useCases UseCaseGrouper object created on startup
     * @param cpg ControllerPresenterGrouper object created on startup
     * @param username username of the user to view
     * @param frame window that was created on startup.
     */
    public ViewUserStatsAsAdmin(UseCaseGrouper useCases, ControllerPresenterGrouper cpg,
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

        backButton.setText(cpg.menuPresenter.getText(Frame.VIEWUSERSTATSMENU,10));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ViewingUserAsAdmin(useCases, cpg, username, frame);
            }
        });
    }
}
