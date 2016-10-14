package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a water source report
 * A water source report can be entered by users, workers, and managers
 *
 * @author Kyle Pelton
 * @version 1.0
 */
public class SourceReport {

    private final StringProperty _date = new SimpleStringProperty();
    private final StringProperty _time = new SimpleStringProperty();
    private final IntegerProperty _reportNum = new SimpleIntegerProperty();
    private static int reportNumCounter = 0;
    private final ObjectProperty<User> _reportingUser = new SimpleObjectProperty<>();
    //private final IntegerProperty _latitude = new SimpleIntegerProperty();
    //private final IntegerProperty _longitude = new SimpleIntegerProperty();
    private final StringProperty _latitude = new SimpleStringProperty();
    private final StringProperty _longitude = new SimpleStringProperty();
    private final ObjectProperty<TypeOfWater> _waterType = new SimpleObjectProperty<>();
    private final ObjectProperty<ConditionOfWater> _waterCondition = new SimpleObjectProperty<>();

    public SourceReport() {
        this(null, null, null, null, null, null, null);
    }

    public SourceReport(String date, String time, User reportingUser, String latitude, String longitude, TypeOfWater waterType,
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

    public String getLatitude() { return _latitude.get(); }
    public void setLatitude(String newLat) { _latitude.set(newLat); }
    public StringProperty getLatitudeProperty() { return _latitude; }

    public String getLongitude() { return _longitude.get(); }
    public void setLongitude(String newLong) { _longitude.set(newLong); }
    public StringProperty getLongitudeProperty() { return _longitude; }

    public TypeOfWater getWaterType() { return _waterType.get(); }
    public void setWaterType(TypeOfWater newWT) { _waterType.set(newWT); }
    public ObjectProperty<TypeOfWater> getWaterTypeProperty() { return _waterType; }

    public ConditionOfWater getWaterCondition() { return _waterCondition.get(); }
    public void setWaterCondition(ConditionOfWater newWC) { _waterCondition.set(newWC); }
    public ObjectProperty<ConditionOfWater> getWaterConditionProperty() { return _waterCondition; }

    public ObservableList<String> getDetails() {
        ObservableList<String> details = FXCollections.observableArrayList();
        details.add("Date: " + getDate());
        details.add("Time: " + getTime());
        details.add("Reporting User: " + getReportingUser().toString());
        details.add("Latitude: " + getLatitude().toString());
        details.add("Longitude: " + getLongitude().toString());
        details.add("Water Type: " + getWaterType().toString());
        details.add("Water Condition: " + getWaterCondition().toString());
        return details;
    }

    public String toString() {
        return "Source Report: " + _reportNum.getValue();
    }

}
