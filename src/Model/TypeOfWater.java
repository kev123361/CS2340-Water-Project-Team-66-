package Model;

/**
 * Enum representing the type of water in a report
 *
 * @author Shivani Bandaru
 * @version 1.0
 */
public enum TypeOfWater {
    BOTTLED("Bottled"), WELL("Well"), STREAM("Stream"), LAKE("Lake"), SPRING("Spring"),OTHER("Other");

    private final String code;

    TypeOfWater(String code) {
        this.code = code;
    }

    public String toString() { return code; }
}
