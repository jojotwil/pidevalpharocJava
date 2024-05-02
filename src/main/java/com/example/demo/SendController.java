package com.example.demo;

import Entities.Message;
import Entities.User;
import Services.MessageinService;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class SendController implements Initializable {
    @FXML
    private Button demoi;

    @FXML
    private Label sender;

    @FXML
    private TextField titre;

    @FXML
    private TextArea message;

    @FXML
    private Button envoyer;
    private Message msg;
    @FXML
    public void troc(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"
            HelloController controller = loader.getController();

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
            mainStage.setWidth(screenWidth-1);
            mainStage.setHeight(screenHeight);

            // Définir la nouvelle scène sur la fenêtre principale
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void amoi(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("boitedereception.fxml"));
            Parent newContent = loader.load();

            // Créer une nouvelle scène avec le nouveau contenu
            Scene scene = new Scene(newContent);

            // Obtenir la fenêtre principale (stage)
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Définir la nouvelle scène sur la fenêtre principale
            mainStage.setScene(scene);

            // Ouvrir la fenêtre en mode plein écran
            mainStage.setFullScreen(true);

            // Afficher la fenêtre
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void demoi(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("envoyés.fxml"));
            Parent newContent = loader.load();

            // Créer une nouvelle scène avec le nouveau contenu
            Scene scene = new Scene(newContent);

            // Obtenir la fenêtre principale (stage)
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Définir la nouvelle scène sur la fenêtre principale
            mainStage.setScene(scene);

            // Ouvrir la fenêtre en mode plein écran
            mainStage.setFullScreen(true);

            // Afficher la fenêtre
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private User senderuser;
    private User recipientuser;

    public User getSenderuser() {
        return senderuser;
    }

    public User getRecipientuser() {
        return recipientuser;
    }

    public void setSenderuser(User senderuser) {
        this.senderuser = senderuser;
    }

    public void setRecipientuser(User recipientuser) {
        this.recipientuser = recipientuser;
    }

    @FXML
    public void sendmessage(){
        //this.msg=mesg;
        Message messg=new Message(titre.getText(),message.getText(),recipientuser,senderuser);
        MessageinService service=new MessageinService();
        service.envoyermsg(messg);

    }
    public void setlable(Message messagee){
        this.msg=messagee;

        if (sender != null && messagee.getSender() != null) {
            sender.setText("Le message à : "+messagee.getSender().getUsername());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
