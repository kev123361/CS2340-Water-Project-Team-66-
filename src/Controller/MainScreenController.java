package Controller;

//import Controller.WelcomeScreenController;
import Fxapp.MainFXApplication;
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

    public void setMainApplication(MainFXApplication mainApp) {
        mainApplication = mainApp;
    }

    public void setMainScreenStage(Stage newStage) {
        mainScreenStage = newStage;
    }

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
    public void handleLogoutPressed() throws IOException {
        //Go back to the welcome screen
        _okClicked = true;
        mainApplication.initRootLayout(new Stage());

        //Close this stage
        mainScreenStage.close();
    }

    /*@FXML
    public void handleLogoutPressed() throws IOException {

        if (_dialogStage == null) {
            System.out.println("NULL #1\n\n");
        }

        MainFXApplication currMainFXApp = mainApplication;

        _okClicked = true;

        if (_dialogStage == null) {
            System.out.println("NULL #2\n\n");
        }

        this.setMainApplication(new MainFXApplication());
        WelcomeScreenController controller = new WelcomeScreenController();

        if (_dialogStage == null) {
            System.out.println("NULL #3\n\n");
        }

        controller.showWelcomeScreen();

        if (_dialogStage == null) {
            System.out.println("NULL #4\n\n");
        }

        //Stage newStage = new Stage();
        //MainFXApplication newMainFXApp = new MainFXApplication();
        //newMainFXApp.initRootLayout(newStage);

        //currMainFXApp.initRootLayout(newStage);

        //controller.showMainScreen();
        //MainScreenController.showMainScreen();

        _dialogStage.close();
        mainApplication.mainScreen.close();
    }*/


}