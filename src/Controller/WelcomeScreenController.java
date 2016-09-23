package Controller;

import Fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

/**
 * The controller for the welcome screen
 *
 * @author Kyle Pelton
 * @version 1.0
 */
public class WelcomeScreenController {

    //Reference of the main FX class
    private MainFXApplication mainApplication;

    @FXML
    private void initialize() {

    }

    public void setMainApplication(MainFXApplication mainApp) {
        mainApplication = mainApp;
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


}
