package com.example.demo;

import Entities.PostTroc;
import Services.PostTrocService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class HelloControllertroc implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private Button btnsearch;



    @FXML
    private GridPane offre;

    PostTrocService postTrocService=new PostTrocService();
    ObservableList<PostTroc> Liste=postTrocService.getAllpostes();
    ObservableList<PostTroc> ListeData= FXCollections.observableArrayList();
    @FXML
    void search(ActionEvent event) {
        // Récupérer le texte de recherche
        String searchText = search.getText();
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            // Effectuer la recherche en temps réel avec le nouveau texte saisi (newValue)
            List<PostTroc> searchResults = searchByMarque(Liste, newValue);

            // Effacer les éléments existants dans le GridPane
            offre.getChildren().clear();

            // Afficher les nouveaux résultats de la recherche
            int row = 0;
            int column = 0;
            for (PostTroc result : searchResults) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
                try {
                    AnchorPane pane = loader.load();
                    MenuController menuController = loader.getController();
                    menuController.postdata(result);
                    offre.add(pane, column, row);

                    // Mettre à jour les indices de ligne et de colonne
                    column++;
                    if (column == 5) {
                        column = 0;
                        row++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        }
    @FXML
    public void monprofil(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profil.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"
            ProfilController controller = loader.getController();

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





    public void menuDisplay(){
        ListeData.clear();
        ListeData.addAll(postTrocService.getAllpostes());
        int row=0;
        int column=0;
        offre.getRowConstraints().clear();
        offre.getColumnConstraints().clear();
        for (int q=0;q<ListeData.size();q++){
            try {
                FXMLLoader load= new FXMLLoader();
                load.setLocation(getClass().getResource("menu.fxml"));
                AnchorPane pane=load.load();
                MenuController menuu=load.getController();
                menuu.postdata(ListeData.get(q));

                if(column ==5){
                    column=0;
                    row+=1;
                }

                offre.add(pane,column++,row);
            }catch (Exception e){
                e.printStackTrace();
            }


        }

    }

    public static List<PostTroc> searchByMarque(List<PostTroc> postTrocs, String marque) {
        List<PostTroc> results = new ArrayList<>();
        for (PostTroc postTroc : postTrocs) {
            if (postTroc.getMarque().equalsIgnoreCase(marque)) {
                results.add(postTroc);
            }
        }
        return results;
    }
    @FXML
    public void troc(ActionEvent event) {
        // Récupérer le post sélectionné

        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-viewtroc.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"
            HelloControllertroc controller = loader.getController();
            //System.out.println(postTroc);

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
    public void monprofil1(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profil.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "profil.fxml"
            ProfilController controller = loader.getController();

            // Créer une nouvelle scène avec le nouveau contenu
            Scene scene = new Scene(newContent);

            // Obtenir la fenêtre principale (stage)
            Stage mainStage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();

            // Obtenir les dimensions de l'écran
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            // Définir la taille de la fenêtre sur les dimensions de l'écran
            mainStage.setWidth(screenBounds.getWidth());
            mainStage.setHeight(screenBounds.getHeight());

            // Définir la nouvelle scène sur la fenêtre principale
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        menuDisplay();
    }
}