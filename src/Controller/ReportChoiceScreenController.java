package Controller;

import Fxapp.MainFXApplication;
import Model.User;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Controller for the Report Choice Screen
 *
 * @author Kevin Tang
 * @version 1.0
 */
public class ReportChoiceScreenController {
    private MainFXApplication mainApp;
    private Stage choiceScreenStage;
    private boolean _okClicked = false;
    private User user;

    /**
     * Constructor for this controller
     */
    public ReportChoiceScreenController() {
    }

    /**
     * @return the state of _okClicked
     */
    public boolean is_okClicked() {
        return this._okClicked;
    }


    /**
     * Set a reference to the main application
     * @param mainApp the main app
     */
    public void setMainApplication(MainFXApplication mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Sets the stage for this screen
     * @param newStage the stage
     */
    public void setStage(Stage newStage) {
        this.choiceScreenStage = newStage;
    }

    /**
     * Handler for when Cancel is pressed
     */
    @FXML
    public void handleCancelPressed() {
        this._okClicked = true;
        this.choiceScreenStage.close();
    }

    /**
     * Handler for when Source Report is pressed
     */
    @FXML
    public void handleSourcePressed() {
        this._okClicked = true;
        this.choiceScreenStage.close();
        this.mainApp.showSubmitReportScreen();
    }

    /**
     * Handler for when Purity Report is pressed
     */
    @FXML
    public void handleQualityPressed() {
        this._okClicked = true;
        this.choiceScreenStage.close();
        this.mainApp.showSubmitPurityReportScreen();
    }
}