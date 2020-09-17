package controllerpresenterpack;

import entitypack.*;
import usecasepack.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A controller class describing the actions a user can take from the menu in the trade system.
 */
public class TradingUserActions extends UserActions {

    /**
     * Adds a new login to the system. Return false iff the login could not be added.
     * @param username the username to be registered.
     * @param password the password to be registered.
     * @param isTrading specifies whether or not the user is trading or not.
     * @param metro the metro area of the user.
     * @return a boolean determining whether or not the login was added.
     */
    public boolean addNewLogin(UserManager userManager, String username, String password, boolean isTrading,
                               MetroArea metro){
        return userManager.addNewLogin(username, password, isTrading, metro);
    }

    /**
     * Get all available items for the user with specified username.
     * @param tradingUserUsername the username of the user from which to fetch the items
     * @return an arraylist of all items this user has available for trade
     */
    public ArrayList<Item> getAvailableItems(ItemManager itemManager, String tradingUserUsername){
        return itemManager.getAvailableItems(tradingUserUsername);
    }

    /**
     * Creates a new item validation request and stores it in itemManager.
     * @param usernameOfCreator the username of the user creating the request.
     * @param itemName the name of the proposed item.
     * @param itemDescription a short description of the proposed item.
     */
    public void createItemValidationRequest(ItemManager itemManager, String usernameOfCreator,
                                            String itemName, String itemDescription){
        itemManager.createItemValidationRequest(usernameOfCreator, itemName, itemDescription);
    }

    /**
     * Get all wishlist item names for the user with the specified username.
     *
     * Precondition: tradingUserUserName must be the username of a trading user.
     *
     * @param tradingUserUsername the username of the trading user from which to fetch the wishlist entries.
     * @return an arraylist of strings representing wishlist items for the user.
     */
    public ArrayList<String> getWishListItems(UserManager userManager, String tradingUserUsername){
        return ((TradingUser)userManager.searchUser(tradingUserUsername)).getWishlistItemNames();
    }

    /**
     *
     * @param active whether or not a user is active
     * @param user user obejct being changed
     */
    public void setActive(Boolean active, TradingUser user){
        user.setActive(active);
    }

    /**
     * Method that prints out items that user1 can lend to user2 after adding an item to their wishlist from user2's
     * available items
     * @param userToView user whom the method will suggest items to trade
     * @param itemManager the ItemManager object
     * @param activeUser user who is asking for the suggestion
     */
    public ArrayList<Item> suggestItems(TradingUser userToView, TradingUser activeUser, ItemManager itemManager) {
        ArrayList<Item> listItems = new ArrayList<Item>();
        for (String wish : userToView.getWishlistItemNames()) {
            for (Item item : itemManager.getAvailableItems(activeUser.getUsername())) {
                if ((item.getName().toLowerCase().contains(wish.toLowerCase()) ||
                        item.getDescription().toLowerCase().contains(wish.toLowerCase()))) {
                    listItems.add(item);
                }
            }
        }
        return listItems;
    }

    /**
     * @return a boolean specifying whether or not the Trading user with username is currently frozen.
     */
    public boolean isUserFrozen(UserManager userManager, String username){
        return ((TradingUser)userManager.searchUser(username)).getFrozen();
    }

    /** Method which allows users to send a message to another user
     *
     * @param sendersUsername username of the sender
     * @param receiversUsername username of the recipient
     * @param messageBody body of the message
     * @param userManager the UserManager object
     */
    public void messageUser(String sendersUsername, String receiversUsername, String messageBody, UserManager userManager) {
        userManager.messageUser(sendersUsername, receiversUsername, messageBody);
    }

    /** Method which allows a user to message an admin
     *
     * @param sendersUsername username of the sender
     * @param messageBody body of the message
     * @param adminUser the AdminUser object
     */
    public void messageAdmin(String sendersUsername, String messageBody, AdminUser adminUser) {
        String message = "[" + sendersUsername + "] ------ " + LocalDateTime.now() + "\n" +
                messageBody + "\n" +
                "--------------------------------------------------------------------" + "\n";
        adminUser.addAdminMessage(message);
    }

    /** Method for a user to add an item to their wish list
     *
     * @param userManager the UserManager object
     * @param username the username of the user adding to their wish list
     * @param itemName the name of the item they wish to add to their wish list
     * @return
     */
    public boolean addItemToWishlist(UserManager userManager, String username, String itemName){
        return userManager.addToWishlist(username, itemName);
    }

    /** Method which creates a new trade request
     *
     * @param tradeCreator TradeCreator object
     * @param user1 first TradingUser involved in the trade
     * @param user2 second TradingUser involved in the trade
     * @param itemIDsSentToUser1 list of items by ID that will be sent to user 1
     * @param itemIDsSentToUser2 list of items by ID that will be sent to user 2
     * @param meetingTime time & date of the meeting
     * @param meetingPlace location of the meeting
     */
    public void createTradeRequest(TradeCreator tradeCreator, TradingUser user1, TradingUser user2,
                                   ArrayList<Integer> itemIDsSentToUser1, ArrayList<Integer> itemIDsSentToUser2,
                                   LocalDateTime meetingTime, String meetingPlace, boolean isTempTrade){
        if (isTempTrade) {
            tradeCreator.createTemporaryTradeRequest(user1, user2, itemIDsSentToUser1, itemIDsSentToUser2, meetingTime,
                    meetingPlace);
        } else {
            tradeCreator.createTradeRequest(user1, user2, itemIDsSentToUser1, itemIDsSentToUser2, meetingTime,
                    meetingPlace);
        }
    }

