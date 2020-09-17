package uipack.usermenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.MetroArea;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A UI class to set the current user's metropolitan area
 */
public class SetActiveCityMenu {
    private JPanel mainPanel;
    private JComboBox cities;
    private JButton backButton;
    private JButton setCityButton;

    /**
     * Constructs the interface for user's to select their city
     * @param useCases the user case grouper
     * @param cpg the controller presenter grouper
     * @param username the current user's username
     * @param frame the main window displayed to the trading user of the program
     */
    public SetActiveCityMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg,
                             String username, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        backButton.setText(cpg.menuPresenter.getText(Frame.SETACTIVECITYMENU, 0));

        setCityButton.setText(cpg.menuPresenter.getText(Frame.SETACTIVECITYMENU, 1));

        for (MetroArea metroArea: MetroArea.values()){
            cities.addItem(metroArea);
        }



        setCityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                cpg.tradingUserActions.setUsersCity(useCases.userManager, username,
                        (MetroArea)cities.getSelectedItem());
                JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.SETACTIVECITYMENU, 2));
                TradingUserActionsMenu userActionsMenu = new TradingUserActionsMenu(useCases, cpg,
                        username, frame);

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new TradingUserActionsMenu(useCases, cpg, username, frame);
            }

        });
    }
}
