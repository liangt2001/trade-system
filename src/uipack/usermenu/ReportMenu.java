package uipack.usermenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.Trade;
import entitypack.TradingUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A UI class to display the ability for users to report trades
 */
public class ReportMenu {
    private JTextPane reportTextPane;
    private JButton backButton;
    private JButton reportButton;
    private JPanel mainPanel;
    private JLabel reportLabel;

    /**
     * Constructs the trade report menu
     * @param useCases the user case grouper
     * @param cpg the controller presenter grouper
     * @param username the current user's username
     * @param frame the main window displayed to the trading user of the program
     * @param trade the trade being reported
     */
    public ReportMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg,
                      String username, JFrame frame, Trade trade) {

        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        backButton.setText(cpg.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 0));
        reportButton.setText(cpg.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 14));
        reportLabel.setText(cpg.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 16));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ConfirmTradeMenu(useCases, cpg, username, frame, trade);
            }
        });
        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String reportText = reportTextPane.getText();
                cpg.tradingUserActions.messageAdmin(username, reportText, useCases.adminUser);
                TradingUser user1 = (TradingUser) useCases.userManager.searchUser(trade.getUsername1());
                TradingUser user2 = (TradingUser) useCases.userManager.searchUser(trade.getUsername2());
                useCases.tradeCreator.afterReportTrade(useCases.userManager, trade, user1, user2);
                JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 15));
                new TradingUserActionsMenu(useCases, cpg, username, frame);
            }
        });
    }
}
