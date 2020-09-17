package uipack.adminmenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.TradingUser;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A UI class that displays a User's status and allows the administrative user to unfreeze/freeze this User
 */
public class FreezeMenu {
    private JButton freezeButton;
    private JPanel mainPanel;
    private JButton backButton;
    private JButton unfreezeButton;
    private JLabel usernameStatus;
    private JLabel currentFrozenStatus;

    /**
     * Constructs the interface to view the status of the User with viewedUsername, and to unfreeze/freeze
     * @param useCases the use case grouper
     * @param cpg the controller presenter grouper
     * @param viewedUsername the username of the User being viewed at
     * @param window the main window displayed to the administrative user of the program
     */
    public FreezeMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, String viewedUsername, JFrame window) {

        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        freezeButton.setText(cpg.menuPresenter.getText(Frame.ADMINFREEZE, 4));
        unfreezeButton.setText(cpg.menuPresenter.getText(Frame.ADMINFREEZE, 5));
        backButton.setText(cpg.menuPresenter.getText(Frame.ADMINFREEZE, 6));

        usernameStatus.setText(viewedUsername + cpg.menuPresenter.getText(Frame.ADMINFREEZE, 0));
        if (!useCases.userManager.isTradingUser(viewedUsername)) {
            freezeButton.setEnabled(false);
            unfreezeButton.setEnabled(false);
            currentFrozenStatus.setText(cpg.menuPresenter.getText(Frame.ADMINFREEZE, 3));
        } else {
            TradingUser viewedUser = (TradingUser) useCases.userManager.searchUser(viewedUsername);
            if (viewedUser.getFrozen()) {
                currentFrozenStatus.setText(cpg.menuPresenter.getText(Frame.ADMINFREEZE, 1));
                freezeButton.setEnabled(false);
            } else {
                currentFrozenStatus.setText(cpg.menuPresenter.getText(Frame.ADMINFREEZE, 2));
                unfreezeButton.setEnabled(false);
            }
            freezeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cpg.adminActions.freezeUser(viewedUser, 1, useCases.adminUser);
                    JOptionPane.showMessageDialog(window, cpg.menuPresenter.getText(Frame.ADMINFREEZE, 7));
                    AdminActionsMenu adminActionsMenu = new AdminActionsMenu(useCases, cpg, window);
                }
            });
            unfreezeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cpg.adminActions.freezeUser(viewedUser, 0, useCases.adminUser);
                    JOptionPane.showMessageDialog(window, cpg.menuPresenter.getText(Frame.ADMINFREEZE, 8));
                    new AdminActionsMenu(useCases, cpg, window);
                }
            });
        }
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewingUserAsAdmin viewingUserAsAdmin = new ViewingUserAsAdmin(useCases, cpg, viewedUsername, window);
            }
        });
    }
}
