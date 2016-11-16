package Model;

import Fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * The report list holds all of the water source reports created
 * Currently, it will only hold all of the reports created in this instance of the app, until persistence is completed
 *
 * @author Kyle Pelton
 * @version 1.0
 */
public class ReportList {

    //Backing structure holding all the reports
    private static final ObservableList<SourceReport> backingList = FXCollections.observableArrayList();


    /**
     * Add a report to the list of reports
     *
     * @param newReport the report to be added
     */
    public static void addReport(SourceReport newReport) {
        backingList.add(newReport);
    }



    /**
     * @return the backing list
     */
    public static ObservableList<SourceReport> getBackingList() {
        ObservableList<SourceReport> list = FXCollections.observableArrayList();
        try {
            PreparedStatement stmt = MainFXApplication.con.prepareStatement("SELECT DATE, TIME, REPORTING_USER, LATITUDE, LONGITUDE, WATER_TYPE, WATER_CONDITION FROM source_report");
            ResultSet table = stmt.executeQuery();
            while (table.next()) {
                String date = table.getDate(1).toString();
                String time = table.getTime(2).toString();
                User user = new User(table.getString(3), "", "", Account.USER, "", "", Title.MR);
                Double latitude = table.getDouble(4);
                Double longitude = table.getDouble(5);
                TypeOfWater waterType = TypeOfWater.valueOf(table.getString(6).toUpperCase());
                String condition = table.getString(7);
                ConditionOfWater waterCondition = getConditionOfWater(condition);
                SourceReport newReport = new SourceReport(date, time, user,
                        latitude, longitude, waterType, waterCondition);
                list.add(newReport);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Takes in a string and returns corresponding enum value
     *
     * @param condition string for condition of water
     * @return ConditionOfWater enum that matches string
     */
    public static ConditionOfWater getConditionOfWater(String condition) {
        if (condition.equals("Waste")) {
            return ConditionOfWater.WASTE;
        } else if (condition.equals("Treatable-Clear")) {
            return ConditionOfWater.TREATABLECLEAR;
        } else if (condition.equals("Treatable-Muddy")) {
            return ConditionOfWater.TREATABLEMUDDY;
        } else if (condition.equals("Potable")) {
            return ConditionOfWater.POTABLE;
        }
        return null;
    }

    /**
     * Basic check to see if latitude is valid. Used when adding a report
     *
     * @param latitude the latitude to check
     * @return true if valid, false otherwise
     */

    public static boolean isValidLatitude(String latitude) {
        //return latitude <= 90.0 && latitude >= -90.0;
        try {
            double latDouble = Double.parseDouble(latitude);
            return (latDouble <= 90.0) && (latDouble >= -90.0);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Basic check to see if longitude is valid. Used when adding a report
     *
     * @param longitude the longitude to check
     * @return true if valid, false otherwise
     */
    public static boolean isValidLongitude(String longitude) {
        //return longitude <= 180.0 && longitude >= -180.0;
        try {
            double longDouble = Double.parseDouble(longitude);
            return (longDouble <= 180.0) && (longDouble >= -180.0);
        } catch (NumberFormatException e) {
            return false;
        }
    }




    /**
     * Basic check to see if date is valid. Used when adding a report
     *
     * @param date the date to check
     * @return true if valid, false otherwise
     */
    public static boolean isValidDate(String date) {
        return (date.length() == 10) && (date.charAt(2) == '/') && (date.charAt(5) == '/') && isInteger(date.substring(0, 2))
                && isInteger(date.substring(3, 5)) && isInteger(date.substring(6));
    }

    /**
     * Basic check to see if time is valid. Used when adding a report
     *
     * @param time the time to check
     * @return true if valid, false otherwise
     */
    public static boolean isValidTime(String time) {
        return (time.length() == 5) && (time.charAt(2) == ':') &&
                isInteger(time.substring(0, 2)) && isInteger(time.substring(3));
    }

    /**
     * Used to check if substring values in the validity checks above integers
     *
     * @param input the substring to check
     * @return true if an integer, false otherwise
     */
    public static boolean isInteger(CharSequence input) {
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
    public static boolean isDouble(CharSequence input) {
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
