package Controller;

import Fxapp.MainFXApplication;
import Model.Account;
import Model.Title;
import Model.User;
import Model.UserList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * The controller for the login screen
 *
 * @author Shivani Bandaru
 * @author Kyle Pelton
 * @version 1.1
 */
public class LoginScreenController {
    private Stage _dialogStage;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    private boolean _okClicked = false;
    //private User _user;

    //Reference of the main FX class
    private MainFXApplication mainApplication;

    /**
     * Sets the main application for this controller
     *
     * @param mainApp this controller's main application
     */
    public void setMainApplication(MainFXApplication mainApp) {
        mainApplication = mainApp;
    }

    @FXML
    private void initialize() {

    }

    /**
     * Sets the login screen's stage. Called when initializing the screen
     *
     * @param dialogStage the stage
     */
    public void setDialogStage(Stage dialogStage) {
        _dialogStage = dialogStage;
    }

    /**
     * Handler for pressing login button
     *
     * @throws IOException if IO issue occurs
     */
    @FXML
    public void handleLoginPressed() throws IOException {
        if (isInputValid()) {
            try {
                PreparedStatement stmt = MainFXApplication.con.prepareStatement(
                        "SELECT * FROM user WHERE USERNAME = ?");
                stmt.setString(1, username.getText());
                ResultSet rslt = stmt.executeQuery();
                rslt.next();
                String title = rslt.getString(7);
                title = title.substring(0, title.indexOf('.')).toUpperCase();
                User user = new User(rslt.getString(1), rslt.getString(2), rslt.getString(3),
                        Account.valueOf(rslt.getString(4)), rslt.getString(5), rslt.getString(6), Title.valueOf(title));
                UserList.setCurrentUser(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Signal success and close the window
            _okClicked = true;
            mainApplication.showMainScreen();

            _dialogStage.close();
            mainApplication.mainScreen.close();
        }
    }

    /**
     * @return the state of _okClicked
     */
    public boolean isOkClicked() {
        return _okClicked;
    }

    /**
     * Handler for pressing cancel. Closes the login screen
     */
    public void handleCancelPressed() {
        _dialogStage.close();
    }

    /**
     * Checks if input is valid
     *
     * @return true if valid, false otherwise
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (!"".equals(username.getText()) && !"".equals(password.getText())
                && UserList.isValidLogin(username.getText(), password.getText())) {
            return true;
        }
        else {
            // Show the error message if bad data
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(_dialogStage);
            alert.setTitle("Invalid Fields");
            if ("".equals(username.getText()) || "".equals(password.getText())){
                alert.setHeaderText("One or more fields were left blank.");
            } else {
                alert.setHeaderText("The username-password combination provided does not exist.");
            }

            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }


}
