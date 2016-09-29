package Controller;

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
 * Created by Kishan on 2016/09/20.
 */
public class MainScreenController {

    //Reference of the main FX class
    private MainFXApplication mainApplication;

    @FXML
    private void initialize() {

    }

    public void setMainApplication(MainFXApplication mainApp) {
        mainApplication = mainApp;
    }

    public static void showMainScreen() {
        try {
            Stage stage = new Stage();
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
    }


}