package Controller;

import Fxapp.MainFXApplication;
import Model.User;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * The controller for the welcome screen
 *
 * @author Kyle Pelton
 * @author Shivani Bandaru
 * @author Kishan Chudasama
 * @version 1.1
 */
public class WelcomeScreenController {

    //Reference of the main FX class
    private MainFXApplication mainApplication;

    private Stage welcomeStage;

    private final boolean isOkClicked = false;
    // @FXML
    //private Button expressLogin;

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
     * Sets the stage for this controller
     * @param stage the stage
     */
    public void setWelcomeStage(Stage stage) {
        this.welcomeStage = stage;
    }

    /**
     * @return the state of _okClicked
     */
    public boolean isOkClicked() {
        return isOkClicked;
    }

    /**
     * Handles button for registering
     */
    @FXML
    public void registerPressed() {
        User user = new User();
        mainApplication.showRegistrationScreen(user);
    }

    /**
     * Handles button for login
     */
    public void loginPressed() {
        User user = new User();
        mainApplication.showLoginScreen(user);

    }

}