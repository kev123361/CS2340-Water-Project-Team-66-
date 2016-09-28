package Controller;

import Fxapp.MainFXApplication;
import Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The controller for the welcome screen
 *
 * @author Shivani Bandaru
 * @version 1.0
 */
public class LoginScreenController {
    private Stage _dialogStage;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    private boolean _okClicked = false;
    private User _user;

    //Reference of the main FX class
    private MainFXApplication mainApplication;
    public void setMainApplication(MainFXApplication mainApp) {
        mainApplication = mainApp;
    }

    @FXML
    private void initialize() {

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
        username.setText(_user.getUsername());
        password.setText(_user.getPassword());


    }

    @FXML
    public void handleLoginPressed() throws IOException {
        if (isInputValid()) {

            //if the data is reasonable, then remember the the student data in the window


            //signal success and close this dialog window.
            _user.setUsername(username.getText());
            _user.setPassword(password.getText());

            _okClicked = true;
            this.setMainApplication(new MainFXApplication());
            mainApplication.showMainScreen();


        }

    }
    public boolean isOkClicked() {
        return _okClicked;
    }

    public void handleCancelPressed() {
        _dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        //for now just check they actually typed something
        if (username.getText().equals("user") && password.getText().equals("pass")) {
            return true;
        }
        else {
            // Show the error message if bad data
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(_dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }


}
