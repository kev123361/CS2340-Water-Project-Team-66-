package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by 2shob on 10/26/2016.
 */
public class PurityReportList {
    private final static ObservableList<PurityReport> backingList = FXCollections.observableArrayList();

    public static void addPurityReport(PurityReport report) {
        backingList.add(report);
    }

    public static ObservableList<PurityReport> getBackingList() {
        return backingList;
    }

    public static boolean isValidLatitude(String latitude) {
        //return latitude <= 90.0 && latitude >= -90.0;
        try {
            double latDouble = Double.parseDouble(latitude);
            return latDouble <= 90.0 && latDouble >= -90.0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidLongitude(String longitude) {
        //return longitude <= 180.0 && longitude >= -180.0;
        try {
            double longDouble = Double.parseDouble(longitude);
            return longDouble <= 180.0 && longDouble >= -180.0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidDate(String date) {
        if (date.length() == 10 && date.charAt(2) == '/' && date.charAt(5) == '/' && isInteger(date.substring(0,2))
                && isInteger(date.substring(3,5)) && isInteger(date.substring(6))) {
            return true;
        }
        return false;
    }

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
