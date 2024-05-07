package com.example.demo;


import Entities.Boutique;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import Services.BoutiqueService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class updateBoutiqueController implements Initializable {

    @FXML
    private Button btnClearBoutique;

    @FXML
    private Button btnUpdateBoutique;

    @FXML
    private TextArea txtUpdateDescriptionBoutique;

    @FXML
    private TextField txtUpdateNomBoutique;

    @FXML
    private TextField txtUpdateVilleBoutique;

    @FXML
    private TextField txtUpdateGouvernoratBoutique;

    @FXML
    private TextField txtUpdateNumeroTel;

    @FXML
    private AnchorPane updateProduitPane;

    @FXML
    private Button btnImportBoutique;

    @FXML
    private ImageView imgBoutiqueInput;
    @FXML
    private Label idBoutique;

    private int id;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Supposons que vous ayez l'identifiant de la boutique à modifier dans la variable id
        BoutiqueService bs = new BoutiqueService();
        Boutique boutiqueToUpdate = bs.getBoutiqueById(id); // Méthode à implémenter dans BoutiqueService
        if (boutiqueToUpdate != null) {
            idBoutique.setVisible(false);
            txtUpdateNomBoutique.setText(boutiqueToUpdate.getNom());
            txtUpdateNumeroTel.setText(String.valueOf(boutiqueToUpdate.getNum_telephone()));
            txtUpdateDescriptionBoutique.setText(boutiqueToUpdate.getDescription());
            txtUpdateGouvernoratBoutique.setText(boutiqueToUpdate.getGouvernorat());
            txtUpdateVilleBoutique.setText(boutiqueToUpdate.getVille());
            // Code pour afficher l'image de la boutique, si vous avez une logique pour cela
        }
    }




    @FXML
    void clearFieldsBoutique() {
        txtUpdateNomBoutique.clear();
        txtUpdateNumeroTel.clear();
        txtUpdateDescriptionBoutique.clear();
        txtUpdateGouvernoratBoutique.clear();
        txtUpdateVilleBoutique.clear();
    }

    private File selectedImageFile;
    private String imageName = null;

    @FXML
    void ajouterImage(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog(imgBoutiqueInput.getScene().getWindow());
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            imgBoutiqueInput.setImage(image);

            // Générer un nom de fichier unique pour l'image
            String uniqueID = UUID.randomUUID().toString();
            String extension = selectedImageFile.getName().substring(selectedImageFile.getName().lastIndexOf("."));
            imageName = uniqueID + extension;

            Path destination = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "uploads", imageName);
            Files.copy(selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

        }


    }


    @FXML
    void UpdateBoutique(ActionEvent event) {
        if (txtUpdateNomBoutique.getText().isEmpty() || txtUpdateNumeroTel.getText().isEmpty()
                || txtUpdateGouvernoratBoutique.getText().isEmpty() || txtUpdateVilleBoutique.getText().isEmpty()
                || txtUpdateDescriptionBoutique.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information manquante");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez remplir tous les détails concernant votre produit.");
            Optional<ButtonType> option = alert.showAndWait();
        } else {
            //modifBoutique();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Modifié avec succès");
            alert.setHeaderText(null);
            alert.setContentText("Votre produit a été modifié avec succès.");
            Optional<ButtonType> option = alert.showAndWait();
        }
    }
    @FXML
    void modifBoutique(ActionEvent event) {
        try {
            String nom = txtUpdateNomBoutique.getText();
            String description = txtUpdateDescriptionBoutique.getText();
            int num_telephone = Integer.parseInt(txtUpdateNumeroTel.getText());
            String gouvernorat = txtUpdateGouvernoratBoutique.getText();
            String ville = txtUpdateVilleBoutique.getText();
            int  idB=Integer.parseInt(idBoutique.getText());
            String image = (imgBoutiqueInput != null) ? String.valueOf(imgBoutiqueInput) : "";

            int user_id = 1;
            Boutique boutique = new Boutique(idB,nom, image, description, num_telephone, gouvernorat, ville, user_id);
            BoutiqueService bs = new BoutiqueService();
            System.out.println("-----"+boutique.getId());

            System.out.println("-----"+boutique.getNom());
            bs.updateEntity(boutique);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }


    // Méthode pour définir les données nécessaires
    public void setData(Boutique boutique) {
        int id_b=boutique.getId();
        // Charger l'image correspondante dans votre ImageView
        String imageName = boutique.getImage();
        if (imageName != null && !imageName.isEmpty()) {
            // Créer l'URL de l'image en utilisant son chemin
            File file = new File(imageName);
            if (file.exists()) {
                Image image = new Image(file.toURI().toString());
                imgBoutiqueInput.setImage(image);
            } else {
                // Gérer le cas où le fichier image n'existe pas
                System.out.println("L'image n'existe pas : " + imageName);
            }
        } else {
            // Gérer le cas où le champ image est vide ou null
            System.out.println("Le champ image est vide ou null.");
        }

        // Afficher les autres détails de la boutique
        idBoutique.setText(  String.valueOf(boutique.getId()));
        txtUpdateNomBoutique.setText( boutique.getNom());
        txtUpdateDescriptionBoutique.setText(boutique.getDescription());
        txtUpdateNumeroTel.setText(  String.valueOf(boutique.getNum_telephone()));
        txtUpdateGouvernoratBoutique.setText( boutique.getGouvernorat());
        txtUpdateVilleBoutique.setText( boutique.getVille());
    }

}


