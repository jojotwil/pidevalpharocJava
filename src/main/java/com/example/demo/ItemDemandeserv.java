package com.example.demo;

import Entities.Demandeserv;
import Services.DemandeservServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ItemDemandeserv {

    @FXML
    private Button btnModifierServicerep;

    @FXML
    private Button btnSupprimerServicerep;

    @FXML
    private Button btndetaills;

    @FXML
    private ImageView imageItem;
    @FXML
    private Label LAbelID;

    @FXML
    private Label labelCategorieVehicule;

    @FXML
    private Label labelDescription;

    @FXML
    private Label labelDuree;

    @FXML
    private Label labelHoraire;

    @FXML
    private Label labelImage;

    @FXML
    private Label labelMarque;

    @FXML
    private Label labelModele;

    @FXML
    private Label labelNom;

    @FXML
    private Label labelStatut;

    @FXML
    private Label labelTypeCarburant;
    private Demandeserv prod;
    @FXML
    void Details(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Détails du Service");
        alert.setHeaderText(null);
        alert.setContentText("Nom du Service : " + labelNom.getText() + "\n" +
                "Marque : " + labelMarque.getText() + "\n" +
                "Modèle : " + labelModele.getText() + "\n" +
                "Description : " + labelDescription.getText() + "\n" +
                "Durée : " + labelDuree.getText() + "\n" +
                "Horaire : " + labelHoraire.getText() + "\n" +
                "Statut : " + labelStatut.getText() + "\n" +
                "Type de Carburant : " + labelTypeCarburant.getText() + "\n" +
                "Catégorie de Véhicule : " + labelCategorieVehicule.getText());
        alert.showAndWait();
    }
 /*   @FXML
    void open_UpdateServicerep(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Mise à jour du service");
        dialog.setHeaderText(null);
        dialog.setContentText("Veuillez saisir l'ID du service à mettre à jour:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                Integer id = Integer.parseInt(result.get());
                if (id == demandeserv.getId()){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/updatedemandeservice.fxml"));
                    Parent fxml = loader.load();

                    UpdateDemandeservice updateService = loader.getController();
                    updateService.setId(id);
                    updateService.setData(demandeserv);

                    Stage stage = new Stage();
                    stage.setTitle("Mise à jour du Produit");
                    stage.setScene(new Scene(fxml));
                    stage.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("L'ID saisi ne correspond pas à l'ID du service.");
                    alert.showAndWait();
                }
            } catch (NumberFormatException | IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez saisir un ID valide (nombre entier).");
                alert.showAndWait();
            }
        }
    }


 /*   @FXML
    void open_UpdateServicerep(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Mise à jour du service");
        dialog.setHeaderText(null);
        dialog.setContentText("Veuillez saisir l'ID du service à mettre à jour:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                Integer id = Integer.parseInt(result.get());
                if (id == demandeserv.getId()){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/updatedemandeservice.fxml"));
                    Parent fxml = loader.load();

                    UpdateDemandeservice UpdateService = loader.getController();
                    UpdateService.setData(demandeserv);

                    Stage stage = new Stage();
                    stage.setTitle("Mise à jour du Produit");
                    stage.setScene(new Scene(fxml));
                    stage.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("L'ID saisi ne correspond pas à l'ID du service.");
                    alert.showAndWait();
                }
            } catch (NumberFormatException | IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez saisir un ID valide (nombre entier).");
                alert.showAndWait();
            }
        }
    }
*/
      @FXML
    void open_UpdateServicerep(ActionEvent event)  throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("updatedemandeservice.fxml"));
        Parent fxml = loader.load();

        // Accéder au contrôleur UpdateService


        // Obtenez une référence au contrôleur de mise à jour de la boutique
        UpdateDemandeservice UpdateService  = loader.getController();
        System.out.println(demandeserv.getNom());
        System.out.println(demandeserv.getId());
        UpdateService.setData(demandeserv); // Passez l'ID de l'utilisateur à la méthode setData

        Stage stage = new Stage();
        stage.setTitle("Update Produit");
        stage.setScene(new Scene(fxml));
        stage.showAndWait();

    }
    @FXML
    void supprimerServicerep(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Confirmation de suppression");
        dialog.setHeaderText(null);
        dialog.setContentText("Veuillez saisir l'ID du service à supprimer:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                Integer id = Integer.parseInt(result.get());
                if (id == demandeserv.getId()) {
                    Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmationAlert.setTitle("Confirmation de suppression");
                    confirmationAlert.setHeaderText(null);
                    confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer ce service ?");
                    Optional<ButtonType> confirmationResult = confirmationAlert.showAndWait();
                    if (confirmationResult.isPresent() && confirmationResult.get() == ButtonType.OK) {
                        DemandeservServices ic = new DemandeservServices();
                        ic.deleteEntity(demandeserv);
                        // Rafraîchir la liste des services après la suppression
                        ListDemandeService serviceManager = new ListDemandeService();
                        serviceManager.refreshServiceList();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("L'ID saisi ne correspond pas à l'ID du service.");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez saisir un ID valide (nombre entier).");
                alert.showAndWait();
            }
        }
    }


    /*@FXML
    void supprimerServicerep(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer ce service ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DemandeservServices ic =new DemandeservServices();
            ic.deleteEntity(demandeserv);
            // Rafraîchir la liste des services après la suppression
            ListDemandeService serviceManager = new ListDemandeService();
            serviceManager.refreshServiceList();

        }

    }*/
    private Demandeserv demandeserv;
    public void setData(Demandeserv demandeserv) {
        this.demandeserv=demandeserv;
        labelCategorieVehicule.setText(demandeserv.getCategorieVehicule());
        labelDescription.setText(demandeserv.getDescription());
        labelDuree.setText(String.valueOf(demandeserv.getDuree()));
       // LAbelID.setText(String.valueOf(demandeserv.getId()));
        labelHoraire.setText(demandeserv.getHoraire().toString());
        // Assurez-vous que getImageName() renvoie une valeur compatible avec le label
        // labelImage.setText(...);
        labelMarque.setText(demandeserv.getMarque());
        labelModele.setText(demandeserv.getModele());
        labelNom.setText(demandeserv.getNom());
        labelStatut.setText(demandeserv.getStatut());
        labelTypeCarburant.setText(demandeserv.getTypeCarburant());

    }

}
