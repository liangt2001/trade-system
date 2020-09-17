package uipack.adminmenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A UI class that displays all the current thresholds of the program
 */
public class ViewCurrentThresholds {
    private JLabel BorrowLendThreshold;
    private JPanel mainPanel;
    private JLabel CompleteThreshold;
    private JLabel BLTnum;
    private JLabel ITTnum;
    private JLabel CTTnum;
    private JButton BackButton;
    private JLabel incompleteTradeThreshold;

    /**
     * Constructs the interface to view all thresholds
     * @param useCases the use case grouper
     * @param controllerPresenterGrouper the controller presenter grouper
     * @param window the main window displayed to the administrative user of the program
     */
    public ViewCurrentThresholds(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper,
                                 JFrame window) {

        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        BorrowLendThreshold.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.VIEWCURRENTTHRESHOLDS, 0));
        incompleteTradeThreshold.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.VIEWCURRENTTHRESHOLDS, 1));
        CompleteThreshold.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.VIEWCURRENTTHRESHOLDS, 2));
        BackButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.VIEWCURRENTTHRESHOLDS, 3));

        BLTnum.setText(Integer.toString(useCases.tradeCreator.getBorrowLendThreshold()));
        ITTnum.setText(Integer.toString(useCases.userManager.getIncompleteThreshold()));
        CTTnum.setText(Integer.toString(useCases.tradeCreator.getCompleteThreshold()));

        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminActionsMenu(useCases, controllerPresenterGrouper, window);
            }
        });
    }
}
