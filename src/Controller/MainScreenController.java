package Controller;

//import Controller.WelcomeScreenController;
import Fxapp.MainFXApplication;
import Model.Account;
import Model.User;
import Model.UserList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A controller for the main screen of the application
 *
 * @author Kishan Chudasama
 * @author Kyle Pelton
 *
 * @version 1.1
 */
public class MainScreenController {

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

    /*public void showMainScreen() {
        try {
            _dialogStage = new Stage();
            //Stage newStage = new Stage();
            Parent root;

            //create a new scene with root and set the stage
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../View/MainScreen.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            _dialogStage.setTitle("Water Report Main Screen");
            _dialogStage.setScene(scene);
            _dialogStage.show();
            //newStage.setTitle("Water Report Main Screen");
            //newStage.setScene(scene);
            //newStage.show();
            //setDialogStage(newStage);
        } catch (IOException e) {
            //Log the error that occurred during loading
            Logger LOGGER = Logger.getLogger("MainFXApplication");
            LOGGER.log(Level.SEVERE, "Couldn't find the fxml file for the main screen");
            e.printStackTrace();
        }
    }*/

    /*public static void showMainScreen() {
        try {
            Stage stage = new Stage();
            //_dialogStage = new Stage();


            Parent root;
            //create a new scene with root and set the stage
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../View/MainScreen.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Water Report Main Screen");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // Log the error that occurred during loading
            Logger LOGGER = Logger.getLogger("MainFXApplication");
            LOGGER.log(Level.SEVERE, "Couldn't find the fxml file for the welcome screen");
            e.printStackTrace();
        }
    }*/

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