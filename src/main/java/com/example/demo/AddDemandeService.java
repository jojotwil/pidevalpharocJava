package com.example.demo;

import Entities.Demandeserv;
import Services.BadWordAPIService;
import Services.DemandeservServices;
import Services.SendSms;
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
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.UUID;

public class AddDemandeService implements Initializable {
    public void location(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/frontvl.fxml"));
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
    private Button IDBUTaddEntity;

    @FXML
    private SplitMenuButton categorieVehicule;

    @FXML
    private TextField description;

    @FXML
    private TextField duree;

    @FXML
    private DatePicker horaire;

    @FXML
    private TextField mapView;


    @FXML
    private ImageView image;


    @FXML
    private TextField LabelIDSERVICE;
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
    private Button versliste;

    @FXML
    private SplitMenuButton typeservice;
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

    public void setServiceData(String id, String typeService, String categorieVehiculee) {
        // Afficher les données du service dans les champs correspondants
        LabelIDSERVICE.setText(id);
        typeservice.setText(typeService);
        categorieVehicule.setText(categorieVehiculee);
    }

    private BadWordAPIService badWordAPIService = new BadWordAPIService();
    @FXML
    void addDemandeService(ActionEvent event) {
        try {
            if (nom.getText().isEmpty() || marque.getText().isEmpty() || modele.getText().isEmpty() ||
                    description.getText().isEmpty() || duree.getText().isEmpty() || horaire.getValue() == null ||
                    statut.getText().isEmpty() || typeCarburant.getText().isEmpty() || categorieVehicule.getText().isEmpty()) {
                showAlert("Information manquante", "Vous devez remplir tous les détails concernant la demande de service.", Alert.AlertType.WARNING);
            }else if (!isValidTunisianPhoneNumber(duree.getText())) {
                showAlert("Numéro de téléphone invalide", "Veuillez entrer un numéro de téléphone tunisien valide.", Alert.AlertType.WARNING);
                return; // Sortir de la méthode si le numéro de téléphone est invalide
            }
            else if (Float.parseFloat(duree.getText()) <= 0) {
                showAlert("Durée invalide", "La durée doit être un nombre positif.", Alert.AlertType.WARNING);
            }
            else if (badWordAPIService.containsBadWord(description.getText())) {
                showAlert("Description invalide", "La description contient des mots inappropriés.", Alert.AlertType.WARNING);
            }else {
                LocalDate selectedDate = horaire.getValue();
                if (selectedDate.isBefore(LocalDate.now())) {
                    showAlert("Date invalide", "La date choisie doit être dans le futur.", Alert.AlertType.WARNING);
                } else {
                    ajouterDemandeService();
                }
            }
        }
        catch (NumberFormatException e) {
            showAlert("Format invalide", "Veuillez entrer une valeur numérique valide pour le prix.", Alert.AlertType.WARNING);
        } catch (Exception e) {
            showAlert("Erreur", "Une erreur s'est produite lors de l'ajout de la demande de service.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    private boolean isValidTunisianPhoneNumber(String phoneNumber) {
        // Expression régulière pour valider un numéro de téléphone tunisien
        String regex = "^(00216|\\+216|0)?[2379]\\d{7}$";
        // Vérifier si le numéro de téléphone correspond à l'expression régulière
        return phoneNumber.matches(regex);
    }
    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    private void ajouterDemandeService() {
        try {
            // Créer un nouvel objet Demandeserv avec les valeurs récupérées
            Demandeserv demandeserv = new Demandeserv();
            demandeserv.setNom(nom.getText());
            demandeserv.setMarque(marque.getText());
            demandeserv.setModele(modele.getText());
            demandeserv.setDescription(description.getText());

            // Vérifier si le champ "duree" est rempli et le convertir en nombre
            String dureeText = duree.getText().trim();
            if (!dureeText.isEmpty()) {
                try {
                    demandeserv.setDuree(Float.parseFloat(dureeText));
                } catch (NumberFormatException e) {
                    showAlert("Format invalide", "Veuillez entrer une valeur numérique valide pour le numero telephone.", Alert.AlertType.WARNING);
                    return; // Sortir de la méthode si la durée n'est pas un nombre valide
                }
            } else {
                showAlert("Champ obligatoire", "Vous devez saisir une numero pour recevoir votre id.", Alert.AlertType.WARNING);
                return; // Sortir de la méthode si le champ "duree" est vide
            }

            // Vérifier si le champ "LabelIDSERVICE" est rempli et le convertir en nombre
            String labelIDSERVICEText = LabelIDSERVICE.getText().trim();
            if (!labelIDSERVICEText.isEmpty()) {
                try {
                    demandeserv.setServicerep_id(Integer.parseInt(labelIDSERVICEText));
                } catch (NumberFormatException e) {
                    showAlert("Format invalide", "Veuillez entrer une valeur numérique valide pour l'ID du service.", Alert.AlertType.WARNING);
                    return; // Sortir de la méthode si l'ID du service n'est pas un nombre valide
                }
            } else {
                showAlert("Champ obligatoire", "Vous devez saisir l'ID du service.", Alert.AlertType.WARNING);
                return; // Sortir de la méthode si le champ "LabelIDSERVICE" est vide
            }

            demandeserv.setHoraire(horaire.getValue());
            demandeserv.setStatut(statut.getText());
            demandeserv.setTypeCarburant(typeCarburant.getText());
            demandeserv.setTypeservice(typeservice.getText());
            demandeserv.setCategorieVehicule(categorieVehicule.getText());
            demandeserv.setLocalisationdemettrelavehicule(mapView.getText());

            // Créer une instance de DemandeservServices
            DemandeservServices demandeservServices = new DemandeservServices();
            // Appeler addEntity avec l'objet demandeserv
            demandeservServices.addEntity(demandeserv);


            // Envoyer un SMS
            String message = "Votre service: a été ajouté avec succès. ID : " + demandeserv.getId();
            SendSms sendSms = new SendSms();
         //   if (sendSmsService != null) {sendSmsService.sendSms(message, duree.getText());}
            sendSms.sendSms(message, dureeText);

            // Afficher une confirmation à l'utilisateur
            ServiceNotification.showNotif("Ajout réussi", "La demande de service a été ajouté avec succès.");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ID envoyer");
            alert.setHeaderText(null);
            alert.setContentText("Le ID a été envoyé avec succès a votre numero.");

            alert.showAndWait();



            alert.showAndWait();
            clearFieldsDemandeService();
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer les exceptions qui pourraient survenir lors de l'ajout de la demande de service
            // Afficher un message d'erreur à l'utilisateur ou gérer l'exception de manière appropriée
        }
    }
    private SendSms sendSmsService;

    public void setSendSmsService(SendSms sendSmsService) {
        this.sendSmsService = sendSmsService;
    }
    private void clearFieldsDemandeService() {
        nom.clear();
        marque.clear();
        modele.clear();
        mapView.clear();
        description.clear();
        duree.clear();
        horaire.setValue(null);
        statut.setText("");
        typeCarburant.setText("");
        categorieVehicule.getItems().clear(); // Clear items or set selection to default
        typeservice.setText(""); // Clear the type service field
        // Clear other fields as needed
    }
    @FXML
    private MenuItem button_logout;



    private File selectedImageFile; // Déclaration de la variable selectedImageFile

    private String imageName = null;
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
    void backfromajoutdemandeserv(ActionEvent event) throws  Exception {
        try {
            Parent adminRoot = FXMLLoader.load(getClass().getResource("listefront.fxml"));
            Scene adminScene = new Scene(adminRoot);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(adminScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur de chargement", "Une erreur s'est produite lors du chargement de la page.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void versliste(ActionEvent event) {
        try {
            // Charger le fichier FXML
            Parent addCivilisationParent = FXMLLoader.load(getClass().getResource("listedemandeservie.fxml"));
            Scene addCivilisationScene = new Scene(addCivilisationParent);

            // Obtenir la scène actuelle à partir de l'événement
            Stage window = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Changer la scène pour afficher la nouvelle vue
            window.setScene(addCivilisationScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace(); // Gérer l'erreur de chargement du fichier FXML


        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScence(actionEvent, "authentifier.fxml", "Log In", null, null,button_logout);
            }
        });
        MenuItem enAttenteItem = new MenuItem("En attente");


        enAttenteItem.setOnAction(e -> statut.setText("En attente"));

        statut.getItems().addAll( enAttenteItem);

        // Ajouter des éléments de menu pour le type de service
     /*   MenuItem lavageItem = new MenuItem("Lavage");
        MenuItem reparationmoteurItem = new MenuItem("Réparation moteur");
        MenuItem toulierItem = new MenuItem("toulier");
        MenuItem reparationinterieurItem = new MenuItem("Réparation interieur");

        MenuItem reparationdespneuxItem = new MenuItem("Réparation des pneux");
        MenuItem diagnostiqueItem = new MenuItem("diagnostique");





        lavageItem.setOnAction(e -> typeservice.setText("Lavage"));
        reparationmoteurItem.setOnAction(e -> typeservice.setText("Réparation moteur"));
        toulierItem.setOnAction(e -> typeservice.setText("toulier"));
        reparationinterieurItem.setOnAction(e -> typeservice.setText("Réparation interieur"));
        reparationdespneuxItem.setOnAction(e -> typeservice.setText("reparationdespneux"));
        diagnostiqueItem.setOnAction(e -> typeservice.setText("diagnostique"));


        typeservice.getItems().addAll(lavageItem, reparationmoteurItem,toulierItem, reparationinterieurItem,reparationdespneuxItem, diagnostiqueItem);

        // Ajouter des éléments de menu pour le type de véhicule
        MenuItem voitureItem = new MenuItem("Voiture");
        MenuItem motoItem = new MenuItem("Moto");
        MenuItem bateauItem = new MenuItem("Bateau");

        voitureItem.setOnAction(e -> categorieVehicule.setText("Voiture"));
        motoItem.setOnAction(e -> categorieVehicule.setText("Moto"));
        bateauItem.setOnAction(e -> categorieVehicule.setText("Bateau"));

        categorieVehicule.getItems().addAll(voitureItem, motoItem, bateauItem);*/

        // Ajouter des éléments au menu du SplitMenuButton
        MenuItem essenceItem = new MenuItem("Essence");
        MenuItem dieselItem = new MenuItem("Diesel");
        MenuItem electriqueItem = new MenuItem("Électrique");

        typeCarburant.getItems().addAll(essenceItem, dieselItem, electriqueItem);

        // Définir un gestionnaire d'événements pour chaque élément du menu
        essenceItem.setOnAction(event -> {
            typeCarburant.setText("Essence");
            // Autre traitement à effectuer
        });

        dieselItem.setOnAction(event -> {
            typeCarburant.setText("Diesel");
            // Autre traitement à effectuer
        });

        electriqueItem.setOnAction(event -> {
            typeCarburant.setText("Électrique");
            // Autre traitement à effectuer
        });

    }
}
