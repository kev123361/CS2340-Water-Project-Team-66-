package Model;

/**
 * Created by 2shob on 10/25/2016.
 */
public enum OverallConditionOfWater {
    SAFE("safe"), TREATABLE("treatable"), UNSAFE("unsafe");
    private String code;

    OverallConditionOfWater(String code) {
        this.code = code;
    }

    public String toString() { return code; }
}
