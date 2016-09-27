package Fxapp;

import Controller.WelcomeScreenController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main runner for the app
 *
 * @author Kyle Pelton
 * @author <insert name anyone else who edits this>
 *
 * @version 1.0
 */
public class MainFXApplication extends Application {

    //The app's main stage
    private Stage mainScreen;



    //The layout for the app's main window
    private AnchorPane rootLayout;

    //A logger for exceptions
    private static final Logger LOGGER = Logger.getLogger("MainFXApplication");

    @Override
    public void start(Stage primaryStage) {
        mainScreen = primaryStage;
        initRootLayout(mainScreen);
    }

    public AnchorPane getRootLayout() {
        return rootLayout;
    }
    /**
     * get a reference to the main stage
     * @return reference to the main stage
     * */
    public Stage getMainScreen() {
        return mainScreen;
    }


    /**
     * Initialize the application's welcome screen.
     *
     * @param mainScreen  the main stage of the application
     */
    private void initRootLayout(Stage mainScreen) {
        try {
            // Load the root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../View/WelcomeScreen.fxml"));
            rootLayout = loader.load();

            // Give the controller access to the welcome screen
            WelcomeScreenController controller = loader.getController();
            controller.setMainApplication(this);

            // Set the title
            mainScreen.setTitle("Welcome");

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            mainScreen.setScene(scene);
            mainScreen.show();
        } catch (IOException e) {
            // Log the error that occurred during loading
            LOGGER.log(Level.SEVERE, "Couldn't find the fxml file for the welcome screen");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
