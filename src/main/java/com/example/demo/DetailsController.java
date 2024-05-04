package com.example.demo;

import Entities.PostTroc;
import Entities.User;
import Services.MessageinService;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DetailsController implements Initializable {
    @FXML
    private Label marque;

    @FXML
    private Label model;

    @FXML
    private Label annee;

    @FXML
    private Label categorievehicule;

    @FXML
    private Label typecarburant;
    @FXML
    private MenuItem button_logout;

    @FXML
    private Label kilometrage;

    @FXML
    private Label typeboitevitesse;

    @FXML
    private Label description;

    @FXML
    private Label location;
    @FXML
    private ImageView imageView;
    private PostTroc postTroc;
    @FXML
    private Label agelabel;
    public int agebymonth;
    public int age;
    public int agebydays;
    String loggedInUserEmail = DBUtils.getLoggedInUserEmail();
    UserService serviceuser=new UserService();
    User user= serviceuser.getuserfromemail(loggedInUserEmail);


    @FXML
    public void demande(ActionEvent event) {
        // Récupérer le post sélectionné

        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("demande.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"
            DemandeController controller = loader.getController();
            System.out.println(postTroc);
            controller.setPostTroc(postTroc);

            // Envoyer le post au contrôleur
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


    public void setdetails(PostTroc postTroc){

        int currentYear = LocalDate.now().getYear();
        int date=postTroc.getAnnee().getYear();
        age=currentYear-date;
        if(age==0){
            int currentmonth=LocalDate.now().getMonthValue();
            int datemonth=postTroc.getAnnee().getMonth();
            agebymonth=currentmonth-datemonth;
            agelabel.setText("Age de la vehicule : "+agebymonth+"mois");
        } else if (agebymonth==0) {
            int currentday=LocalDate.now().getDayOfMonth();
           int agebydaysanne=postTroc.getAnnee().getDay();
           agebydays=currentday-agebydaysanne;
            agelabel.setText("Age de la vehicule : "+agebydays+"jours");

        }else
        {
            agelabel.setText("Age de la vehicule : "+age+"année");
        }

        this.postTroc=postTroc;

        marque.setText("Marque : "+postTroc.getModele());
        model.setText("Model : "+postTroc.getMarque());
        categorievehicule.setText("Type de véhicule :  "+postTroc.getCategorievehicule());
        location.setText("Localisation  :  "+postTroc.getLocalisation());
        description.setText("Description : "+postTroc.getDescription());
        typeboitevitesse.setText("Type de boite vitesse : "+postTroc.getTypeboitevitesse());
        typecarburant.setText("Type de carburant : "+postTroc.getTypecarburant());
        kilometrage.setText("Kilometrage : "+postTroc.getKilometrage());
        annee.setText("Date de mise en circulation :"+postTroc.getAnnee());


        //image.setImage(postTroc.getImage());
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
    public void troc(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-viewtroc.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"
            HelloControllertroc controller = loader.getController();

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
    @FXML
    public void envoyermsg(ActionEvent event){
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("newmessage.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "profil.fxml"
            SendController controller = loader.getController();
            controller.setSenderuser(user.getId());
            controller.setRecipientuser(postTroc.getUser());
            controller.setlablefromdetails(postTroc);
            MessageinService messageinService=new MessageinService();
            messageinService.setPost(postTroc);
            System.out.println(postTroc);


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
        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScence(actionEvent, "authentifier.fxml", "Log In", null, null,button_logout);
            }
        });
    }
}
