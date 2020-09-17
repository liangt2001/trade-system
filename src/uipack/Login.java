package uipack;

import uipack.adminmenu.AdminActionsMenu;
import uipack.usermenu.BrowsingUserActionsMenu;
import uipack.usermenu.TradingUserActionsMenu;
import controllerpresenterpack.*;
import entitypack.Frame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A UI class to allow for user login
 */
public class Login extends JFrame{

    JFormattedTextField username;
    private JPasswordField password;
    private JButton loginButton;
    private JPanel mainPanel;
    private JButton backButton;
    private JLabel usernameLabel = new JLabel();
    private JLabel passwordLabel = new JLabel();

    /**
     * Constructs the interface for users to login
     * @param useCases the use case grouper
     * @param cpg the controller presenter grouper
     * @param isAdmin boolean for whether or not user is an admin
     * @param frame the main window displayed to the trading user of the program
     */
    public Login(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, boolean isAdmin, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        passwordLabel.setText(cpg.menuPresenter.getText(Frame.LOGIN,0));
        usernameLabel.setText(cpg.menuPresenter.getText(Frame.LOGIN,1));
        backButton.setText(cpg.menuPresenter.getText(Frame.LOGIN,2));
        loginButton.setText(cpg.menuPresenter.getText(Frame.LOGIN,3));

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String pass = new String(password.getPassword());
                String user = username.getText();
                boolean validLogin;
                if(isAdmin) {
                    AdminActions adminActions = new AdminActions();
                    validLogin = adminActions.validateLogin(useCases.adminUser, user, pass);
                    if (validLogin) {
                        frame.setTitle(cpg.menuPresenter.getText(Frame.LOGIN, 5) +" "+ user);
                        new AdminActionsMenu(useCases,
                                cpg, frame);
                         /*cpg.adminAlertManager.handleAlertQueue(cpg.menuPresenter, useCases.adminUser,
                                useCases.userManager, useCases.tradeCreator, useCases.itemManager,
                                useCases.adminUser.getAdminAlerts()); */
                    } else {
                        JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.LOGIN,4));
                    }
                }else{
                    UserActions userActions = new UserActions();

                    //This version of validateLogin returns null if the login is invalid, true if the user is a
                    //TradingUser, and false if the user is a BrowsingUser
                    validLogin = userActions.validateLogin(useCases.userManager, user, pass);
                    if (validLogin) {
                        boolean isTradingUser = userActions.isTradingUser(useCases.userManager, user);
                        /*
                        cpg.userAlertManager.handleAlertQueue(cpg.menuPresenter, useCases.adminUser, useCases.userManager,
                                useCases.tradeCreator, useCases.itemManager, useCases.userManager.getUserAlerts(user));

                         */
                        frame.setTitle(cpg.menuPresenter.getText(Frame.LOGIN, 5) + " "+ user);
                        if (isTradingUser) {
                            new TradingUserActionsMenu(useCases, cpg, user, frame);
                        } else{
                            new BrowsingUserActionsMenu(useCases, cpg, user, frame);
                        }

                    } else {
                        JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.LOGIN,4));
                    }
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new MainMenu(useCases, cpg, frame);
            }
        });
    }


}
