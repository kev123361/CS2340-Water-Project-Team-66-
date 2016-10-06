package Fxapp;

import Controller.LoginScreenController;
import Controller.MainScreenController;
import Controller.RegistrationScreenController;
import Controller.WelcomeScreenController;
import Controller.ProfileScreenController;

import Model.User;
import Model.UserList;
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
 * @author Shivani Bandaru
 *
 * @version 1.1
 */
public class MainFXApplication extends Application {

    //The app's main stage
    public Stage mainScreen;

    //The layout for the app's main window
    private Parent rootLayout;

    //A logger for exceptions
    private static final Logger LOGGER = Logger.getLogger("MainFXApplication");

    @Override
    public void start(Stage primaryStage) {
        initRootLayout(primaryStage);
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
    public void initRootLayout(Stage mainScreen) {
        try {
            // Load the root layout from fxml file.
            this.mainScreen = mainScreen;
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

    /**
     * Display the app's login screen
     *
     * @param user  the user trying to log in
     * @return whether or not displaying the login screen was successful
     */
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
            //controller.setUser(user);

            controller.setMainApplication(this);

            // Show the dialog and wait until the user closes it
            dialogStage.show();

            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean showRegistrationScreen(User user) {
        try {

            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../View/RegistrationScreen.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Registration");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainScreen);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            RegistrationScreenController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setUser(user);

            controller.setMainApplication(this);

            // Show the dialog and wait until the user closes it
            dialogStage.show();

            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Display the app's main screen
     *
     * @return whether or not displaying the login screen was successful
     */
    public boolean showMainScreen() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../View/MainScreen.fxml"));
            AnchorPane page = loader.load();

            // Create the main screen stage.
            Stage mainScreenStage = new Stage();
            mainScreenStage.setTitle("Water Report Main Screen");
            Scene scene = new Scene(page);
            mainScreenStage.setScene(scene);

            // Set the controller.
            MainScreenController controller = loader.getController();
            controller.setMainScreenStage(mainScreenStage);

            controller.setMainApplication(this);

            // Show the main screen stage
            mainScreenStage.show();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Display the profile screen of user
     *
     * @return whether or not displaying the screen was successful
     */
    public boolean showProfileScreen() {
        try {
            // Load the fxml file and create new stage for the popup dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../View/ProfileScreen.fxml"));
            AnchorPane page = loader.load();

            // Create the profile screen stage
            Stage profileScreenStage = new Stage();
            profileScreenStage.setTitle("Profile Screen");
            Scene scene = new Scene(page);
            profileScreenStage.setScene(scene);

            // Set the controller
            ProfileScreenController controller = loader.getController();
            controller.setDialogStage(profileScreenStage);
            // Set user into controller
            controller.setUser(UserList.getCurrentUser());

            controller.setMainApplication(this);

            // Show the profile screen
            profileScreenStage.show();

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
