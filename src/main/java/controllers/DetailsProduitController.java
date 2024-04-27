package controllers;

import entities.Produit;
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
import services.ProduitService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class DetailsProduitController implements Initializable {

    @FXML
    private Label lblTitre;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblPrix;

    @FXML
    private Label lblType;
    @FXML
    private Label lblCategorie;


    @FXML
    private Button btnModifierBoutique;

    @FXML
    private Button btnSupprimerBoutique;

    private Produit prod;
    MyListener myListener;

    public void setProduit(Produit prod) {
        this.prod = prod;
        updateDetails();
    }

    private void updateDetails() {
        lblTitre.setText(prod.getTitre());
        lblDescription.setText(prod.getDescription());
        lblType.setText(prod.getType());
        lblCategorie.setText(prod.getCategory());
        lblPrix.setText(String.valueOf(prod.getPrix()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Code d'initialisation, si nécessaire
    }

    @FXML
    void supprimerProduit(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Supprimer le produit ?");
        alert.setContentText("Êtes-vous sûr de vouloir supprimer ce Produit ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // L'utilisateur a confirmé la suppression
            try {
                ProduitService produitService = new ProduitService();
                produitService.deleteEntity(prod.getId()); // Supprimer la boutique de la base de données
                myListener.onProduitDeleted(prod); // Informer le listener que la boutique a été supprimée
                // Afficher un message de confirmation
                Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
                confirmationAlert.setTitle("Produit supprimée");
                confirmationAlert.setHeaderText(null);
                confirmationAlert.setContentText("Le produit  a été supprimée avec succès.");
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
    void open_UpdateProduit(ActionEvent event) throws IOException {
        System.out.println("inside method");
        // Vous pouvez initialiser l'ID de l'utilisateur comme vous le souhaitez
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateProduit.fxml"));
        Parent fxml = loader.load();

        // Obtenez une référence au contrôleur de mise à jour de la boutique
        UpdateProduitController controller = loader.getController();
        System.out.println(prod.getTitre());
        System.out.println(prod.getId());
        controller.setData(prod); // Passez l'ID de l'utilisateur à la méthode setData

        Stage stage = new Stage();
        stage.setTitle("Update Produit");
        stage.setScene(new Scene(fxml));
        stage.showAndWait();
    }




}
