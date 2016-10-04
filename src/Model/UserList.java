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

}
