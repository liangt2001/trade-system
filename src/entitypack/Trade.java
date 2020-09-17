package entitypack;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * An Entity class describing a trade, which stores users involved and items exchanged among other data.
 */
public class Trade implements Serializable {

    /**
     * the unique ID of this trade.
     */
    protected int tradeID;

    /**
     * the username of the first user involved.
     */
    protected String username1;
    /**
     * the username of the second user involved.
     */
    protected String username2;

    /**
     * A boolean describing whether or not user 1 has accepted the trade.
     */
    protected boolean user1AcceptedRequest = false;
    /**
     * A boolean describing whether or not user 2 has accepted the trade.
     */
    protected boolean user2AcceptedRequest = false;

    /**
     * A boolean describing whether or not user 1 has confirmed the trade.
     */
    protected boolean user1TradeConfirmed = false;
    /**
     * A boolean describing whether or not user 2 has confirmed the trade.
     */
    protected boolean user2TradeConfirmed = false;

    /**
     * A boolean representing whether or not the users of the trade have been alerted that their trade should have been
     * confirmed, as the date at which they were to exchange the items has passed.
     */
    protected boolean usersAlertedToPastDue = false;

    /**
     * The number of times user 1 has edited the trade request.
     */
    protected int user1NumRequests = 0;
    /**
     * The number of times user 2 has edited the trade request.
     */
    protected int user2NumRequests = 0;
    /**
     * the LocalDateTime object representing when the users are to meet up to exchange their items.
     */
    protected LocalDateTime timeOfTrade;
    /**
     * A string describing the place where the users
     */
    protected String meetingPlace;
    /**
     * an arraylist of all the ids of items sent to user 1 in this trade.
     */
    protected ArrayList<Integer> itemIDsSentToUser1;
    /**
     * an arraylist of all the ids of items sent to user 2 in this trade.
     */
    protected ArrayList<Integer> itemIDsSentToUser2;

    /**
     * Constructs a new trade, storing users involved, items sent to both users, and a unique ID
     * @param username1 the username of the first user involved.
     * @param username2 the username of the second user involved.
     * @param itemIDsSentToUser1 a list of IDs of items sent to user 1.
     * @param itemIDsSentToUser2 a list of IDs of items sent to user 2.
     * @param tradeID the unique ID of this trade.
     */
    public Trade(String username1, String username2,
                 ArrayList<Integer> itemIDsSentToUser1, ArrayList<Integer> itemIDsSentToUser2, int tradeID){
        this.username1 = username1;
        this.username2 = username2;
        this.itemIDsSentToUser1 = itemIDsSentToUser1;
        this.itemIDsSentToUser2 = itemIDsSentToUser2;
        this.tradeID = tradeID;
    }

    /**
     * Return whether or not the trade has been accepted and confirmed by both parties. The trade is still
     * considered a trade request while this returns false.
     * @return A boolean determining whether or not the trade is completed and items have been exchanged.
     */
    public boolean isTradeCompleted(){
        return user1AcceptedRequest && user2AcceptedRequest && user1TradeConfirmed && user2TradeConfirmed;
    }

    /**
     * Return whether or not the trade has been accepted by both parties. The this returns false while
     * the two parties are still negotiating the trade.
     * @return A boolean determining whether or not the trade request is accepted by both parties.
     */
    public boolean isTradeRequestAccepted(){
        return user1AcceptedRequest && user2AcceptedRequest;
    }

    /**
     *
     * @return the username of user1 in the trade.
     */
    public String getUsername1(){
        return this.username1;
    }

    /**
     *
     * @return the username of user2 in the trade.
     */
    public String getUsername2(){
        return this.username2;
    }

    /**
     *
     * @param user1AcceptedRequest whether or not user1 has accepted the trade request.
     */
    public void setUser1AcceptedRequest(boolean user1AcceptedRequest) {
        this.user1AcceptedRequest = user1AcceptedRequest;
    }

