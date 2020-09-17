package usecasepack;

import entitypack.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class which handles all of the items that are within the trading system.
 */
public class ItemManager implements Serializable {

    private ArrayList<Item> items = new ArrayList<Item>();

    private int validationRequestIDGenerator = 0;

    private ArrayList<ItemValidationRequest> validationRequests = new ArrayList<ItemValidationRequest>();

    private int itemIDGenerator = 0;

    /**
     * Creates a new item with specified name, ID, username of owner, and description.
     * @param itemName the name of the item
     * @param itemID the ID of the item.
     * @param usernameOfOwner the username of the item's owner.
     * @param description a short description of the item.
     */
    private void createItem(String itemName, int itemID, String usernameOfOwner, String description) {
        Item newItem = new Item(itemName, description, itemID, usernameOfOwner);
        items.add(newItem);
    }


    /**
     * Creates and adds a new item validation request to the validationRequests arraylist.
     * @param usernameOfOwner the username of the user sending the validation request.
     * @param itemName the name of the item to be validated.
     * @param description a short description of the item.
     */
    public void createItemValidationRequest(String usernameOfOwner, String itemName, String description){

        ItemValidationRequest validationRequest = new ItemValidationRequest(usernameOfOwner, itemName, description,
                validationRequestIDGenerator);
        validationRequestIDGenerator++;
        validationRequests.add(validationRequest);
    }

    /**
     * Removes validationRequest and creates the item associated with it
     * @param validationRequest the validation request sent by TradingUser
     */
    public void approveValidationRequest(ItemValidationRequest validationRequest){
        for (int i = 0; i < validationRequests.size(); i++){
            if (validationRequest.getID() == validationRequests.get(i).getID()){
                validationRequests.remove(i);
                createItem(validationRequest.getItemName(), itemIDGenerator, validationRequest.getUsernameOfCreator(),
                        validationRequest.getItemDescription());
                itemIDGenerator++;
                return;
            }
        }
    }

    /**
     *
     * @return all pending item validation requests in an arraylist.
     */
    public ArrayList<ItemValidationRequest> getValidationRequests(){
        return this.validationRequests;
    }


    /**
     * Get all available items for the user with specified username.
     * @param tradingUserUsername the username of the user from which to fetch the items
     * @return an arraylist of all items this user has available for trade
     */
    public ArrayList<Item> getAvailableItems(String tradingUserUsername){
        ArrayList<Item> availableItems = new ArrayList<Item>();
        for (Item item : items){
            if (item.getOwner().equals(tradingUserUsername) && item.getOwner().equals(item.getInPossessionOf())){
                availableItems.add(item);
            }
        }
        return availableItems;
    }

    /** Method which returns a item (that is in a user's available items) when given its ID number.
     * Returns null if an invalid ID is given
     * Author: Louis Scheffer V
     * @param itemID ID number of the item
     * @return item
     */
    public Item searchItem(int itemID) {
        for (Item item: items){
            if (itemID == item.getId()){
                return item;
            }
        }
        return null;
    }

    /** Method which exchanges the items in the trade system after a trade has been marked as completed
     * Author: Louis Scheffer V
     * @param trade trade object
     */
    public void exchangeItems(Trade trade, TradingUser user1, TradingUser user2){
        for(int itemID : trade.getItemIDsSentToUser1()){

            Item item = searchItem(itemID);
            ArrayList<String> wishlistItemsToRemoveUser1 = new ArrayList<String>();
            for (String string: user1.getWishlistItemNames()){
                if(item.getName().equals(string)){
                    wishlistItemsToRemoveUser1.add(string);
                }
            }
            for (String string : wishlistItemsToRemoveUser1){
                user1.removeItemFromWishList(string);
            }

            user1.increaseNumBorrowed(1);
            user2.increaseNumLent(1);

            if (!(trade instanceof TemporaryTrade)){
                item.setOwner(user1.getUsername());
            }
            item.setInPossessionOf(user1.getUsername());
        }

        for(int itemID : trade.getItemIDsSentToUser2()){
            Item item = searchItem(itemID);
            ArrayList<String> wishlistItemsToRemoveUser2 = new ArrayList<String>();
            for (String string: user2.getWishlistItemNames()){
                if(item.getName().equals(string)){
                    wishlistItemsToRemoveUser2.add(string);
                }
            }
            for (String string : wishlistItemsToRemoveUser2){
                user2.removeItemFromWishList(string);
            }

            user2.increaseNumBorrowed(1);
            user1.increaseNumLent(1);

            if (!(trade instanceof TemporaryTrade)){
                item.setOwner(user2.getUsername());
            }
            item.setInPossessionOf(user2.getUsername());
        }
    }

    /** Method which undoes an exchange of items in the trade system
     * @param trade trade object
     */
    public void undoExchangeItems(Trade trade, TradingUser user1, TradingUser user2) {
        for(int itemID : trade.getItemIDsSentToUser1()){
            Item item = searchItem(itemID);
            user1.increaseNumBorrowed(-1);
            user2.increaseNumLent(-1);

            if (!(trade instanceof TemporaryTrade)) {
                item.setOwner(user2.getUsername());
            }
            item.setInPossessionOf(user2.getUsername());
        }
        for(int itemID : trade.getItemIDsSentToUser2()){
            Item item = searchItem(itemID);
            //do borrowed and lent get incremented every trade or just during TemporaryTrades? - Louis
            user2.increaseNumBorrowed(-1);
            user1.increaseNumLent(-1);
            if (!(trade instanceof TemporaryTrade)) {
                item.setOwner(user1.getUsername());
            }
            item.setInPossessionOf(user1.getUsername());
        }
    }

    /** Method which returns items to their owners after the expiration of a temporary trade
     * Author: Louis Scheffer V
     * @param trade Temporary EntityPack.Trade Object
     */
    public void reExchangeItems(TemporaryTrade trade){
        for(int itemID : trade.getItemIDsSentToUser1()) {
            Item item = searchItem(itemID);
            item.setInPossessionOf(null);

        }
        for(int itemID : trade.getItemIDsSentToUser2()) {
            Item item = searchItem(itemID);
            item.setInPossessionOf(null);
        }
    }

    /**
     * Removes the item with itemID from the trading system.
     * @param itemID the ID of the item requested to be removed
     */
    public void removeFromInventory(int itemID) {
        Item item = searchItem(itemID);
        assert item != null;
        items.remove(item);
    }

    /**
     * @param user the trading user that the items in their inventory are being inspected
     * @param itemID the ID of the item that is being searched for
     * @return true iff user contains itemName in their inventory
     */
    public boolean checkIfUserHas(TradingUser user, int itemID) {
        Item item = searchItem(itemID);
        if (item == null) {
            return false;
        }
        else {
            return item.getInPossessionOf().equals(user.getUsername());
        }
    }

}
