package controllers;

import entities.Boutique;
import entities.Produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ProduitService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
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
    void ajouterImage(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog(imgProduitInput.getScene().getWindow());
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            imgProduitInput.setImage(image);

            // Générer un nom de fichier unique pour l'image
            String uniqueID = UUID.randomUUID().toString();
            String extension = selectedImageFile.getName().substring(selectedImageFile.getName().lastIndexOf("."));
            imageName = uniqueID + extension;

            Path destination = Path.of("src/main/resources/uploads");
            Files.createDirectories(destination);
            Files.copy(selectedImageFile.toPath(), destination.resolve(imageName), StandardCopyOption.REPLACE_EXISTING);

        }
    }


    @FXML
    void ajouterProduit(ActionEvent event) {
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

            // Assurez-vous que imageName contient le chemin de l'image
            Produit produit = new Produit(titre, prix, image, description, categorie, type, boutique.getId(), 1);

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
