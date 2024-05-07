package com.example.demo;


import Entities.Demandeserv;
import Services.DemandeservServices;
import Services.ServiceNotification;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.UUID;

public class UpdateDemandeservice implements Initializable {
    public void boutique(ActionEvent event){
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listBoutiqueFront.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"


            // Créer une nouvelle scène avec le nouveau contenu
            Scene scene = new Scene(newContent);

            // Obtenir la fenêtre principale (stage)
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Obtenir les dimensions de l'écran
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            // Obtenez les dimensions de l'écran
            Screen screen = Screen.getPrimary();
            double screenWidth = screen.getBounds().getWidth();
            double screenHeight = screen.getBounds().getHeight();

// Définissez la taille de la fenêtre sur les dimensions de l'écran
            mainStage.setWidth(screenWidth);
            mainStage.setHeight(screenHeight);

            // Définir la nouvelle scène sur la fenêtre principale
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    public void monprofil(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profil.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"
            ProfilController controller = loader.getController();

            // Créer une nouvelle scène avec le nouveau contenu
            Scene scene = new Scene(newContent);

            // Obtenir la fenêtre principale (stage)
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Obtenir les dimensions de l'écran
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            // Obtenez les dimensions de l'écran
            Screen screen = Screen.getPrimary();
            double screenWidth = screen.getBounds().getWidth();
            double screenHeight = screen.getBounds().getHeight();

// Définissez la taille de la fenêtre sur les dimensions de l'écran
            mainStage.setWidth(screenWidth);
            mainStage.setHeight(screenHeight);

            // Définir la nouvelle scène sur la fenêtre principale
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void location(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/addloca.fxml"));
            Parent newContent = loader.load();



            // Créer une nouvelle scène avec le nouveau contenu
            Scene scene = new Scene(newContent);

            // Obtenir la fenêtre principale (stage)
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Obtenir les dimensions de l'écran
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            // Obtenez les dimensions de l'écran
            Screen screen = Screen.getPrimary();
            double screenWidth = screen.getBounds().getWidth();
            double screenHeight = screen.getBounds().getHeight();

// Définissez la taille de la fenêtre sur les dimensions de l'écran
            mainStage.setWidth(screenWidth);
            mainStage.setHeight(screenHeight);

            // Définir la nouvelle scène sur la fenêtre principale
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void monprofil1(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profil.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "profil.fxml"
            ProfilController controller = loader.getController();

            // Créer une nouvelle scène avec le nouveau contenu
            Scene scene = new Scene(newContent);

            // Obtenir la fenêtre principale (stage)
            Stage mainStage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();

            // Obtenir les dimensions de l'écran
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            // Définir la taille de la fenêtre sur les dimensions de l'écran
            mainStage.setWidth(screenBounds.getWidth());
            mainStage.setHeight(screenBounds.getHeight());

            // Définir la nouvelle scène sur la fenêtre principale
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void event(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("index-evenement-front.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"


            // Créer une nouvelle scène avec le nouveau contenu
            Scene scene = new Scene(newContent);

            // Obtenir la fenêtre principale (stage)
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Obtenir les dimensions de l'écran
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            // Obtenez les dimensions de l'écran
            Screen screen = Screen.getPrimary();
            double screenWidth = screen.getBounds().getWidth();
            double screenHeight = screen.getBounds().getHeight();

// Définissez la taille de la fenêtre sur les dimensions de l'écran
            mainStage.setWidth(screenWidth);
            mainStage.setHeight(screenHeight);

            // Définir la nouvelle scène sur la fenêtre principale
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void troc(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-viewtroc.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"
            HelloControllertroc controller = loader.getController();

            // Créer une nouvelle scène avec le nouveau contenu
            Scene scene = new Scene(newContent);

            // Obtenir la fenêtre principale (stage)
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Obtenir les dimensions de l'écran
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            // Obtenez les dimensions de l'écran
            Screen screen = Screen.getPrimary();
            double screenWidth = screen.getBounds().getWidth();
            double screenHeight = screen.getBounds().getHeight();

// Définissez la taille de la fenêtre sur les dimensions de l'écran
            mainStage.setWidth(screenWidth);
            mainStage.setHeight(screenHeight);

            // Définir la nouvelle scène sur la fenêtre principale
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void service(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listefront.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"
            HelloControllertroc controller = loader.getController();

            // Créer une nouvelle scène avec le nouveau contenu
            Scene scene = new Scene(newContent);

            // Obtenir la fenêtre principale (stage)
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Obtenir les dimensions de l'écran
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            // Obtenez les dimensions de l'écran
            Screen screen = Screen.getPrimary();
            double screenWidth = screen.getBounds().getWidth();
            double screenHeight = screen.getBounds().getHeight();

// Définissez la taille de la fenêtre sur les dimensions de l'écran
            mainStage.setWidth(screenWidth);
            mainStage.setHeight(screenHeight);

            // Définir la nouvelle scène sur la fenêtre principale
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button IDBUTupdateEntity;

    @FXML
    private SplitMenuButton categorieVehicule;
    @FXML
    private TextField serviceid;


    @FXML
    private TextField description;
    @FXML
    private TextField localisation;
    @FXML
    private TextField duree;

    @FXML
    private DatePicker horaire;

    @FXML
    private ImageView image;

    @FXML
    private TextField marque;

    @FXML
    private TextField modele;

    @FXML
    private TextField nom;

    @FXML
    private SplitMenuButton statut;

    @FXML
    private SplitMenuButton typeCarburant;

    @FXML
    private SplitMenuButton typeservice;

    private File selectedImageFile; // Déclaration de la variable selectedImageFile

    private String imageName = null;

    @FXML
    void ajouterImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog(image.getScene().getWindow());

        if (selectedImageFile != null) {
            try {
                Image image = new Image(selectedImageFile.toURI().toString());
                this.image.setImage(image);


                // Générer un nom de fichier unique pour l'image
                String uniqueID = UUID.randomUUID().toString();
                String extension = selectedImageFile.getName().substring(selectedImageFile.getName().lastIndexOf("."));
                imageName = uniqueID + extension;

                // Spécifiez l'emplacement de sauvegarde des images en dehors des ressources de l'application
                Path destination = Paths.get("resources/uploads");
                Files.createDirectories(destination);
                Files.copy(selectedImageFile.toPath(), destination.resolve(imageName), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
                // Gérer l'exception selon vos besoins
            }
        }
    }

    @FXML
    void backfromupdatedemandeserv(ActionEvent event) throws  Exception {
        try {
            Parent adminRoot = FXMLLoader.load(getClass().getResource("/listedemandeservie.fxml"));
            Scene adminScene = new Scene(adminRoot);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(adminScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur de chargement", "Une erreur s'est produite lors du chargement de la page.", Alert.AlertType.ERROR);
        }
    }
    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    // Déclaration de la variable id
    private int id;

    // Méthode pour initialiser l'ID
    public void setId(int id) {
        this.id = id;
    }

    // Le reste de votre code...

    @FXML
    void updateDemandeService(ActionEvent event) {
        try {
            if (id == 0) {
                showAlert("ID non valide", "L'ID de la demande de service n'est pas valide.", Alert.AlertType.WARNING);
            } else if (nom.getText().isEmpty() || marque.getText().isEmpty() || modele.getText().isEmpty() ||
                    description.getText().isEmpty() || duree.getText().isEmpty() || horaire.getValue() == null ||
                    statut.getText().isEmpty() || typeCarburant.getText().isEmpty() || categorieVehicule.getText().isEmpty()) {
                showAlert("Information manquante", "Vous devez remplir tous les détails concernant la demande de service.", Alert.AlertType.WARNING);
            } else if (Float.parseFloat(duree.getText()) <= 0) {
                showAlert("Durée invalide", "La durée doit être un nombre positif.", Alert.AlertType.WARNING);
            } else {
                Demandeserv demandeserv = new Demandeserv();
                demandeserv.setNom(nom.getText());
                demandeserv.setMarque(marque.getText());
                demandeserv.setModele(modele.getText());
                demandeserv.setDescription(description.getText());
                demandeserv.setDuree(Float.parseFloat(duree.getText()));
                demandeserv.setHoraire(horaire.getValue());
                demandeserv.setStatut(statut.getText());
                demandeserv.setTypeCarburant(typeCarburant.getText());
                demandeserv.setCategorieVehicule(categorieVehicule.getText());

                DemandeservServices demandeservServices = new DemandeservServices();
                demandeservServices.updateEntity(demandeserv, id);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Demande de service mise à jour");
                alert.setHeaderText(null);
                alert.setContentText("La demande de service a été mise à jour avec succès.");
                ServiceNotification.showNotif("Mise à jour réussie", "Le service a été mis à jour avec succès.");

                alert.showAndWait();
                clearFieldsDemandeService();
            }
        } catch (NumberFormatException e) {
            showAlert("Format invalide", "Veuillez entrer une valeur numérique valide pour la durée.", Alert.AlertType.WARNING);
        } catch (Exception e) {
            showAlert("Erreur", "Une erreur s'est produite lors de la mise à jour de la demande de service.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }


    private void clearFieldsDemandeService() {
        nom.clear();
        marque.clear();
        modele.clear();
        description.clear();
        duree.clear();
        horaire.setValue(null);
        statut.setText("");
        typeCarburant.setText("");
        categorieVehicule.getItems().clear(); // Clear items or set selection to default
        typeservice.setText(""); // Clear the type service field
        // Clear other fields as needed
    }

    public void setData(Demandeserv demandeserv) {
        // Définir l'ID de la demande de service
        this.id = demandeserv.getId();

        // Utilisez les champs du formulaire pour afficher les détails de la demande de service
        nom.setText(demandeserv.getNom());
        marque.setText(demandeserv.getMarque());
        modele.setText(demandeserv.getModele());
        description.setText(demandeserv.getDescription());
        duree.setText(String.valueOf(demandeserv.getDuree()));
        horaire.setValue(demandeserv.getHoraire());
        statut.setText(demandeserv.getStatut());
        typeCarburant.setText(demandeserv.getTypeCarburant());
        typeservice.setText(demandeserv.getTypeservice());
        categorieVehicule.setText(demandeserv.getCategorieVehicule());
        localisation.setText(demandeserv.getLocalisationdemettrelavehicule());
      serviceid.setText(String.valueOf(demandeserv.getServicerep_id()));

        // Charger et afficher l'image
        String imagePath = demandeserv.getImage(); // Supposons que l'entité Demandeserv ait un champ imagePath
        if (imagePath != null && !imagePath.isEmpty()) {
            try {
                Image image = new Image(new FileInputStream(imagePath));
                this.image.setImage(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            // Si aucune image n'est disponible, vous pouvez définir une image par défaut
            this.image.setImage(null);
        }
    }
    @FXML
    private MenuItem button_logout;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScence(actionEvent, "authentifier.fxml", "Log In", null, null,button_logout);
            }
        });
    }
}