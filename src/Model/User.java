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
 * @version 1.2
 */

public class User {

    private final StringProperty _username = new SimpleStringProperty();
    private final StringProperty _password = new SimpleStringProperty();
    private final StringProperty _id = new SimpleStringProperty();
    private final ObjectProperty<Account> _account = new SimpleObjectProperty<>();

    private final StringProperty _emailAddress = new SimpleStringProperty();
    private final StringProperty _homeAddress = new SimpleStringProperty();
    private final ObjectProperty<Title> _title = new SimpleObjectProperty<>();

    /**
     * @return the username of the User
     */
    public String getUsername() { return _username.get(); }

    /**
     * Set the username of the User
     * @param name the username to set to the User
     */
    public void setUsername(String name) { _username.set(name); }

    /**
     * @return the password of the User
     */
    public String getPassword() {return _password.get(); }

    /**
     * Set the password of the User
     * @param major the password to set to the User
     */
    public void setPassword(String major) { _password.set(major); }

    /**
     * @return the ID of the User
     */
    public String getId() {return _id.get(); }

    /**
     * Set the ID of the User
     * @param major the ID to set to the User
     */
    public void setId(String major) { _id.set(major); }

    /**
     * @return the account of the User
     */
    public Account getAccount() {return _account.get(); }

    /**
     * Set the account of the User
     * @param a the account to set to the User
     */
    public void setAccount(Account a) { _account.set(a); }

    /**
     * @return the email address of the User
     */
    public String getEmailAddress() { return _emailAddress.get(); }

    /**
     * Set the email address of the User
     * @param emailAdd the email address to set to the User
     */
    public void setEmailAddress(String emailAdd) { _emailAddress.set(emailAdd); }

    /**
     * @return the home address of the User
     */
    public String getHomeAddress() { return _homeAddress.get(); }

    /**
     * Set the home address of the User
     * @param homeAdd the home address to set to the User
     */
    public void setHomeAddress(String homeAdd) { _homeAddress.set(homeAdd); }

    /**
     * @return the title of the User, which could be Mr., Mrs., Ms., Dr.
     */
    public Title getTitle() { return _title.get(); }

    /**
     * Set the title of the User
     * @param title the title to set to the User
     */
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
