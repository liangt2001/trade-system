package uipack.usermenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.Trade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A UI class to display the confirm trade menu.
 * Allows users to select a trade and to confirm it
 */
public class ConfirmTradeMenu {
    private JLabel tradeIDLabel;
    private JLabel tradeIDValue;
    private JLabel user1Label;
    private JLabel user1Value;
    private JLabel itemsToUser1Label;
    private JLabel itemsToUser2Label;
    private JLabel meetingLocationLabel;
    private JLabel meetingTimeLabel;
    private JLabel user2Label;
    private JLabel user2Value;
    private JTextPane itemsToUser2List;
    private JLabel meetingLocationValue;
    private JLabel meetingTimeValue;
    private JButton confirmTradeButton;
    private JButton backButton;
    private JButton reportButton;
    private JLabel user1ConfirmedValue;
    private JLabel user1ConfirmedLabel;
    private JLabel user2ConfirmedValue;
    private JLabel user2ConfirmedLabel;
    private JPanel mainPanel;
    private JTextPane itemsToUser1List;

    /**
     * Constructs the interface to confirm trades
     * @param useCases the use case grouper
     * @param controllerPresenterGrouper the controller presenter grouper
     * @param username the current user's username
     * @param frame the main window displayed to the trading user of the program
     * @param trade the trade to confirm
     */
    public ConfirmTradeMenu(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper,
                            String username, JFrame frame, Trade trade) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        backButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 0));
        confirmTradeButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 1));
        reportButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 14));

        user1Label.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 2));
        user2Label.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 3));
        itemsToUser1Label.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 4));
        itemsToUser2Label.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 5));
        meetingLocationLabel.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 6));
        meetingTimeLabel.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 7));
        user1ConfirmedLabel.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 8));
        user2ConfirmedLabel.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 9));
        tradeIDLabel.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 12));


        //Should i do this or does this break clean architecture? - Louis
        user1Value.setText(trade.getUsername1());
        user2Value.setText(trade.getUsername2());
        meetingLocationValue.setText(trade.getMeetingPlace());
        meetingTimeValue.setText(trade.getTimeOfTrade().toString());
        Boolean user1ConfirmedBoolean = trade.getUser1TradeConfirmed();
        Boolean user2ConfirmedBoolean = trade.getUser2TradeConfirmed();
        user1ConfirmedValue.setText(user1ConfirmedBoolean.toString());
        user2ConfirmedValue.setText(user2ConfirmedBoolean.toString());
        Integer user1NumRequests =  trade.getUser1NumRequests();
        Integer user2NumRequests =  trade.getUser2NumRequests();
        Integer tradeID =  trade.getTradeID();
        tradeIDValue.setText(tradeID.toString());

        ArrayList<String> itemsToUser1 = controllerPresenterGrouper.menuPresenter.getItemStringsFromUser2ToUser1(trade,
                useCases.itemManager);
        ArrayList<String> itemsToUser2 = controllerPresenterGrouper.menuPresenter.getItemStringsFromUser1ToUser2(trade,
                useCases.itemManager);


        StringBuilder itemsToU1SB = new StringBuilder();
        int i = 1;
        for(String string: itemsToUser1){
            itemsToU1SB.append(i).append("- ");
            i++;
            itemsToU1SB.append(string).append("\n");
        }
        itemsToUser1List.setText(itemsToU1SB.toString());


        StringBuilder itemsToU2SB = new StringBuilder();
        i = 1;
        for(String string: itemsToUser2){
            itemsToU1SB.append(i).append("- ");
            i++;
            itemsToU2SB.append(string).append("\n");
        }
        itemsToUser2List.setText(itemsToU2SB.toString());


        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        if(LocalDateTime.now().isBefore(trade.getTimeOfTrade())){
            confirmTradeButton.setEnabled(false);
            confirmTradeButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMTRADEMENU, 17));
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ViewPendingTradesMenu(useCases, controllerPresenterGrouper,
                        username, frame);
            }
        });
        confirmTradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(frame, controllerPresenterGrouper.menuPresenter.getText(
                        Frame.CONFIRMTRADEMENU, 13));
                controllerPresenterGrouper.tradingUserActions.confirmTrade(useCases.userManager, useCases.tradeCreator,
                        useCases.itemManager, trade, username);
                new TradingUserActionsMenu(useCases, controllerPresenterGrouper, username, frame);
            }
        });
        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ReportMenu(useCases, controllerPresenterGrouper, username, frame, trade);
            }
        });
    }
}
