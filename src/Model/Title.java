package Model;

/**
 * Represents a User's title
 *
 * @author Kyle Pelton
 * @version 1.0
 */
public enum Title {

    MR("Mr."),
    MS("Ms."),
    MRS("Mrs."),
    DR("Dr.");

    private String code;

    Title (String code) {
        this.code = code;
    }

    public String toString() { return code; }
}
