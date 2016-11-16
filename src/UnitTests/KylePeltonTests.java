package UnitTests;

import Model.UserList;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit for M10
 *
 * These JUnit Tests cover the isValidEmailAddress() method in the UserList class, checked when registering a user.
 *
 * A valid email address should be of the form <something>@<somethingelse>.<somedomainname>, although something and
 *      somethingelse can be equal
 *
 * See UserList.isValidEmailAddress() for more details
 *
 * @author Kyle Pelton
 * @version 1.0
 */
public class KylePeltonTests {

    /**
     * Tests situations in which the input string may be null or empty. These should return false.
     */
    @Test
    public void testNullAndEmptyString() {
        Assert.assertFalse(UserList.isValidEmailAddress(null));
        Assert.assertFalse(UserList.isValidEmailAddress(""));
    }

    /**
     * Tests various situations in which the "@" is used incorrectly. These should return false.
     */
    @Test
    public void testInvalidAtSigns() {
        String noAtSignWithoutDot = "blahblahcom";
        Assert.assertFalse(UserList.isValidEmailAddress(noAtSignWithoutDot));

        String noAtSignWithDot = "blahblah.com";
        Assert.assertFalse(UserList.isValidEmailAddress(noAtSignWithDot));

        String atZeroIndex = "@asdfjkl.com";
        Assert.assertFalse(UserList.isValidEmailAddress(atZeroIndex));

        String atLastIndex = "asdfjk.@";
        Assert.assertFalse(UserList.isValidEmailAddress(atLastIndex));
    }

    /**
     * Tests various situations in which the "." is used incorrectly. These should return false.
     */
    @Test
    public void testInvalidDots() {
        String noDotWithoutAtSign = "blahblah";
        Assert.assertFalse(UserList.isValidEmailAddress(noDotWithoutAtSign));

        String noDotWithAtSign = "blah@blah";
        Assert.assertFalse(UserList.isValidEmailAddress(noDotWithAtSign));

        String dotJustBeforeAt = "abc.@fc";
        Assert.assertFalse(UserList.isValidEmailAddress(dotJustBeforeAt));

        String dotWellBeforeAt = "abc.de@f.com";
        Assert.assertFalse(UserList.isValidEmailAddress(dotWellBeforeAt));

        String dotLastIndex = "abc.";
        Assert.assertFalse(UserList.isValidEmailAddress(dotLastIndex));
    }

    /**
     * Test to make sure there is something (e.g., some website name) in between the "@" and the "."
     */
    @Test
    public void testSomethingElseThere() {
        String missingSomethingElse = "abcdef@.com";
        Assert.assertFalse(UserList.isValidEmailAddress(missingSomethingElse));

        String hasSomethingElse = "abcdef@b.com";
        String alsoHasSomethingElse = "abcdef@bc.com";
        String hasSomethingElseAsWell = "abcdef@bcdefg.com";

        Assert.assertTrue(UserList.isValidEmailAddress(hasSomethingElse));
        Assert.assertTrue(UserList.isValidEmailAddress(alsoHasSomethingElse));
        Assert.assertTrue(UserList.isValidEmailAddress(hasSomethingElseAsWell));
    }

    /**
     * Tests a number of valid email addresses. All of these should return true.
     */
    @Test
    public void testValidEmails() {
        String validEmailA = "a@a.a";
        String validEmailB = "abc@bbc.com";
        String validEmailC = "a@bbc.com";
        String validEmailD = "aaa@b.com";

        Assert.assertTrue(UserList.isValidEmailAddress(validEmailA));
        Assert.assertTrue(UserList.isValidEmailAddress(validEmailB));
        Assert.assertTrue(UserList.isValidEmailAddress(validEmailC));
        Assert.assertTrue(UserList.isValidEmailAddress(validEmailD));
    }

}
