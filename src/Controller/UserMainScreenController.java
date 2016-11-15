package Controller;

import Fxapp.MainFXApplication;
import Model.Account;
import Model.UserList;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A controller for the main screen of the application
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

    @FXML
    private void initialize() {
    }

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

    public Stage getMainScreenStage() { return mainScreenStage; }

    public boolean isOkClicked() {
        return _okClicked;
    }

    @FXML
    /**
     * Handler for pressing logout button
     *
     * @throws IOException if IO issues occur
     */
    public void handleLogoutPressed() throws IOException {
        //Go back to the welcome screen
        _okClicked = true;
        mainApplication.initRootLayout(new Stage());

        //Close this stage
        mainScreenStage.close();
    }

    @FXML
    public void submitReportPressed() {
        if (!UserList.getCurrentUser().getAccount().equals(Account.ADMIN)) {
            _okClicked = true;
            mainApplication.showListReportsScreen();
            mainScreenStage.close();
        }
    }

    @FXML
    /**
     * Handler for pressing view profile
     *
     * @throws IOException if IO issues occur
     */
    public void handleViewProfilePressed() throws IOException {
        _okClicked = true;
        mainApplication.showProfileScreen();

        mainScreenStage.close();
    }


}