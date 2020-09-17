package usecasepack;

import java.io.Serializable;
import java.util.HashMap;

public class ValidateLoginStrategy implements Serializable {

    /** Method that validates login
     *
     * @param username login username
     * @param password login password
     * @param validLogins a hashmap with valid usernames as keys and their passwords as values
     * @return whether or not the login is valid
     */
    public boolean validateLogin(String username, String password, HashMap<String, String> validLogins) {
        String login = null;
        for (String loginUsername : validLogins.keySet()) {
            if (loginUsername.equals(username)) {
                login = loginUsername;
            }
        }
        if (login != null) {
            if (validLogins.get(login).equals(password)) {
                return true;
            }
        }
        return false;
    }

    /** Method that returns whether or not the username is available
     *
     * @param username username that is checked
     * @param validLogins a hashmap with valid usernames as keys and their passwords as values
     * @return true if the username is not a key in validLogins. false otherwise.
     */
    public boolean usernameAvailable(String username, HashMap<String, String> validLogins) {
        if (validLogins.isEmpty()) {
            return true;
        }
        for (String loginUsername : validLogins.keySet()) {
            if (loginUsername.equals(username)) {
                return false;
            }
        }
        return true;
    }
}