package uipack.usermenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.TradingUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A UI class to display the menu for trading users
 */
public class TradingUserActionsMenu {
    private JButton viewItemsAndWishlistButton;
    private JButton viewUserStatsButton;
    private JButton viewOtherUsersButton;
    private JButton viewPendingTradesButton;
    private JButton setActiveStatusButton;
    private JButton changeMetropolitanAreaButton;
    private JButton requestUnfreezeButton;
    private JPanel mainPanel;
    private JButton viewDeadTradesButton;
    private JButton viewPendingTradeRequestsButton;
    private JButton viewCompletedTradesButton;
    private JButton viewActiveTempTradesButton;
    private JButton viewMessagesButton;

    /**
     * Constructs the interface for a trading user's menu
     * @param useCases the user case grouper
     * @param cpg the controller presenter grouper
     * @param username the current user's username
     * @param frame the main window displayed to the trading user of the program
     */
    public TradingUserActionsMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, String username, JFrame frame){
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        viewItemsAndWishlistButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 0));
        viewOtherUsersButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 1));
        viewUserStatsButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 2));
        viewPendingTradesButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 3));
        setActiveStatusButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 4));
        changeMetropolitanAreaButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 5));
        requestUnfreezeButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 6));
        viewActiveTempTradesButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 13));
        viewDeadTradesButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 10));
        viewCompletedTradesButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 12));
        viewPendingTradeRequestsButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 11));
        viewMessagesButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 15));



        if (((TradingUser)useCases.userManager.searchUser(username)).getFrozen()){
            //Your account has been frozen.
            JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 9));
        }

        viewItemsAndWishlistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            new ViewItemsAndWishlistMenu(useCases, cpg, username,
                    frame);
            }
        });
        viewUserStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ViewUserStatsMenu(useCases, cpg, username, frame);
            }
        });
        viewOtherUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ViewAllOtherUsersMenu(useCases, cpg, username, frame,
                        true);
            }
        });
        viewPendingTradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ViewPendingTradesMenu(useCases, cpg, username, frame);
            }
        });
        setActiveStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new SetActiveStatusMenu(useCases, cpg, username, frame);
            }
        });
        changeMetropolitanAreaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new SetActiveCityMenu(useCases, cpg, username, frame);
            }
        });
        requestUnfreezeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(cpg.tradingUserActions.isUserFrozen(useCases.userManager, username)){
                    cpg.tradingUserActions.messageAdmin(username,
                            username + " " + cpg.menuPresenter.getText(Frame.ADMINFREEZE, 9), useCases.adminUser);
                    JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 7));
                }else {
                    JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU, 8));
                }
            }
        });
        viewDeadTradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ViewDeadTradesMenu(useCases, cpg, username, frame);
            }
        });
        viewPendingTradeRequestsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ViewTradeRequestsMenu(useCases, cpg, username, frame);
            }
        });
        viewActiveTempTradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ViewActiveTemporaryTradesMenu(useCases, cpg, username, frame);
            }
        });
        viewCompletedTradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ViewCompletedTradesMenu(useCases, cpg, username, frame);
            }
        });
        viewMessagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewMessagesMenu(useCases, cpg, username, frame);
            }
        });
    }
}
