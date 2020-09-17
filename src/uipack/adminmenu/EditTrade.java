package uipack.adminmenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A UI class allows Admin to edit trades and links to the following menus:
 *  - Pending trades
 *  - Confirmed trades (in progress)
 *  - Completed trades
 */
public class EditTrade {
    private JButton viewPendingTradesButton;
    private JButton viewConfirmedTradesButton;
    private JButton viewCompletedTradesButton;
    private JButton backButton;
    private JPanel mainPanel;

    /**
     * Constructs the interface to edit trades
     * @param useCases the use case grouper
     * @param controllerPresenterGrouper the controller presenter grouper
     * @param window the main window displayed to the user of the program
     */
    public EditTrade(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper, JFrame window) {

        viewPendingTradesButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.EDITTRADE, 0));
        viewConfirmedTradesButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.EDITTRADE, 1));
        viewCompletedTradesButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.EDITTRADE, 2));
        backButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.EDITTRADE, 3));

        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        viewPendingTradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewPendingRequests(useCases, controllerPresenterGrouper,
                        window);
            }
        });
        viewConfirmedTradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewPendingTrades(useCases, controllerPresenterGrouper,
                        window);
            }
        });
        viewCompletedTradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewCompletedTrades(useCases, controllerPresenterGrouper,
                        window);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminActionsMenu(useCases, controllerPresenterGrouper, window);
            }
        });
    }
}
