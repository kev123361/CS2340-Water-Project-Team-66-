package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The report list holds all of the water source reports created
 * Currently, it will only hold all of the reports created in this instance of the app, until persistence is completed
 *
 * @author Kyle Pelton
 * @version 1.0
 */
public class ReportList {

    //Backing structure holding all the reports
    private final static ObservableList<SourceReport> backingList = FXCollections.observableArrayList();

    //Reference to the current report
    private static SourceReport currentReport;

    /**
     * Add a report to the list of reports
     *
     * @param newReport the report to be added
     */
    public static void addReport(SourceReport newReport) {
        backingList.add(newReport);
        currentReport = newReport;
    }

    /**
     * @return the current report
     */
    public static SourceReport getCurrentReport() {
        return currentReport;
    }

    /**
     * Set the current report
     * @param newReport the report to be set to current
     */
    public static void setCurrentReport(SourceReport newReport) {
        currentReport = newReport;
    }

    /**
     * @return the backing list
     */
    public static ObservableList<SourceReport> getBackingList() {
        return backingList;
    }

    /**
     * Basic check to see if latitude is valid. Used when adding a report
     *
     * @param latitude the latitude to check
     * @return true if valid, false otherwise
     */
    public static boolean isValidLatitude(String latitude) {
        if (isDouble(latitude.substring(0, latitude.length() - 1)) && ((latitude.charAt(latitude.length() - 1) == 'N')
                || (latitude.charAt(latitude.length() - 1) == 'S'))) {
            return true;
        }
        return false;
    }

    /**
     * Basic check to see if longitude is valid. Used when adding a report
     *
     * @param longitude the longitude to check
     * @return true if valid, false otherwise
     */
    public static boolean isValidLongitude(String longitude) {
        if (isDouble(longitude.substring(0, longitude.length() - 1)) && ((longitude.charAt(longitude.length() - 1) == 'W')
                || (longitude.charAt(longitude.length() - 1) == 'E'))) {
            return true;
        }
        return false;
    }

    /**
     * Basic check to see if date is valid. Used when adding a report
     *
     * @param date the date to check
     * @return true if valid, false otherwise
     */
    public static boolean isValidDate(String date) {
        if (date.length() == 10 && date.charAt(2) == '/' && date.charAt(5) == '/' && isInteger(date.substring(0,2))
                && isInteger(date.substring(3,5)) && isInteger(date.substring(6))) {
            return true;
        }
        return false;
    }

    /**
     * Basic check to see if time is valid. Used when adding a report
     *
     * @param time the time to check
     * @return true if valid, false otherwise
     */
    public static boolean isValidTime(String time) {
        if (time.length() == 5 && time.charAt(2) == ':' && isInteger(time.substring(0,2)) && isInteger(time.substring(3))) {
            return true;
        }
        return false;
    }

    /**
     * Used to check if substring values in the validity checks above integers
     *
     * @param input the substring to check
     * @return true if an integer, false otherwise
     */
    public static boolean isInteger(String input) {
        String digits = "0123456789";
        for (int i = 0; i < input.length(); i++) {
            boolean inDigits = false;
            for (int j = 0; j < digits.length(); j++) {
                if (digits.charAt(j) == input.charAt(i)) {
                    inDigits = true;
                }
            }
            if (!inDigits) {
                return false;
            }
        }
        return true;
    }

    /**
     * Used to check if substring values in the validity checks above doubles
     *
     * @param input the substring to check
     * @return true if an double, false otherwise
     */
    public static boolean isDouble(String input) {
        String digits = "0123456789.";
        for (int i = 0; i < input.length(); i++) {
            boolean inDigits = false;
            for (int j = 0; j < digits.length(); j++) {
                if (digits.charAt(j) == input.charAt(i)) {
                    inDigits = true;
                }
            }
            if (!inDigits) {
                return false;
            }
        }
        return true;
    }


}
