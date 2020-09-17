package uipack.adminmenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A UI class that allows the administrative user to create new AdminUsers by inputting new username and password
 */
public class AddNewAdmin {
    private JPanel mainPanel;
    private JLabel addNewAdminTitle;
    private JLabel setAdminUsername;
    private JFormattedTextField newUsername;
    private JLabel setAdminPassword;
    private JPasswordField newPassword;
    private JButton submitButton;
    private JButton backButton;

    /**
     * Constructs the interface to add new AdminUsers
     * @param useCases the use case grouper
     * @param controllerPresenterGrouper the controller presenter grouper
     * @param frame the main window displayed to the administrative user of the program
     */
    public AddNewAdmin(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper,
                       JFrame frame) {

        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        addNewAdminTitle.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADDNEWADMIN,8));
        setAdminUsername.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADDNEWADMIN,0));
        setAdminPassword.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADDNEWADMIN,1));
        backButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADDNEWADMIN,2));
        submitButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.ADDNEWADMIN,3));

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = newUsername.getText();
                String password = new String(newPassword.getPassword());
                controllerPresenterGrouper.adminActions.addNewLogin(useCases.adminUser, username, password);

                char[] passwordCharArr = newPassword.getPassword();
                if (passwordCharArr.length == 0){
                    JOptionPane.showMessageDialog(frame,
                            controllerPresenterGrouper.menuPresenter.getText(Frame.ADDNEWADMIN, 5));
                    return;

            }
                JOptionPane.showMessageDialog(frame,
                        controllerPresenterGrouper.menuPresenter.getText(Frame.ADDNEWADMIN, 7));
                new AdminActionsMenu(useCases, controllerPresenterGrouper, frame);

        };

    });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminActionsMenu(useCases, controllerPresenterGrouper, frame);
            }
        });
    }};
