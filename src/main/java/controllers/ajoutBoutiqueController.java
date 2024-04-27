package controllers;


import entities.Boutique;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.util.Duration;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import services.BoutiqueService;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;

public class ajoutBoutiqueController implements Initializable {




    @FXML
    private Label nomboutique;
    @FXML
    private AnchorPane addBoutiquePane;



    @FXML
    private Button btnAddBoutique;

    @FXML
    private Button btnClearBoutique;

    @FXML
    private Button btnReturnBoutique;

    @FXML
    private TextField txtNomBoutique;
    @FXML
    private TextArea areaDescriptionBoutique;

    @FXML
    private TextField txtNumTelBoutique;

    @FXML
    private TextField txtgouvBoutique;

    @FXML
    private TextField txtvilleBoutique;
    @FXML
    private ImageView imgBoutiqueInput;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }




    @FXML
    void clearFieldsBoutique() {
        txtNomBoutique.clear();
        txtNumTelBoutique.clear();
        txtgouvBoutique.clear();
        txtvilleBoutique.clear();
        areaDescriptionBoutique.clear();
        imgBoutiqueInput.setImage(null);
    }

    private File selectedImageFile;
    private String imageName = null ;

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

            Path destination = Path.of("src/main/resources/uploads");
            Files.createDirectories(destination);
            Files.copy(selectedImageFile.toPath(), destination.resolve(imageName), StandardCopyOption.REPLACE_EXISTING);

        }
    }

    @FXML
    void AjoutBoutique(ActionEvent event) {
        if (event.getSource() == btnAddBoutique) {
            if (txtNomBoutique.getText().isEmpty() || txtNumTelBoutique.getText().isEmpty()
                    || txtgouvBoutique.getText().isEmpty() || txtvilleBoutique.getText().isEmpty()
                    || areaDescriptionBoutique.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information manquante");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez remplir tous les détails concernant votre produit.");
                alert.showAndWait();
            } else {
                ajouterBoutique();
            }
        }
        if (event.getSource() == btnClearBoutique) {
            clearFieldsBoutique();
        }
    }

    private void ajouterBoutique() {
        try {
            // À partir du formulaire
            String nom = txtNomBoutique.getText();
            String image = imageName; // Assurez-vous que imageName contient le chemin de l'image
            String description = areaDescriptionBoutique.getText();
            String gouvernorat = txtgouvBoutique.getText();
            String ville = txtvilleBoutique.getText();
            // Vérifier que le numéro de téléphone est un nombre entier
            int num_telephone;
            try {
                num_telephone = Integer.parseInt(txtNumTelBoutique.getText());
            } catch (NumberFormatException e) {
                // Afficher un message d'erreur à l'utilisateur et quitter la méthode si le numéro de téléphone n'est pas un nombre valide
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("Le numéro de téléphone doit être un nombre entier.");
                alert.showAndWait();
                return;
            }

            // Création d'un objet Boutique
            int user_id = 1; // Assurez-vous de récupérer correctement l'ID de l'utilisateur
            Boutique boutique = new Boutique(nom, image, description, num_telephone, gouvernorat, ville, user_id);

            // Service pour ajouter la boutique
            BoutiqueService bs = new BoutiqueService();
            bs.addEntity(boutique);

            // Afficher une confirmation à l'utilisateur
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ajouté avec succès");
            alert.setHeaderText(null);
            alert.setContentText("Votre boutique a été ajoutée avec succès.");
            alert.showAndWait();

            // Réinitialiser les champs après l'ajout
            clearFieldsBoutique();
        } catch (Exception e) {
            e.printStackTrace();
            // Afficher un message d'erreur à l'utilisateur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur lors de l'ajout");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de l'ajout de la boutique. Veuillez réessayer.");
            alert.showAndWait();
        }
    }
    }


