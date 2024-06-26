package com.example.demo;

import Entities.Message;
import Entities.User;
import Services.MessageinService;
import Services.UserService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class envoyéesController implements Initializable {
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
    public void location(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/frontvl.fxml"));
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
    private Button amoi;

    @FXML
    private Button demoi;
    @FXML
    private MenuItem button_logout;
    public void event(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("index-evenement-front.fxml"));
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
    private TableColumn<Message, String> à;
    @FXML
    private TableView<Message> tableView;

    @FXML
    private TableColumn<Message, String> sujet;

    @FXML
    private TableColumn<Message, Void> action;
    private User currentUser; // Supposez que currentUser contient l'utilisateur actuellement connecté
    String loggedInUserEmail = DBUtils.getLoggedInUserEmail();
    UserService serviceuser=new UserService();
    User user= serviceuser.getuserfromemail(loggedInUserEmail);
    @FXML
    private Label nbrmsgs ;
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
        List<Message> liste = messageinService.getsentmsg(user.getId()); // Remplacez 2 par l'ID de l'utilisateur actuel
        int numberOfMessages = liste.size();
        System.out.println(numberOfMessages);

        // Mise à jour du Label nbrmsgs avec le nombre de messages reçus
        nbrmsgs.setText(String.valueOf("Vous avez evoyé  "+numberOfMessages+"  messages"));
    }
    @FXML
    public void showlesmsgs(){
        MessageinService messageinService = new MessageinService();
        List<Message> Liste = messageinService.getsentmsg(user.getId());
        System.out.println(Liste);
        ObservableList<Message> observableList = FXCollections.observableList(Liste);
        //User delapart=;
           à.setCellValueFactory(cellData -> {
            Message message = cellData.getValue();
            int senderId = message.getRecipient();
            User recipient = serviceuser.getUserById(senderId);
            if (recipient != null) {
                String nomPrenom = recipient.getNom() + " " + recipient.getPrenom();
                return new SimpleStringProperty(nomPrenom);
            } else {
                return new SimpleStringProperty("Expéditeur inconnu");
            }
        });
        sujet.setCellValueFactory(new PropertyValueFactory<Message,String>("title"));
        tableView.setItems(observableList);

        // Ajouter un bouton "Supprimer" à chaque ligne dans la colonne "Action"
        action.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Supprimer");

            {
                deleteButton.setOnAction(event -> {
                    Message message = getTableView().getItems().get(getIndex());
                    MessageinService messageinService = new MessageinService();
                    List<Message> liste = messageinService.getAllreceptmsg(user.getId()); // Remplacez 2 par l'ID de l'utilisateur actuel
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
            public void mouseClicked(MouseEvent e) {
                try {
                    // Récupérer le message sélectionné dans le TableView
                    Message message = tableView.getSelectionModel().getSelectedItem();

                    // Charger la vue FXML du contrôleur ReadController
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("readmine.fxml"));
                    Parent root = loader.load();

                    // Accéder au contrôleur de la vue FXML chargée
                    ReadmineController controller = loader.getController();
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


            // Méthode pour mettre à jour le nombre de messages reçus
            private void updateReceivedMessagesCount() {
                if (currentUser != null) {
                    List<Message> receivedMessages = currentUser.getSentMessages();

                    int unreadCount = 0;
                    for (Message message : receivedMessages) {
                        à.setText(serviceuser.getUserById(message.getSender()).getNom()+" "+serviceuser.getUserById(message.getSender()).getPrenom());
                        sujet.setText(message.getTitle());
                        action.setText("Supprimer");
                        if (!message.isRead()) {
                            unreadCount++;
                        }
                    }
                    nbrmsgs.setText(Integer.toString(unreadCount));
                } else {
                    nbrmsgs.setText("0");
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
                updateReceivedMessagesCount();
                showlesmsgs();
                setn();
                tableView.setOnMouseClicked(this::mouseClicked);
                button_logout.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        DBUtils.changeScence(actionEvent, "authentifier.fxml", "Log In", null, null,button_logout);
                    }
                });

            }
        }
