package Controller;

import Fxapp.MainFXApplication;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Model.Account;
import Model.Title;
import Model.User;
import Model.UserList;

import java.io.IOException;

/**
 * Controller for the profile screen
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
    public void setMainApplication(MainFXApplication mainApp) {
        mainApplication = mainApp;
    }

    @FXML
    public void initialize() {
        comboBoxAccount.setItems(FXCollections.observableArrayList(Account.values()));
        comboBoxTitle.setItems(FXCollections.observableArrayList(Title.values()));
    }

    public void setUser(User user) {
        //set current user
        _user = user;

        if (_user == null) {
            System.out.println("User was null");
        }

        //show profile data in gui fields
        username.setText(user.getUsername());
        id.setText(user.getId());
        password.setText(user.getPassword());
        comboBoxAccount.setValue(user.getAccount());
        email.setText(user.getEmailAddress());
        home.setText(user.getHomeAddress());
        comboBoxTitle.setValue(user.getTitle());
    }

    public void setDialogStage(Stage dialogStage) {
        _dialogStage = dialogStage;
    }

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

            _okClicked = true;
            _dialogStage.close();

            mainApplication.showMainScreen();
        }
    }

    @FXML
    public void handleClosePressed() throws IOException {
        _okClicked = true;
        _dialogStage.close();

        mainApplication.showMainScreen();
    }

    public boolean isOkClicked() {return _okClicked;}

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
