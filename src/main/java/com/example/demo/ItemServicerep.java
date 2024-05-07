package com.example.demo;

import Entities.Servicerep;
import Services.ServiceNotification;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ItemServicerep implements Initializable {

        private MyListener myListener;

        public void setyListener(MyListener myListener) {
                this.myListener = myListener;
        }

        @FXML
        private Button btnModifierServicerep;

        @FXML
        private Button btnSupprimerServicerep;

        @FXML
        private Button btndetaills;

        @FXML
        private Label labelDislike;
        @FXML
        private ImageView imageItem;


        @FXML
        private Label labelIdServ;

        @FXML
        private Label labelImage;

        @FXML
        private ImageView imageView;


        @FXML
        private Label labelLike;

        @FXML
        private Label labelNomServ;

        @FXML
        private Label labelStatut;

        @FXML
        private Label labelTypeServ;


        @FXML
        private Label labelTypeVehicule;

        @FXML
        private Label labelraisonderefus;
        @FXML
        private VBox vBox;

        @FXML
        void Details(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Détails du Service");
                alert.setHeaderText(null);
                alert.setContentText("Nom du Service : " + labelNomServ.getText() + "\n" +
                        "Type du Service : " + labelTypeServ.getText() + "\n" +
                        "Type de Véhicule : " + labelTypeVehicule.getText() + "\n" +
                        "Statut : " + labelStatut.getText() + "\n" +
                        "Raison de Refus : " + labelraisonderefus.getText() + "\n" +
                        "Likes : " + labelLike.getText() + "\n" +
                        "Dislikes : " + labelDislike.getText());
                alert.showAndWait();
        }
        private ServicerepServices servicerepServices;

        public ItemServicerep() {
                this.servicerepServices = new ServicerepServices();
        }
        @FXML
        void open_UpdateServicerep(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Confirmation de la mise à jour");
                dialog.setHeaderText(null);
                dialog.setContentText("Veuillez entrer l'ID du service à mettre à jour:");

                Optional<String> result = dialog.showAndWait();
                result.ifPresent(id -> {
                        try {
                                long serviceId = Long.parseLong(id);
                                if (serviceId == servicerep.getId()) {
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("updateservice.fxml"));
                                        Parent fxml = loader.load();

                                        // Accéder au contrôleur UpdateService


                                        // Obtenez une référence au contrôleur de mise à jour de la boutique
                                        UpdatService UpdateService  = loader.getController();
                                        System.out.println(servicerep.getNomservice());
                                        System.out.println(servicerep.getId());
                                        UpdateService.setData(servicerep); // Passez l'ID de l'utilisateur à la méthode setData

                                        Stage stage = new Stage();
                                        stage.setTitle("Update Produit");
                                        stage.setScene(new Scene(fxml));
                                        stage.showAndWait();
                                } else {
                                        // Afficher un message d'erreur si aucun service n'est trouvé avec l'ID saisi
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("Erreur");
                                        alert.setHeaderText(null);
                                        alert.setContentText(" l'ID : " + id+" est incorrecte");
                                        alert.showAndWait();
                                }
                        } catch (NumberFormatException | IOException e) {
                                // Afficher un message d'erreur en cas d'erreur de conversion de l'ID ou de chargement de la vue
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Erreur");
                                alert.setHeaderText(null);
                                alert.setContentText("Veuillez entrer un ID valide.");
                                alert.showAndWait();
                        }
                });
        }





        @FXML
        void supprimerServicerep(ActionEvent event) {
                if (servicerep != null) {
                        TextInputDialog dialog = new TextInputDialog();
                        dialog.setTitle("Confirmation de suppression");
                        dialog.setHeaderText(null);
                        dialog.setContentText("Entrez l'ID du service pour confirmer la suppression :");

                        Optional<String> result = dialog.showAndWait();
                        result.ifPresent(id -> {
                                try {
                                        long serviceId = Long.parseLong(id);
                                        if (serviceId == servicerep.getId()) {
                                                // L'ID saisi correspond à l'ID du service actuel, supprimer le service
                                                ServicerepServices servicerepServices = new ServicerepServices();
                                                servicerepServices.deleteEntity(servicerep);

                                                // Rafraîchir la liste des services en supprimant le service de la liste affichée
                                                Node source = (Node) event.getSource();
                                                Node parent = source.getParent();
                                                while (!(parent instanceof AnchorPane)) {
                                                        parent = parent.getParent();
                                                }
                                                vBox.getChildren().remove(parent);



                                                // Afficher un message de confirmation
                                                Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
                                                confirmationAlert.setTitle("Service Supprimé");
                                                confirmationAlert.setHeaderText(null);
                                                confirmationAlert.setContentText("Le service a été supprimé avec succès.");
                                                confirmationAlert.showAndWait();
                                                ServiceNotification.showNotif("Suppression réussie", "Le service a été supprimé avec succès.");


                                        } else {
                                                // L'ID saisi ne correspond pas à l'ID du service actuel, afficher un message d'erreur
                                                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                                                errorAlert.setTitle("Erreur");
                                                errorAlert.setHeaderText(null);
                                                errorAlert.setContentText("L'ID saisi ne correspond pas à l'ID du service.");
                                                errorAlert.showAndWait();
                                        }
                                } catch (NumberFormatException e) {
                                        // L'ID saisi n'est pas un nombre valide, afficher un message d'erreur
                                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                                        errorAlert.setTitle("Erreur");
                                        errorAlert.setHeaderText(null);
                                        errorAlert.setContentText("Veuillez saisir un ID valide.");
                                        errorAlert.showAndWait();
                                }
                        });
                } else {
                        // Afficher un message d'erreur si servicerep est null
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Erreur");
                        errorAlert.setHeaderText(null);
                        errorAlert.setContentText("Impossible de supprimer le service. Service non trouvé.");
                        errorAlert.showAndWait();
                }
        }



        private Servicerep servicerep; // Déclaration de la variable
        public void setData(Servicerep servicerep) {
                this.servicerep = servicerep;
                labelIdServ.setText(String.valueOf(servicerep.getId()));
                labelNomServ.setText(servicerep.getNomservice());
                labelTypeServ.setText(servicerep.getTypeservice());
                labelTypeVehicule.setText(servicerep.getTypeVehicule()); // Affichage du type de véhicule
                labelStatut.setText(servicerep.getStatut());
                labelraisonderefus.setText(servicerep.getRaisonderefus());
                labelLike.setText(String.valueOf(servicerep.getLikes())); // Affichage des likes
                labelDislike.setText(String.valueOf(servicerep.getDislikes())); // Affichage des dislikes
                //String imagePath = "/uploads/" + servicerep.getImage(); // chemin relatif au répertoire resources



        }


        public Servicerep getData() {

                return this.servicerep;
        }
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
               // getData();



        }

        public void gotobackpartenaire(ActionEvent event) {
                String serviceIdd = labelIdServ.getText(); // Récupérer l'ID du service
                System.out.println(serviceIdd);
               PartenaireBack addDemandeServiceControllerr=new PartenaireBack();
               addDemandeServiceControllerr.setServiceId(Integer.parseInt(serviceIdd)); // Passer l'ID du service à la nouvelle fenêtre



                try {
                        // Charger le fichier FXML
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("partenaireback.fxml"));
                        Parent addDemandeServiceParent = loader.load();
                        PartenaireBack addDemandeServiceController = loader.getController();

                        // Passer les données du service à la nouvelle fenêtre
                        String serviceId = labelIdServ.getText(); // Récupérer l'ID du service
                        System.out.println(serviceId);

                        addDemandeServiceController.setServiceId(Integer.parseInt(serviceId)); // Passer l'ID du service à la nouvelle fenêtre

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

}
