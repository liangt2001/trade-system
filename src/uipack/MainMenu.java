package uipack;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import entitypack.Frame;

/**
 * A UI class for the main menu of the program. A different menu is presented depending
 * on type of user: browsing/non-browsing and if they are an admin
 */
public class MainMenu {
    private JButton createAccountButton;
    private JButton loginAsUserButton;
    private JButton loginAsAdminButton;
    private JPanel mainPanel;

    /**
     * Constructs the interface to display the main menu
     * @param useCases the use case grouper
     * @param controllerPresenterGrouper the controller presenter grouper
     * @param frame the main window displayed to the trading user of the program
     */
    public MainMenu(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createAccountButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.MAINMENU,0));
        loginAsAdminButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.MAINMENU,1));
        loginAsUserButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.MAINMENU,2));

        frame.pack();
        frame.setVisible(true);
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new CreateUserAccount(useCases, controllerPresenterGrouper, frame);

            }
        });
        loginAsUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new Login(useCases, controllerPresenterGrouper, false, frame);

            }
        });
        loginAsAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new Login(useCases, controllerPresenterGrouper, true, frame);
            }
        });
    }

}
