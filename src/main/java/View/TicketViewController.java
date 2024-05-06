package View;

import Entities.DemandeTroc;
import Entities.Ticket;
import Services.TicketService;
import com.example.demo.DBUtils;
import com.example.demo.TrocbackController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TicketViewController implements Initializable {
    // Vos autres champs et méthodes ici...

    @FXML
    private TableView<Ticket> ticketTable;
    @FXML
    private TableColumn<Ticket, Integer> idColumn;
    @FXML
    private TableColumn<Ticket, Integer> userIdColumn;
    @FXML
    private TableColumn<Ticket, Integer> eventIdColumn;
    @FXML
    private TableColumn<Ticket, Double> prixColumn;
    @FXML
    private TableColumn<Ticket, String> typeColumn;

    private ObservableList<Ticket> ticketData = FXCollections.observableArrayList();



    private int id;

    private TicketService ticketService = new TicketService();

    @FXML
    public void initialize() {
        // Initialisez la table avec les colonnes et le modèle de données
        idColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));
        userIdColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getUser_id()));
        eventIdColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getEvent_id()));
        prixColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getPrix()));
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType_ticket()));

        // Ajoutez la liste observable à la table
        ticketTable.setItems(ticketData);
    }




    // Vos autres méthodes ici...


    public void event(ActionEvent event){
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("index-evenement.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"

            // Créer une nouvelle scène avec le nouveau contenu
            Scene scene = new Scene(newContent);

            // Obtenir la fenêtre principale (stage)
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Obtenir les dimensions de l'écran
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            // Obtenez les dimensions de l'écran
            Screen screen = Screen.getPrimary();
            double screenWidth = screen.getBounds().getWidth();
            double screenHeight = screen.getBounds().getHeight();

// Définissez la taille de la fenêtre sur les dimensions de l'écran
            mainStage.setWidth(screenWidth);
            mainStage.setHeight(screenHeight);

            // Définir la nouvelle scène sur la fenêtre principale
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void handleDeleteTicket() {
        Ticket selectedTicket = ticketTable.getSelectionModel().getSelectedItem();
        if (selectedTicket != null) {
            ticketService.delete(selectedTicket.getId());
            refreshTableView(); // Rafraîchit la table après la suppression
        } else {
            showAlertDialog(Alert.AlertType.WARNING, "No Selection", "No Ticket Selected", "Please select a ticket from the table to delete.");
        }
    }

    private void refreshTableView() {
        ObservableList<Ticket> tickets = FXCollections.observableArrayList(ticketService.getAllByEventId(id));
        ticketTable.setItems(tickets);
    }

    private void showAlertDialog(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    @FXML
    private TableView<DemandeTroc> tableView;

    @FXML
    private TableColumn<DemandeTroc, String> marque;

    @FXML
    private TableColumn<DemandeTroc, String> model;

    @FXML
    private TableColumn<DemandeTroc, String> typecarburant;

    @FXML
    private TableColumn<DemandeTroc, String> categorievehicule;

    @FXML
    private TableColumn<DemandeTroc, String> annee;

    @FXML
    private TableColumn<DemandeTroc, String> kilometrage;

    @FXML
    private TableColumn<DemandeTroc, String> typeboitevitesse;

    @FXML
    private TableColumn<DemandeTroc, String> description;

    @FXML
    private TableColumn<DemandeTroc, String> rdv;

    public void logout(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource("authentifier.fxml"));
            Parent root = loader.load();
            TrocbackController adminDashboardController = loader.getController();

            // Set any necessary data or parameters for the controller

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Admin Dashboard");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }    @FXML
    public void trocs(ActionEvent event){
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("trocback.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"

            // Créer une nouvelle scène avec le nouveau contenu
            Scene scene = new Scene(newContent);

            // Obtenir la fenêtre principale (stage)
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Obtenir les dimensions de l'écran
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            // Obtenez les dimensions de l'écran
            Screen screen = Screen.getPrimary();
            double screenWidth = screen.getBounds().getWidth();
            double screenHeight = screen.getBounds().getHeight();

// Définissez la taille de la fenêtre sur les dimensions de l'écran
            mainStage.setWidth(screenWidth);
            mainStage.setHeight(screenHeight);

            // Définir la nouvelle scène sur la fenêtre principale
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void showtrocs(List<DemandeTroc> demandeTroc) {

        ObservableList<DemandeTroc> observableList = FXCollections.observableList(demandeTroc);

        model.setCellValueFactory(new PropertyValueFactory<DemandeTroc, String>("modele"));
        marque.setCellValueFactory(new PropertyValueFactory<DemandeTroc, String>("marque"));
        annee.setCellValueFactory(new PropertyValueFactory<DemandeTroc, String>("annee"));
        kilometrage.setCellValueFactory(new PropertyValueFactory<DemandeTroc, String>("kilometrage"));
        description.setCellValueFactory(new PropertyValueFactory<DemandeTroc, String>("description"));
        typeboitevitesse.setCellValueFactory(new PropertyValueFactory<DemandeTroc, String>("typeboitevitesse"));
        typecarburant.setCellValueFactory(new PropertyValueFactory<DemandeTroc, String>("typedecarburant"));
        categorievehicule.setCellValueFactory(new PropertyValueFactory<DemandeTroc, String>("categorievehicule"));
        rdv.setCellValueFactory(new PropertyValueFactory<DemandeTroc, String>("daterdv"));


        // Ajouter les données à la table
        tableView.setItems(observableList);

        // Ajouter un bouton "Supprimer" à chaque ligne dans la colonne "Action"

    }



    @FXML
    void mouseClicked(ContextMenuEvent event) {

    }
    @FXML
    public void chart(ActionEvent event){
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chartjs.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"

            // Créer une nouvelle scène avec le nouveau contenu
            Scene scene = new Scene(newContent);

            // Obtenir la fenêtre principale (stage)
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Obtenir les dimensions de l'écran
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            // Obtenez les dimensions de l'écran
            Screen screen = Screen.getPrimary();
            double screenWidth = screen.getBounds().getWidth();
            double screenHeight = screen.getBounds().getHeight();

// Définissez la taille de la fenêtre sur les dimensions de l'écran
            mainStage.setWidth(screenWidth);
            mainStage.setHeight(screenHeight);

            // Définir la nouvelle scène sur la fenêtre principale
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //showtrocs();

        //tableView.setOnMouseClicked(this::mouseClicked);

    }
    public void user(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource("admindashboard.fxml"));
            Parent root = loader.load();
            TrocbackController adminDashboardController = loader.getController();

            // Set any necessary data or parameters for the controller

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Admin Dashboard");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
