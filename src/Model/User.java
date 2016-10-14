package Model;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * User
 *
 * @author Shivani Bandaru
 * @author Kyle Pelton
 * @version 1.1
 */

public class User {

    private final StringProperty _username = new SimpleStringProperty();
    private final StringProperty _password = new SimpleStringProperty();
    private final StringProperty _id = new SimpleStringProperty();
    private final ObjectProperty<Account> _account = new SimpleObjectProperty<>();

    private final StringProperty _emailAddress = new SimpleStringProperty();
    private final StringProperty _homeAddress = new SimpleStringProperty();
    private final ObjectProperty<Title> _title = new SimpleObjectProperty<>();

    /* **********************
     * Getters and setters for properties
     */
    public String getUsername() { return _username.get(); }
    public void setUsername(String name) { _username.set(name); }

    public String getPassword() {return _password.get(); }
    public void setPassword(String major) { _password.set(major); }

    public String getId() {return _id.get(); }
    public void setId(String major) { _id.set(major); }

    public Account getAccount() {return _account.get(); }
    public void setAccount(Account a) { _account.set(a); }

    public String getEmailAddress() { return _emailAddress.get(); }
    public void setEmailAddress(String emailAdd) { _emailAddress.set(emailAdd); }

    public String getHomeAddress() { return _homeAddress.get(); }
    public void setHomeAddress(String homeAdd) { _homeAddress.set(homeAdd); }

    public Title getTitle() { return _title.get(); }
    public void setTitle(Title title) { _title.set(title); }


    /**
     * Make a new user
     * @param username      the user's username
     * @param password      the user's password
     * @param id            the user's ID
     * @param a             the user's account
     * @param email         the user's email address
     * @param home          the user's home address
     * @param t             the user's title
     */
    public User(String username, String password, String id, Account a, String email, String home, Title t) {
        _username.set(username);
        _password.set(password);
        _id.set(id);
        _account.set(a);
        _emailAddress.set(email);
        _homeAddress.set(home);
        _title.set(t);
    }

    /**
     * No param constructor -- DO NOT CALL NORMALLY
     * This constructor only for GUI use in edit/new student dialog
     */
    public User() {
        _username.set("Enter your username");
        _password.set("Enter your password");
        _id.set("Enter your id");
        _emailAddress.set("Enter your email address");
        _homeAddress.set("Enter your home address");
    }

    /**
     *
     * @return the display string representation
     */
    public String toString() {
        /*return _username.get() + " " + _password.get() + " " + _id.get() + " " + _account.get() + _emailAddress.get()
                + _homeAddress.get() + _title.get();*/
        return _username.get();
    }

}
