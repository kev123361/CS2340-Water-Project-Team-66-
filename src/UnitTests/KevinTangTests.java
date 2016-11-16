package UnitTests;

import Model.UserList;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit tests by Kevin Tang
 *
 * Created by Kevin Tang on 2016/11/15.
 */
public class KevinTangTests {

    @Test
    public void testValidAddresses() {
        String noCommas = "1015 Lancaster Walk Atlanta GA";
        String noSpaces = "1015LancasterWalkAtlantaGA";
        String missingComma = "1015 Lancaster Walk, Atlanta GA";
        String missingSpace = "1015 Lancaster Walk,Atlanta, GA";
        String goodAddress = "1015 Lancaster Walk, Atlanta, GA";

        Assert.assertFalse(UserList.isValidHomeAddress(noCommas));
        Assert.assertFalse(UserList.isValidHomeAddress(noSpaces));
        Assert.assertFalse(UserList.isValidHomeAddress(missingComma));
        Assert.assertFalse(UserList.isValidHomeAddress(missingSpace));
        Assert.assertTrue(UserList.isValidHomeAddress(goodAddress));
    }
}
