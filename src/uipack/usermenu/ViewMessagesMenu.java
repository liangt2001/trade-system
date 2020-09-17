package uipack.usermenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.TradingUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A UI class for user's to view their messages
 */
public class ViewMessagesMenu {
    private JButton backButton;
    private JPanel mainPanel;
    private JTextPane messagePane;

    /**
     * Constructs the interface to view messages
     * @param useCases the user case grouper
     * @param cpg the controller presenter grouper
     * @param username the current user's username
     * @param frame the main window displayed to the trading user of the program
     */
    public ViewMessagesMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, String username, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        backButton.setText(cpg.menuPresenter.getText(Frame.VIEWPENDINGTRADESMENU, 0));

        ArrayList<String> messages;
        StringBuilder messageString = new StringBuilder();
        messages = useCases.userManager.getUserMessages(username);

        for (String message : messages) {
            messageString.append(message);
        }
        messagePane.setText(messageString.toString());

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (useCases.userManager.searchUser(username) instanceof TradingUser) {
                    new TradingUserActionsMenu(useCases, cpg, username, frame);
                } else {
                    new BrowsingUserActionsMenu(useCases, cpg, username, frame);
                }
            }
        });
    }
}
