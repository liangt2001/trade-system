package entitypack;

import java.io.Serializable;
import java.util.*;

/**
 * An Entity class describing a user of a program.
 */
public class TradingUser implements Serializable, User {
    // Sorting method orderPartners() is taken from https://howtodoinjava.com/sort/java-sort-map-by-values/

    private final String username;
    private String password; // private so no one can access except EntityPack.User; have setters and getters for change password function
    private int numLent;
    private int numBorrowed;
    private int numIncompleteTrades;
    private boolean frozen = false; // true being frozen or Lent>Borrowed; false being no violations
    private boolean active = true;
    private ArrayList<String> wishlistItemNames = new ArrayList<String>();// presenter needs to access this as well
    private ArrayList<String> messages = new ArrayList<String>();
    private MetroArea metro;


    /**
     * Creates a new user object.
     * @param username the username to give the user.
     */
    public TradingUser(String username) {
        numBorrowed = 0;
        numIncompleteTrades = 0;
        numLent = 0;
        this.username = username; // Admin needs to access to freeze; USerManager needs to access/search by EntityPack.User
    }

    /**
     * @return the username of the TradingUser
     */
    public String getUsername() {return username;}

    /**
     * Set the activeness of the User to active
     * @param active the expected active status
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return true if User is active, false otherwise
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @return the number of items they have borrowed.
     */
    public int getNumBorrowed() {
        return numBorrowed;
    }

    /**
     * @return the number of incomplete trades that have been attributed to this user.
     */
    public int getNumIncompleteTrades() {
        return numIncompleteTrades;
    }

    /**
     * @return the number of items that this user has lent.
     */
    public int getNumLent() {
        return numLent;
    }

    /**
     * Increases the numBorrowed index by x
     * @param x the number to increase the number of items this user has borrowed by.
     */
    public void increaseNumBorrowed(int x){
        numBorrowed += x;
    }

    /**
     * Increases the numLent index by x
     * @param x the number to increase the number of items this user has lent by.
     */
    public void increaseNumLent(int x){
        numLent += x;
    }

    /**
     * Increases the numIncompleteTrades index by x
     * @param x the number to increase the number of incomplete trades attributed to this user by.
     */
    public void increaseNumIncompleteTrades(int x){
        numIncompleteTrades += x;
    }

    /**
     * Sets the password of the TradingUser to password
     * @param password new password for this user.
     */
    public void setPassword(String password) { this.password = password; }// may want to extend a use case to change password if forgotten

    /**
     * @return the password of this user
     */
    public String getPassword() { return password; }

    /**
     * Checks if pass matches the password of TradingUser
     * @param pass string to be checked against this user's password.
     * @return whether the entered string matches this user's password.
     */
    public boolean checkPassword(String pass){return pass.equals(password);}

    //for adding and removing from wishlist and available-to-lend lists, and getters for this EntityPack.User's lists

    /**
     * @return The wish list of the user.
     */
    public ArrayList<String> getWishlistItemNames() {return this.wishlistItemNames;}

    /**
     * Adds item to the wishlist
     * @param item item name to be added to this user's wish list.
     * @return true iff item is not contained in the wishlist
     */
    public boolean addItemToWishList(String item){
        boolean itemIsThere = containItemInWishlist(item);
        if (!itemIsThere){
            wishlistItemNames.add(item);
        }
        return !itemIsThere;
    }

    /**
     * Removes item from the wishlist
     * @param item item name to be removed from the user's wish list
     */
    public void removeItemFromWishList(String item){
        wishlistItemNames.remove(item);
    }

    /**
     * Checks if itemSearched is contained in the wishlist
     * @param itemSearched the name of the item that is being searched for
     * @return true iff the user contains itemSearched in their wishlist
     */
    public boolean containItemInWishlist(String itemSearched) {
        for (String item: getWishlistItemNames()) {
            if (item.equals(itemSearched)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds message to the messages list of the TradingUser
     * @param message the message received
     */
    public void addMessage(String message) {
        messages.add(message);
    }

    /**
     * @return the message list of the TradingUser
     */
    public ArrayList<String> getMessages() {
        return messages;
    }

    /**
     * @return whether or not a user is frozen.
     */
    public boolean getFrozen(){ // wondering how to implement freeze function with this; or should this only be a ThresholdChecker?
        // no code to automatically freeze because design says admin needs to do this
        return frozen;
    }

    /**
     * Setter for frozen boolean. true means the account is frozen. false means the account is not frozen.
     * @param frozen whether or not a user is frozen.
     */
    public void setFrozen(boolean frozen){
    this.frozen = frozen;
    } // whether the account is set to frozen or not


    /**
     * Sets the metro area of TradingUser to metro
     * @param metro Supported metropolitan area the user is located in.
     */
    public void setMetro(MetroArea metro) {
        this.metro = metro;
    }

    /**
     * @return Supported metropolitan area the use is located in.
     */
    public MetroArea getMetro() {
        return this.metro;
    }

}
    // top 3 trading partners, access orderedPartners LinkedHashMap and return first three username Strings.
    // this needs to be updated after every transaction.

    //I added these getters and setters for use in UseCasePack.UserManager - Louis --> these were already made under different names,
    // I've modified your UseCasePack.UserManager code so they work for you. :) - Melody
