package controllers;

import entities.Boutique;
import entities.Produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import services.BoutiqueService;
import services.ProduitService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;

public class UpdateProduitController implements Initializable {

    @FXML
    private Button btnClearProduit;

    @FXML
    private Button btnUpdateProduit;

    @FXML
    private TextArea txtUpdateDescriptionProduit;

    @FXML
    private TextField txtUpdateTitreProduit;

    @FXML
    private TextField txtUpdateCategoryProduit;

    @FXML
    private TextField txtUpdateTypeProduit;

    @FXML
    private TextField txtUpdatePrix;

    @FXML
    private AnchorPane updateProduitPane;

    @FXML
    private Button btnImportProduit;

    @FXML
    private ImageView imgProduitInput;
    @FXML
    private Label idProduit;

    @FXML
    private Label idBoutique;


    private int id;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Supposons que vous ayez l'identifiant de la boutique à modifier dans la variable id
        ProduitService ps = new ProduitService();
        Produit produitToUpdate = ps.getProduitById(id); // Méthode à implémenter dans BoutiqueService
        if (produitToUpdate != null) {
            idProduit.setVisible(false);
            idBoutique.setVisible(false);
            txtUpdateTitreProduit.setText(produitToUpdate.getTitre());
            txtUpdatePrix.setText(String.valueOf(produitToUpdate.getPrix()));
            txtUpdateDescriptionProduit.setText(produitToUpdate.getDescription());
            txtUpdateTypeProduit.setText(produitToUpdate.getType());
            txtUpdateCategoryProduit.setText(produitToUpdate.getCategory());
            idBoutique.setText(String.valueOf(produitToUpdate.getBoutique_id()));
            // Code pour afficher l'image de la boutique, si vous avez une logique pour cela
        }
    }




    @FXML
    void clearFieldsProduit() {
        txtUpdateTitreProduit.clear();
        txtUpdatePrix.clear();
        txtUpdateDescriptionProduit.clear();
        txtUpdateCategoryProduit.clear();
        txtUpdateTypeProduit.clear();
    }

    private File selectedImageFile;
    private String imageName = null;

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

            Path destination = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "uploads", imageName);
            Files.copy(selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

        }


    }


    @FXML
    void UpdateProduit(ActionEvent event) {
        if (txtUpdateTitreProduit.getText().isEmpty() || txtUpdatePrix.getText().isEmpty()
                || txtUpdateCategoryProduit.getText().isEmpty() || txtUpdateTypeProduit.getText().isEmpty()
                || txtUpdateDescriptionProduit.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information manquante");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez remplir tous les détails concernant votre produit.");
            Optional<ButtonType> option = alert.showAndWait();
        } else {
           // modifProduit();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Modifié avec succès");
            alert.setHeaderText(null);
            alert.setContentText("Votre produit a été modifié avec succès.");
            Optional<ButtonType> option = alert.showAndWait();
        }
    }
    @FXML
    void modifProduit(ActionEvent event) {
        try {
            String titre = txtUpdateTitreProduit.getText();
            String description = txtUpdateDescriptionProduit.getText();
            int prix = Integer.parseInt(txtUpdatePrix.getText());
            String category = txtUpdateCategoryProduit.getText();
            String type = txtUpdateTypeProduit.getText();
            int  idP=Integer.parseInt(idProduit.getText());
            System.out.println("-----****"+ idP);
            ProduitService ps = new ProduitService();
            Produit produitExistant = ps.getProduitById(idP);
            // Utiliser les valeurs existantes pour boutique_id et commande_id
            int boutique_id = produitExistant.getBoutique_id();
            System.out.println("-----/"+produitExistant);
            String image = (imgProduitInput != null) ? String.valueOf(imgProduitInput) : "";
            Produit prod = new Produit(idP,titre, prix, image, description, category, type, ProduitService.xyz, 1);

            System.out.println("-----"+prod.getId());

            System.out.println("-----"+prod.getTitre());
            System.out.println("-----"+prod.getBoutique_id());

            ps.updateEntity(prod);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }


    // Méthode pour définir les données nécessaires
    public void setData(Produit prod) {
        int id_b=prod.getId();
        // Charger l'image correspondante dans votre ImageView
        String imageName = prod.getImage();
        if (imageName != null && !imageName.isEmpty()) {
            // Créer l'URL de l'image en utilisant son chemin
            File file = new File(imageName);
            if (file.exists()) {
                Image image = new Image(file.toURI().toString());
                imgProduitInput.setImage(image);
            } else {
                // Gérer le cas où le fichier image n'existe pas
                System.out.println("L'image n'existe pas : " + imageName);
            }
        } else {
            // Gérer le cas où le champ image est vide ou null
            System.out.println("Le champ image est vide ou null.");
        }

        // Afficher les autres détails de la boutique
        idProduit.setText(  String.valueOf(prod.getId()));
        txtUpdateTitreProduit.setText( prod.getTitre());
        txtUpdateDescriptionProduit.setText(prod.getDescription());
        txtUpdatePrix.setText(  String.valueOf(prod.getPrix()));
        txtUpdateCategoryProduit.setText( prod.getCategory());
        txtUpdateTypeProduit.setText( prod.getType());
        idBoutique.setText(  String.valueOf(prod.getBoutique_id()));

    }

}



