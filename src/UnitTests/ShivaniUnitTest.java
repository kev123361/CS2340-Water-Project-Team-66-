package UnitTests;

import Model.ReportList;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit tests by Shivani Bandaru
 *
 * Created by Shivani Bandaru on 2016/11/15.
 */
public class ShivaniUnitTest {

    /**
     * Tests if the inputted latitude is valid.
     * Checks with decimal, no decimal, amount too big, amount too small, and with comma.
     */
    @Test
    public void testValidLatitude() {
        String validLat = "89.3";
        String notDecimal = "73";
        String tooBig = "92.2";
        String tooSmall = "-92.2";
        String accidentalComma = "92,3";

        Assert.assertFalse(ReportList.isValidLatitude(notDecimal));
        Assert.assertFalse(ReportList.isValidLatitude(tooBig));
        Assert.assertFalse(ReportList.isValidLatitude(tooSmall));
        Assert.assertFalse(ReportList.isValidLatitude(accidentalComma));
        Assert.assertTrue(ReportList.isValidLatitude(validLat));
    }
}