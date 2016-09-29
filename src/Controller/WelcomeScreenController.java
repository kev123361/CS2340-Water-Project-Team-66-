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

    public void setMainApplication(MainFXApplication mainApp) {
        mainApplication = mainApp;
    }

    public void setWelcomeStage(Stage welcomeStage) {
        welcomeStage = welcomeStage;
    }

    public boolean isOkClicked() {
        return isOkClicked;
    }

    /**
     * Handles button for registering
     *
     * Registering currently not implemented
     */
    @FXML
    public void registerPressed() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(mainApplication.getMainScreen());
        alert.setTitle("Unimplemented");
        alert.setHeaderText("Unimplemented Component");
        alert.setContentText("Registration hasn't been implemented yet!");

        alert.showAndWait();

    }

    public void loginPressed() {
        User user = new User();
        mainApplication.showLoginScreen(user);

    }

    /* @FXML
    public void expressLoginPressed(ActionEvent event) throws IOException{
        try {
            Stage stage = mainApplication.getMainScreen();
            Parent root;
            if (event.getSource() == expressLogin) {
                //get reference to the button's stage
                stage = (Stage) expressLogin.getScene().getWindow();
                //load up OTHER FXML document
                root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            }
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
*/

}
