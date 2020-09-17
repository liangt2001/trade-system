package uipack.usermenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.TemporaryTrade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A UI class which displays the ability for users to confirm a trade re-exchange
 */
public class ConfirmReExchangeMenu {
    private JPanel mainPanel;
    private JLabel tradeIDLabel;
    private JLabel tradeIDValue;
    private JLabel user1Label;
    private JLabel user2Label;
    private JLabel itemsToUser1Label;
    private JLabel itemsToUser2Label;
    private JLabel user1Value;
    private JLabel user2Value;
    private JTextPane itemsToUser1List;
    private JTextPane itemsToUser2List;
    private JButton backButton;
    private JButton confirmReExchangeButton;
    private JButton reportButton;
    private JLabel reExchangeTimeLabel;
    private JLabel user1ConfirmedReExchangeLabel;
    private JLabel user2ConfirmedReExchangeLabel;
    private JLabel reExchangeTimeValue;
    private JLabel user1ConfirmedReExchangeValue;
    private JLabel user2ConfirmedReExchangeValue;

    /**
     * Constructs the interface to confirm trade re-exchanges
     * @param useCases use case grouper
     * @param controllerPresenterGrouper the controller presenter grouper
     * @param username current user's username
     * @param frame the main window displayed to the trading user of the program
     * @param trade the trade to re-exchange
     */
    public ConfirmReExchangeMenu(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper,
                                 String username, JFrame frame, TemporaryTrade trade) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        backButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMREEXCHANGEMENU, 0));
        confirmReExchangeButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMREEXCHANGEMENU, 1));
        reportButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMREEXCHANGEMENU, 14));

        user1Label.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMREEXCHANGEMENU, 2));
        user2Label.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMREEXCHANGEMENU, 3));
        itemsToUser1Label.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMREEXCHANGEMENU, 4));
        itemsToUser2Label.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMREEXCHANGEMENU, 5));
        reExchangeTimeLabel.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMREEXCHANGEMENU, 7));
        user1ConfirmedReExchangeLabel.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMREEXCHANGEMENU, 8));
        user2ConfirmedReExchangeLabel.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMREEXCHANGEMENU, 9));
        tradeIDLabel.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMREEXCHANGEMENU, 12));


        //Should i do this or does this break clean architecture? - Louis
        user1Value.setText(trade.getUsername1());
        user2Value.setText(trade.getUsername2());
        reExchangeTimeValue.setText(trade.getDueDate().toString());
        Boolean user1ConfirmedBoolean = (Boolean)trade.getUser1TradeConfirmed();
        Boolean user2ConfirmedBoolean = (Boolean)trade.getUser2TradeConfirmed();
        user1ConfirmedReExchangeValue.setText(user1ConfirmedBoolean.toString());
        user2ConfirmedReExchangeValue.setText(user2ConfirmedBoolean.toString());
        Integer user1NumRequests = (Integer) trade.getUser1NumRequests();
        Integer user2NumRequests = (Integer) trade.getUser2NumRequests();
        Integer tradeID = (Integer) trade.getTradeID();
        tradeIDValue.setText(tradeID.toString());

        ArrayList<String> itemsToUser1 = controllerPresenterGrouper.menuPresenter.getItemStringsFromUser2ToUser1(trade, useCases.itemManager);
        ArrayList<String> itemsToUser2 = controllerPresenterGrouper.menuPresenter.getItemStringsFromUser1ToUser2(trade, useCases.itemManager);


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

        if(LocalDateTime.now().isBefore(trade.getDueDate())){
            confirmReExchangeButton.setEnabled(false);
            confirmReExchangeButton.setText(controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMREEXCHANGEMENU, 17));
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new TradingUserActionsMenu(useCases, controllerPresenterGrouper, username, frame);
            }
        });
        confirmReExchangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(frame, controllerPresenterGrouper.menuPresenter.getText(Frame.CONFIRMREEXCHANGEMENU, 13));
                controllerPresenterGrouper.tradingUserActions.confirmReExchange(useCases.userManager, useCases.tradeCreator, useCases.itemManager, trade, username);
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

