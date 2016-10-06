package Controller;

import Fxapp.MainFXApplication;
import Model.Account;
import Model.Title;
import Model.User;
import Model.UserList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The controller for the welcome screen
 *
 * @author Shivani Bandaru
 * @version 1.0
 */
public class RegistrationScreenController {
    private Stage _dialogStage;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField id;
    @FXML
    private ComboBox<Account> comboBoxDrop;
    @FXML
    private TextField email;
    @FXML
    private TextField home;
    @FXML
    private ComboBox<Title> comboBoxTitle;

    private boolean _okClicked = false;
    private User _user;

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
        comboBoxDrop.setItems(FXCollections.observableArrayList(Account.values()));
        comboBoxTitle.setItems(FXCollections.observableArrayList(Title.values()));
    }

    /**
     * Sets the registration screen's stage. Called when initializing the screen
     *
     * @param dialogStage the stage
     */
    public void setDialogStage(Stage dialogStage) {
        _dialogStage = dialogStage;
    }

    /**
     * Sets the initial user for this screen. Will be modified when user is registered
     *
     * @param user the user being registered
     */
    public void setUser(User user) {
        //remember the current user
        _user = user;

        if (_user == null) {
            System.out.println("User was null");
        }

        //make the data show up in the gui fields
        username.setText("Enter a Username");
        password.setText("Enter a Password");
        id.setText("Create an Id");
        comboBoxDrop.setValue(Account.USER);
        email.setText("Enter an Email Address");
        home.setText("Enter a Home Address");
        comboBoxTitle.setValue(Title.MR); //just using the first one in the enum

    }

    @FXML
    /**
     * Handler for pressing register
     * Adds user to the list of users provided input is valid
     *
     * @throws IOException if IO errors occur
     */
    public void handleRegisterPressed() throws IOException {
        if (isInputValid()) {

            //signal success and close this dialog window.
            UserList.addUser(new User(username.getText(), password.getText(), id.getText(),
                    comboBoxDrop.getSelectionModel().getSelectedItem(), email.getText(), home.getText(),
                    comboBoxTitle.getSelectionModel().getSelectedItem()));


            _okClicked = true;
            _dialogStage.close();
            mainApplication.mainScreen.close();
            this.setMainApplication(new MainFXApplication());
            mainApplication.showMainScreen();


        }

    }

    public boolean isOkClicked() {
        return _okClicked;
    }

    /**
     * Handler for pressing cancel. Closes out of the registration screen
     */
    public void handleCancelPressed1() {
        _dialogStage.close();
    }

    /**
     * Checks if valid input has been entered
     * @return true if valid input entered, false otherwise
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (!username.getText().equals("") && !password.getText().equals("") && !id.getText().equals("")
                && !email.getText().equals("") && !home.getText().equals("")
                && UserList.isUniqueUserName(username.getText()) && UserList.isUniqueID(id.getText())
                && UserList.isValidEmailAddress(email.getText()) && UserList.isValidHomeAddress(home.getText())) {
            return true;
        }
        else {
            // Show the error message if bad data
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(_dialogStage);
            alert.setTitle("Invalid Fields");
            if (!UserList.isUniqueUserName(username.getText())) {
                alert.setHeaderText("This username is already taken.");
            } else if (!UserList.isUniqueID(id.getText())) {
                alert.setHeaderText("This ID is already taken.");
            } else if (!UserList.isValidEmailAddress(email.getText())) {
                alert.setHeaderText("This email address isn't valid (should be of the form <>@<>.<>)");
            } else if (!UserList.isValidHomeAddress(home.getText())) {
                alert.setHeaderText("This home address isn't valid (should be <address>, <city>, <state> <ZIP>)");
            } else {
                alert.setHeaderText("One or more fields have been left blank.");
            }

            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }


}
