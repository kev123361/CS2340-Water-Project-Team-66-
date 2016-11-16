package Controller;

import Fxapp.MainFXApplication;
import Model.PurityReportList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import Model.PurityReport;

import java.io.IOException;

/**
 * Controller for the ListQualityReportsScreen
 *
 * @author Shivani Bandaru
 * @version 1.1
 */
public class ListQualityReportsScreenController {

    //The stage for this controller
    private Stage reportsScreenStage;

    private boolean _okClicked = false;

    //A reference to the main application
    private MainFXApplication mainApplication;

    //The user currently signed in
    //private User _user;

    /** References to the widgets in the fxml file */
    @FXML
    private ListView<PurityReport> purityReportList;

    @FXML
    private ListView<String> detailsList;

    @FXML
    private void initialize() {
        //reportList.setCellValueFactory(cellData -> cellData.getValue().getReportNumProperty());
        purityReportList.setItems(PurityReportList.getBackingList());
        purityReportList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails(newValue));

    }

    /**
     * Sets the main application for this controller
     *
     * @param mainApp this controller's main application
     */
    public void setMainApplication(MainFXApplication mainApp) {
        this.mainApplication = mainApp;
    }

    /**
     * Sets the stage for this controller
     *
     * @param newStage the stage to be set
     */
    public void setQReportsScreenStage(Stage newStage) {
        reportsScreenStage = newStage;
    }


    //public void setUser(User user) { _user = user; }

    /**
     * Display the details of the selected report
     *
     * @param report the selected report
     */
    private void showDetails(PurityReport report) {
        detailsList.setItems(report.getDetails());
    }

    /**
     * @return the state of _okClicked
     */
    public boolean isOkClicked() { return _okClicked; }


    /**
     *  Handler for the close button being pressed
     */
    @FXML
    public void closePressed() {
        _okClicked = true;
        reportsScreenStage.close();

        //mainApplication.showListReportsScreen();
    }

    /**
     * Handler for the View Graph button being pressed
     */
    @FXML
    public void handleViewGraphPressed() {
        mainApplication.showGraphSelectScreen();
    }
}