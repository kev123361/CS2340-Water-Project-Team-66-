package Model;

/**
 * Account
 *
 * @author Shivani Bandaru
 * @version 1.0
 */
public enum Account {
    USER("U"), WORKER("W"), MANAGER("M"), ADMIN("A");
    private String code;
    Account(String code) {
        this.code = code;
    }
}
