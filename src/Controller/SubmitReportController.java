package Controller;

import Fxapp.MainFXApplication;
import Model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The controller for the submit report screen
 *
 * @author Shivani Bandaru
 * @author Kyle Pelton
 * @version 1.0
 */
public class SubmitReportController {
    private Stage _dialogStage;

    @FXML
    private TextField latitudeOfWater;
    @FXML
    private TextField longitudeOfWater;
    @FXML
    private ComboBox<ConditionOfWater> conditionOfWaterComboBox;
    @FXML
    private ComboBox<TypeOfWater> typeOfWaterComboBox;
    @FXML
    private TextField date;
    @FXML
    private TextField time;


    private boolean _okClicked = false;
    private User _user;

    //Reference of the main FX class
    private MainFXApplication mainApplication;

    /**
     * Sets the main application for this controller
     *
     * @param mainApp this controller's main application
     */
    public void setMainApplication(MainFXApplication mainApp) {
        mainApplication = mainApp;
    }

    @FXML
    private void initialize() {
        conditionOfWaterComboBox.setItems(FXCollections.observableArrayList(ConditionOfWater.values()));
        typeOfWaterComboBox.setItems(FXCollections.observableArrayList(TypeOfWater.values()));
    }

    /**
     * Sets the submit report screen's stage. Called when initializing the screen
     *
     * @param dialogStage the stage
     */
    public void setDialogStage(Stage dialogStage) {
        _dialogStage = dialogStage;
    }

    /**
     * Sets the user for this screen.
     *
     */
    public void setUser(User user) { _user = user; }

    /**
     * Set the initial report for the screen. Will be edited if submit report is pressed
     */
    public void setReport() {
        latitudeOfWater.setText("Of the form: <Number><N/S>");
        longitudeOfWater.setText("Of the form: <Number><W/E>");
        conditionOfWaterComboBox.setValue(ConditionOfWater.WASTE); //setting default to first one listed
        typeOfWaterComboBox.setValue(TypeOfWater.BOTTLED); //setting default to first one listed
        date.setText("Of the form: MM/DD/YYYY");
        time.setText("Of the form: HH:MM (24 hour clock)");
    }

    @FXML
    /**
     * Handler for pressing submit
     * Adds report to report list if valid
     *
     * @throws IOException if IO errors occur
     */
    public void handleSubmitPressed() throws IOException {

        if (isInputValid()) {
            ReportList.addReport(new SourceReport(date.getText(), time.getText(), _user, latitudeOfWater.getText(),
                    longitudeOfWater.getText(), typeOfWaterComboBox.getSelectionModel().getSelectedItem(),
                    conditionOfWaterComboBox.getSelectionModel().getSelectedItem()));
            _okClicked = true;
            _dialogStage.close();
        }

    }

    public boolean isOkClicked() {
        return _okClicked;
    }

    @FXML
    /**
     * Handler for pressing cancel. Closes out of the registration screen
     */
    public void handleCancelPressed() {
        _okClicked = true;
        _dialogStage.close();

        //mainApplication.showMainScreen();
    }

    /**
     * Checks if valid input has been entered
     * @return true if valid input entered, false otherwise
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (ReportList.isValidDate(date.getText()) && ReportList.isValidTime(time.getText())
                && ReportList.isValidLatitude(latitudeOfWater.getText())
                && ReportList.isValidLongitude(longitudeOfWater.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(_dialogStage);
            alert.setTitle("Invalid Fields");
            if (!ReportList.isValidDate(date.getText())) {
                alert.setHeaderText("This date isn't valid (should be of the form MM/DD/YYYY)");
            } else if (!ReportList.isValidTime(time.getText())) {
                alert.setHeaderText("This time isn't valid (should be of form HH:MM)");
            } else if (!ReportList.isValidLatitude(latitudeOfWater.getText())) {
                alert.setHeaderText("This latitude isn't valid (should be of form <Number><N/S>)");
            } else if (!ReportList.isValidLongitude(longitudeOfWater.getText())) {
                alert.setHeaderText("This longitude isn't valid (should be of form <Number><W/E>)");
            } else {
                alert.setHeaderText("One or more fields have been left blank");
            }
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }


}