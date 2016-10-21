package Model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a water source report
 * A water source report can be entered by users, workers, and managers
 *
 * @author Kyle Pelton
 * @version 1.1
 */
public class SourceReport {

    private final StringProperty _date = new SimpleStringProperty();
    private final StringProperty _time = new SimpleStringProperty();
    private final IntegerProperty _reportNum = new SimpleIntegerProperty();
    private static int reportNumCounter = 0;
    private final ObjectProperty<User> _reportingUser = new SimpleObjectProperty<>();
    private final DoubleProperty _latitude = new SimpleDoubleProperty();
    private final DoubleProperty _longitude = new SimpleDoubleProperty();
    //private final StringProperty _latitude = new SimpleStringProperty();
    //private final StringProperty _longitude = new SimpleStringProperty();
    private final ObjectProperty<TypeOfWater> _waterType = new SimpleObjectProperty<>();
    private final ObjectProperty<ConditionOfWater> _waterCondition = new SimpleObjectProperty<>();

    public SourceReport() {
        this(null, null, null, 0.0, 0.0, null, null);
    }

    /*public SourceReport(String date, String time, User reportingUser, String latitude, String longitude, TypeOfWater waterType,
                        ConditionOfWater waterCondition) {
        _date.set(date);
        _time.set(time);
        _reportingUser.set(reportingUser);
        _reportNum.set(reportNumCounter);
        reportNumCounter++;
        _latitude.set(latitude);
        _longitude.set(longitude);
        _waterType.set(waterType);
        _waterCondition.set(waterCondition);
    }*/

    public SourceReport(String date, String time, User reportingUser, double latitude, double longitude, TypeOfWater waterType,
                        ConditionOfWater waterCondition) {
        _date.set(date);
        _time.set(time);
        _reportingUser.set(reportingUser);
        _reportNum.set(reportNumCounter);
        reportNumCounter++;
        _latitude.set(latitude);
        _longitude.set(longitude);
        _waterType.set(waterType);
        _waterCondition.set(waterCondition);
    }

    /* ****************************************
       All the property getters and setters
     */
    public String getDate() { return _date.get(); }
    public void setDate(String newDate) { _date.set(newDate); }
    public StringProperty getDateProperty() { return _date; }

    public String getTime() { return _time.get(); }
    public void setTime(String newTime) { _time.set(newTime); }
    public StringProperty getTimeProperty() { return _time; }

    public User getReportingUser() { return _reportingUser.get(); }
    public void setReportingUser(User newUser) { _reportingUser.set(newUser); }
    public ObjectProperty<User> getReportingUserProperty() { return _reportingUser; }

    public int getReportNum() { return _reportNum.get(); }
    public void setReportNum(int newReportNum) { _reportNum.set(newReportNum); }
    public IntegerProperty getReportNumProperty() { return _reportNum; }

    /*public String getLatitude() { return _latitude.get(); }
    public void setLatitude(String newLat) { _latitude.set(newLat); }
    public StringProperty getLatitudeProperty() { return _latitude; }

    public String getLongitude() { return _longitude.get(); }
    public void setLongitude(String newLong) { _longitude.set(newLong); }
    public StringProperty getLongitudeProperty() { return _longitude; }*/

    public double getLatitude() { return _latitude.get(); }
    public void setLatitude(double newLat) { _latitude.set(newLat); }
    public DoubleProperty getLatitudeProperty() { return _latitude; }

    public double getLongitude() { return _longitude.get(); }
    public void setLongitude(double newLong) { _longitude.set(newLong); }
    public DoubleProperty getLongitudeProperty() { return _longitude; }

    public TypeOfWater getWaterType() { return _waterType.get(); }
    public void setWaterType(TypeOfWater newWT) { _waterType.set(newWT); }
    public ObjectProperty<TypeOfWater> getWaterTypeProperty() { return _waterType; }

    public ConditionOfWater getWaterCondition() { return _waterCondition.get(); }
    public void setWaterCondition(ConditionOfWater newWC) { _waterCondition.set(newWC); }
    public ObjectProperty<ConditionOfWater> getWaterConditionProperty() { return _waterCondition; }

    //public ObservableList<String> getDetails() {
    //   ObservableList<String> details = FXCollections.observableArrayList();
    //   details.add("Date: " + getDate());
    //   details.add("Time: " + getTime());
    //   details.add("Reporting User: " + getReportingUser().toString());
    //   details.add("Latitude: " + getLatitude().toString());
    //   details.add("Longitude: " + getLongitude().toString());
    //   details.add("Water Type: " + getWaterType().toString());
    //   details.add("Water Condition: " + getWaterCondition().toString());
    //   return details;
    //}

    public ObservableList<String> getDetails() {
        ObservableList<String> details = FXCollections.observableArrayList();
        details.add("Date: " + getDate());
        details.add("Time: " + getTime());
        details.add("Reporting User: " + getReportingUser().toString());
        details.add("Latitude: " + getLatitude());
        details.add("Longitude: " + getLongitude());
        details.add("Water Type: " + getWaterType().toString());
        details.add("Water Condition: " + getWaterCondition().toString());
        return details;
    }

    public String toString() {
        return "Source Report: " + _reportNum.getValue();
    }

}
