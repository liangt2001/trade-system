package entitypack;

import java.util.ArrayList;

/**
 * Interface defining common functionality to all users of the program.
 */
public interface User {
    final String username = null;

    String password = null;

    MetroArea metro = null;

    ArrayList<String> messages = new ArrayList<String>();

    String getUsername();

    MetroArea getMetro();

    void setMetro(MetroArea metro);

    void setPassword(String password);

    String getPassword();

    boolean checkPassword(String password);

    void addMessage(String message);

    ArrayList<String> getMessages();
}
