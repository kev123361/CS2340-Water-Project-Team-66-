package Fxapp;

import Controller.LoginScreenController;
import Controller.WelcomeScreenController;

import Model.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
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
    public static Stage mainScreen;

    //The layout for the app's main window
    private Parent rootLayout;

    //A logger for exceptions
    private static final Logger LOGGER = Logger.getLogger("MainFXApplication");

    @Override
    public void start(Stage primaryStage) {
        mainScreen = primaryStage;
        initRootLayout(mainScreen);
    }

    public Parent getRootLayout() {
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
    public boolean showLoginScreen(User user) {
        try {

            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../View/LoginScreen.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Login");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainScreen);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            LoginScreenController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setUser(user);

            // Show the dialog and wait until the user closes it
            dialogStage.show();

            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

}
