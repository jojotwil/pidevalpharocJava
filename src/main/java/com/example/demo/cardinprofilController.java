package com.example.demo;

import Entities.PostTroc;
import Services.PostTrocService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.SocketOption;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class cardinprofilController implements Initializable {
    @FXML
    private ImageView imageView;


    @FXML
    private Label marque;

    @FXML
    private Label model;

    @FXML
    private Label carburant;

    @FXML
    private Label boite;

    @FXML
    private Label description;

    @FXML
    private Button demandes;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    private Image imagee;
    private PostTroc postTroc;
    @FXML
    private AnchorPane anchorpane;
    public void postdata(PostTroc postTroc){

        this.postTroc=postTroc;
        model.setText(postTroc.getModele());
        marque.setText(postTroc.getMarque());
        carburant.setText(postTroc.getTypecarburant());
        boite.setText(postTroc.getTypeboitevitesse());
        description.setText(postTroc.getDescription());
        try {
            // Convertir la chaîne de caractères en objet Image
            Image image = new Image(postTroc.getImage());

            // Définir l'image sur l'ImageView
            imageView.setImage(image);
        } catch (Exception e) {
            // Gérer l'erreur, par exemple afficher un message d'erreur ou une image par défaut
            System.out.println("Erreur lors du chargement de l'image : " + e.getMessage());
        }
    }



    @FXML
    public void deletepost(){
        try {
            PostTroc postTrocid=this.postTroc;
            PostTrocService postTrocService=new PostTrocService();
            System.out.println(this.postTroc.getId());
            postTrocService.deletePost(postTrocid);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void modifier(ActionEvent event) {
        // Récupérer le post sélectionné

        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("postcrud.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "pos
            postcrudController controller = loader.getController();
            //System.out.println(postTroc);

            // Envoyer le post au contrôleur
            controller.setPostTroc(postTroc);
            controller.mouseClicked(postTroc);
            controller.btnupp();

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
    public void demande(ActionEvent event) {
       // System.out.println(postTroc);
        AlldemandesController controllerr = new AlldemandesController();
        controllerr.setPostTroc(postTroc);
        //controllerr.menuDisplay(postTroc);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("alldemandes.fxml"));
            Parent newContent = loader.load();

            AlldemandesController controller = loader.getController();

            controller.setPostTroc(postTroc);
            controller.menuDisplay(postTroc);
            // Vérifier si postTroc est null avant de passer à AlldemandesController
            if (postTroc != null) {
                controller.setPostTroc(postTroc);
            } else {
                // Gérer le cas où postTroc est null
                System.out.println("L'objet postTroc est null");
            }
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


    }
}
