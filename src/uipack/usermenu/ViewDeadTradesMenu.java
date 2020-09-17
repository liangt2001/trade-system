package uipack.usermenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.Trade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A UI class for users to view their dead trades (trades that have fully been completed)
 */
public class ViewDeadTradesMenu {
    private JButton backButton;
    private JPanel mainPanel;
    private JScrollPane tradeInfoPane;

    /**
     * Constructs the interface to view dead trades
     * @param useCases the user case grouper
     * @param controllerPresenterGrouper the controller presenter grouper
     * @param username the current user's username
     * @param frame the main window displayed to the trading user of the program
     */
    public ViewDeadTradesMenu(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper,
                                 String username, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        backButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.VIEWPENDINGTRADESMENU, 0));
        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;



        int n = 0;
        ArrayList<Trade> trades = controllerPresenterGrouper.tradingUserActions.searchDeadTradesUser(username,
                useCases.userManager, useCases.tradeCreator);
        if(trades != null) {
            for (Trade trade : trades) {
                JLabel generatedLabel = new JLabel();
                generatedLabel.setText(controllerPresenterGrouper.menuPresenter.printTradeToString(useCases.itemManager,
                        trade));
                c.gridy = n;
                n++;
                content.add(generatedLabel, c);
            }
        }

        tradeInfoPane.setViewportView(content);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new TradingUserActionsMenu(useCases, controllerPresenterGrouper, username, frame);
            }
        });
    }
}
