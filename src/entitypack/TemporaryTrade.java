package entitypack;

import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * An Entity class describing a temporary trade, which is a trade in which the items need to be traded back
 * after a time period.
 */
public class TemporaryTrade extends Trade implements Serializable {

    private LocalDateTime tradeUntil;

    private boolean user1ItemReturnRequestAccepted = false;
    private boolean user2ItemReturnRequestAccepted = false;

    //Two constructors, one that takes a date for the object to be returned and annother that sets it by
    //default to thirty days after the present.

    /**
     * Initializes a new temporary trade, storing users involved, items sent to each user, and a return date and ID.
     * @param username1 the username of the first user involved
     * @param username2 the username of the second user involved
     * @param itemIDsSentToUser1 A list of IDs of items sent to user 1 in this trade
     * @param itemIDsSentToUser2 A list of IDs of items sent to user 2 in this trade
     * @param tradeUntil a LocalDateTime object representing the time when the items should be given back.
     * @param TradeID the unique Id of this EntityPack.Trade.
     */
    public TemporaryTrade(String username1, String username2, ArrayList<Integer> itemIDsSentToUser1,
                          ArrayList<Integer> itemIDsSentToUser2, LocalDateTime tradeUntil, int TradeID) {
        super(username1, username2, itemIDsSentToUser1, itemIDsSentToUser2, TradeID);
        this.tradeUntil = tradeUntil;
    }
    /**
     * Initializes a new temporary trade, storing users involved, items sent to each user, and a return date and ID.
     * By default the temporary trade lasts 30 days.
     * @param username1 the username of the first user involved
     * @param username2 the username of the second user involved
     * @param itemIDsSentToUser1 A list of IDs of items sent to user 1 in this trade
     * @param itemIDsSentToUser2 A list of IDs of items sent to user 2 in this trade
     * @param TradeID the unique Id of this EntityPack.Trade.
     */
    public TemporaryTrade(String username1, String username2, ArrayList<Integer> itemIDsSentToUser1,
                          ArrayList<Integer> itemIDsSentToUser2, int TradeID){
        super(username1, username2, itemIDsSentToUser1, itemIDsSentToUser2, TradeID);
        LocalDateTime now = LocalDateTime.now();
        this.tradeUntil = now.plusDays(30);
    }

    /**
     *
     * @return the time & date at which this temporary trade expires.
     */
    public LocalDateTime getDueDate(){
        return this.tradeUntil;
    }

    /**
     *
     * @param newDueDate the new time & date that this temporary trade will end.
     */
    public void setDueDate(LocalDateTime newDueDate){
        this.tradeUntil = newDueDate;
    }

    /**
     *
     * @return whether or not user1 has accepted the request to return the items.
     */
    public boolean getUser1ItemReturnRequestAccepted(){
        return this.user1ItemReturnRequestAccepted;
    }

    /**
     *
     * @return whether or not user2 has accepted the request to return the items.
     */
    public boolean getUser2ItemReturnRequestAccepted(){
        return this.user2ItemReturnRequestAccepted;
    }

    /**
     *
     * @param user1ItemReturnRequestAccepted whether or not user1 has accepted the request to return the items.
     */
    public void setUser1ItemReturnRequestAccepted(boolean user1ItemReturnRequestAccepted){
        this.user1ItemReturnRequestAccepted = user1ItemReturnRequestAccepted;
    }

    /**
     *
     * @param user2ItemReturnRequestAccepted whether or not user2 has accepted the request to return the items.
     */
    public void setUser2ItemReturnRequestAccepted(boolean user2ItemReturnRequestAccepted){
        this.user2ItemReturnRequestAccepted = user2ItemReturnRequestAccepted;
    }
}
