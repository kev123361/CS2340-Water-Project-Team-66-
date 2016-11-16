package Controller;

import Fxapp.MainFXApplication;
import Model.Account;
import Model.User;
import Model.UserList;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A controller for the Main Screen of the application
 *
 * @author Kishan Chudasama
 * @author Kyle Pelton
 *
 * @version 1.1
 */
public class UserMainScreenController {

    private Stage mainScreenStage;

    private boolean _okClicked = false;

    //Reference of the main FX class
    private MainFXApplication mainApplication;

    //Reference to currently logged in user
    private User _user;

    /**
     * Sets the main application for this controller
     *
     * @param mainApp this controller's main application
     */
    public void setMainApplication(MainFXApplication mainApp) {
        mainApplication = mainApp;
    }

    /**
     * Sets the main screen's stage. Called when initializing the stage
     *
     * @param newStage the stage
     */
    public void setMainScreenStage(Stage newStage) {
        mainScreenStage = newStage;
    }

    /**
     * @return the main screen stage
     */
    public Stage getMainScreenStage() { return mainScreenStage; }

    /**
     * @return the state of _okClicked
     */
    public boolean isOkClicked() {
        return _okClicked;
    }

    public void setUser(User user) {
        _user = user;
    }

    /**
     * Handler for pressing logout button
     */
    @FXML
    public void handleLogoutPressed() {
        //Go back to the welcome screen
        _okClicked = true;
        mainApplication.initRootLayout(new Stage());

        //Close this stage
        mainScreenStage.close();
    }

    /**
     * Handler for when Submit Report is pressed
     */
    @FXML
    public void submitReportPressed() {
        if (!_user.getAccount().equals(Account.ADMIN)) {
            _okClicked = true;
            mainApplication.showListReportsScreen();
            mainScreenStage.close();
        }
    }

    /**
     * Handler for pressing view profile
     */
    @FXML
    public void handleViewProfilePressed() {
        _okClicked = true;
        mainApplication.showProfileScreen();

        mainScreenStage.close();
    }


}