package UnitTests;

import Fxapp.MainFXApplication;
import Model.UserList;
import com.sun.javaws.Main;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit tests by Shobhit Srivastava
 * Created by Shobhit Srivastava on 11/15/2016.
 */
public class ShobhitSrivastavaTests {

    private MainFXApplication app = new MainFXApplication();
    @Test
    public void testIsValidUsername() {
        app.launchConnection();
        Assert.assertFalse(UserList.isUniqueUserName("Shobhit"));
        Assert.assertTrue(UserList.isUniqueUserName("Johnathan"));
    }
}
