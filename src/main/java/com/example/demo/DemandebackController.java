package com.example.demo;

import Entities.DemandeTroc;
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

public class DemandebackController implements Initializable {
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
    }
    @FXML
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
}