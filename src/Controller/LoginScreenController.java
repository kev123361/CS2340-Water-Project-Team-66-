package Controller;

import Fxapp.MainFXApplication;
import Model.User;
import Model.UserList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.SyncFailedException;

/**
 * The controller for the welcome screen
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
//    public void setUser(User user) {
//        //remember the current user
//        _user = user;
//
//        if (_user == null) {
//            System.out.println("User was null");
//        }
//
//        //make the data show up in the gui fields
//        username.setText(_user.getUsername());
//        password.setText(_user.getPassword());
//
//
//    }

    @FXML
    public void handleLoginPressed() throws IOException {
        if (isInputValid()) {

            //Signal success and close the window
            _okClicked = true;
            mainApplication.showMainScreen(_user);

            _dialogStage.close();
            mainApplication.mainScreen.close();
        }
    }

    /*@FXML
    public void handleLoginPressed() throws IOException {
        if (isInputValid()) {

            //if the data is reasonable, then remember the the student data in the window


            //signal success and close this dialog window.
            _user.setUsername(username.getText());
            _user.setPassword(password.getText());

            _okClicked = true;

            //MainFXApplication currMainFXApp = mainApplication;

            this.setMainApplication(new MainFXApplication());
            MainScreenController controller = new MainScreenController();

            //controller.setMainApplication(currMainFXApp);

            controller.showMainScreen();
            //MainScreenController.showMainScreen();
            _dialogStage.close();
            mainApplication.mainScreen.close();
        }

    }*/
    public boolean isOkClicked() {
        return _okClicked;
    }

    public void handleCancelPressed() {
        _dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        //for now just check they actually typed something
        if (!username.getText().equals("") && !password.getText().equals("") && UserList.isValidLogin(username.getText(), password.getText())) {
            return true;
        }
        else {
            // Show the error message if bad data
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(_dialogStage);
            alert.setTitle("Invalid Fields");
            if (username.getText().equals("") || password.getText().equals("")){
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
