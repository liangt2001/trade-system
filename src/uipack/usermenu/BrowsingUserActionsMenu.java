package uipack.usermenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A UI class that displays and links to the following menu classes for BrowsingUser:
 *  - View messages
 *  - View other users
 */
public class BrowsingUserActionsMenu {

    private JPanel mainPanel;
    private JButton viewOtherUsersButton;
    private JLabel Loggedin;
    private JButton viewMessagesButton;

    /**
     * Constructs the interface of BrowsingUser's menu
     * @param useCases the use case grouper
     * @param cpg the controller presenter grouper
     * @param username the username of the browsing user
     * @param frame the main window displayed to the browsing user of the program
     */
    public BrowsingUserActionsMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, String username, JFrame frame){
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        Loggedin.setText(cpg.menuPresenter.getText(Frame.BROWSINGUSERACTIONSMENU,0) + username);
        viewOtherUsersButton.setText(cpg.menuPresenter.getText(Frame.BROWSINGUSERACTIONSMENU,1));
        viewMessagesButton.setText(cpg.menuPresenter.getText(Frame.TRADINGUSERACTIONSMENU,15));

        viewOtherUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ViewAllOtherUsersMenu(useCases, cpg, username, frame, false);
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
