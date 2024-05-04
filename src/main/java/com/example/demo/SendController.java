package com.example.demo;

import Entities.Message;
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
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SendController implements Initializable {

    @FXML
    private Label nbmot;

    @FXML
    private Label sender;

    @FXML
    private TextField titre;
    @FXML
    private MenuItem button_logout;

    @FXML
    private TextArea message;

    @FXML
    private Button envoyer;
    private Message msg;
    private int senderuser;
    private int recipientuser;
    String loggedInUserEmail = DBUtils.getLoggedInUserEmail();
    UserService serviceuser=new UserService();
    User user= serviceuser.getuserfromemail(loggedInUserEmail);

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
    public void amoi(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("boitedereception.fxml"));
            Parent newContent = loader.load();

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
    public void demoi(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("envoyés.fxml"));
            Parent newContent = loader.load();

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



    public int getSenderuser() {
        return senderuser;
    }

    public int getRecipientuser() {
        return recipientuser;
    }

    public void setSenderuser(int senderuser) {
        this.senderuser = senderuser;
    }


    public void setRecipientuser(int recipientuser) {
        this.recipientuser = recipientuser;
    }

    @FXML
    public void sendmessage(ActionEvent event){
        if (validateFields()) {
            Message messg=new Message(titre.getText(),message.getText(),recipientuser,user.getId());
        MessageinService service=new MessageinService();
        service.envoyermsg(messg);
            demoi(event);
            // Affichage d'un message de succès
            showAlert("Message envoyé avec succès !", event);
        }
    }


    private boolean validateFields() {
        boolean isValid = true;
        StringBuilder errorMessage = new StringBuilder();

        // Validation du champ titre
        if (titre.getText().isEmpty()) {
            isValid = false;
            errorMessage.append("Le champ 'Titre' ne peut pas être vide.\n");
        }

        // Validation du champ message
        if (message.getText().isEmpty()) {
            isValid = false;
            errorMessage.append("Le champ 'Message' ne peut pas être vide.\n");
        }

        // Retourner le message d'erreur
        if (!isValid) {
            showErrorAlert(errorMessage.toString());
        }

        return isValid;
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    @FXML
    public void sendmessagefromdetails(ActionEvent event) {
        // Validation des champs titre et message
        if (validateFields()) {

            // Envoi du message
            Message messg = new Message(titre.getText(), message.getText(), user.getId(), recipientuser);
            MessageinService service = new MessageinService();
            service.envoyermsgfromdetails(messg);
            troc(event);
            // Affichage d'un message de succès
            showAlert("Message envoyé avec succès !", event);
        }
    }

    private void showAlert(String message, ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Ajouter un événement handler pour le bouton "OK"
        alert.setOnCloseRequest(e -> {
            // Naviguer vers la page "trocback"
            demoi(event);
        });

        alert.showAndWait();
    }
    UserService service=new UserService();

    public void setlable(Message messagee){
        this.msg=messagee;

        if (sender != null && messagee.getRecipient() != 0) {
            sender.setText("Le message à : "+service.getUserById(messagee.getRecipient()).getNom()+" "+service.getUserById(messagee.getRecipient()).getPrenom());
        }
    }

    public void setlablefromdetails(PostTroc postTroc){

            sender.setText("Le message à : "+service.getUserById(postTroc.getUser()).getNom()+" "+service.getUserById(postTroc.getUser()).getPrenom());
            System.out.println("Le message à : "+service.getUserById(postTroc.getUser()).getNom()+" "+service.getUserById(postTroc.getUser()).getPrenom());

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
        //setlablefromdetails();
        //nbrmots();
        start();
        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScence(actionEvent, "authentifier.fxml", "Log In", null, null,button_logout);
            }
        });

    }
    public void start() {
        // Création d'un TextArea
        TextArea textArea = new TextArea();

        // Création d'une étiquette pour afficher le nombre de mots
        Label wordCountLabel = new Label("[0]");

        // Ajout d'un écouteur sur le texte du TextArea pour mettre à jour le compteur de mots
        message.textProperty().addListener((observable, oldValue, newValue) -> {
            // Compter les mots dans le nouveau texte
            int wordCount = countWords(newValue);
            // Mettre à jour l'étiquette avec le nombre de mots
            nbmot.setText("[" + wordCount+"]");
        });

        // Création de la mise en page
        VBox root = new VBox(textArea, wordCountLabel);

        // Création de la scène
        Scene scene = new Scene(root, 400, 300);

        // Configuration de la scène et affichage de la fenêtre

    }

    // Méthode pour compter les mots dans une chaîne de texte
    private int countWords(String text) {

            // Supprimer les espaces en début et fin de la chaîne
            text = text.trim();
            // Diviser la chaîne en mots en utilisant l'espace comme délimiteur
            String[] words = text.split("\\s+");
            // Retourner le nombre de mots
            return words.length;


    }

}
