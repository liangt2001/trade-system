package uipack.usermenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.Item;
import entitypack.TradingUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A UI class for users to view a specific other user
 */
public class ViewUserMenu {
    private JPanel mainPanel;
    private JButton backButton;
    private JTextPane itemList;
    private JButton createTradeRequestButton;
//  private JTextPane userTitle;
    private JTextPane wishList;
    private JButton sendMessageButton;
    private JLabel frozenStatus;
    private JLabel userTitleLabel;
    private JLabel isActive;
    private JButton suggestTradeButton;
    private JButton addItemToWishlistButton;

    /**
     * Constructs the interface to view another user
     * @param useCases the use case grouper
     * @param cpg the controller presenter grouper
     * @param activeUsername the viewing user's username
     * @param userToViewUsername the username of the user who is being viewed
     * @param frame the main window displayed to the trading user of the program
     * @param isTradingUserViewing boolean for whether or not the viewing user is a trading user
     * @param isUserToViewTrading boolean for whether or not the user being viewed is a trading user
     */
    public ViewUserMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg,
                        String activeUsername, String userToViewUsername, JFrame frame, boolean isTradingUserViewing,
                        boolean isUserToViewTrading) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        backButton.setText(cpg.menuPresenter.getText(Frame.VIEWUSERMENU, 3));
        createTradeRequestButton.setText(cpg.menuPresenter.getText(Frame.VIEWUSERMENU, 4));
        sendMessageButton.setText(cpg.menuPresenter.getText(Frame.VIEWUSERMENU, 8));
        addItemToWishlistButton.setText(cpg.menuPresenter.getText(Frame.VIEWUSERMENU, 11));
        boolean userToViewIsFrozen;
        boolean active;

        if (isUserToViewTrading) {
            userToViewIsFrozen = ((TradingUser) useCases.userManager.searchUser(userToViewUsername)).getFrozen();
            active = ((TradingUser) useCases.userManager.searchUser(userToViewUsername)).isActive();

            TradingUser user1 = (TradingUser) useCases.userManager.searchUser(activeUsername);
            if(user1.getFrozen()){
                createTradeRequestButton.setEnabled(false);
                JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.VIEWUSERMENU, 13));
            } else if (!user1.isActive()){
                createTradeRequestButton.setEnabled(false);
                JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.VIEWUSERMENU, 12));
            }
        } else{
            userToViewIsFrozen = false;
            active = true;
        }

        if (!isTradingUserViewing) {
            createTradeRequestButton.setEnabled(false);
            suggestTradeButton.setEnabled(false);
            addItemToWishlistButton.setEnabled(false);
        }

        if (cpg.tradingUserActions.isTradingUser(useCases.userManager, userToViewUsername)){
             userTitleLabel.setText(cpg.menuPresenter.getText(Frame.VIEWUSERMENU, 0) + userToViewUsername);

             if (userToViewIsFrozen){
                 frozenStatus.setText(cpg.menuPresenter.getText(Frame.VIEWUSERMENU, 6));
                 createTradeRequestButton.setEnabled(false);
             }

             if (!active){
                 isActive.setText(cpg.menuPresenter.getText(Frame.VIEWUSERMENU, 7));
                 createTradeRequestButton.setEnabled(false);
             }


            ArrayList<Item> availableItems = cpg.tradingUserActions.getAvailableItems(useCases.itemManager,
                    userToViewUsername);
            StringBuilder availItemsString = new StringBuilder();

            for (Item item : availableItems){
                availItemsString.append(item.getName() + " ID: " + item.getId() + "\n");
            }

            itemList.setText(cpg.menuPresenter.getText(Frame.VIEWUSERMENU, 9) + "\n" +
                    availItemsString.toString());

            ArrayList<String> wishlistItems = cpg.tradingUserActions.getWishListItems(useCases.userManager,
                    userToViewUsername);
            StringBuilder wishlistItemsString = new StringBuilder();

            for (String wishlistItem : wishlistItems){
                wishlistItemsString.append(wishlistItem + "\n");
            }

            wishList.setText(cpg.menuPresenter.getText(Frame.VIEWUSERMENU, 10) + "\n" +
                    wishlistItemsString.toString());

        } else {
            userTitleLabel.setText(cpg.menuPresenter.getText(Frame.VIEWUSERMENU, 0) + userToViewUsername);
            createTradeRequestButton.setEnabled(false);
            suggestTradeButton.setEnabled(false);
            addItemToWishlistButton.setEnabled(false);
        }



        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewAllOtherUsersMenu viewOtherUsersMenu = new ViewAllOtherUsersMenu(useCases, cpg, activeUsername,
                        frame, isTradingUserViewing);
            }
        });

        addItemToWishlistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new AddItemToWishlistMenu(useCases, cpg, activeUsername, userToViewUsername, frame,
                        isTradingUserViewing, isUserToViewTrading);
            }
        });

        createTradeRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {



                if (isTradingUserViewing) {
                    //note: availableItemIDs here is a list of all possible items to select in the trade request, not
                    // the IDs the user already wants to trade for.
                    if (userToViewIsFrozen) {
                        JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.VIEWUSERMENU, 6));
                        return;
                    } else if (!active){
                        JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.VIEWUSERMENU, 7));
                        return;
                    }
                    SendTradeRequestMenu sendTradeRequestMenu = new SendTradeRequestMenu(useCases, cpg, activeUsername,
                            userToViewUsername, frame);
                } else{
                    JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.VIEWUSERMENU, 5));
                    return;
                }

            }
        });

        suggestTradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SuggestTrade(useCases, cpg, activeUsername, userToViewUsername, frame);
            }
        });


        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = JOptionPane.showInputDialog(frame,cpg.menuPresenter.getText(Frame.SENDMESSAGEMENU, 2),
                        cpg.menuPresenter.getText(Frame.SENDMESSAGEMENU, 0), JOptionPane.PLAIN_MESSAGE);
                if (!message.isEmpty()) {
                    cpg.tradingUserActions.messageUser(activeUsername, userToViewUsername, message, useCases.userManager);
                    JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.SENDMESSAGEMENU, 3));
                }
            }
        });
    }
}
