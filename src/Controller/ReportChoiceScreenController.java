package Controller;

import Fxapp.MainFXApplication;
import Model.User;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Created by DudeLong on 2016/10/26.
 */
public class ReportChoiceScreenController {
    private MainFXApplication mainApp;
    private Stage choiceScreenStage;
    private boolean _okClicked = false;
    private User user;

    public ReportChoiceScreenController() {
    }

    public boolean is_okClicked() {
        return this._okClicked;
    }

    @FXML
    private void initialize() {
    }

    public void setMainApplication(MainFXApplication mainApp) {
        this.mainApp = mainApp;
    }

    public void setStage(Stage newStage) {
        this.choiceScreenStage = newStage;
    }

    @FXML
    public void handleCancelPressed() {
        this._okClicked = true;
        this.choiceScreenStage.close();
    }

    @FXML
    public void handleSourcePressed() {
        this._okClicked = true;
        this.choiceScreenStage.close();
        this.mainApp.showSubmitReportScreen();
    }

    @FXML
    public void handleQualityPressed() {
        this._okClicked = true;
        this.choiceScreenStage.close();
        this.mainApp.showSubmitPurityReportScreen();
    }
}