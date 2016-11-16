package Model;

import Fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Backing list of Purity Reports
 *
 * @author Shobhit Srivastrava
 * @version 1.0
 */
public class PurityReportList {
    private static final ObservableList<PurityReport> backingList = FXCollections.observableArrayList();

    /**
     * Adds a Purity Report to the backing list
     * @param report the report to add
     */
    public static void addPurityReport(PurityReport report) {
        backingList.add(report);
    }

    /**
     * @return the backing list for the Purity Reports
     */
    public static ObservableList<PurityReport> getBackingList() {
        ObservableList<PurityReport> list = FXCollections.observableArrayList();
        try {
            PreparedStatement stmt = MainFXApplication.con.prepareStatement("SELECT DATE, TIME, REPORTING_USER," +
                    " LATITUDE, LONGITUDE, OVERALL_CONDITION_OF_WATER, VIRUS_PPM, CONTAMINANT_PPM FROM purity_report");
            ResultSet table = stmt.executeQuery();
            while (table.next()) {
                String date = table.getDate(1).toString();
                String time = table.getTime(2).toString();
                User user = new User(table.getString(3), "", "", Account.USER, "", "", Title.MR);
                Double latitude = table.getDouble(4);
                Double longitude = table.getDouble(5);
                OverallConditionOfWater condition = OverallConditionOfWater.valueOf(table.getString(6).toUpperCase());
                int virusPPM = table.getInt(7);
                int contaminantPPM = table.getInt(8);
                PurityReport newReport = new PurityReport(date, time, user, latitude, longitude,
                        condition, virusPPM, contaminantPPM);
                list.add(newReport);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }


    /**
     * Checks if valid latitude was entered
     *
     * @param latitude user input latitude
     * @return true if valid latitude, false otherwise
     */
    /*public static boolean isValidLatitude(String latitude) {
        final double LATBOUNDARY = 90.0;
        try {
            double latDouble = Double.parseDouble(latitude);
            return (latDouble <= LATBOUNDARY) && (latDouble >= -LATBOUNDARY);
        } catch (NumberFormatException e) {
            return false;
        }
    }*/

    /**
     * Checks if valid longitude was entered
     *
     * @param longitude user input longitude
     * @return true if valid longitude, false otherwise
     */
    /*public static boolean isValidLongitude(String longitude) {
        final double LONGBOUNDARY = 180.0;
        try {
            double longDouble = Double.parseDouble(longitude);
            return (longDouble <= LONGBOUNDARY) && (longDouble >= -LONGBOUNDARY);
        } catch (NumberFormatException e) {
            return false;
        }
    }*/

    /**
     *  Checks to make sure date is in format Mo/Da/Year
     * @param date date being checked
     * @return true if valid date, false otherwise
     */
    /*public static boolean isValidDate(String date) {
        return (date.length() == 10) && (date.charAt(2) == '/') && (date.charAt(5) == '/')
                && isInteger(date.substring(0, 2)) && isInteger(date.substring(3, 5)) && isInteger(date.substring(6));
    }*/

    /**
     * Checks if a string is an integer
     * @param input the string inputted
     * @return whether the string is an integer
     */
    /*private static boolean isInteger(CharSequence input) {
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
    }*/

    /**
     * Checks whether a string is a double
     * @param input the string inputted
     * @return whether the string is a double or not
     */
    /*public static boolean isDouble(CharSequence input) {
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
    }*/





}
