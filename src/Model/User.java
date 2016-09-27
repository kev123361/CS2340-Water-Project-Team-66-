package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by DudeLong on 2016/09/20.
 */
public class User {
    private String username = "user";
    private String password = "password";

    public String get_username() {
        return username;
    }


    public String get_password() {
        return password;
    }

    public void setUserName(String username) {this.username = username; }

    public void setPassword(String password) {this.password = password;}

}
