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
import javafx.scene.chart.XYChart;
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
    private ObservableList<String> monthNames = FXCollections.observableArrayList();

    private final int NEARBY = 1;


    //List of all purity reports
    private List<PurityReport> reportList = PurityReportList.getBackingList();

    // Targeted year, lat and long to show nearby quality reports
    private double targetLat;
    private double targetLong;
    private int targetYear;

    private boolean _okClicked;
    private Stage _dialogStage;

    //Reference to the main application
    MainFXApplication mainApplication;

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
        XYChart.Series virusSeries = new XYChart.Series();
        virusSeries.setName("Virus PPM");
        XYChart.Series contaminantSeries = new XYChart.Series();
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
            if (year == targetYear &&
                Math.abs(targetLat - latitude) < NEARBY &&
                Math.abs(targetLong - longitude) < NEARBY) {
                // If the reported virus or contaminant PPM is higher, replaces currently held value
                monthCounterVirus[month - 1] = Math.max(monthCounterVirus[month -1], virusPPM);
                monthCounterContaminant[month - 1] = Math.max(monthCounterContaminant[month -1], contaminantPPM);
            }
        }

        // Create a XYChart.Data object for each month and add it to the respective series
        for (int i = 0; i < monthCounterVirus.length; i++) {
            virusSeries.getData().add(new XYChart.Data(monthNames.get(i), monthCounterVirus[i]));
        }
        for (int i = 0; i < monthCounterContaminant.length; i++) {
            contaminantSeries.getData().add(new XYChart.Data(monthNames.get(i), monthCounterContaminant[i]));
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
