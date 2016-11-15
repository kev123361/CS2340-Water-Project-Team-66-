package Model;
import Fxapp.MainFXApplication;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * UserList class holds all the users in the current instance of the application
 *
 * @author Shobhit Srivastava
 * @version 1.0
 */
public class UserList {
    private static ArrayList<User> backingList = new ArrayList<User>();
    private static User currentUser;

    /**
     * Adds a user to the list of users
     * @param user the user to be added
     */
    public static void addUser(User user) {
        backingList.add(user);
        currentUser = user;
    }

    /**
     * Checks if login was valid. That is, does user exist in the system
     * @param username the inputted username
     * @param password the inputted password
     * @return true if valid, false otherwise
     */
    public static boolean isValidLogin(String username, String password) {
        try {
            PreparedStatement stmt = MainFXApplication.con.prepareStatement("SELECT COUNT(*) FROM user WHERE USERNAME = ?");
            stmt.setString(1, username);
            ResultSet count = stmt.executeQuery();
            count.next();
            if (count.getInt(1) != 1) {
                return false;
            }
            PreparedStatement stmt1 = MainFXApplication.con.prepareStatement("SELECT USERNAME, PASSWORD FROM user WHERE USERNAME = ?");
            stmt1.setString(1, username);
            ResultSet credentials = stmt1.executeQuery();
            credentials.next();
            if (!password.equals(credentials.getString(2))) {
                return false;
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return true;
    }

    /**
     * Getter for the current user
     * @return the current user
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Set the current user
     * @param user the current user to be set
     */
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    /**
     * Checks if unique username has been entered. Cannot create two users with same username
     * @param username the username inputted during registration
     * @return true if unique, false otherwise
     */
    public static Boolean isUniqueUserName(String username) {
        try {
            PreparedStatement stmt = MainFXApplication.con.prepareStatement("SELECT COUNT(*) FROM user WHERE USERNAME = ?");
            stmt.setString(1, username);
            ResultSet count = stmt.executeQuery();
            count.next();
            if (count.getInt(1) > 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    /**
     * Checks if unique ID has been entered. Cannot create two users with same username
     * @param ID the ID inputted during registration
     * @return true if unique, false otherwise
     */
    public static Boolean isUniqueID(String ID) {
        try {
            PreparedStatement stmt = MainFXApplication.con.prepareStatement("SELECT COUNT(*) FROM user WHERE ID = ?");
            stmt.setString(1, ID);
            ResultSet count = stmt.executeQuery();
            count.next();
            if (count.getInt(1) > 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
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
