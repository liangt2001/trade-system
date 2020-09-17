package entitypack;

import java.io.Serializable;

/**
 * An entity class storing login info for a single admin account.
 */
public class AdminLogin implements Serializable {

    private String username;
    private String password;

    /**
     * Initialize a new AdminLogin with a specified username and password
     * @param username the username of the login
     * @param password the matching password
     */
    public AdminLogin(String username, String password){
        this.username = username;
        this.password = password;
    }

    /**
     * @return the username of the AdminLogin
     */
    public String getUsername() { return username; }

    /**
     * @return the password of the AdminLogin
     */
    public String getPassword() {return password;}
}
