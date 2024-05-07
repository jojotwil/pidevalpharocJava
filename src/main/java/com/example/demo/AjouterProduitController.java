package com.example.demo;
import Entities.Boutique;
import Entities.PostTroc;
import Entities.Produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Services.ProduitService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

public class AjouterProduitController {

    @FXML
    private TextField txtTitre;

    @FXML
    private TextField txtPrix;

    @FXML
    private ImageView imgProduitInput;
    @FXML
    private TextArea areaDescriptionProduit;

    @FXML
    private TextField txtCategorie;

    @FXML
    private TextField txtType;

    @FXML
    private Button btnAjouter;

    private Boutique boutique;

    private File selectedImageFile;
    private String imageName = null ;

    public void setBoutique(Boutique boutique) {
        this.boutique = boutique;
    }

    @FXML
    private void browseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Fichiers images", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Tous les fichiers", "*.*")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imgProduitInput.setImage(image);
            System.out.println(selectedFile.toURI().toString());
            PostTroc post=new PostTroc();
            post.setImage(selectedFile.toURI().toString());
            imageName=selectedFile.toURI().toString();
        }
    }

    @FXML
    void ajouterProduit(ActionEvent event) {
        Produit produit = null;
        try {
            String titre = txtTitre.getText();
            String prixText = txtPrix.getText();
            String image = imageName;
            String description = areaDescriptionProduit.getText();
            String categorie = txtCategorie.getText();
            String type = txtType.getText();

            // Vérifier que tous les champs sont remplis
            if (titre.isEmpty() || prixText.isEmpty() || image.isEmpty() || description.isEmpty() || categorie.isEmpty() || type.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez remplir tous les champs.");
                return; // Arrêter l'exécution de la méthode si un champ est vide
            }

            // Vérifier que le prix est positif
            float prix = Float.parseFloat(prixText);
            if (prix <= 0) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Le prix doit être positif.");
                return; // Arrêter l'exécution de la méthode si le prix est négatif ou nul
            }


            // Création de la boîte de dialogue Alert
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Voulez-vous appliquer une remise de 10% ?");

            // Ajout des boutons personnalisés pour Oui et Non
            ButtonType ouiButton = new ButtonType("Oui");
            ButtonType nonButton = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
            confirmationAlert.getButtonTypes().setAll(ouiButton, nonButton);

            // Affichage de la boîte de dialogue et attente de la réponse de l'utilisateur
            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ouiButton) {
                // Si l'utilisateur choisit "Oui", appliquer une remise de 10%
                float prixRemise = prix - (prix * 10 / 100);
                // Utiliser le prix avec remise pour créer le produit
                produit = new Produit(titre, prixRemise, image, description, categorie, type, boutique.getId(), 1);
            } else {
                // Sinon, utiliser le prix initial pour créer le produit
                produit = new Produit(titre, prix, image, description, categorie, type, boutique.getId(), 1);
            }

            ProduitService produitService = new ProduitService();
            produitService.addEntity(produit);
            showAlert(Alert.AlertType.INFORMATION, "Succès", "Produit ajouté avec succès.");
            closeWindow();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Le format du prix est invalide.");
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer les exceptions qui pourraient survenir lors de l'ajout de la boutique
            // Afficher un message d'erreur à l'utilisateur ou gérer l'exception de manière appropriée
        }
    }


    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void closeWindow() {
        Stage stage = (Stage) btnAjouter.getScene().getWindow();
        stage.close();
    }
}

