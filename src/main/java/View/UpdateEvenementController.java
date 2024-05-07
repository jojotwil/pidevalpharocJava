package View;

import Entities.DemandeTroc;
import Entities.Evenement;
import Utils.DateUtils;
import Services.EvenementService;
import com.example.demo.DBUtils;
import com.example.demo.TrocbackController;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateEvenementController implements Initializable {
    public void boutique(ActionEvent event){
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listBoutiqueFront.fxml"));
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

    public void demandeserv(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource("Gestiondemandeservadmin.fxml"));
            Parent root = loader.load();


            // Set any necessary data or parameters for the controller

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Admin Dashboard");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void servicerep(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource("Gestionadminservicerep.fxml"));
            Parent root = loader.load();


            // Set any necessary data or parameters for the controller

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Admin Dashboard");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public void location(ActionEvent event){
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Showvl.fxml"));
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

    @FXML
    private TextField descriptionField;
    @FXML
    private TextField titreField;
    @FXML
    private DatePicker dateDebutPicker;
    @FXML
    private DatePicker dateFinPicker;
    @FXML
    private TextField themeField;
    @FXML
    private TextField localisationField;

    private IndexEvenementController indexEvenementController;
    private Evenement evenement;

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
        loadEvenementData();
    }

    public void setIndexEvenementController(IndexEvenementController controller) {
        this.indexEvenementController = controller;
    }

    private void loadEvenementData() {
        titreField.setText(evenement.getTitre());
        dateDebutPicker.setValue(DateUtils.convertToLocalDateUsingCalendar(evenement.getDate_debut()));
        dateFinPicker.setValue(DateUtils.convertToLocalDateUsingCalendar(evenement.getDate_fin()));

        themeField.setText(evenement.getTheme());
        localisationField.setText(evenement.getLocalisation());
        descriptionField.setText(evenement.getDescription());
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (titreField.getText() == null || titreField.getText().isEmpty()) {
            errorMessage += "Le champ titre est vide !\n";
        }

        if (dateDebutPicker.getValue() == null) {
            errorMessage += "Aucune date de début sélectionnée !\n";
        }

        if (dateFinPicker.getValue() == null) {
            errorMessage += "Aucune date de fin sélectionnée !\n";
        } else if (dateFinPicker.getValue().isBefore(dateDebutPicker.getValue())) {
            errorMessage += "La date de fin ne peut pas être avant la date de début !\n";
        }

        if (themeField.getText() == null || themeField.getText().isEmpty()) {
            errorMessage += "Le champ thème est vide !\n";
        }

        if (localisationField.getText() == null || localisationField.getText().isEmpty()) {
            errorMessage += "Le champ localisation est vide !\n";
        }

        if (!errorMessage.isEmpty()) {
            showAlertDialog(Alert.AlertType.ERROR, "Erreur de validation", "Veuillez corriger les erreurs suivantes", errorMessage);
            return false;
        }
        return true;
    }

    @FXML
    private void handleUpdate() {
        if (isInputValid()) {
            evenement.setTitre(titreField.getText());
            evenement.setDate_debut(java.sql.Date.valueOf(dateDebutPicker.getValue()));
            evenement.setDate_fin(java.sql.Date.valueOf(dateFinPicker.getValue()));
            evenement.setTheme(themeField.getText());
            evenement.setLocalisation(localisationField.getText());
            evenement.setDescription(descriptionField.getText());

            EvenementService service = new EvenementService();
            service.update(evenement);
            indexEvenementController.refreshTableView();

            closeStage();
        }
    }

    @FXML
    private void handleCancel() {
        closeStage();
    }

    private void closeStage() {
        Stage stage = (Stage) titreField.getScene().getWindow();
        stage.close();
    }

    private void showAlertDialog(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
