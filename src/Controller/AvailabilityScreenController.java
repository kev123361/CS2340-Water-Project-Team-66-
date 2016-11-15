package Controller;

import Fxapp.MainFXApplication;
import Model.PurityReport;
import Model.ReportList;
import Model.SourceReport;
import Model.PurityReportList;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the Availability Screen
 * This screen displays a map representation of the water source reports
 *
 * Note this doesn't have a corresponding .fxml file in package View
 *
 * @author Kyle Pelton
 * @author Shobhit Srivastava
 * @author Kishan Chudasama
 * @version 1.0
 */
public class AvailabilityScreenController implements Initializable, MapComponentInitializedListener {

    //A GUI view in the GMapsFX library
    private GoogleMapView mapView;

    //The JavaScript interface for the Google Map
    private GoogleMap map;

    //Reference to main application
    //private final MainFXApplication mainApp;

    //A reference to the current stage
    private Stage stage;

    /**
     * Constructs the Availability Screen Controller
     * Called when transitioning to the Availability Screen
     *
     * @param app  reference to main FX application
     * @param stage the stage in which this map is being displayed
     */
    public AvailabilityScreenController(MainFXApplication app, Stage stage) {
        this.stage = stage;
        setUpMapView(stage);
    }

    /**
     * Construct the Google Map and set up different parts of the layout
     *
     * @param stage the stage in which to place the map scene
     */
    private void setUpMapView(Stage stage) {
        //Create the view
        mapView = new GoogleMapView();

        //we cannot do anything until the map is constructed, so we need a callback
        //this is provided by the listener.  this class implements the MapComponentInitializedListener
        //interface
        mapView.addMapInializedListener(this);

        //Create the top level layout
        BorderPane bp = new BorderPane();

        //put the menu into the top of the border layout
        bp.setTop(makeMenuBar());


        //put the map into the center of the border layout
        bp.setCenter(mapView);

        //put the map into the scene
        Scene scene = new Scene(bp);
        stage.setScene(scene);
    }

    /**
     * Makes the Menu Bar
     *
     * @return the constructed Menu Bar
     */
    private Node makeMenuBar() {
        MenuBar mb = new MenuBar();
        // --- Menu Close
        Menu menuClose = new Menu("Close");
        addCloseOptions(menuClose);

        mb.getMenus().addAll(menuClose);

        return mb;
    }

    /**
     * Helper for constructing the Close menu
     *
     * @param menuClose reference to Close menu
     */
    private void addCloseOptions(Menu menuClose) {

        MenuItem closeMap = new MenuItem("Close Map");
        closeMap.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });

        menuClose.getItems().addAll(closeMap);

    }


    @Override
    public void mapInitialized() {

        //Set the initial properties of the map

        MapOptions options = new MapOptions();

        //Set center location for the map as center of Atlanta
        LatLong center = new LatLong(33.7490, -84.3880);

        options.center(center)
                .zoom(9)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(true)
                .mapType(MapTypeIdEnum.TERRAIN);

        map = mapView.createMap(options);


        //Get all the markers from the list of reports
        ObservableList<SourceReport> reports = ReportList.getBackingList();

        for (SourceReport sr : reports) {
            MarkerOptions markerOptions = new MarkerOptions();
            LatLong loc = new LatLong(sr.getLatitude(), sr.getLongitude());

            markerOptions.position(loc).visible(Boolean.TRUE).title("Source Report");

            Marker marker = new Marker(markerOptions);

            map.addUIEventHandler(marker,
                    UIEventType.click,
                    (JSObject obj) -> {
                        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                        infoWindowOptions.content("<h2>Report </h2>"
                                + "Date: " + sr.getDate() + "<br>\nTime: " + sr.getTime() + "<br>\nReporting User: "
                                + sr.getReportingUser().getUsername() + "<br>\nLatitude: " + sr.getLatitude()
                                + "<br>\nLongitude: " + sr.getLongitude() + "<br>\nWater Type: "
                                + sr.getWaterType().toString() + "<br>\nWater Condition: " + sr.getWaterCondition().toString());
                        InfoWindow window = new InfoWindow(infoWindowOptions);
                        window.open(map, marker);
                    });

            map.addMarker(marker);
        }




    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}