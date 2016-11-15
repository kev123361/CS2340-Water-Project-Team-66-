package Controller;

import Fxapp.MainFXApplication;
import Model.User;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Model.Account;
import Model.Title;
import Model.UserList;
import sun.applet.Main;

import java.io.IOException;
import java.sql.PreparedStatement;

/**
 * Controller for the Profile Screen
 *
 * @author Kevin Tang
 * @author Kyle Pelton
 * @version 1.1
 */
public class ProfileScreenController {
    private Stage _dialogStage;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField id;
    @FXML
    private ComboBox<Account> comboBoxAccount;
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

    /**
     * Calls made before the screen comes up
     */
    @FXML
    public void initialize() {
        comboBoxAccount.setItems(FXCollections.observableArrayList(Account.values()));
        comboBoxAccount.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                comboBoxAccount.requestFocus();
            }
        });
        comboBoxTitle.setItems(FXCollections.observableArrayList(Title.values()));
        comboBoxTitle.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                comboBoxTitle.requestFocus();
            }
        });
    }

    /**
     * Sets the initial user for this screen. Will be modified if user is updated
     *
     * @param user the user being registered
     */
    public void setUser(User user) {
        //set current user
        _user = user;

        //To be replaced when logging comes
        //if (_user == null) {
            //System.out.println("User was null");
        //}

        //show profile data in gui fields
        username.setText(user.getUsername());
        id.setText(user.getId());
        password.setText(user.getPassword());
        comboBoxAccount.setValue(user.getAccount());
        email.setText(user.getEmailAddress());
        home.setText(user.getHomeAddress());
        comboBoxTitle.setValue(user.getTitle());
    }

    /**
     * Sets the stage for this controller
     *
     * @param dialogStage the stage
     */
    public void setDialogStage(Stage dialogStage) {
        _dialogStage = dialogStage;
    }

    /**
     * Handler for pressing edit profile. Updates user profile if valid input passed
     *
     * @throws IOException if IO error occurs
     */
    @FXML
    public void handleEditPressed() throws IOException {
        if (isInputValid()) {
            _user.setUsername(username.getText());
            _user.setPassword(password.getText());
            _user.setId(id.getText());
            _user.setAccount(comboBoxAccount.getSelectionModel().getSelectedItem());
            _user.setEmailAddress(email.getText());
            _user.setHomeAddress(home.getText());
            _user.setTitle(comboBoxTitle.getSelectionModel().getSelectedItem());
            try {
                PreparedStatement stmt = MainFXApplication.con.prepareStatement("UPDATE user SET USERNAME = ?, PASSWORD = ?, ID = ?, ACCOUNT = ?, EMAIL = ?, ADDRESS = ?, TITLE = ? WHERE USERNAME = ?");
                stmt.setString(1, username.getText());
                stmt.setString(2, password.getText());
                stmt.setString(3, id.getText());
                stmt.setString(4, comboBoxAccount.getSelectionModel().getSelectedItem().toString());
                stmt.setString(5, email.getText());
                stmt.setString(6, home.getText());
                stmt.setString(7, comboBoxTitle.getSelectionModel().getSelectedItem().toString());
                stmt.setString(8, UserList.getCurrentUser().getUsername());
                stmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }

            _okClicked = true;
            _dialogStage.close();

            mainApplication.showMainScreen();
        }
    }

    /**
     * Handler for pressing close. Closes the window and does not edit anything
     *
     * @throws IOException if IO error occurs
     */
    @FXML
    public void handleClosePressed() throws IOException {
        _okClicked = true;
        _dialogStage.close();

        mainApplication.showMainScreen();
    }

    /**
     * @return the state of _okClicked
     */
    public boolean isOkClicked() {return _okClicked;}

    /**
     * Checks if input entered is valid. Updates user profile if so.
     * @return true if valid, false otherwise
     */
    public boolean isInputValid() {
        String errorMessage = "";

        if (!username.getText().equals("") && !password.getText().equals("") && !id.getText().equals("")
                && !email.getText().equals("") && !home.getText().equals("")
                && UserList.isValidEmailAddress(email.getText()) && UserList.isValidHomeAddress(home.getText())) {
            return true;
        }
        else {
            // Show the error message if bad data
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(_dialogStage);
            alert.setTitle("Invalid Fields");

            if (!UserList.isValidEmailAddress(email.getText())) {
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