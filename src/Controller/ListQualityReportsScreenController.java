package Controller;

import Fxapp.MainFXApplication;
import Model.ReportList;
import Model.PurityReportList;
import Model.User;
import Model.Account;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Model.SourceReport;
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
    private User _user;

    /** References to the widgets in the fxml file */
    @FXML
    private ListView<PurityReport> purityReportList;

    @FXML
    private ListView<String> detailsList;

    @FXML
    private void initialize() {
        //reportList.setCellValueFactory(cellData -> cellData.getValue().getReportNumProperty());
        purityReportList.setItems(PurityReportList.getBackingList());
        purityReportList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDetails(newValue));

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

    /**
     * Set the user currently signed in
     *
     * @param user currently signed in
     */
    public void setUser(User user) { _user = user; }

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


    /** Handler for the close button being pressed
     *
     * @throws IOException if any IO errors
     */
    @FXML
    public void closePressed() throws IOException {
        _okClicked = true;
        reportsScreenStage.close();

        //mainApplication.showListReportsScreen();
    }

    /**
     * Handler for the View Graph button being pressed
     *
     * @throws IOException if any IO errors
     */
    @FXML
    public void handleViewGraphPressed() throws IOException {
        mainApplication.showGraphSelectScreen();
    }
}