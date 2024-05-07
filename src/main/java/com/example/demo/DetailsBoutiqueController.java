package com.example.demo;


import Entities.Boutique;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import Services.BoutiqueService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class DetailsBoutiqueController implements Initializable {

    @FXML
    private Label lblNom;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblGouvernorat;

    @FXML
    private Label lblVille;

    @FXML
    private Label lblNumeroTel;

    @FXML
    private Button btnModifierBoutique;

    @FXML
    private Button btnSupprimerBoutique;

    private Boutique boutique;
    MyListener myListener;

    public void setBoutique(Boutique boutique) {
        this.boutique = boutique;
        updateDetails();
    }

    private void updateDetails() {
        lblNom.setText(boutique.getNom());
        lblDescription.setText(boutique.getDescription());
        lblGouvernorat.setText(boutique.getGouvernorat());
        lblVille.setText(boutique.getVille());
        lblNumeroTel.setText(String.valueOf(boutique.getNum_telephone()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Code d'initialisation, si nécessaire
    }

    @FXML
    void supprimerBoutique(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Supprimer la boutique ?");
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cette boutique ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // L'utilisateur a confirmé la suppression
            try {
                BoutiqueService boutiqueService = new BoutiqueService();
                boutiqueService.deleteEntity(boutique.getId()); // Supprimer la boutique de la base de données
                myListener.onBoutiqueDeleted(boutique); // Informer le listener que la boutique a été supprimée
                // Afficher un message de confirmation
                Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
                confirmationAlert.setTitle("Boutique supprimée");
                confirmationAlert.setHeaderText(null);
                confirmationAlert.setContentText("La boutique a été supprimée avec succès.");
                confirmationAlert.showAndWait();
            } catch (SQLException e) {
                e.printStackTrace(); // Gérer les erreurs liées à la suppression de la boutique
                // Afficher un message d'erreur
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Erreur");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Une erreur s'est produite lors de la suppression de la boutique.");
                errorAlert.showAndWait();
            }
        }
    }
    @FXML
    void open_UpdateBoutique(ActionEvent event) throws IOException {
        System.out.println("inside method");
        int userId = 1; // Vous pouvez initialiser l'ID de l'utilisateur comme vous le souhaitez
        FXMLLoader loader = new FXMLLoader(getClass().getResource("updateBoutique.fxml"));
        Parent fxml = loader.load();

        // Obtenez une référence au contrôleur de mise à jour de la boutique
        updateBoutiqueController controller = loader.getController();
        System.out.println(boutique.getNom());
        System.out.println(boutique.getId());
        controller.setData(boutique); // Passez l'ID de l'utilisateur à la méthode setData

        Stage stage = new Stage();
        stage.setTitle("Update Boutique");
        stage.setScene(new Scene(fxml));
        stage.showAndWait();
    }


}

