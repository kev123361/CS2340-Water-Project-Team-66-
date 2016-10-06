package Controller;

import javafx.fxml.FXML;
import Fxapp.MainFXApplication;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Model.User;
import Model.UserList;

import java.io.IOException;

/**
 * controller for the ProfileScreen
 *
 * @author Kevin Tang
 * @version 1.0
 */
public class ProfileScreenController {
    private Stage _dialogStage;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField id;

    private boolean _okClicked = false;
    private User _user;

    //Reference of the main FX class
    private MainFXApplication mainApplication;
    public void setMainApplication(MainFXApplication mainApp) {
        mainApplication = mainApp;
    }

    @FXML
    public void initialize() {
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
    }

    public void setDialogStage(Stage dialogStage) {
        _dialogStage = dialogStage;
    }

    @FXML
    public void handleEditPressed() throws IOException {
        _user.setUsername(username.getText());
        _user.setPassword(password.getText());
        _user.setId(id.getText());
        //_user.setAccount();

        _okClicked = true;
        _dialogStage.close();

        mainApplication.showMainScreen();
        /*if (mainApplication == null) { System.out.println("THIS!!!!"); }
        if (mainApplication.mainScreen == null) { System.out.println("THAT!!!!!"); }
        mainApplication.mainScreen.close();
        this.setMainApplication(new MainFXApplication());
        mainApplication.showMainScreen();*/
    }

    @FXML
    public void handleClosePressed() throws IOException {
        _okClicked = true;
        _dialogStage.close();

        mainApplication.showMainScreen();
    }

    public boolean isOkClicked() {return _okClicked;}
}
