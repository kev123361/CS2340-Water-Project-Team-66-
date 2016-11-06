package Model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by 2shob on 10/25/2016.
 */
public class PurityReport {
    private final StringProperty _date = new SimpleStringProperty();
    private final StringProperty _time = new SimpleStringProperty();
    private final IntegerProperty _reportNum = new SimpleIntegerProperty();
    private static int reportNumCounter = 0;
    private final ObjectProperty<User> _reportingUser = new SimpleObjectProperty<>();
    private final DoubleProperty _latitude = new SimpleDoubleProperty();
    private final DoubleProperty _longitude = new SimpleDoubleProperty();
    private final ObjectProperty<OverallConditionOfWater> _overallWaterCondition = new SimpleObjectProperty<>();
    private final IntegerProperty _virusPPM = new SimpleIntegerProperty();
    private final IntegerProperty _contaminantPPM = new SimpleIntegerProperty();

    public PurityReport() {
        this(null, null, null, 0.0, 0.0, null, 0, 0);
    }

    public PurityReport(String date, String time, User reportingUser, Double latitude,
                        Double longitude, OverallConditionOfWater overallWaterCondition, int virusPPM,
                        int contaminantPPM) {
        _date.set(date);
        _time.set(time);
        _reportNum.set(reportNumCounter);
        reportNumCounter++;
        _reportingUser.set(reportingUser);
        _latitude.set(latitude);
        _longitude.set(longitude);
        _overallWaterCondition.set(overallWaterCondition);
        _virusPPM.set(virusPPM);
        _contaminantPPM.set(contaminantPPM);
    }

    public String getDate() { return _date.get(); }
    public void setDate(String newDate) { _date.set(newDate); }

    public String getTime() { return _time.get(); }
    public void setTime(String newTime) { _time.set(newTime); }

    public User getReportingUser() { return _reportingUser.get(); }
    public void setReportingUser(User newUser) { _reportingUser.set(newUser); }

    public int getReportNum() { return _reportNum.get(); }
    public void setReportNum(int newReportNum) { _reportNum.set(newReportNum); }

    public double getLatitude() { return _latitude.get(); }
    public void setLatitude(double newLat) { _latitude.set(newLat); }

    public double getLongitude() { return _longitude.get(); }
    public void setLongitude(double newLong) { _longitude.set(newLong); }

    public OverallConditionOfWater getOverallWaterCondition() {
        return _overallWaterCondition.get();
    }
    public void setOverallWaterCondition(OverallConditionOfWater overallWaterCondition) {
        _overallWaterCondition.set(overallWaterCondition);
    }

    public int getVirusPPM() {
        return _virusPPM.get();
    }
    public void setVirusPPM(int ppm) {
        _virusPPM.set(ppm);
    }

    public int getContaminantPPM() {
        return _contaminantPPM.get();
    }
    public void setContaminantPPM(int ppm) {
        _contaminantPPM.set(ppm);
    }

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

    public String toString() {
        return "Quality Report: " + _reportNum.getValue();
    }


}
