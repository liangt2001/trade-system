package usecasepack;

import entitypack.AdminLogin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class AdminValidateLoginStrategy extends ValidateLoginStrategy implements Serializable {

    /** Method that converts a list of all valid logins into a hashmap where keys are usernames their values are the
     *  respective passwords
     *
     * @param logins an arraylist of AdminLogins
     * @return a hashmap where keys are usernames and their values are the respective passwords
     */
    public HashMap<String, String> turnListIntoHashMap(ArrayList<AdminLogin> logins) {
        HashMap<String, String> loginHashMap = new HashMap<String, String>();
        for (AdminLogin login : logins) {
            loginHashMap.put(login.getUsername(), login.getPassword());
        }
        return loginHashMap;
    }
}
