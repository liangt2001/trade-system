package usecasepack;

import entitypack.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class UserValidateLoginStrategy extends ValidateLoginStrategy implements Serializable {

    /** A method that converts a list of all valid logins into a hashmap where keys are usernames their values are the
     *  respective passwords
     *
     * @param logins an arraylist of all users in the system
     * @return a hashmap where the keys are usernames and their vlaues are the respective passwords
     */
    public HashMap<String, String> turnListIntoHashMap(ArrayList<User> logins) {
        HashMap<String, String> loginHashMap = new HashMap<String, String>();
        for (User login : logins) {
            loginHashMap.put(login.getUsername(), login.getPassword());
        }
        return loginHashMap;

    }
}
