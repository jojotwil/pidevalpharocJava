package View;

import Entities.DemandeTroc;
import Entities.Evenement;
import Services.EvenementService;
import Services.IService;
import com.example.demo.DBUtils;
import com.example.demo.TrocbackController;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

public class IndexEvenementController implements Initializable {
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
            //TrocbackController adminDashboardController = loader.getController();

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/trocback.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/chartjs.fxml"));
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
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource("/com/example/demo/admindashboard.fxml"));
            Parent root = loader.load();
            //TrocbackController adminDashboardController = loader.getController();

            // Set any necessary data or parameters for the controller

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Admin Dashboard");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Label labelDateFin;
    public Label labelDescription;
    @FXML
    private TableView<Evenement> evenementTable;

    @FXML
    private TableColumn<Evenement, String> titreColumn;

    @FXML
    private TableColumn<Evenement, String> dateDebutColumn;

    @FXML
    private TableColumn<Evenement, String> themeColumn;

    @FXML
    private TableColumn<Evenement, String> localisationColumn;

    @FXML
    private Label labelTitre;

    @FXML
    private Label labelDateDebut;

    @FXML
    private Label labelTheme;

    @FXML
    private Label labelLocalisation;

    private ObservableList<Evenement> evenements = FXCollections.observableArrayList();
    private IService<Evenement> evenementService = new EvenementService();

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @FXML
    private void initialize() {
        titreColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitre()));
        dateDebutColumn.setCellValueFactory(cellData -> new SimpleStringProperty(dateFormat.format(cellData.getValue().getDate_debut())));
        themeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTheme()));
        localisationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocalisation()));

        evenementTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showEvenementDetails(newValue));

        refreshTableView();
    }

    private void showEvenementDetails(Evenement evenement) {
        if (evenement != null) {
            labelTitre.setText(evenement.getTitre());
            labelDateDebut.setText(dateFormat.format(evenement.getDate_debut()));
            labelDateFin.setText(dateFormat.format(evenement.getDate_fin()));
            labelTheme.setText(evenement.getTheme());
            labelLocalisation.setText(evenement.getLocalisation());
            labelDescription.setText(evenement.getDescription());
        } else {
            labelTitre.setText("");
            labelDateDebut.setText("");
            labelDateFin.setText("");
            labelTheme.setText("");
            labelLocalisation.setText("");
            labelDescription.setText("");

        }}

    @FXML
    public void refreshTableView() {
        try {
            evenements.clear();
            evenements.addAll(evenementService.readAll());
            evenementTable.setItems(evenements);
        } catch (Exception e) {
            e.printStackTrace();
            showAlertDialog(Alert.AlertType.ERROR, "Database Error", "Failed to fetch Evenements", e.getMessage());
        }
    }

    private void showAlertDialog(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void createEvenement(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/add-evenement.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Ajouter un Événement");
            stage.setScene(scene);

            AddEvenementController controller = loader.getController();
            controller.setIndexEvenementController(this);

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            showAlertDialog(Alert.AlertType.ERROR, "Error", "Failed to load the add event form", e.getMessage());
        }
    }

    @FXML
    private void updateEvenement() {
        Evenement selectedEvenement = evenementTable.getSelectionModel().getSelectedItem();
        if (selectedEvenement != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/update-evenement.fxml"));
                Parent root = loader.load();

                UpdateEvenementController controller = loader.getController();
                controller.setEvenement(selectedEvenement);
                controller.setIndexEvenementController(this);

                Stage stage = new Stage();
                stage.setTitle("Mettre à jour Événement");
                stage.setScene(new Scene(root));
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleDeleteEvent() {
        Evenement selectedEvenement = evenementTable.getSelectionModel().getSelectedItem();
        if (selectedEvenement != null) {
            evenementService.delete(selectedEvenement.getId());
            refreshTableView();
        } else {
            showAlertDialog(Alert.AlertType.WARNING, "No Selection", "No Event Selected", "Please select an event in the table before attempting to delete.");
        }
    }
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

    @FXML
    private void handleViewTickets() {
        Evenement selectedEvent = evenementTable.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tickets.fxml"));
                Parent root = loader.load();

                TicketViewController controller = loader.getController();
                //controller.loadTickets(selectedEvent.getId());

                Stage stage = new Stage();
                stage.setTitle("Tickets for Event: " + selectedEvent.getTitre());
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlertDialog(Alert.AlertType.WARNING, "No Event Selected", "Please select an event to view tickets.","");
        }
    }

    public void launchAddTicketForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/reserver.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add New Ticket");
            stage.setScene(new Scene(root));
            stage.showAndWait();  // Cette méthode bloque jusqu'à ce que la fenêtre soit fermée
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
