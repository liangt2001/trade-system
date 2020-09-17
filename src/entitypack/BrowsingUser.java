package entitypack;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class defining the data associated with a user who cannot make any trades, but only browses.
 */
public class BrowsingUser implements Serializable, User {

    private String username;
    private String password;
    private MetroArea metro;
    private ArrayList<String> messages = new ArrayList<String>();

    /**
     *
     * @param pass string to be checked against this user's password.
     * @return whether the entered string matches this user's password.
     */
    public boolean checkPassword(String pass){return pass.equals(password);}

    /**
     * @return the username of the browsing user
     */
    public String getUsername() {return username;}

    /**
     * @return the password of this user
     */
    public String getPassword() { return password; }

    /**
     * Sets the username of the browsing user to be username
     * @param username the expected username
     */
    public BrowsingUser(String username){
        this.username = username;
    }

    /**
     * @return the metro area of the browsing user
     */
    public MetroArea getMetro(){
        return this.metro;
    }

    /**
     * Sets the metro area of the browsing user to be metro
     * @param metro the expected username
     */
    public void setMetro(MetroArea metro){
        this.metro = metro;
    }

    /**
     * Sets the password of the browsing user to be password
     * @param password the expected password
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Adds message to the messages list of the browsing user
     * @param message the message received
     */
    public void addMessage(String message) {
        messages.add(message);
    }

    /**
     * @return the messages list of the browsing user
     */
    public ArrayList<String> getMessages() {
        return messages;
    }
}
