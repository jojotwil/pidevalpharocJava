package alpharoc.pidev.controllers.gestionlocation;
import alpharoc.pidev.entities.VehiculeLouer;
import alpharoc.pidev.services.VehiculeLouerServie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class vehiculeLouecontroller implements Initializable {
    @FXML
    private TableView<VehiculeLouer> tableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the table columns
        TableColumn<VehiculeLouer, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<VehiculeLouer, String> marqueColumn = new TableColumn<>("Marque");
        marqueColumn.setCellValueFactory(new PropertyValueFactory<>("marque"));

        TableColumn<VehiculeLouer, String> modeleColumn = new TableColumn<>("Modele");
        modeleColumn.setCellValueFactory(new PropertyValueFactory<>("modele"));

        TableColumn<VehiculeLouer, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<VehiculeLouer, Date> periodeDispoColumn = new TableColumn<>("Période Dispo");
        periodeDispoColumn.setCellValueFactory(new PropertyValueFactory<>("periode_dispo"));

        TableColumn<VehiculeLouer, String> typeCarburantColumn = new TableColumn<>("Type Carburant");
        typeCarburantColumn.setCellValueFactory(new PropertyValueFactory<>("type_carburant"));

        TableColumn<VehiculeLouer, String> categorieVehiculeColumn = new TableColumn<>("Catégorie Véhicule");
        categorieVehiculeColumn.setCellValueFactory(new PropertyValueFactory<>("categorie_vehicule"));

        // Add the columns to the table view
        tableView.getColumns().addAll(idColumn, marqueColumn, modeleColumn, descriptionColumn,
                periodeDispoColumn, typeCarburantColumn, categorieVehiculeColumn);

        // Add data from the service to the table
        VehiculeLouerServie ps = new VehiculeLouerServie();
        ObservableList<VehiculeLouer> data = FXCollections.observableArrayList(ps.getAllData());
        tableView.setItems(data);

    }
}
