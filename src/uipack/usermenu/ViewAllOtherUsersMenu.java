package uipack.usermenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.TradingUser;
import entitypack.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A UI class for user's to view all other users in their metropolitan area
 */
public class ViewAllOtherUsersMenu {
    private JComboBox<String> selectUserBox;
    private JPanel mainPanel;
    private JButton backButton;
    private JButton selectUserButton;

    /**
     * Constructs the interface for users to view other users
     * @param useCases the user case grouper
     * @param cpg the controller presenter grouper
     * @param username the current user's username
     * @param frame the main window displayed to the trading user of the program
     * @param isTradingUser boolean for whether or not the current user is of type TradingUser
     */
    public ViewAllOtherUsersMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, String username, JFrame frame,
                                 boolean isTradingUser) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        selectUserBox.addItem(cpg.menuPresenter.getText(Frame.VIEWALLOTHERUSERSMENU, 0));
        backButton.setText(cpg.menuPresenter.getText(Frame.VIEWALLOTHERUSERSMENU, 1));
        selectUserButton.setText(cpg.menuPresenter.getText(Frame.VIEWALLOTHERUSERSMENU, 2));

        ArrayList<User> allUsers = cpg.tradingUserActions.getLocalUsers(useCases.userManager,
                cpg.tradingUserActions.getUsersMetroArea(useCases.userManager, username));
        for (int i = 0; i < allUsers.size(); i++){
            selectUserBox.addItem(allUsers.get(i).getUsername());
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (isTradingUser) {
                    TradingUserActionsMenu tradingUserActionsMenu = new TradingUserActionsMenu(useCases, cpg,
                            username, frame);
                } else{
                    BrowsingUserActionsMenu browsingUserActionsMenu = new
                            BrowsingUserActionsMenu(useCases, cpg, username, frame);
                }
            }
        });
        selectUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if ((selectUserBox.getSelectedItem()).equals(cpg.menuPresenter.getText(Frame.VIEWALLOTHERUSERSMENU,
                        0))) {
                    JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.VIEWALLOTHERUSERSMENU, 3));
                }
                else {
                    String userToViewUsername = (String) selectUserBox.getSelectedItem();
                    ViewUserMenu viewUserMenu = new ViewUserMenu(useCases, cpg, username,
                            userToViewUsername, frame, isTradingUser,
                            useCases.userManager.searchUser(userToViewUsername) instanceof TradingUser);
                }
            }
        });
    }

}
