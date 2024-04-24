package alpharoc.pidev.controllers.gestionlocation;

import alpharoc.pidev.entities.VehiculeLouer;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;



public class Showvl implements Initializable {

    @FXML
    private TableView<VehiculeLouer> vlTable;

    @FXML
    private TableColumn<VehiculeLouer, String> tcate;

    @FXML
    private TableColumn<VehiculeLouer, String> tdesc;

    @FXML
    private TableColumn<VehiculeLouer, String> tmarque;

    @FXML
    private TableColumn<VehiculeLouer, String> tmodele;

    @FXML
    private TableColumn<VehiculeLouer, Date> tperio;

    @FXML
    private TableColumn<VehiculeLouer, String> ttype;
    ObservableList<VehiculeLouer> list = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tmarque.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarque()));
        tmodele.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModele()));
        tdesc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));

        tperio.setCellValueFactory(cellData -> new SimpleObjectProperty<Date>(cellData.getValue().getPeriode_dispo()));
        ttype.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType_carburant()));
        tcate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategorie_vehicule()));


        /*vlTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showVehiculeLouerDetails(newValue));

        refreshVlTableView();*/
    }

}
