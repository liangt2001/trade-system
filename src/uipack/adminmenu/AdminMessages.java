package uipack.adminmenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A UI class for user's to view their messages
 */
public class AdminMessages {
    private JButton backButton;
    private JPanel mainPanel;
    private JTextPane messagePane;

    /**
     * Constructs the interface to view messages
     * @param useCases the user case grouper
     * @param cpg the controller presenter grouper
     * @param frame the main window displayed to the trading user of the program
     */
    public AdminMessages(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        backButton.setText(cpg.menuPresenter.getText(Frame.VIEWPENDINGTRADESMENU, 0));

        ArrayList<String> messages;
        StringBuilder messageString = new StringBuilder();

        messages = useCases.adminUser.getAdminMessages();

        for (String message : messages) {
            messageString.append(message);
        }
        messagePane.setText(messageString.toString());

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    new AdminActionsMenu(useCases, cpg, frame);
            }
        });
    }
}
