package uipack.usermenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.TradingUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A UI class for users to set their own active status: either active or inactive
 */
public class SetActiveStatusMenu {
    private JComboBox statusBox;
    private JButton setStatusButton;
    private JButton backButton;
    private JPanel mainPanel;

    /**
     * Constructs the interface for user's to set their active status
     * @param useCases the user case grouper
     * @param cpg the controller presenter grouper
     * @param username the current user's username
     * @param frame the main window displayed to the trading user of the program
     */
    public SetActiveStatusMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, String username, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        backButton.setText(cpg.menuPresenter.getText(Frame.SETACTIVESTATUSMENU,0));
        setStatusButton.setText(cpg.menuPresenter.getText(Frame.SETACTIVESTATUSMENU,1));
        statusBox.addItem(cpg.menuPresenter.getText(Frame.SETACTIVESTATUSMENU,2));
        statusBox.addItem(cpg.menuPresenter.getText(Frame.SETACTIVESTATUSMENU,3));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new TradingUserActionsMenu(useCases, cpg, username, frame);
            }
        });
        setStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TradingUser user = (TradingUser) useCases.userManager.searchUser(username);
                if (statusBox.getSelectedItem() == cpg.menuPresenter.getText(Frame.SETACTIVESTATUSMENU,2) &&
                        !user.isActive()){
                    cpg.tradingUserActions.setActive(true, user);
                    JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.SETACTIVESTATUSMENU,4));
                }
                else if (statusBox.getSelectedItem() == cpg.menuPresenter.getText(Frame.SETACTIVESTATUSMENU,3) &&
                        user.isActive()){
                    cpg.tradingUserActions.setActive(false, user);
                    JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.SETACTIVESTATUSMENU,4));
                }
                else {
                    JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.SETACTIVESTATUSMENU,5));
                }
                new TradingUserActionsMenu(useCases, cpg, username, frame);
            }
        });
    }
}
