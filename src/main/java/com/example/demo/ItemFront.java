package com.example.demo;

import Entities.Servicerep;
import Services.SendSms;
import Services.ServicerepServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemFront implements Initializable {

    private MyListener myListener;

    public void setyListener(MyListener myListener) {
        this.myListener = myListener;
    }

    @FXML
    private Button btnDemanderServicerep;

    @FXML
    private Button btndetaills;

    @FXML
    private ImageView imageItem;

    @FXML
    private Label labelCategorieVehicule;

    // @FXML
    //   private Label labelDescription;

    @FXML
    private Label labelDuree;

    @FXML
    private Label labelHoraire;

    @FXML
    private Label labelHorairedejour;

    @FXML
    private Label labelID;


    @FXML
    private Label labelImage;

    @FXML
    private Label labelNom;

    @FXML
    private Label labelPrix;

    @FXML
    private Label labelDislike;

    @FXML

    private Label labelLike;
    @FXML
    private Label labelvue;

    @FXML
    private Label labelJourrepos;

    @FXML
    private Label labelTypeService;

    public void updateViews(long id, int newViews) {
        try {
            // Mettre à jour les vues dans la base de données
            servicerepServices.updateViews(id, newViews);
            // Mettre à jour l'affichage dans l'interface utilisateur
            labelvue.setText(String.valueOf(newViews));
            System.out.println("Views updated");
        } catch (SQLException e) {
            System.out.println("An error occurred while updating views: " + e.getMessage());
        }
    }

    @FXML
    void Details(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Détails du Service");
        alert.setHeaderText(null);
        alert.setContentText("Nom du Service : " + labelNom.getText() + "\n" +
                        //"Id : " + labelID.getText() + "\n"+
                        "Type du Service : " + labelTypeService.getText() + "\n" +
                        "Type de Véhicule : " + labelCategorieVehicule.getText() + "\n" +
                      //  "Prix : " + labelPrix.getText() + "\n" +
                        "Dislike: " + labelDislike.getText() + "\n" +
                        "Likes : " + labelLike.getText() + "\n" +
                        // "Jours de repos : " + labelJourrepos.getText()+
                        "ouverture: " + labelHorairedejour.getText() + "\n" +
                        "Fermeture: " + labelHoraire.getText() + "\n" +
                        "Vues: " + labelvue.getText() + "\n"+
                "Duree: " + labelDuree.getText() + "\n"
                //  "Description: " + labelDescription.getText() + "\n"

        );

        // Mettre à jour les vues
        int currentViews = Integer.parseInt(labelvue.getText());
        updateViews(Long.parseLong(labelID.getText()), currentViews + 1);

        alert.showAndWait();
    }


    private Servicerep servicerep; // Déclaration de la variable
    public void setData(Servicerep servicerep) {
        this.servicerep = servicerep;
        labelID.setText(String.valueOf(servicerep.getId()));
        labelNom.setText(servicerep.getNomservice());
        labelTypeService.setText(servicerep.getTypeservice());
        labelCategorieVehicule.setText(servicerep.getTypeVehicule());
        labelDuree.setText(String.valueOf(servicerep.getDuree()));
        labelHorairedejour.setText(servicerep.getHorairedujour());
        labelHoraire.setText(servicerep.getHoraire());
        labelLike.setText(String.valueOf(servicerep.getLikes()));
        labelDislike.setText(String.valueOf(servicerep.getDislikes()));
        labelvue.setText(String.valueOf(servicerep.getNbvue()));

        if (servicerep.getStatut().equalsIgnoreCase("accepter")) {
            // Afficher les éléments uniquement si le statut est "accepté"
            ((Node) imageItem).setVisible(true);
            ((Node) labelID).setVisible(true);
            ((Node) labelNom).setVisible(true);
            ((Node) labelTypeService).setVisible(true);
            ((Node) labelCategorieVehicule).setVisible(true);
            ((Node) labelDuree).setVisible(true);
            ((Node) labelHorairedejour).setVisible(true);
            ((Node) labelHoraire).setVisible(true);
            ((Node) labelLike).setVisible(true);
            ((Node) labelDislike).setVisible(true);
            ((Node) labelvue).setVisible(true);
        } else {
            // Masquer tous les éléments si le statut n'est pas "accepté"
            ((Node) imageItem).setVisible(false);
            ((Node) labelID).setVisible(false);
            ((Node) labelNom).setVisible(false);
            ((Node) labelTypeService).setVisible(false);
            ((Node) labelCategorieVehicule).setVisible(false);
            ((Node) labelDuree).setVisible(false);
            ((Node) labelHorairedejour).setVisible(false);
            ((Node) labelHoraire).setVisible(false);
            ((Node) labelLike).setVisible(false);
            ((Node) labelDislike).setVisible(false);
            ((Node) labelvue).setVisible(false);
        }
    }

    /* public void setData(Servicerep servicerep) {
        if (servicerep.getStatut().equalsIgnoreCase("accepter")) { // Vérifier si le statut est "accepté"
            this.servicerep = servicerep;
            labelID.setText(String.valueOf(servicerep.getId()));
            labelNom.setText(servicerep.getNomservice());
            labelTypeService.setText(servicerep.getTypeservice());
            labelCategorieVehicule.setText(servicerep.getTypeVehicule());
            labelPrix.setText(String.valueOf(servicerep.getPrix()));// Affichage du type de véhicule
            //   labelJourrepos.setText(servicerep.getJoursderepos());
            labelHorairedejour.setText(servicerep.getHorairedujour());
            labelHoraire.setText(servicerep.getHoraire());
            //    labelDescription.setText(servicerep.getDescription());
            // labelDuree.setText(servicerep.getDuree());
            labelLike.setText(String.valueOf(servicerep.getLikes())); // Affichage des likes
            labelDislike.setText(String.valueOf(servicerep.getDislikes())); // Affichage des dislikes
            labelvue.setText(String.valueOf(servicerep.getNbvue())); // Affichage des nbvue

        } else {
            // Si le statut n'est pas "accepté", masquer l'élément
            ((Node) imageItem).setVisible(false);
        }
    }

    /*
   public void setData(Servicerep servicerep) {
        this.servicerep = servicerep;
        labelID.setText(String.valueOf(servicerep.getId()));
        labelNom.setText(servicerep.getNomservice());
        labelTypeService.setText(servicerep.getTypeservice());
        labelCategorieVehicule.setText(servicerep.getTypeVehicule());
        labelPrix.setText(String.valueOf(servicerep.getPrix()));// Affichage du type de véhicule
        //   labelJourrepos.setText(servicerep.getJoursderepos());
        labelHorairedejour.setText(servicerep.getHorairedujour());
        labelHoraire.setText(servicerep.getHoraire());
        //    labelDescription.setText(servicerep.getDescription());
        // labelDuree.setText(servicerep.getDuree());
        labelLike.setText(String.valueOf(servicerep.getLikes())); // Affichage des likes
        labelDislike.setText(String.valueOf(servicerep.getDislikes())); // Affichage des dislikes
        labelvue.setText(String.valueOf(servicerep.getNbvue())); // Affichage des nbvue

            /*  String imagePath = "/uploads/" + servicerep.getImage(); // chemin relatif au répertoire resources

                try {
                        ImageView imageView = new ImageView(getClass().getResource(imagePath).toExternalForm());
                        imageItem.setImage(imageView.getImage());
                } catch (Exception e) {
                        System.err.println("Error loading image: " + e.getMessage());
                }
    }
*/
    @FXML
    void open_DemandeServicerep(ActionEvent event) {
        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("adddemandesevice.fxml"));
            Parent addDemandeServiceParent = loader.load();
            AddDemandeService addDemandeServiceController = loader.getController();

            // Passer les données du service à la nouvelle fenêtre
            addDemandeServiceController.setServiceData(labelID.getText(), labelTypeService.getText(), labelCategorieVehicule.getText());

            Scene addDemandeServiceScene = new Scene(addDemandeServiceParent);

            // Obtenir la scène actuelle à partir de l'événement
            Stage window = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Changer la scène pour afficher la nouvelle vue
            window.setScene(addDemandeServiceScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace(); // Gérer l'erreur de chargement du fichier FXML
        }
    }



    public Servicerep getData() {
        return this.servicerep;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // getData();



    }

    public void setLikesTextField(int likesTextField) {
        this.labelLike.setText(String.valueOf(likesTextField));
    }

    public void setDislikesTextField(int dislikesTextField) {
        this.labelDislike.setText(String.valueOf(dislikesTextField));
    }

    private void afficherErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }



        private ServicerepServices servicerepServices;

        public ItemFront() {
            this.servicerepServices = new ServicerepServices();
        }

    public void updateLikes(long id, int newLikes) {
        try {
            // Mettre à jour les likes dans la base de données
            servicerepServices.updateLikess(id, newLikes);
            // Mettre à jour l'affichage dans l'interface utilisateur
            setLikesTextField(newLikes);
            System.out.println("Likes updated");
        } catch (SQLException e) {
            System.out.println("An error occurred while updating likes: " + e.getMessage());
        }
    }

    public void updateDislikes(long id, int newDislikes) {
        try {
            // Mettre à jour les dislikes dans la base de données
            servicerepServices.updateDislikess(id, newDislikes);
            // Mettre à jour l'affichage dans l'interface utilisateur
            setDislikesTextField(newDislikes);
            System.out.println("Dislikes updated");

            // Vérifier si le nombre de dislikes est supérieur au nombre de likes de plus de 4
            int likes = Integer.parseInt(labelLike.getText());
            if (newDislikes > likes + 4) {
                // Mettre à jour le statut en "En attente"
                servicerepServices.updateStatut(id, "En attente");
                // Remettre à zéro les likes et dislikes
                setLikesTextField(0);
                setDislikesTextField(0);
                // Récupérer le numéro de téléphone à partir du Servicerep
                String numeroTelephone = servicerep.getDuree();

// Envoyer un SMS à l'utilisateur pour l'informer du blocage de son compte

                SendSms sendSms = new SendSms();
                String message = "Votre compte a été bloqué en raison d'un nombre excessif de dislikes.";
                sendSms.sendSms(message,numeroTelephone ); // Remplacez "numéro_de_téléphone" par le numéro de téléphone de l'utilisateur

            }
        } catch (SQLException e) {
            System.out.println("An error occurred while updating dislikes: " + e.getMessage());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 /*   public void updateDislikes(long id, int newDislikes) {
        try {
            // Mettre à jour les dislikes dans la base de données
            servicerepServices.updateDislikess(id, newDislikes);
            // Mettre à jour l'affichage dans l'interface utilisateur
            setDislikesTextField(newDislikes);
            System.out.println("Dislikes updated");
        } catch (SQLException e) {
            System.out.println("An error occurred while updating dislikes: " + e.getMessage());
        }
    }*/




    @FXML
    void DisLikeButton(ActionEvent event) {
        if (!labelDislike.getText().isEmpty()) {
            int currentDislikes = Integer.parseInt(labelDislike.getText());
            setDislikesTextField(currentDislikes + 1);
            long id = Long.parseLong(labelID.getText()); // Récupérer l'ID du service
            System.out.println("Updating dislikes for service with ID: " + id);
            updateDislikes(id, currentDislikes + 1); // Appeler la méthode avec l'ID et le nouveau nombre de dislikes
        }
    }

    @FXML
    void LikesButton(ActionEvent event) {
        if (!labelLike.getText().isEmpty()) {
            int currentLikes = Integer.parseInt(labelLike.getText());
            setLikesTextField(currentLikes + 1);
            long id = Long.parseLong(labelID.getText()); // Récupérer l'ID du service
            System.out.println("Updating likes for service with ID: " + id);
            updateLikes(id, currentLikes + 1); // Appeler la méthode avec l'ID et le nouveau nombre de likes
        }
    }




}
