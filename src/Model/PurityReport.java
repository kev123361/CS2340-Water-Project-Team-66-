package Model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a Purity Report
 *
 * @author Shobhit Srivastava
 * @version 1.0
 */
public class PurityReport {
    private final StringProperty _date = new SimpleStringProperty();
    private final StringProperty _time = new SimpleStringProperty();
    private final IntegerProperty _reportNum = new SimpleIntegerProperty();
    //private static int reportNumCounter = 0;
    private final ObjectProperty<User> _reportingUser = new SimpleObjectProperty<>();
    private final DoubleProperty _latitude = new SimpleDoubleProperty();
    private final DoubleProperty _longitude = new SimpleDoubleProperty();
    private final ObjectProperty<OverallConditionOfWater> _overallWaterCondition = new SimpleObjectProperty<>();
    private final IntegerProperty _virusPPM = new SimpleIntegerProperty();
    private final IntegerProperty _contaminantPPM = new SimpleIntegerProperty();

    /**
     * Empty Constructor
     * Should not normally call!
     */
    public PurityReport() {
        this(null, null, null, 0.0, 0.0, null, 0, 0);
    }

    /**
     * Constructs a Purity Report
     *
     * @param date the date the report was created
     * @param time the time the report was created
     * @param reportingUser the user who created the report
     * @param latitude the latitude of the report
     * @param longitude the longitude of the report
     * @param overallWaterCondition the condition of the water of the report
     * @param virusPPM the parts per million virus count
     * @param contaminantPPM the contaminant parts per million count
     */
    public PurityReport(String date, String time, User reportingUser, Double latitude,
                        Double longitude, OverallConditionOfWater overallWaterCondition, int virusPPM,
                        int contaminantPPM) {
        _date.set(date);
        _time.set(time);
        //_reportNum.set(reportNumCounter);
        //reportNumCounter++;
        _reportingUser.set(reportingUser);
        _latitude.set(latitude);
        _longitude.set(longitude);
        _overallWaterCondition.set(overallWaterCondition);
        _virusPPM.set(virusPPM);
        _contaminantPPM.set(contaminantPPM);
    }

    /**
     * @return the date of the report
     */
    public String getDate() { return _date.get(); }

    /**
     * Set the date of the report
     * @param newDate the date to set in the report
     */
    public void setDate(String newDate) { _date.set(newDate); }

    /**
     * @return the time of the report
     */
    public String getTime() { return _time.get(); }

    /**
     * Set the time of the report
     * @param newTime the time to set in the report
     */
    public void setTime(String newTime) { _time.set(newTime); }

    /**
     * @return the user who created the report
     */
    public User getReportingUser() { return _reportingUser.get(); }

    /**
     * Set the user who created the report
     * @param newUser the user to set in the report
     */
    public void setReportingUser(User newUser) { _reportingUser.set(newUser); }

    /**
     * @return the number of the report, which should be unique
     */
    public int getReportNum() { return _reportNum.get(); }

    /**
     * @return the latitude of the report
     */
    public double getLatitude() { return _latitude.get(); }

    /**
     * Set the latitude of the report
     * @param newLat the latitude to set in the report
     */
    public void setLatitude(double newLat) { _latitude.set(newLat); }

    /**
     * @return the longitude of the report
     */
    public double getLongitude() { return _longitude.get(); }

    /**
     * Set the longitude of the report
     * @param newLong the longitude to set in the report
     */
    public void setLongitude(double newLong) { _longitude.set(newLong); }

    /**
     * @return the overall condition of the water
     */
    public OverallConditionOfWater getOverallWaterCondition() {
        return _overallWaterCondition.get();
    }

    /**
     * Set the overall condition of the water
     * @param overallWaterCondition the condition to set in the report
     */
    public void setOverallWaterCondition(OverallConditionOfWater overallWaterCondition) {
        _overallWaterCondition.set(overallWaterCondition);
    }

    /**
     * @return the virus parts per million of the report
     */
    public int getVirusPPM() {
        return _virusPPM.get();
    }

    /**
     * Set the virus parts per million of the report
     * @param ppm the virus ppm to set in the report
     */
    public void setVirusPPM(int ppm) {
        _virusPPM.set(ppm);
    }

    /**
     * @return the contaminant parts per million of the report
     */
    public int getContaminantPPM() {
        return _contaminantPPM.get();
    }

    /**
     * Set the contaminant parts per million of the report
     * @param ppm the contaminant ppm to set in the report
     */
    public void setContaminantPPM(int ppm) {
        _contaminantPPM.set(ppm);
    }

    /**
     * Creates an observable list of strings with all the properties of a Purity Report
     * Used in outputting each Purity Report to the screen
     *
     * @return an observable list of strings with all the properties of this Purity Report
     */
    public ObservableList<String> getDetails() {
        ObservableList<String> details = FXCollections.observableArrayList();
        details.add("Date: " + getDate());
        details.add("Time: " + getTime());
        details.add("Reporting User: " + getReportingUser().toString());
        details.add("Latitude: " + getLatitude());
        details.add("Longitude: " + getLongitude());
        details.add("Virus PPM: " + getVirusPPM());
        details.add("Contaminant PPM: " + getContaminantPPM());
        return details;
    }

    @Override
    public String toString() {
        return "Quality Report: " + _reportNum.getValue();
    }


}
