package com.example.demo;

import Entities.Message;
import Entities.PostTroc;
import Entities.User;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BoitedereceptionController implements Initializable {
    @FXML
    private Button amoi;

    @FXML
    private Button demoi;



    @FXML
    private TableColumn<Message, String> de;
    @FXML
    private TableView<Message> tableView;

    @FXML
    private TableColumn<Message, String> sujet;

    @FXML
    private TableColumn<Message, Void> action;

    private User currentUser; // Supposez que currentUser contient l'utilisateur actuellement connecté
    @FXML
    private Label nbrmsgs ;
    private Message message;
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
    public void read(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("read.fxml"));
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


    // Label sentLabel = new Label("Envoyés : " + (currentUser != null ? currentUser.getSentMessages().size() : 0));
    @FXML
    public void setn() {
        MessageinService messageinService = new MessageinService();
        List<Message> liste = messageinService.getAllreceptmsg(2); // Remplacez 2 par l'ID de l'utilisateur actuel
        int numberOfMessages = liste.size();
        System.out.println(numberOfMessages);

        // Mise à jour du Label nbrmsgs avec le nombre de messages reçus
        nbrmsgs.setText(String.valueOf("Vous avez reçus  "+numberOfMessages+"  messages"));
    }


    // Méthode pour mettre à jour le nombre de messages reçus
    private void updateReceivedMessagesCount() {
        if (currentUser != null) {
            List<Message> receivedMessages = currentUser.getReceivedMessages();

            int unreadCount = 0;
            for (Message message : receivedMessages) {
                if (!message.isRead()) {
                    unreadCount++;
                }
            }
            setn();
        } else {
            nbrmsgs.setText("0");
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
    @FXML
    public void showlesmsgs() {
        MessageinService messageinService = new MessageinService();
        List<Message> Liste = messageinService.getAllreceptmsg(2);
        ObservableList<Message> observableList = FXCollections.observableList(Liste);

        de.setCellValueFactory(new PropertyValueFactory<Message,String>("id"));
        sujet.setCellValueFactory(new PropertyValueFactory<Message,String>("title"));

        // Ajouter les données à la table
        tableView.setItems(observableList);

        // Ajouter un bouton "Supprimer" à chaque ligne dans la colonne "Action"
        action.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Supprimer");

            {
                deleteButton.setOnAction(event -> {
                    Message message = getTableView().getItems().get(getIndex());
                    MessageinService messageinService = new MessageinService();
                    List<Message> liste = messageinService.getAllreceptmsg(2); // Remplacez 2 par l'ID de l'utilisateur actuel
                    int numberOfMessages = liste.size();
                    System.out.println(numberOfMessages);

                    // Mise à jour du Label nbrmsgs avec le nombre de messages reçus
                    nbrmsgs.setText(String.valueOf("Vous avez reçus  "+numberOfMessages+"  messages"));

                    // Ajoutez ici la logique pour supprimer le message
                    messageinService.deletemsg(message);

                    // Par exemple, supprimez-le de la liste observableList
                    observableList.remove(message);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });
    }
    @FXML
    public void mouseClicked(MouseEvent e){
        try {
            // Récupérer le message sélectionné dans le TableView
            Message message = tableView.getSelectionModel().getSelectedItem();

            // Charger la vue FXML du contrôleur ReadController
            FXMLLoader loader = new FXMLLoader(getClass().getResource("read.fxml"));
            Parent root = loader.load();

            // Accéder au contrôleur de la vue FXML chargée
            ReadController controller = loader.getController();
            controller.setMsg(message);

            // Appeler la méthode set du contrôleur pour afficher les données du message
            controller.set(message);

            //controller.répondre(e,message);

            // Créer une nouvelle scène avec la vue chargée
            Scene scene = new Scene(root);

            // Créer une nouvelle fenêtre pour afficher la scène
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
        updateReceivedMessagesCount();
        showlesmsgs();
        setn();
        tableView.setOnMouseClicked(this::mouseClicked);


    }
}
