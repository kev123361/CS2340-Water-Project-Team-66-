package UnitTests;

import Model.ConditionOfWater;
import Model.ReportList;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.events.MouseEvent;

/**
 * Created by Kishan on 11/16/16.
 *
 * Unit Tests for checking branch coverage.
 */
public class KishanTests {

    @Test
    public void testGetConditionOfWater() {
        String waste = "Waste";
        String treatableclear = "Treatable-Clear";
        String treatablemuddy = "Treatable-Muddy";
        String potable = "Potable";
        String emptyString = "";
        String invalid = "Invalid";
        Assert.assertEquals(ConditionOfWater.WASTE, Model.ReportList.getConditionOfWater(waste));
        Assert.assertEquals(ConditionOfWater.TREATABLECLEAR, Model.ReportList.getConditionOfWater(treatableclear));
        Assert.assertEquals(ConditionOfWater.TREATABLEMUDDY, Model.ReportList.getConditionOfWater(treatablemuddy));
        Assert.assertEquals(ConditionOfWater.POTABLE, Model.ReportList.getConditionOfWater(potable));
        Assert.assertNull(Model.ReportList.getConditionOfWater(emptyString));
        Assert.assertNull(Model.ReportList.getConditionOfWater(invalid));
    }
}
