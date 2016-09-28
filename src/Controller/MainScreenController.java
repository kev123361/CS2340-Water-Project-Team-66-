package Controller;

import Fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created by Kishan on 2016/09/20.
 */
public class MainScreenController {

    //Reference of the main FX class
    private MainFXApplication mainApplication;

    @FXML
    private void initialize() {

    }

    public void setMainApplication(MainFXApplication mainApp) {
        mainApplication = mainApp;
    }

}