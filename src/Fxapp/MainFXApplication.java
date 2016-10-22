package Fxapp;

import Controller.ListReportsScreenController;
import Controller.LoginScreenController;
import Controller.MainScreenController;
import Controller.ProfileScreenController;
import Controller.RegistrationScreenController;
import Controller.SubmitReportController;
import Controller.WelcomeScreenController;
import Controller.AvailabilityScreenController;

import Model.Account;
import Model.ReportList;
import Model.User;
import Model.UserList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main runner for the app
 *
 * @author Kyle Pelton
 * @author Shivani Bandaru
 * @author Kevin Tang
 *
 * @version 1.2
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

    /**
     * Shows the registration screen
     *
     * @param user the user you are trying to register
     * @return true if success, false otherwise
     */
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

    public boolean showSubmitReportScreen() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../View/SubmitReportScreen.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage reportScreen = new Stage();
            reportScreen.setTitle("Submit Report");
            reportScreen.initModality(Modality.WINDOW_MODAL);
            reportScreen.initOwner(mainScreen);
            Scene scene = new Scene(page);
            reportScreen.setScene(scene);

            // Set the person into the controller.
            SubmitReportController controller = loader.getController();
            controller.setDialogStage(reportScreen);
            //controller.setUser(user);

            controller.setMainApplication(this);

            // Show the dialog and wait until the user closes it
            reportScreen.show();

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
            profileScreenStage.initModality(Modality.WINDOW_MODAL);
            profileScreenStage.initOwner(mainScreen);
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

    public boolean showListReportsScreen() {
        if (UserList.getCurrentUser().getAccount().equals(Account.ADMIN)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainScreen);
            alert.setTitle("Can't Access the List of Reports");
            alert.setHeaderText("Since you are signed in as an admin, you cannot access the list of reports");
            //alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }

        try {
            // Load the fxml file and create new stage for the popup dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../View/ListReportsScreen.fxml"));
            AnchorPane page = loader.load();

            // Create the profile screen stage
            Stage listReportsScreenStage = new Stage();
            listReportsScreenStage.setTitle("List of Reports");
            listReportsScreenStage.initModality(Modality.WINDOW_MODAL);
            listReportsScreenStage.initOwner(mainScreen);
            Scene scene = new Scene(page);
            listReportsScreenStage.setScene(scene);

            // Set the controller
            ListReportsScreenController controller = loader.getController();
            controller.setReportsScreenStage(listReportsScreenStage);
            // Set user into controller
            controller.setUser(UserList.getCurrentUser());

            controller.setMainApplication(this);

            // Show the profile screen
            listReportsScreenStage.show();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean showAvailabilityScreen() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../View/AvailabilityScreen.fxml"));
            AnchorPane page = loader.load();

            // Create the main screen stage.
            Stage availabilityScreenStage = new Stage();
            availabilityScreenStage.setTitle("Water Report Availability Screen");
            availabilityScreenStage.initModality(Modality.WINDOW_MODAL);
            availabilityScreenStage.initOwner(mainScreen);
            Scene scene = new Scene(page);
            availabilityScreenStage.setScene(scene);

            // Set the controller.
            AvailabilityScreenController controller = loader.getController();
            controller.setAvailabilityScreenStage(availabilityScreenStage);

            controller.setMainApplication(this);

            // Show the main screen stage
            availabilityScreenStage.show();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Main runner for the app
     * @param args unimportant
     */
    public static void main(String[] args) {
        launch(args);
    }

}
