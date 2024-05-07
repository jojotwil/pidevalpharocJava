package com.example.demo;

import Entities.DemandeTroc;
import Entities.Message;
import Entities.PostTroc;
import Services.DemnadeTrocService;
import Services.MessageinService;
import Services.PostTrocService;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TrocbackController implements Initializable {
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
    }    public void servicerep(ActionEvent event){
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
    private TableView<PostTroc> tableView;

    @FXML
    private TableColumn<PostTroc, String> marque;

    @FXML
    private TableColumn<PostTroc, String>  model;

    @FXML
    private TableColumn<PostTroc, String>  typecarburant;

    @FXML
    private TableColumn<PostTroc, String>  categorievehicule;

    @FXML
    private TableColumn<PostTroc, String>  annee;

    @FXML
    private TableColumn<PostTroc, String> kilometrage;

    @FXML
    private TableColumn<PostTroc, String>  typeboitevitesse;

    @FXML
    private TableColumn<PostTroc, String>  description;

    @FXML
    private TableColumn<PostTroc, Void> action;

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
    }
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

    @FXML
    public void showtrocs() {

        PostTrocService service = new PostTrocService();
        List<PostTroc> Liste = service.getAllpostes();
        ObservableList<PostTroc> observableList = FXCollections.observableList(Liste);

        model.setCellValueFactory(new PropertyValueFactory<PostTroc,String>("modele"));
        marque.setCellValueFactory(new PropertyValueFactory<PostTroc,String>("marque"));
        annee.setCellValueFactory(new PropertyValueFactory<PostTroc,String>("annee"));
        kilometrage.setCellValueFactory(new PropertyValueFactory<PostTroc,String>("kilometrage"));
        description.setCellValueFactory(new PropertyValueFactory<PostTroc,String>("description"));
        typeboitevitesse.setCellValueFactory(new PropertyValueFactory<PostTroc,String>("typeboitevitesse"));
        typecarburant.setCellValueFactory(new PropertyValueFactory<PostTroc,String>("typecarburant"));
        categorievehicule.setCellValueFactory(new PropertyValueFactory<PostTroc,String>("categorievehicule"));


        // Ajouter les données à la table
        tableView.setItems(observableList);

        // Ajouter un bouton "Supprimer" à chaque ligne dans la colonne "Action"
        action.setCellFactory(param -> new TableCell<>() {
            private final Button demandeButton = new Button("Demandes");

            {
                demandeButton.setOnAction(event -> {
                    PostTroc post = getTableView().getItems().get(getIndex());
                    DemnadeTrocService servicepost = new DemnadeTrocService();
                    List<DemandeTroc> liste = servicepost.getDemandesByPostid(post.getId()); // l'ID de post actuel

                    // Ajoutez ici la logique pour afficher la demande
                    // ...

                    // Affichez la demande uniquement si la liste n'est pas vide
                    if (!liste.isEmpty()) {
                        try {
                            // Charger la nouvelle interface dans un Node
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("demandeback.fxml"));
                            Parent newContent = loader.load();

                            // Accéder au contrôleur de la vue "posttroccrud.fxml"
                            DemandebackController controller = loader.getController();
                            controller.showtrocs(liste);

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
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    // Si la liste est vide, désactivez le bouton
                    PostTroc post = getTableView().getItems().get(getIndex());
                    DemnadeTrocService servicepost = new DemnadeTrocService();
                    List<DemandeTroc> liste = servicepost.getDemandesByPostid(post.getId()); // l'ID de post actuel
                    demandeButton.setDisable(liste.isEmpty());

                    setGraphic(demandeButton);
                }
            }
        });
    }

    @FXML
    public void chart(ActionEvent event){
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chartjs.fxml"));
            Parent newContent = loader.load();

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
    public void trocs(ActionEvent event){
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("trocback.fxml"));
            Parent newContent = loader.load();

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
    void mouseClicked(ContextMenuEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showtrocs();

        //tableView.setOnMouseClicked(this::mouseClicked);

    }
}
