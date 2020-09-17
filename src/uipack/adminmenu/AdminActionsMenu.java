package uipack.adminmenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;

/**
 * A UI class that displays and links to the following menu classes for AdminUser:
 *  - Set borrow lend threshold
 *  - Set complete trade threshold
 *  - Set incomplete trade threshold
 *  - Add new AdminUser to the program
 *  - View all thresholds
 *  - Edit/Undo trades conducted by TradingUsers
 *  - View all Users
 *  - View item validation request queue
 *  _ View messages
 */
public class AdminActionsMenu {
    public JPanel mainPanel;
    public JButton setThresholds;
    public JButton addNewAdmin;
    private JButton viewThresholdValues;
    private JButton editUndoTrade;
    public JButton viewAllUsers;
    private JButton viewItemValidationRequests;
    private JButton viewMessages;
    public JButton searchUser;

    /**
     * Constructs the interface of Admin's menu
     * @param useCases the use case grouper
     * @param cpg the controller presenter grouper
     * @param frame the main window displayed to the administrative user of the program
     */
    public AdminActionsMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, JFrame frame){
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        setThresholds.setText(cpg.menuPresenter.getText(Frame.ADMINACTIONSMENU,0));
        addNewAdmin.setText(cpg.menuPresenter.getText(Frame.ADMINACTIONSMENU, 1));
        viewThresholdValues.setText(cpg.menuPresenter.getText(Frame.ADMINACTIONSMENU, 2));
        editUndoTrade.setText(cpg.menuPresenter.getText(Frame.ADMINACTIONSMENU, 3));
        viewAllUsers.setText(cpg.menuPresenter.getText(Frame.ADMINACTIONSMENU, 4));
        viewItemValidationRequests.setText(cpg.menuPresenter.getText(Frame.ADMINACTIONSMENU, 5));
        viewMessages.setText(cpg.menuPresenter.getText(Frame.ADMINACTIONSMENU, 6));

        setThresholds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SetThresholds setThresholds = new SetThresholds(useCases, cpg, frame);
            }
        });

        addNewAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddNewAdmin addNewAdmin = new AddNewAdmin(useCases, cpg,  frame);
            }

        });
        viewAllUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewAllUsers viewAllUsers = new ViewAllUsers(useCases, cpg, true, frame);
            }
        });
        viewThresholdValues.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewCurrentThresholds viewCurrentThresholds = new ViewCurrentThresholds(useCases,
                        cpg, frame);
            }
        });
        editUndoTrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditTrade editTrade = new EditTrade(useCases, cpg, frame);
            }
        });

        viewItemValidationRequests.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewItemValidationRequests viewItemValidationRequests = new ViewItemValidationRequests(useCases,
                        cpg, frame);
            }
        });
        viewMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminMessages adminMessages = new AdminMessages(useCases, cpg, frame);
            }
        });
    }
}