package Controller;

import Fxapp.MainFXApplication;
import Model.Account;
import Model.ReportList;
import Model.SourceReport;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the ListReportsScreen
 *
 * @author Kyle Pelton
 * @version 1.1
 */
public class ListReportsScreenController {

    //The stage for this controller
    private Stage reportsScreenStage;

    private boolean _okClicked = false;

    //A reference to the main application
    private MainFXApplication mainApplication;

    //The user currently signed in
    private User _user;

    /** References to the widgets in the fxml file */
    @FXML
    private ListView<SourceReport> reportList;

    @FXML
    private ListView<String> detailsList;

    @FXML
    private void initialize() {
        reportList.setItems(ReportList.getBackingList());
        reportList.getSelectionModel().selectedItemProperty().addListener(
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
    public void setReportsScreenStage(Stage newStage) {
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
    private void showDetails(SourceReport report) {
        detailsList.setItems(report.getDetails());
    }

    /**
     * @return the state of _okClicked
     */
    public boolean isOkClicked() { return _okClicked; }

    /**
     * Handler for add report button being pressed
     */
    @FXML
    public void addReportPressed() {
        _okClicked = true;
        if (_user.getAccount().equals(Account.USER)) {
            showSubmitReportScreen();
        } else {
            showReportChoiceScreen();
        }
    }

    /**
     * Handler for view quality report being pressed
     */
    public void viewQualityReportPressed() {
        _okClicked = true;

        if (_user.getAccount().equals(Account.MANAGER)) {
            mainApplication.viewQualityReportListScreen();
        }


    }

    /**
     * Pops up the submit report screen when add report pressed
     *
     * @return true if load successful, false otherwise
     */
    private boolean showSubmitReportScreen() {
        try {
            // Load the fxml file and create a new stage
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../View/SubmitReportScreen.fxml"));
            AnchorPane page = loader.load();

            // Create the report screen stage
            Stage reportScreen = new Stage();
            reportScreen.setTitle("Submit Report");
            reportScreen.initModality(Modality.WINDOW_MODAL);
            //reportScreen.initOwner(mainScreen);
            reportScreen.initOwner(reportsScreenStage);
            Scene scene = new Scene(page);
            reportScreen.setScene(scene);

            // Set the user into the controller.
            SubmitReportController controller = loader.getController();
            controller.setDialogStage(reportScreen);
            controller.setUser(_user);
            controller.setReport();

            //controller.setMainApplication(mainApplication);

            // Show the dialog and wait until the user closes it
            reportScreen.show();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Pops up the Report Choice screen when applicable
     * @return whether or not the screen successfully loaded
     */
    private boolean showReportChoiceScreen() {
        try {
            // Load the fxml file and create a new stage
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../View/ReportChoiceScreen.fxml"));
            AnchorPane page = loader.load();

            // Create the stage
            Stage Screen = new Stage();
            Screen.setTitle("Report Type");
            Screen.initModality(Modality.WINDOW_MODAL);
            Screen.initOwner(reportsScreenStage);
            Scene scene = new Scene(page);
            Screen.setScene(scene);

            ReportChoiceScreenController controller = loader.getController();
            controller.setStage(Screen);
            controller.setMainApplication(mainApplication);

            // Show the dialog and wait until the user closes it
            Screen.show();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Pops up a map of all the water reports when View Map button pressed
     */
    @FXML
    public void viewMapPressed() {
        _okClicked = true;
        Stage availabilityScreen = new Stage();
        availabilityScreen.setTitle("Map of Water Reports");
        availabilityScreen.initModality(Modality.WINDOW_MODAL);
        availabilityScreen.initOwner(reportsScreenStage);

        availabilityScreen.show();
    }

    /**
     * Handler for the close button being pressed
     */
    @FXML
    public void closePressed() {
        _okClicked = true;
        reportsScreenStage.close();

        mainApplication.showMainScreen();
    }
}