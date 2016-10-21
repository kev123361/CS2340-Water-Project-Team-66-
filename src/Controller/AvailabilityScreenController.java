package Controller;

import Fxapp.MainFXApplication;
import Model.User;
import Model.UserList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * A controller for the availability screen
 *
 * @author Shobhit Srivastava
 *
 * @version 1.1
 */
public class AvailabilityScreenController {
    @FXML
    private Stage availabilityScreenStage;

    @FXML
    private VBox root;

    @FXML
    private WebView webView;

    private WebEngine webEngine;

    //WebEngine engine = webView.getEngine();
    //engine.load();

    private boolean _okClicked = false;

    //Reference of the main FX class
    private MainFXApplication mainApplication;

    @FXML
    private void initialize() {
        webEngine = webView.getEngine();
        //webEngine.load(MainFXApplication.class.getResource("../Food fight/index.html").toString());
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
    public void setAvailabilityScreenStage(Stage newStage) {
        availabilityScreenStage = newStage;
        availabilityScreenStage.setTitle("Water Reports Map");
    }

    public boolean isOkClicked() {
        return _okClicked;
    }


}