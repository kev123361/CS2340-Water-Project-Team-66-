package Model;
import java.util.ArrayList;

/**
 * UserList class holds all the users in the current instance of the application
 * @author Shobhit Srivastava
 */
public class UserList {
    private static ArrayList<User> backingList = new ArrayList<User>();
    private static User currentUser;

    public static void addUser(User user) {
        backingList.add(user);
        currentUser = user;
    }

    public static boolean isValidLogin(String username, String password) {
        if (backingList.size() == 0) {
            return false;
        }
        for (int i = 0; i < backingList.size(); i++) {
            if (backingList.get(i).getUsername().equals(username)) {
                if (backingList.get(i).getPassword().equals(password)) {
                    currentUser = backingList.get(i);
                    return true;
                }
            }
        }
        return false;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static Boolean isUniqueUserName(String username) {
        for (int i = 0; i < backingList.size(); i++) {
            if (backingList.get(i).getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public static Boolean isUniqueID(String ID) {
        for (int i = 0; i < backingList.size(); i++) {
            if (backingList.get(i).getId().equals(ID)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checking if valid email address entered
     *
     * A valid email address should be of the form <something>@<somethingelse>.<somedomainname>
     *
     * @param email the email address
     * @return true if valid, false otherwise
     */
    public static Boolean isValidEmailAddress(String email) {
        if (email.contains("@") && email.indexOf("@") != 0 && email.indexOf("@") != email.length() - 1
                && email.contains(".") && email.indexOf(".") > email.indexOf("@") && email.indexOf(".") != email
                .length() - 1) {
            return true;
        }
        return false;
    }

    /**
     * Checking if valid home address entered
     *
     * For now, a valid home address is something of the form Some Address, City, State ZIP
     * This code checks to see if there are two commas followed by a space to define the address, city, and ZIP
     *
     * @param home the home address
     * @return true if valid, false otherwise
     */
    public static Boolean isValidHomeAddress(String home) {
        int commaCount = 0;
        int spaceCount = 0;
        for (int i = 0; i < home.length() - 1; i++) {
            if (home.charAt(i) == ',') {
                commaCount++;
            } else if (commaCount == 2 && home.charAt(i) == ' ') {
                spaceCount++;
            }
        }
        return commaCount == 2 && spaceCount == 2;
    }

}