    /** Method which allows a user to accept a trade request
     *
     * @param tradeCreator TradeCreator object
     * @param username username of the user accepting the request
     * @param trade Trade object of the trade
     */
    public void acceptTradeRequest(TradeCreator tradeCreator, String username, Trade trade){
        tradeCreator.acceptTradeRequest(trade, username);
    }

    /** Method which allows a user to decline a trade request
     *
     * @param tradeCreator TradeCreator object
     * @param trade Trade object of the trade
     */
    public void declineTradeRequest(TradeCreator tradeCreator, Trade trade){
        tradeCreator.declineTradeRequest(trade);
    }

    /** Method wich allows a user to counter-offer a meeting time and locaion
     *
     * @param tradeCreator TradeCreator object
     * @param username username of the user countering the request
     * @param trade Trade object of the trade
     * @param newMeetingPlace new proposed location of the meeting
     * @param newDateTime new proposed time & date of the meeting
     */
    public void counterTradeRequest(TradeCreator tradeCreator, String username, Trade trade, String newMeetingPlace, LocalDateTime newDateTime){
        tradeCreator.editTradeRequest(trade, newDateTime, newMeetingPlace, username);
    }

    /** Method which allows a user to confirm the exchange of items has occurred
     *
     * @param userManager UserManager object
     * @param tradeCreator TradeCreator object
     * @param itemManager ItemManager object
     * @param trade Trade object of the trade
     * @param username username of the user confirming the exchange
     */
    public void confirmTrade(UserManager userManager, TradeCreator tradeCreator, ItemManager itemManager, Trade trade, String username){
        TradingUser user1 = (TradingUser) userManager.searchUser(trade.getUsername1());
        TradingUser user2 = (TradingUser) userManager.searchUser(trade.getUsername2());
        TradingUser userAccepting = (TradingUser) userManager.searchUser(username);
        tradeCreator.confirmTrade(userManager, userAccepting, trade, itemManager, user1, user2);
    }

    /** Method which allows a user to confirm a re-exhange has occurred
     *
     * @param userManager UserManager object
     * @param tradeCreator TradeCreator object
     * @param itemManager ItemManager object
     * @param trade TemporaryTrade object of the trade
     * @param username username of the user confirming the exchange
     */
    public void confirmReExchange(UserManager userManager, TradeCreator tradeCreator, ItemManager itemManager, TemporaryTrade trade, String username){
        tradeCreator.getTradeHistories().confirmReExchange(itemManager, (TradingUser) userManager.searchUser(username), trade);
    }

    /** Method which returns a list of pending trades involving a user
     *
     * @param username username of the user
     * @param userManager UserManager object
     * @param tradeCreator TradeCreator object
     * @return list of pending trades involving the user
     */
    public ArrayList<Trade> searchPendingTradesUser(String username, UserManager userManager, TradeCreator tradeCreator){
        TradingUser user = (TradingUser) userManager.searchUser(username);
        return tradeCreator.searchPendingTradesByUser(user);
    }

    /** Method which returns a list of pending trade requests involving a user
     *
     * @param username username of the user
     * @param userManager UserManager object
     * @param tradeCreator TradeCreator object
     * @return list of pending trades involving the user
     */
    public ArrayList<Trade> searchPendingTradeRequestsUser(String username, UserManager userManager, TradeCreator tradeCreator){
        TradingUser user = (TradingUser) userManager.searchUser(username);
        return tradeCreator.searchPendingTradeRequestsByUser(user);
    }

    /** Method which returns a list of current temporary trades involving a user
     *
     * @param username username of the user
     * @param userManager UserManager object
     * @param tradeCreator TradeCreator object
     * @return list of pending trades involving the user
     */
    public ArrayList<TemporaryTrade> searchCurrentTempTradesUser(String username, UserManager userManager, TradeCreator tradeCreator){
        TradingUser user = (TradingUser) userManager.searchUser(username);
        TradeHistories tradeHistories = tradeCreator.getTradeHistories();
        return tradeHistories.searchActiveTempTradesByUser(user);
    }

    /** Method which returns a list of completed trades involving a user
     *
     * @param username username of the user
     * @param userManager UserManager object
     * @param tradeCreator TradeCreator object
     * @return list of pending trades involving the user
     */
    public ArrayList<Trade> searchCompletedTradesUser(String username, UserManager userManager, TradeCreator tradeCreator){
        TradingUser user = (TradingUser) userManager.searchUser(username);
        TradeHistories tradeHistories = tradeCreator.getTradeHistories();
        return tradeHistories.searchCompletedTradesByUser(user);
    }

    /** Method which returns a list of dead trades involving a user
     *
     * @param username username of the user
     * @param userManager UserManager object
     * @param tradeCreator TradeCreator object
     * @return list of pending trades involving the user
     */
    public ArrayList<Trade> searchDeadTradesUser(String username, UserManager userManager, TradeCreator tradeCreator){
        TradingUser user = (TradingUser) userManager.searchUser(username);
        TradeHistories tradeHistories = tradeCreator.getTradeHistories();
        return tradeHistories.searchDeadTradesByUser(user);
    }

    /** Method which returns a Trade object when given a trade ID
     *
     * @param tradeCreator TradeCreator object
     * @param tradeID ID number of the trade
     * @return Trade object
     */
    public Trade searchTrade(TradeCreator tradeCreator, int tradeID){
        return tradeCreator.searchTrades(tradeID);
    }

}
