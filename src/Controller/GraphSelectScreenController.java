package Controller;

import Fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by DudeLong on 2016/11/05.
 */
public class GraphSelectScreenController {

    //References to text boxes on the screen
    @FXML
    private TextField year;
    @FXML
    private TextField latitude;
    @FXML
    private TextField longitude;

    private Stage _dialogStage;

    //Reference to the main application
    private MainFXApplication mainApplication;

    private boolean _okClicked;

    /**
     * Sets the stage
     *
     * @param stage stage to be shown
     */
    public void set_dialogStage(Stage stage) {
        _dialogStage = stage;
    }

    /**
     * Sets reference to the main application
     *
     * @param mainApp the main app
     */
    public void setMainApplication(MainFXApplication mainApp) {
        mainApplication = mainApp;
    }

    /**
     * @return _okClicked
     */
    public boolean is_okClicked() {return _okClicked;}

    /**
     * Handler for Close button
     * Closes the screen
     */
    @FXML
    public void handleClosePressed() {
        _okClicked = true;
        _dialogStage.close();
    }

    /**
     * Handler for Submit button
     * Closes the screen and displays the history graph
     */
    @FXML
    public void handleSubmitPressed() {
        _okClicked = true;
        mainApplication.showHistoryGraphScreen(Double.parseDouble(latitude.getText()),
                                                Double.parseDouble(longitude.getText()),
                                                Integer.parseInt(year.getText()));
        _dialogStage.close();
    }
}
