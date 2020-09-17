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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A UI class to counter offer a trade
 */
public class CounterOfferMenu {

    private JPanel mainPanel;
    private JLabel meetingPlaceLabel;
    private JTextField meetingPlace;
    private JLabel meetingTimeLabel;
    private JTextField meetingTime;
    private JButton sendTradeRequestButton;
    private JButton backButton;

    /**
     * Constructs the interface to create a counter offer
     * @param useCases the use case grouper
     * @param cpg the controller presenter grouper
     * @param username the current user's username
     * @param frame the main window displayed to the trading user of the program
     * @param trade the trade to counter offer
     */
    public CounterOfferMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg,
                            String username, JFrame frame, Trade trade) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        meetingPlaceLabel.setText(cpg.menuPresenter.getText(Frame.SENDTRADEREQUESTMENU, 1));
        meetingTimeLabel.setText(cpg.menuPresenter.getText(Frame.SENDTRADEREQUESTMENU, 2));

        sendTradeRequestButton.setText(cpg.menuPresenter.getText(Frame.SENDTRADEREQUESTMENU, 5));
        backButton.setText(cpg.menuPresenter.getText(Frame.SENDTRADEREQUESTMENU, 6));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AcceptTradeMenu acceptTradeMenu = new AcceptTradeMenu(useCases, cpg, username, frame, trade);
            }
        });

        sendTradeRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                LocalDateTime timeOfTrade = null;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                try {
                    timeOfTrade = LocalDateTime.parse(meetingTime.getText(), formatter);
                } catch (DateTimeParseException e) {
                    // "Invalid format for Date and Time, Try again (format: yyyy-MM-dd HH:mm):"
                    JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.SENDTRADEREQUESTMENU, 3));
                    return;
                }
                cpg.tradingUserActions.counterTradeRequest(useCases.tradeCreator, username, trade, meetingPlace.getText(),
                        timeOfTrade);
                JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.SENDTRADEREQUESTMENU, 4));
                new TradingUserActionsMenu(useCases, cpg, username, frame);
            }
        });

    }
}
