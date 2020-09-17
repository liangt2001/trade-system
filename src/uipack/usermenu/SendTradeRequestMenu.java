package uipack.usermenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.Item;
import entitypack.TradingUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * A UI class to display the ability for users to send trade requests
 */
public class SendTradeRequestMenu {
    private JButton sendTradeRequestButton;
    private JButton backButton;
    private JPanel mainPanel;
    private JLabel userViewingLabel;
    private JLabel usertoViewLabel;
    private JTextField meetingPlace;
    private JTextField meetingTime;
    private JLabel meetingPlaceLabel;
    private JLabel meetingTimeLabel;
    private JScrollPane userToViewItemsPane;
    private JScrollPane myItemsPane;
    private JCheckBox isTempTradeBox;

    /**
     * Constructs the interface to send trade requests
     * @param useCases the user case grouper
     * @param cpg the controller presenter grouper
     * @param activeUsername the current user's username
     * @param userToViewUsername the trade recipient's username
     * @param frame the main window displayed to the trading user of the program
     */
    public SendTradeRequestMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, String activeUsername,
                                String userToViewUsername, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        JPanel userToViewItemsPaneContent = new JPanel();
        JPanel myItemsPaneContent = new JPanel();
        userToViewItemsPaneContent.setLayout(new GridBagLayout());
        myItemsPaneContent.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        usertoViewLabel.setText(userToViewUsername + cpg.menuPresenter.getText(Frame.SENDTRADEREQUESTMENU, 0));
        userViewingLabel.setText(activeUsername + cpg.menuPresenter.getText(Frame.SENDTRADEREQUESTMENU, 0));

        meetingPlaceLabel.setText(cpg.menuPresenter.getText(Frame.SENDTRADEREQUESTMENU, 1));
        meetingTimeLabel.setText(cpg.menuPresenter.getText(Frame.SENDTRADEREQUESTMENU, 2));

        sendTradeRequestButton.setText(cpg.menuPresenter.getText(Frame.SENDTRADEREQUESTMENU, 5));
        backButton.setText(cpg.menuPresenter.getText(Frame.SENDTRADEREQUESTMENU, 6));
        isTempTradeBox.setText(cpg.menuPresenter.getText(Frame.SENDTRADEREQUESTMENU, 8));

        JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.SENDTRADEREQUESTMENU,7));

        ArrayList<Item> user2Items= useCases.itemManager.getAvailableItems(userToViewUsername);
        ArrayList<Item> user1Items= useCases.itemManager.getAvailableItems(activeUsername);

        ArrayList<JCheckBox> user1ItemsCheckboxes = new ArrayList<JCheckBox>();
        ArrayList<JCheckBox> user2ItemsCheckboxes = new ArrayList<JCheckBox>();

        int n1 = 0;
        for (Item item: user2Items){
            JCheckBox box = new JCheckBox();
            box.setText(item.getName() + " -- itemID: " + item.getId());
            user2ItemsCheckboxes.add(box);
            c.gridy = n1;
            n1++;
            userToViewItemsPaneContent.add(box, c);
        }

        int n2 = 0;
        c.gridy = 0;
        for (Item item: user1Items){
            JCheckBox box = new JCheckBox();
            box.setText(item.getName() + " -- itemID: " + item.getId());
            user1ItemsCheckboxes.add(box);
            c.gridy = n2;
            n2++;
            myItemsPaneContent.add(box, c);
        }

        myItemsPane.setViewportView(myItemsPaneContent);
        userToViewItemsPane.setViewportView(userToViewItemsPaneContent);


        sendTradeRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ArrayList<Integer> otherSelectedItems = new ArrayList<Integer>();
                ArrayList<Integer> mySelectedItems = new ArrayList<Integer>();

                for (int i = 0; i < user1Items.size(); i++){
                    if (user1ItemsCheckboxes.get(i).isSelected()){
                        mySelectedItems.add(user1Items.get(i).getId());
                    }
                }

                for (int i = 0; i < user2Items.size(); i++){
                    if (user2ItemsCheckboxes.get(i).isSelected()){
                        otherSelectedItems.add(user2Items.get(i).getId());
                    }
                }

                TradingUser user1 = (TradingUser) useCases.userManager.searchUser(activeUsername);
                TradingUser user2 = (TradingUser) useCases.userManager.searchUser(userToViewUsername);

                LocalDateTime timeOfTrade = null;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                try {
                    timeOfTrade = LocalDateTime.parse(meetingTime.getText(), formatter);
                } catch (DateTimeParseException e) {
                    // "Invalid format for Date and Time, Try again (format: yyyy-MM-dd HH:mm):"
                    JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.SENDTRADEREQUESTMENU, 3));
                    return;
                }
                boolean isTempTrade = isTempTradeBox.isSelected();
                cpg.tradingUserActions.createTradeRequest(useCases.tradeCreator, user1, user2, otherSelectedItems,
                        mySelectedItems, timeOfTrade, meetingPlace.getText(), isTempTrade);
                //"Trade Request sent successfully"
                JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.SENDTRADEREQUESTMENU, 4));
                TradingUserActionsMenu userActionsMenu = new TradingUserActionsMenu(useCases,
                        cpg, activeUsername, frame);
            }
        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ViewUserMenu(useCases, cpg, activeUsername, userToViewUsername, frame, true,
                        useCases.userManager.searchUser(userToViewUsername) instanceof TradingUser);
            }
        });
    }

    private ArrayList<Integer> getSelectedItemIdsHelper(JList bruh){
        ArrayList<Integer> selectedItems = new ArrayList<>();

        ListModel list = bruh.getModel();
        for(int i = 0; i < list.getSize(); i++){
            JCheckBox checkbox = (JCheckBox) list.getElementAt(i);
            if (checkbox.isSelected()){
                selectedItems.add(Integer.parseInt(checkbox.getText().split(" - ")[1]));
            }
        }

        return selectedItems;
    }


}
