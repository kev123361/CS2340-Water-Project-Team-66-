package Controller;

import Fxapp.MainFXApplication;
import Model.*;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;

/**
 * The controller for the submit report screen
 *
 * @author Shivani Bandaru
 * @author Kyle Pelton
 * @version 1.1
 */
public class SubmitPurityReportController {
    private Stage _dialogStage;

    @FXML
    private TextField latitudeOfWater;
    @FXML
    private TextField longitudeOfWater;
    @FXML
    private ComboBox<OverallConditionOfWater> conditionOfWaterComboBox;
    @FXML
    private TextField date;
    @FXML
    private TextField time;
    @FXML
    private TextField contppm;
    @FXML
    private TextField virusppm;

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
        conditionOfWaterComboBox.setItems(FXCollections.observableArrayList(OverallConditionOfWater.values()));
        conditionOfWaterComboBox.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                conditionOfWaterComboBox.requestFocus();
            }
        });
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
    public void setPurityReport() {
        latitudeOfWater.setText("Between -90.0 and 90.0");
        longitudeOfWater.setText("Between -180.0 and 180.0");
        conditionOfWaterComboBox.setValue(OverallConditionOfWater.UNSAFE); //setting default to first one listed
        date.setText("Of the form: MM/DD/YYYY");
        time.setText("Of the form: HH:MM (24 hour clock)");
        contppm.setText("Enter the number of contaminants per million");
        virusppm.setText("Enter the number of viruses per million");

    }

    /**
     * Handler for pressing submit
     * Adds report to report list if valid
     *
     * @throws IOException if IO errors occur
     */
    @FXML
    public void handleSubmitPressed() throws IOException {
        if (_user == null) {
            System.out.println("USER IS NULL");
        }
        if (isInputValid()) {
//            PurityReportList.addPurityReport(new PurityReport(date.getText(), time.getText(),  _user, Double.parseDouble(latitudeOfWater.getText()),
//                    Double.parseDouble(longitudeOfWater.getText()),
//                    conditionOfWaterComboBox.getSelectionModel().getSelectedItem(),Integer.parseInt(contppm.getText()), Integer.parseInt(virusppm.getText())));
            try {
                PreparedStatement stmt = MainFXApplication.con.prepareStatement("INSERT INTO purity_report (DATE, TIME, REPORTING_USER, LATITUDE, LONGITUDE, OVERALL_CONDITION_OF_WATER, VIRUS_PPM, CONTAMINANT_PPM) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                PurityReport newReport = new PurityReport(date.getText(), time.getText(), _user, Double.parseDouble(latitudeOfWater.getText()),
                        Double.parseDouble(longitudeOfWater.getText()), conditionOfWaterComboBox.getSelectionModel().getSelectedItem(),
                        Integer.parseInt(contppm.getText()), Integer.parseInt(virusppm.getText()));
                String[] dateArray = newReport.getDate().split("/");
                int year = Integer.parseInt(dateArray[2]) - 1900;
                int month = Integer.parseInt(dateArray[0]) - 1;
                int day = Integer.parseInt(dateArray[1]);
                Date date = new Date(year, month, day);
                stmt.setDate(1, date);
                String[] timeArray = newReport.getTime().split(":");
                int hour = Integer.parseInt(timeArray[0]);
                int minute = Integer.parseInt(timeArray[1]);
                Time time = new Time(hour, minute, 0);
                stmt.setTime(2, time);
                stmt.setString(3, UserList.getCurrentUser().getUsername());
                stmt.setDouble(4, newReport.getLatitude());
                stmt.setDouble(5, newReport.getLongitude());
                stmt.setString(6, newReport.getOverallWaterCondition().toString());
                stmt.setInt(7, newReport.getVirusPPM());
                stmt.setInt(8, newReport.getContaminantPPM());
                stmt.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
            _okClicked = true;
            _dialogStage.close();
        }

    }
    public boolean isOkClicked() {
        return _okClicked;
    }

    /**
     * Handler for pressing cancel. Closes out of the registration screen
     */
    @FXML
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
                alert.setHeaderText("This latitude isn't valid (should be number between -90.0 and 90.0)");
            } else if (!ReportList.isValidLongitude(longitudeOfWater.getText())) {
                alert.setHeaderText("This longitude isn't valid (should be number between -180.0 and 180.0)");
            } else {
                alert.setHeaderText("One or more fields have been left blank");
            }
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}


