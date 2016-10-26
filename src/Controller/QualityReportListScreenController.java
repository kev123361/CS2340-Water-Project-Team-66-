package Controller;

import Fxapp.MainFXApplication;
import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * Created by 2shob on 10/26/2016.
 */
public class QualityReportListScreenController {
    private Stage qualityReportsScreenStage;
    private boolean _okClicked = false;
    private MainFXApplication mainApplication;
    private User _user;

    @FXML
    private ListView<PurityReport> reportList;

    @FXML
    private ListView<String> detailsList;

    @FXML
    private void initialize() {
        //reportList.setCellValueFactory(cellData -> cellData.getValue().getReportNumProperty());
        reportList.setItems(PurityReportList.getBackingList());
        reportList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDetails(newValue));
    }

    public void setMainApplication(MainFXApplication mainApp) {
        this.mainApplication = mainApp;
    }

    public void setQualityReportsScreenStage(Stage newStage) {
        qualityReportsScreenStage = newStage;
    }

    public void setUser(User user) { _user = user; }

    private void showDetails(PurityReport report) {
        detailsList.setItems(report.getDetails());
    }

    public boolean isOkClicked() { return _okClicked; }

    public void addReportPressed() {
        _okClicked = true;
        System.out.println("Make it link to QualityReportScreen.fxml here");
    }

    public void closePressed() {
        _okClicked = true;
        System.out.println("Make it go to the appropriate MainScreen.");
    }
}
