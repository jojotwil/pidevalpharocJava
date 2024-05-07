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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import Services.BoutiqueService;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class listBoutiqueCardFrontController implements Initializable {

    @FXML
    private Label labelNomBoutique;


    @FXML
    private ImageView imgBoutique;

    @FXML
    private Button btnModifierBoutique;

    @FXML
    private Button btnSupprimerBoutique;

    @FXML
    private Button btnDetailsBoutique ;

    @FXML
    private Label labelidBoutique ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelidBoutique.setVisible(false);

    }

    Boutique boutique;
    MyListener myListener;
    private Image imagee;
    @FXML
    private ImageView image;

    public void setData (Boutique boutique, MyListener myListener){
        this.boutique = boutique ;
        this.myListener = myListener;
        labelidBoutique.setText(String.valueOf(boutique.getId()));
        labelNomBoutique.setText(boutique.getNom());
        try {
            imagee = new Image( boutique.getImage());
            System.out.println(boutique.getImage());
            imgBoutique.setImage(imagee);

        } catch (Exception e) {
            // Gérer l'erreur, par exemple afficher un message d'erreur ou une image par défaut
            System.out.println("Erreur lors du chargement de l'image : " + e.getMessage());
        }
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
    void ajouterProduit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterProduit.fxml"));
        Parent fxml = loader.load();

        // Obtenez une référence au contrôleur d'ajout de produit
        AjouterProduitController controller = loader.getController();
        controller.setBoutique(boutique); // Passez la boutique actuelle au contrôleur d'ajout de produit

        Stage stage = new Stage();
        stage.setTitle("Ajouter Produit");
        stage.setScene(new Scene(fxml));
        stage.showAndWait();

    }


    @FXML
    void showDetails(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsBoutique.fxml"));
            Parent root = loader.load();

            DetailsBoutiqueController controller = loader.getController();
            controller.setBoutique(boutique);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Détails de la boutique : " + boutique.getNom());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Gérer l'erreur de chargement de la vue DetailsBoutique.fxml
        }
    }
}
