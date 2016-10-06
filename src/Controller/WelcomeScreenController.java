package Controller;

import Fxapp.MainFXApplication;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private boolean isOkClicked = false;
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
     * @param welcomeStage the stage
     */
    public void setWelcomeStage(Stage welcomeStage) {
        welcomeStage = welcomeStage;
    }

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
