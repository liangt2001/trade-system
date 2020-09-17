package usecasepack;

import entitypack.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.HashMap;

/**
 * A use case class describing the business rules of the users in the trade system.
 */
public class UserManager implements Serializable{
    //Note: We are aware this is static. We are afraid to delete it because we don't remember who put it here and why,
    // also we have to submit our phase 1 in an hour so please ignore this its probably not nescesary. Ty <3
    private static final long serialVersionUID = 886852790530090694L;

    private ArrayList<User> listUsers = new ArrayList<User>(); // List of all users
    private int incompleteThreshold = 3; // # of incomplete trades allowed

    private UserValidateLoginStrategy strategy = new UserValidateLoginStrategy();


    /**
     * Method called on program Start up.
     */
    public void onStartUp(){

    }

    /**
     * Return true iff username and password are a valid user login.
     * @param username the username in question
     * @param password the password in question
     * @return a boolean determining whether or not the login info is valid.
     */
    public boolean validateLogin(String username, String password){
        return strategy.validateLogin(username, password, strategy.turnListIntoHashMap(listUsers));
    }

    /**
     * Return true iff the user with username is a TradingUser
     * @param username the username of the user.
     * @return a boolean determining whether or not the user is a TradingUser
     */
    public boolean isTradingUser(String username){
        User user = searchUser(username);
        return user instanceof TradingUser;
    }

    /**
     * Return a list of all users registered in the program.
     * @return an arraylist of all users.
     */
    public ArrayList<User> getListUsers() {
        return listUsers;
    }




    /**
     * Adds a new user to the system
     * @param username the username of the new user
     * @param password the password of the new user
     * @param isBrowsingOnly a boolean specifying whether or not the user can have and trade items
     * @param metro the metro area of the user.
     * @return true iff the account creation was successful, else return false.
     */
    public boolean addNewLogin(String username, String password, boolean isBrowsingOnly, MetroArea metro) {
        if (strategy.usernameAvailable(username, strategy.turnListIntoHashMap(listUsers))) {
            if (isBrowsingOnly) {
                createBrowsingUser(username, password, metro);
            } else {
                createTradingUser(username, password, metro);
            }
            return true;
        }
        return false;
    }

    /** Method which creates a user and adds it to the list of users
     * Author: Jinyu Liu
     * @param username username of user
     * @param password password of user
     * @return the created user
     */
    public TradingUser createTradingUser(String username, String password, MetroArea metro)
    {
        TradingUser newUser = new TradingUser(username);
        newUser.setPassword(password);
        newUser.setMetro(metro);
        listUsers.add(newUser);
        return newUser;
    }

    /** Method which creates a user and adds it to the list of users
     * Author: Jinyu Liu
     * @param username username of user
     * @param password password of user
     * @return the created user
     */ //UseCasePack.UserManager
    public BrowsingUser createBrowsingUser(String username, String password, MetroArea metro)
    {
        BrowsingUser newUser = new BrowsingUser(username);
        newUser.setPassword(password);
        newUser.setMetro(metro);
        listUsers.add(newUser);
        return newUser;
    }

    /**
     * Adds an item to the user's wishlist
     * @param username the username of the user.
     * @param itemName name of item
     */ //UseCasePack.UserManager
    public boolean addToWishlist(String username, String itemName){

        TradingUser user = (TradingUser)searchUser(username);
        if (user != null){
            return user.addItemToWishList(itemName);
        } else{
            return false;
        }
    }

    /**
     * removes an item from the user's wishlist
     * @param user user
     * @param itemName name of item
     */ //UseCasePack.UserManager
    public void removeFromWishList(TradingUser user, String itemName){
        user.removeItemFromWishList(itemName);
    }


    /**
     * @param user the trading user that the items in their wishlist are being inspected
     * @param itemName the name of the item that is being searched for
     * @return true iff user contains itemName in their wishlist
     */
    public boolean checkIfUserContain(TradingUser user, String itemName) { return user.containItemInWishlist(itemName); }


    /** Method which returns a user when given their username
     * Author: Louis Scheffer V
     * @param username username of the user
     * @return user object
     */ //UseCasePack.UserManager
    public User searchUser(String username){
        for(User user: listUsers){
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    /**
     * Formats a message with messageBody and sendersUsername and sends it to the User with receiversUsername
     * @param sendersUsername the username of the User who sends the message
     * @param receiversUsername the username of the User who will receiver the message
     * @param messageBody the text part inputted by the sender
     */
    public void messageUser(String sendersUsername, String receiversUsername, String messageBody) {
        String message = "[" + sendersUsername + "] ------ " + LocalDateTime.now() + "\n" +
                messageBody + "\n" +
                "--------------------------------------------------------------------" + "\n";
        User user = searchUser(receiversUsername);
        user.addMessage(message);
    }

    /**
     * @param username the username of the User whose messages are being searched for
     * @return the list of messages which the User with username receives
     */
    public ArrayList<String> getUserMessages(String username) {
        User user = searchUser(username);
        return user.getMessages();
    }

    /**
     *
     * @return the threshold of lent - borrowed which all users should be at or above.
     */
    public int getIncompleteThreshold() {
        return incompleteThreshold;
    }

    /**
     *
     * @param incompleteThreshold the threshold of lent - borrowed which all users should be at or above.
     */
    public void setIncompleteThreshold(int incompleteThreshold) {
        this.incompleteThreshold = incompleteThreshold;
    }

    /**
     *
     * @param user the number by which to increase the number of incomplete trades attributed to a user by.
     */
    public void increaseUserIncompleteTrades(TradingUser user){
        user.increaseNumIncompleteTrades(1);
    }

    /**
     *
     * @param user the user which is the number of incomplete trades is being queried.
     * @return the number of incomplete trades attributed to the user.
     */
    public int getUserIncompleteTrades(TradingUser user){
        return user.getNumIncompleteTrades();
    }

    /** Method to set which metropolitan area a user is based in.
     *
     * @param username the username of the user.
     * @param metroArea the metro area the user will be based in.
     */
    public void setUserMetro(String username, MetroArea metroArea){
        User user = searchUser(username);
        user.setMetro(metroArea);
    }

    /** Method to return a list of users by the metropolitan area they are located in.
     *
     * @param metroArea metro area you are searching
     * @return the list of active users in that metro.
     */
    public ArrayList<User> searchUsersByMetro(MetroArea metroArea){
        ArrayList<User> usersInMetro = new ArrayList<User>();
        for(User user: listUsers){
            if (user.getMetro().equals(metroArea)){
                usersInMetro.add(user);
            }
        }
        return usersInMetro;
    }

    /** Method to get the metro area of a user
     *
     * @param user the user whose metro area you are checking.
     * @return the user
     */
    public MetroArea getUsersMetro(User user){
        return user.getMetro();
    }
}