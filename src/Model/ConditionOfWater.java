package Model;

/**
 * Enum representing the condition of water in a report
 *
 * @author Shivani Bandaru
 * @version 1.0
 */
public enum ConditionOfWater {
    WASTE("Waste"), TREATABLECLEAR("Treatable-Clear"), TREATABLEMUDDY("Treatable-Muddy"), POTABLE("Potable");

    private final String code;

    ConditionOfWater(String code) {
        this.code = code;
    }

    public String toString() { return code; }
}
