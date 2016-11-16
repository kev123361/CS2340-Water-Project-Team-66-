package Controller;

import Fxapp.MainFXApplication;
import Model.PurityReport;
import Model.PurityReportList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.chart.XYChart.Data;
import javafx.stage.Stage;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Controller for History Graph that shows highest reported virus and contaminant ppm of an area each month
 *
 * Created by DudeLong on 2016/11/05.
 */
public class HistoryGraphController {

    //reference to the BarChart widget
    @FXML
    private BarChart<String, Double> qualityGraph;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    private final ObservableList<String> monthNames = FXCollections.observableArrayList();


    //List of all purity reports
    private final List<PurityReport> reportList = PurityReportList.getBackingList();

    // Targeted year, lat and long to show nearby quality reports
    private double targetLat;
    private double targetLong;
    private int targetYear;

    private boolean _okClicked;
    private Stage _dialogStage;

// --Commented out by Inspection START (2016/11/15 20:17):
//    //Reference to the main application
//    MainFXApplication mainApplication;
// --Commented out by Inspection STOP (2016/11/15 20:17)

    @FXML
    private void initialize() {
        // Array of English month names
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        // Convert to a list and add to our ObservableList of months
        monthNames.addAll(Arrays.asList(months));

        // Set the categories of xAxis to be months
        xAxis.setCategories(monthNames);
    }

    /**
     * Populates the graph with data
     */
    public void populateGraph() {
        Series virusSeries = new Series();
        virusSeries.setName("Virus PPM");
        Series contaminantSeries = new Series();
        contaminantSeries.setName("Contaminant PPM");

        int[] monthCounterVirus = new int[12];
        int[] monthCounterContaminant = new int[12];
        for (PurityReport report : reportList) {
            String[] dateArray = report.getDate().split("-");
            int year = Integer.parseInt(dateArray[0]);
            int month = Integer.parseInt(dateArray[1]);

            double latitude = report.getLatitude();
            double longitude = report.getLongitude();

            int virusPPM = report.getVirusPPM();
            int contaminantPPM = report.getContaminantPPM();

            // Checks to see if report is in the target year and in the nearby area of target lat and long
            int NEARBY = 1;
            if ((year == targetYear) &&
                    (Math.abs(targetLat - latitude) < NEARBY) &&
                    (Math.abs(targetLong - longitude) < NEARBY)) {
                // If the reported virus or contaminant PPM is higher, replaces currently held value
                monthCounterVirus[month - 1] = Math.max(monthCounterVirus[month -1], virusPPM);
                monthCounterContaminant[month - 1] = Math.max(monthCounterContaminant[month -1], contaminantPPM);
            }
        }

        // Create a XYChart.Data object for each month and add it to the respective series
        for (int i = 0; i < monthCounterVirus.length; i++) {
            virusSeries.getData().add(new Data(monthNames.get(i), monthCounterVirus[i]));
        }
        for (int i = 0; i < monthCounterContaminant.length; i++) {
            contaminantSeries.getData().add(new Data(monthNames.get(i), monthCounterContaminant[i]));
        }

        qualityGraph.getData().addAll(virusSeries, contaminantSeries);
    }

    /**
     * Sets the stage
     *
     * @param stage stage to be shown
     */
    public void set_dialogStage(Stage stage) {
        _dialogStage = stage;
    }


    //public void setMainApplication(MainFXApplication mainApp) {
        //mainApplication = mainApp;
    //}

    /**
     * @return _okClicked
     */
    public boolean is_okClicked() {return _okClicked;}

    /**
     * Sets the target latitude
     * @param lat latitude of area to be shown
     */
    public void setTargetLat(double lat) {
        targetLat = lat;
    }

    /**
     * Sets the target longitude
     * @param longitude longitude of area to be shown
     */
    public void setTargetLong(double longitude) {
        targetLong = longitude;
    }

    /**
     * Sets the targeted year
     * @param year Year from which to draw reports
     */
    public void setTargetYear(int year) { targetYear = year;}

    /**
     * Handler for pressing the Close button
     * Closes the HistoryGraph screen
     */
    public void handleClosePressed() {
        _okClicked = true;
        _dialogStage.close();
    }
}
