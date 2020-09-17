package uipack.adminmenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entitypack.Frame;

/**
 * A UI class that links the following action menus that can be done to the user being viewed at:
 *  - Send message
 *  - Remove items from the user's wishlist
 *  - Remove items from the user's inventory
 *  - Freeze user
 */
public class ViewingUserAsAdmin {
    private JButton sendAMessageButton;
    private JButton removeAnItemFromWishlistButton;
    private JButton removeAnItemFromInventoryButton;
    private JButton freezeUserButton;
    private JButton backButton;
    private JPanel mainPanel;
    private JButton viewStatsButton;

    /**
     * Constructs the interface that links multiple actions can be done to the User with usernameViewed
     * @param useCases the use case grouper
     * @param cpg the controller presenter grouper
     * @param usernameViewed the username of the User being viewed at
     * @param window the main window displayed to the administrative user of the program
     */
    public ViewingUserAsAdmin(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, String usernameViewed,
                              JFrame window) {
        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        if (!useCases.userManager.isTradingUser(usernameViewed)) {
            viewStatsButton.setEnabled(false);
            removeAnItemFromInventoryButton.setEnabled(false);
            removeAnItemFromWishlistButton.setEnabled(false);
            freezeUserButton.setEnabled(false);
        }

        sendAMessageButton.setText(cpg.menuPresenter.getText(Frame.ADMINVIEWUSER, 0));
        viewStatsButton.setText(cpg.menuPresenter.getText(Frame.ADMINVIEWUSER, 1));
        removeAnItemFromWishlistButton.setText(cpg.menuPresenter.getText(Frame.ADMINVIEWUSER, 2));
        removeAnItemFromInventoryButton.setText(cpg.menuPresenter.getText(Frame.ADMINVIEWUSER, 3));
        freezeUserButton.setText(cpg.menuPresenter.getText(Frame.ADMINVIEWUSER, 4));
        backButton.setText(cpg.menuPresenter.getText(Frame.ADMINVIEWUSER, 5));


        sendAMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                SendMessageMenu sendMessageMenu = new SendMessageMenu("admin", usernameViewed, cpg,
//                        useCases, window, false, useCases.userManager.searchUser(usernameViewed)
//                        instanceof TradingUser);
                String message = JOptionPane.showInputDialog(window,cpg.menuPresenter.getText(Frame.SENDMESSAGEMENU, 2),
                        cpg.menuPresenter.getText(Frame.SENDMESSAGEMENU, 0), JOptionPane.PLAIN_MESSAGE);
                if (!message.isEmpty()) {
                    cpg.tradingUserActions.messageUser("admin", usernameViewed, message, useCases.userManager);
                    JOptionPane.showMessageDialog(window, cpg.menuPresenter.getText(Frame.SENDMESSAGEMENU, 3));
                }
            }
        });
        removeAnItemFromWishlistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RemoveItemFromUserWishlist(useCases, cpg, usernameViewed, window);
            }
        });
        removeAnItemFromInventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RemoveItemFromUserInventory(useCases, cpg, usernameViewed, window);
            }
        });
        freezeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FreezeMenu(useCases, cpg, usernameViewed, window);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewAllUsers(useCases, cpg, true, window);
            }
        });
        viewStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewUserStatsAsAdmin(useCases, cpg, usernameViewed, window);
            }
        });
    }
}
