package com.example.demo;

import Entities.Servicerep;
import Services.BadWordAPIService;
import Services.SendSms;
import Services.ServiceNotification;
import Services.ServicerepServices;
import javafx.concurrent.Service;
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
import java.util.ResourceBundle;
import java.util.UUID;


public class AddService implements Initializable {
    @FXML
    private MenuItem button_logout;

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
    private Button  IDBUTaddEntity;

    @FXML
    private TextField description;

    @FXML
    private TextField duree;

    @FXML
    private Spinner<Integer> horaire;

    @FXML
    private TextField id;

    @FXML
    private Spinner<Integer> idhorairedejour;

    @FXML
    private ImageView image;

    @FXML
    private TextField jourrepos;

    @FXML
    private TextField nomservice;

    @FXML
    private TextField prix;

    @FXML
    private SplitMenuButton statut;

    @FXML
    private SplitMenuButton typedeservice;

    @FXML
    private SplitMenuButton typedevehicule;



    private Service<Void> addService; // Déclaration du service


    @FXML

    public void initialize() {




            SpinnerValueFactory<Integer> valueFactory =
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 24);
        valueFactory.setValue(00);
        idhorairedejour.setValueFactory(valueFactory);
        SpinnerValueFactory<Integer> valueFactory2 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 24);
        valueFactory2.setValue(00);
        horaire.setValueFactory(valueFactory2);



        // Ajouter des éléments de menu pour le statut

        MenuItem enAttenteItem = new MenuItem("En attente");


        enAttenteItem.setOnAction(e -> statut.setText("En attente"));

        statut.getItems().addAll( enAttenteItem);

        // Ajouter des éléments de menu pour le type de service
        MenuItem lavageItem = new MenuItem("Lavage");
        MenuItem reparationmoteurItem = new MenuItem("Réparation moteur");
        MenuItem toulierItem = new MenuItem("toulier");
        MenuItem reparationinterieurItem = new MenuItem("Réparation interieur");

        MenuItem reparationdespneuxItem = new MenuItem("Réparation des pneux");
        MenuItem diagnostiqueItem = new MenuItem("diagnostique");





        lavageItem.setOnAction(e -> typedeservice.setText("Lavage"));
        reparationmoteurItem.setOnAction(e -> typedeservice.setText("Réparation moteur"));
        toulierItem.setOnAction(e -> typedeservice.setText("toulier"));
        reparationinterieurItem.setOnAction(e -> typedeservice.setText("Réparation interieur"));
        reparationdespneuxItem.setOnAction(e -> typedeservice.setText("reparationdespneux"));
        diagnostiqueItem.setOnAction(e -> typedeservice.setText("diagnostique"));


        typedeservice.getItems().addAll(lavageItem, reparationmoteurItem,toulierItem, reparationinterieurItem,reparationdespneuxItem, diagnostiqueItem);

        // Ajouter des éléments de menu pour le type de véhicule
        MenuItem voitureItem = new MenuItem("Voiture");
        MenuItem motoItem = new MenuItem("Moto");
        MenuItem bateauItem = new MenuItem("Bateau");

        voitureItem.setOnAction(e -> typedevehicule.setText("Voiture"));
        motoItem.setOnAction(e -> typedevehicule.setText("Moto"));
        bateauItem.setOnAction(e -> typedevehicule.setText("Bateau"));

        typedevehicule.getItems().addAll(voitureItem, motoItem, bateauItem);

    }

    private BadWordAPIService badWordAPIService = new BadWordAPIService();




    @FXML
    void addService(ActionEvent event) {
        try {
            if (nomservice.getText().isEmpty() || description.getText().isEmpty() ||
                    jourrepos.getText().isEmpty() || prix.getText().isEmpty()) {
                showAlert("Information manquante", "Vous devez remplir tous les détails concernant le service.", Alert.AlertType.WARNING);
            } else if (Float.parseFloat(prix.getText()) <= 0) {
                showAlert("Prix invalide", "Le prix doit être un nombre positif.", Alert.AlertType.WARNING);
            } else if (Integer.parseInt(horaire.getValue().toString()) <= Integer.parseInt(idhorairedejour.getValue().toString())) {
                showAlert("Erreur dans les horaires", "L'horaire d ouverture doit être avant à l'horaire de fermeture.", Alert.AlertType.WARNING);
            } else if (badWordAPIService.containsBadWord(description.getText())) {
                showAlert("Description invalide", "La description contient des mots inappropriés.", Alert.AlertType.WARNING);
            } else if (!isValidPhoneNumber(duree.getText())) {
                showAlert("Numéro de téléphone invalide", "Veuillez entrer un numéro de téléphone tunisien valide.", Alert.AlertType.WARNING);
            } else {
                ajouterService();
            }
        } catch (NumberFormatException e) {
            showAlert("Format invalide", "Veuillez entrer une valeur numérique valide pour le prix.", Alert.AlertType.WARNING);
        } catch (Exception e) {
            showAlert("Erreur", "Une erreur s'est produite lors de l'ajout du service.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Expression régulière pour vérifier un numéro de téléphone tunisien valide
        String regex = "(\\+?216)?\\d{8}";
        return phoneNumber.matches(regex);
    }


    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void ajouterService() {
        try {
            // Récupérer les valeurs des champs
            String nomService = nomservice.getText();
            String desc = description.getText();
            String dureeService = duree.getText();
            int horaireService = horaire.getValue() != null ? horaire.getValue() : 0;
            String jourRepos = jourrepos.getText();
            String statutService = statut.getText();
            String typeService = typedeservice.getText();
            String typeVehicule = typedevehicule.getText();
            Float prixService = Float.parseFloat(prix.getText());

            // Récupérer le numéro de téléphone saisi par l'utilisateur
            // String numeroTelephone = "NUMERO_TEL_SAISI_PAR_UTILISATEUR";
            String numeroTelephone = duree.getText();


            // Créer un nouvel objet Servicerep avec les valeurs récupérées
            Servicerep servicerep = new Servicerep();
            servicerep.setDescription(desc);
            servicerep.setPrix(prixService);
            servicerep.setDuree(dureeService);
            servicerep.setNomservice(nomService);
            servicerep.setHoraire(String.valueOf(horaireService));
            servicerep.setTypeVehicule(typeVehicule);
            servicerep.setImage(imageName); // Assurez-vous que imageName est défini dans ajouterImage()
            servicerep.setTypeservice(typeService);
            servicerep.setHorairedujour(String.valueOf(idhorairedejour.getValue()));
            servicerep.setJoursderepos(jourRepos);
            servicerep.setStatut(statutService);

            // Créer une instance de ServicerepServices
            ServicerepServices servicerepServices = new ServicerepServices();
            // Appeler addEntity avec l'objet servicerep
            servicerepServices.addEntity(servicerep);


            // Envoyer un SMS
            String message = "Votre service:" + servicerep.getNomservice()+" a été ajouté avec succès. ID : " + servicerep.getId();
            SendSms sendSms = new SendSms();
            sendSms.sendSms(message, numeroTelephone);

            // Afficher une confirmation à l'utilisateur
            ServiceNotification.showNotif("Ajout réussi", "Le service a été ajouté avec succès.");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ID envoyer");
            alert.setHeaderText(null);
            alert.setContentText("Le ID a été envoyé avec succès a votre numero.");

            alert.showAndWait();
            clearFieldsService();


        } catch (Exception e) {
            e.printStackTrace();

        }
    }




    private void clearFieldsService() {

        nomservice.clear();
        description.clear();
        duree.clear();
        
        image.setImage(null);
        jourrepos.clear();
        prix.clear();
        statut.setText("");
        typedeservice.setText("");
        typedevehicule.setText("");
        horaire.getValueFactory().setValue(0);
        idhorairedejour.getValueFactory().setValue(0);
        id.clear();




    }


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
                Path destination = Paths.get("resources/images");
                Files.createDirectories(destination);
                Files.copy(selectedImageFile.toPath(), destination.resolve(imageName), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
                // Gérer l'exception selon vos besoins
            }
        }
    }
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
    void backfromajoutserv(ActionEvent event) throws  Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("listeservice.fxml"));
        Parent adminRoot = loader.load();

        Scene adminScene = new Scene(adminRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(adminScene);
        window.show();

    }


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