    /**
     *
     * @param user2AcceptedRequest whether or not user2 has accepted the trade request.
     */
    public void setUser2AcceptedRequest(boolean user2AcceptedRequest){
        this.user2AcceptedRequest = user2AcceptedRequest;
    }

    /**
     *
     * @param user1TradeConfirmed whether or not user1 has confirmed the trade.
     */
    public void setUser1TradeConfirmed(boolean user1TradeConfirmed){
        this.user1TradeConfirmed = user1TradeConfirmed;
    }

    /**
     *
     * @param user2TradeConfirmed whether or not user2 has confirmed the trade.
     */
    public void setUser2TradeConfirmed(boolean user2TradeConfirmed){
        this.user2TradeConfirmed = user2TradeConfirmed;
    }

    /**
     *Increments the number of times User1 has sent this trade as a counter-offer.
     */
    public void incrementUser1NumRequests(){
        user1NumRequests+=1;
    }

    /**
     * Increments the number of times User2 has sent this trade as a counter-offer.
     */
    public void incrementUser2NumRequests(){
        user2NumRequests+=1;
    }

    /**
     *
     * @return the list of item IDs that are being transferred from user2 to user 1.
     */
    public ArrayList<Integer> getItemIDsSentToUser1(){ return this.itemIDsSentToUser1; }

    /**
     *
     * @return the list of item IDs that are being transferred from user1 to user 2.
     */
    public ArrayList<Integer> getItemIDsSentToUser2(){
        return this.itemIDsSentToUser2;
    }

    /**
     *
     * @return the time & date the trade will take place.
     */
    public LocalDateTime getTimeOfTrade(){
        return this.timeOfTrade;
    }

    /**
     *
     * @param newTimeOfTrade the time & date the trade will take place.
     */
    public void setTimeOfTrade(LocalDateTime newTimeOfTrade){
        this.timeOfTrade = newTimeOfTrade;
    }

    /**
     *
     * @return the location of the trade.
     */
    public String getMeetingPlace(){
        return this.meetingPlace;
    }

    /**
     *
     * @param meetingPlace the location of the trade.
     */
    public void setMeetingPlace(String meetingPlace){
        this.meetingPlace = meetingPlace;
    }

    /**
     *
     * @param user1NumRequests the number of offers (will be 1) and counter-offers made by user 1.
     */
    public void setUser1NumRequests(int user1NumRequests) {
        this.user1NumRequests = user1NumRequests;
    }

    /**
     *
     * @param user2NumRequests the number of counter-offers made by user 2.
     */
    public void setUser2NumRequests(int user2NumRequests) {
        this.user2NumRequests = user2NumRequests;
    }

    /**
     *
     * @return the number of offer (will be 1) and counter-offers made by user 1.
     */
    public int getUser1NumRequests() {
        return user1NumRequests;
    }

    /**
     *
     * @return the number of counter-offers made by user 2.
     */
    public int getUser2NumRequests() {
        return user2NumRequests;
    }

    /**
     *
     * @return the ID number of the trade.
     */
    public int getTradeID(){return tradeID;}

    /**
     *
     * @return returns whether the users have been alerted to the fact that the trade is past due.
     */
    public boolean getUsersAlertedToPastDue() {
        return usersAlertedToPastDue;
    }

    /**
     *
     * @return whether user 1 has accepted the trade request
     */
    public boolean getUser1AcceptedRequest() {
        return user1AcceptedRequest;
    }

    /**
     *
     * @return whether user 2 has accepted the trade request
     */
    public boolean getUser2AcceptedRequest() {
        return user2AcceptedRequest;
    }

    /**
     *
     * @return whether user 1 has confirmed the trade occurred
     */
    public boolean getUser1TradeConfirmed() {
        return user1TradeConfirmed;
    }

    /**
     *
     * @return whether user 2 has confirmed the trade occurred.
     */
    public boolean getUser2TradeConfirmed() {
        return user2TradeConfirmed;
    }
}
