package Model;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
 * @version 1.2
 */
public class SourceReport {

    private final StringProperty _date = new SimpleStringProperty();
    private final StringProperty _time = new SimpleStringProperty();
    private final IntegerProperty _reportNum = new SimpleIntegerProperty();
    //private static final int reportNumCounter = 0;
    private final ObjectProperty<User> _reportingUser = new SimpleObjectProperty<>();
    private final DoubleProperty _latitude = new SimpleDoubleProperty();
    private final DoubleProperty _longitude = new SimpleDoubleProperty();
    private final ObjectProperty<TypeOfWater> _waterType = new SimpleObjectProperty<>();
    private final ObjectProperty<ConditionOfWater> _waterCondition = new SimpleObjectProperty<>();

    /**
     * Empty constructor of a Source Report
     * This implements constructor chaining
     */
    public SourceReport() {
        this(null, null, null, 0.0, 0.0, null, null);
    }


    /**
     * Creates a Source Report
     * Should only be called when these values are valid, so no checking needed
     *
     * @param date the date the Source Report was added
     * @param time the time the Source Report was added
     * @param reportingUser the user reporting the Source Report
     * @param latitude the latitude at which the Source Report was reported
     * @param longitude the longitude at which the Source Report was reported
     * @param waterType the type of water
     * @param waterCondition the condition of the water
     */
    public SourceReport(String date, String time, User reportingUser, double latitude, double longitude,
                        TypeOfWater waterType, ConditionOfWater waterCondition) {
        _date.set(date);
        _time.set(time);
        _reportingUser.set(reportingUser);
        //_reportNum.set(reportNumCounter);
        //reportNumCounter++;
        _latitude.set(latitude);
        _longitude.set(longitude);
        _waterType.set(waterType);
        _waterCondition.set(waterCondition);
    }

    /**
     * @return the date of the Source Report
     */
    public String getDate() { return _date.get(); }

    /**
     * Set the date for the Source Report
     * @param newDate the date to set in the Source Report
     */
    public void setDate(String newDate) { _date.set(newDate); }

    /**
     * @return the StringProperty representation of the date
     */
    public StringProperty getDateProperty() { return _date; }

    /**
     * @return the time for the Source Report
     */
    public String getTime() { return _time.get(); }

    /**
     * Set the time for the Source Report
     * @param newTime the time to set in the Source Report
     */
    public void setTime(String newTime) { _time.set(newTime); }

    /**
     * @return the StringProperty representation of the time
     */
    public StringProperty getTimeProperty() { return _time; }

    /**
     * @return the reporting user of the Source Report
     */
    public String getReportingUser() { return _reportingUser.get().getUsername(); }

    /**
     * Set the reporting user for the Source Report
     * @param newUser the new report user to add to the Source Report
     */
    public void setReportingUser(User newUser) { _reportingUser.set(newUser); }

    /**
     * @return the ObjectProperty representation of the reporting user
     */
    public ObjectProperty<User> getReportingUserProperty() { return _reportingUser; }

    /**
     * @return the number of the Source Report
     */
    public int getReportNum() { return _reportNum.get(); }

    /**
     * @return the IntegerProperty representation of the number of the report
     */
    public IntegerProperty getReportNumProperty() { return _reportNum; }

    /**
     * @return the latitude of the Source Report
     */
    public double getLatitude() { return _latitude.get(); }

    /**
     * Set the latitude to a new latitude
     * @param newLat the latitude to set in the Source Report
     */
    public void setLatitude(double newLat) { _latitude.set(newLat); }

    /**
     * @return the DoubleProperty representation of the latitude
     */
    public DoubleProperty getLatitudeProperty() { return _latitude; }

    /**
     * @return the longitude of the Source Report
     */
    public double getLongitude() { return _longitude.get(); }

    /**
     * Set the longitude to a new longitude
     * @param newLong the longitude to set in the Source Report
     */
    public void setLongitude(double newLong) { _longitude.set(newLong); }

    /**
     * @return the DoubleProperty representation of the longitude
     */
    public DoubleProperty getLongitudeProperty() { return _longitude; }

    /**
     * @return the type of water for the Source Report
     */
    public TypeOfWater getWaterType() { return _waterType.get(); }

    /**
     * Set the type of water for the Source Report
     * @param newWT the new type of water to set for the Source Report
     */
    public void setWaterType(TypeOfWater newWT) { _waterType.set(newWT); }

    /**
     * @return the ObjectProperty representation of the water type
     */
    public ObjectProperty<TypeOfWater> getWaterTypeProperty() { return _waterType; }

    /**
     * @return the condition of water for the Source Report
     */
    public ConditionOfWater getWaterCondition() { return _waterCondition.get(); }

    /**
     * Set the condition of water for the Source Report
     * @param newWC the new condition of water for the Source Report
     */
    public void setWaterCondition(ConditionOfWater newWC) { _waterCondition.set(newWC); }

    /**
     * @return the ObjectProperty representation of the condition of water
     */
    public ObjectProperty<ConditionOfWater> getWaterConditionProperty() { return _waterCondition; }


    /**
     * Creates an observable list of strings with all the properties of a Source Report
     * Used in outputting each Source Report to the screen
     *
     * @return an observable list of strings with all the properties of this Source Report
     */
    public ObservableList<String> getDetails() {
        ObservableList<String> details = FXCollections.observableArrayList();
        details.add("Date: " + getDate());
        details.add("Time: " + getTime());
        details.add("Reporting User: " + getReportingUser());
        details.add("Latitude: " + getLatitude());
        details.add("Longitude: " + getLongitude());
        details.add("Water Type: " + getWaterType().toString());
        details.add("Water Condition: " + getWaterCondition().toString());
        return details;
    }

    @Override
    public String toString() {
        return "Source Report: " + _reportNum.getValue();
    }

}
