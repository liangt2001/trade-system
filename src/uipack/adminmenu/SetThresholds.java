package uipack.adminmenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A UI class that allows the administrative user to set each threshold:
 *  - Max number of complete trades per week
 *  - Max number of incomplete trades per week
 *  - Borrow/lend threshold
 */
public class SetThresholds {
    private JLabel menuTitle;
    private JLabel enterBorrowLend;
    private JLabel incompleteThreshold;
    private JTextField newIncompletes;
    private JTextField newCompletes;
    private JLabel completeThreshold;
    private JTextField newBorrowLend;
    private JButton submitBorrowLend;
    private JButton submitIncompletes;
    private JButton submitCompletes;
    private JPanel mainPanel;
    private JButton backButton;

    /**
     * Constructs the interface to set each threshold
     * @param useCases the use case grouper
     * @param controllerPresenterGrouper the controller presenter grouper
     * @param window the main window displayed to the administrative user of the program
     */
    public SetThresholds(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper, JFrame window) {
        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();


        menuTitle.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.THRESHOLDSMENU, 0));
        enterBorrowLend.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.THRESHOLDSMENU, 1));
        incompleteThreshold.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.THRESHOLDSMENU, 3));
        completeThreshold.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.THRESHOLDSMENU, 2));
        newBorrowLend.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.THRESHOLDSMENU, 4));
        newCompletes.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.THRESHOLDSMENU, 4));
        newIncompletes.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.THRESHOLDSMENU, 4));
        submitBorrowLend.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.THRESHOLDSMENU, 5));
        submitCompletes.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.THRESHOLDSMENU, 5));
        submitIncompletes.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.THRESHOLDSMENU, 5));
        backButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.THRESHOLDSMENU, 7));
        window.setVisible(true);

        submitIncompletes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int incompleted;
                try {
                    incompleted = Integer.parseInt(newIncompletes.getText());
                } catch (NumberFormatException x){
                    JOptionPane.showMessageDialog(window,
                            controllerPresenterGrouper.menuPresenter.getText(Frame.THRESHOLDSMENU, 8));
                    return;
                }
                controllerPresenterGrouper.adminActions.changeIncompleteThreshold(incompleted, useCases.adminUser,
                        useCases.userManager);
                JOptionPane.showMessageDialog(window,
                        controllerPresenterGrouper.menuPresenter.getText(Frame.THRESHOLDSMENU, 6));
            }
        });

        submitCompletes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int completed;
                try {
                    completed = Integer.parseInt(newCompletes.getText());
                } catch (NumberFormatException x){
                    JOptionPane.showMessageDialog(window,
                            controllerPresenterGrouper.menuPresenter.getText(Frame.THRESHOLDSMENU, 8));
                    return;
                }
                controllerPresenterGrouper.adminActions.changeCompleteThreshold(completed, useCases.adminUser,
                        useCases.tradeCreator);
                JOptionPane.showMessageDialog(window,
                        controllerPresenterGrouper.menuPresenter.getText(Frame.THRESHOLDSMENU, 6));
            }
        });

        submitBorrowLend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int borrowLentThresh;
                try {
                    borrowLentThresh = Integer.parseInt(newBorrowLend.getText());
                } catch (NumberFormatException x){
                    JOptionPane.showMessageDialog(window,
                            controllerPresenterGrouper.menuPresenter.getText(Frame.THRESHOLDSMENU, 8));
                    return;
                }
                controllerPresenterGrouper.adminActions.changeBorrowLendThreshold(useCases.adminUser,
                        useCases.tradeCreator, borrowLentThresh);
                JOptionPane.showMessageDialog(window,
                        controllerPresenterGrouper.menuPresenter.getText(Frame.THRESHOLDSMENU, 6));
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
