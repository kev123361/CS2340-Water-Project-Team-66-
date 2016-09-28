package Model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * User
 *
 * @author Shobhit
 * @version 1.0
 */

public class User {
    /**
     * Properties are a way of binding data under the JavaBeans specification.
     *
     * See the article at: http://docs.oracle.com/javafx/2/binding/jfxpub-binding.htm
     * for more details.
     */
    private final StringProperty _username = new SimpleStringProperty();
    private final StringProperty _password = new SimpleStringProperty();

    /* **********************
     * Getters and setters for properties
     */
    public String getUsername() { return _username.get(); }
    public void setUsername(String name) { _username.set(name); }

    public String getPassword() {return _password.get(); }
    public void setPassword(String major) { _password.set(major); }

    /**
     * Make a new student
     * @param username      the student's username
     * @param password     the student's password
     */
    public User(String username, String password) {
        _username.set(username);
        _password.set(password);


    }

    /**
     * No param constructor -- DO NOT CALL NORMALLY
     * This constructor only for GUI use in edit/new student dialog
     */
    public User() {
        _username.set("Enter your username");
        _password.set("Enter your password");
    }

    /**
     *
     * @return the display string representation
     */
    public String toString() {

        return _username.get() + " " + _password.get();
    }

}
