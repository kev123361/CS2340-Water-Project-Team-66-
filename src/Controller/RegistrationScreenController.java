package Controller;

import Fxapp.MainFXApplication;
import Model.Account;
import Model.User;
import Model.UserList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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

    private boolean _okClicked = false;
    private User _user;

    //Reference of the main FX class
    private MainFXApplication mainApplication;
    public void setMainApplication(MainFXApplication mainApp) {
        mainApplication = mainApp;
    }

    @FXML
    private void initialize() {
        comboBoxDrop.setItems( FXCollections.observableArrayList(Account.values()));

    }
    public void setDialogStage(Stage dialogStage) {
        _dialogStage = dialogStage;
    }
    public void setUser(User user) {
        //remember the current student
        _user = user;

        if (_user == null) {
            System.out.println("User was null");
        }

        //make the data show up in the gui fields
        username.setText("Enter a Username");
        password.setText("Enter a Password");
        id.setText("Create an Id");
        comboBoxDrop.setValue(Account.USER);


    }

    @FXML
    public void handleRegisterPressed() throws IOException {
        if (isInputValid()) {

            //if the data is reasonable, then remember the the student data in the window


            //signal success and close this dialog window.
            UserList.addUser(new User(username.getText(), password.getText(), id.getText(), comboBoxDrop.getSelectionModel().getSelectedItem()));


            _okClicked = true;
            _dialogStage.close();
            mainApplication.mainScreen.close();
            this.setMainApplication(new MainFXApplication());
            mainApplication.showMainScreen(_user);


        }

    }
    public boolean isOkClicked() {
        return _okClicked;
    }

    public void handleCancelPressed1() {
        _dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (!username.getText().equals("") && !password.getText().equals("") && !id.getText().equals("") && UserList.isUniqueUserName(username.getText()) && UserList.isUniqueID(id.getText())) {
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
            } else {
                alert.setHeaderText("One or more fields have been left blank.");
            }

            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }


}
