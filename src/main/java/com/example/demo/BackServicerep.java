package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import Entities.Servicerep;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import Services.BadWordAPIService;
import Services.SendSms;
import Services.ServiceNotification;
import Services.ServicerepServices;

import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;


public class BackServicerep {
    public void event(ActionEvent event){
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("index-evenement.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"

            // Créer une nouvelle scène avec le nouveau contenu
            Scene scene = new Scene(newContent);

            // Obtenir la fenêtre principale (stage)
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Obtenir les dimensions de l'écran


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
    public void trocs(ActionEvent event){
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("trocback.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"

            // Créer une nouvelle scène avec le nouveau contenu
            Scene scene = new Scene(newContent);

            // Obtenir la fenêtre principale (stage)
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Obtenir les dimensions de l'écran


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
    public void chart(ActionEvent event){
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chartjs.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"

            // Créer une nouvelle scène avec le nouveau contenu
            Scene scene = new Scene(newContent);

            // Obtenir la fenêtre principale (stage)
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Obtenir les dimensions de l'écran


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
    public void location(ActionEvent event){
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Showvl.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"

            // Créer une nouvelle scène avec le nouveau contenu
            Scene scene = new Scene(newContent);

            // Obtenir la fenêtre principale (stage)
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Obtenir les dimensions de l'écran


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
    public void logout(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource("authentifier.fxml"));
            Parent root = loader.load();
            //TrocbackController adminDashboardController = loader.getController();

            // Set any necessary data or parameters for the controller

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Admin Dashboard");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void user(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource("admindashboard.fxml"));
            Parent root = loader.load();


            // Set any necessary data or parameters for the controller

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Admin Dashboard");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void demandeserv(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource("Gestiondemandeservadmin.fxml"));
            Parent root = loader.load();


            // Set any necessary data or parameters for the controller

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Admin Dashboard");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void servicerep(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource("Gestionadminservicerep.fxml"));
            Parent root = loader.load();


            // Set any necessary data or parameters for the controller

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Admin Dashboard");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private TextField NomService;
    @FXML
    private TextField raisonderefus;

    @FXML
    private SplitMenuButton Typeservice;

    @FXML
    private SplitMenuButton Typevehiculee;

    @FXML
    private TableColumn<Servicerep, Long> colId;

    @FXML
    private TableColumn<Servicerep, String> colNom;

    @FXML
    private TableColumn<Servicerep, String> coldescription;

    @FXML
    private TableColumn<Servicerep, Integer> coldislik;

    @FXML
    private TableColumn<Servicerep, String> colduree;

    @FXML
    private TableColumn<Servicerep, String> colhoraire;

    @FXML
    private TableColumn<Servicerep, String> colhorairejours;

    @FXML
    private TableColumn<Servicerep, String> coljoursrepos;

    @FXML
    private TableColumn<Servicerep, Integer> collike;

    @FXML
    private TableColumn<Servicerep, Float> colprix;

    @FXML
    private TableColumn<Servicerep, String> colstatut;

    @FXML
    private TableColumn<Servicerep, String> coltypeservice;

    @FXML
    private TableColumn<Servicerep, String> coltypevehicule;
    @FXML
    private TableColumn<Servicerep, Integer> colnbvue;



    @FXML
    private TextField decription;

    @FXML
    private TextField idduree;

    @FXML
    private Spinner<Integer> idhoraire;

    @FXML
    private Spinner<Integer> horairejours;


    @FXML
    private ImageView image;

    @FXML
    private TextField joursrepos;

    @FXML
    private TextField idprix;

    @FXML
    private SplitMenuButton idstatut;

    @FXML
    private TextField tfId;

    @FXML
    private TableView<Servicerep>  tvServicerep;
    @FXML
    private Label labelRaisonRefus;


    @FXML

    public void initialize() {

        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 24);
        valueFactory.setValue(00);
        horairejours.setValueFactory(valueFactory);
        SpinnerValueFactory<Integer> valueFactory2 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 24);
        valueFactory2.setValue(00);
        idhoraire.setValueFactory(valueFactory2);



        // Ajouter des éléments de menu pour le statut

        MenuItem enAttenteItem = new MenuItem("En attente");
        MenuItem accepterItem = new MenuItem("accepter");
        MenuItem refuserItem = new MenuItem("refuser");


        enAttenteItem.setOnAction(e -> idstatut.setText("En attente"));
        accepterItem.setOnAction(e -> idstatut.setText("accepter"));
        refuserItem.setOnAction(e -> idstatut.setText("refuser"));

        idstatut.getItems().addAll( enAttenteItem,accepterItem,refuserItem);

        // Ajouter des éléments de menu pour le type de service
        MenuItem lavageItem = new MenuItem("Lavage");
        MenuItem reparationmoteurItem = new MenuItem("Réparation moteur");
        MenuItem toulierItem = new MenuItem("toulier");
        MenuItem reparationinterieurItem = new MenuItem("Réparation interieur");

        MenuItem reparationdespneuxItem = new MenuItem("Réparation des pneux");
        MenuItem diagnostiqueItem = new MenuItem("diagnostique");





        lavageItem.setOnAction(e -> Typeservice.setText("Lavage"));
        reparationmoteurItem.setOnAction(e -> Typeservice.setText("Réparation moteur"));
        toulierItem.setOnAction(e -> Typeservice.setText("toulier"));
        reparationinterieurItem.setOnAction(e -> Typeservice.setText("Réparation interieur"));
        reparationdespneuxItem.setOnAction(e -> Typeservice.setText("reparationdespneux"));
        diagnostiqueItem.setOnAction(e -> Typeservice.setText("diagnostique"));


        Typeservice.getItems().addAll(lavageItem, reparationmoteurItem,toulierItem, reparationinterieurItem,reparationdespneuxItem, diagnostiqueItem);

        // Ajouter des éléments de menu pour le type de véhicule
        MenuItem voitureItem = new MenuItem("Voiture");
        MenuItem motoItem = new MenuItem("Moto");
        MenuItem bateauItem = new MenuItem("Bateau");

        voitureItem.setOnAction(e -> Typevehiculee.setText("Voiture"));
        motoItem.setOnAction(e -> Typevehiculee.setText("Moto"));
        bateauItem.setOnAction(e -> Typevehiculee.setText("Bateau"));

        Typevehiculee.getItems().addAll(voitureItem, motoItem, bateauItem);



        setupUserTableSelection();

        showServicerep();
 /*       // Rendre le Label et le TextField invisibles au démarrage de l'application
       if (labelRaisonRefus != null) {
            labelRaisonRefus.setVisible(false);
            labelRaisonRefus.managedProperty().bind(labelRaisonRefus.visibleProperty());
        }
        if (raisonderefus != null) {
        raisonderefus.setVisible(false);
        raisonderefus.managedProperty().bind(raisonderefus.visibleProperty());
        }
        // Ajouter un écouteur pour mettre à jour la visibilité en fonction du statut
        idstatut.textProperty().addListener((obs, oldVal, newVal) -> {
            if (idstatut.equals("En attente")) {
                labelRaisonRefus.setVisible(false);
                raisonderefus.setVisible(false);
            } else {
                labelRaisonRefus.setVisible(true);
                raisonderefus.setVisible(true);
            }
        });
*/
    }



    public void showServicerep() {
        ServicerepServices service = new ServicerepServices();
        List<Servicerep> services = service.getAllData();

        ObservableList<Servicerep> observableList = FXCollections.observableArrayList(services);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nomservice"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        coltypeservice.setCellValueFactory(new PropertyValueFactory<>("typeservice"));
        coltypevehicule.setCellValueFactory(new PropertyValueFactory<>("typeVehicule"));
        colstatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        colhoraire.setCellValueFactory(new PropertyValueFactory<>("horaire"));
        colhorairejours.setCellValueFactory(new PropertyValueFactory<>("horairedujour"));
        coljoursrepos.setCellValueFactory(new PropertyValueFactory<>("joursderepos"));
        collike.setCellValueFactory(new PropertyValueFactory<>("likes"));
        coldislik.setCellValueFactory(new PropertyValueFactory<>("dislikes"));
        colnbvue.setCellValueFactory(new PropertyValueFactory<>("nbvue"));
        //  colImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        colduree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        //   colRaisonderefus.setCellValueFactory(new PropertyValueFactory<>("raisonderefus"));
        //  colNbvue.setCellValueFactory(new PropertyValueFactory<>("nbvue"));

        tvServicerep.setItems(observableList);
        tvServicerep.refresh();
    }

    private void setupUserTableSelection() {
        tvServicerep.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Récupérer les détails de l'élément sélectionné
                Servicerep selectedService = tvServicerep.getSelectionModel().getSelectedItem();
                // Afficher les détails dans les champs correspondants
                tfId.setText(String.valueOf(selectedService.getId()));
                NomService.setText(selectedService.getNomservice());
                Typeservice.setText(selectedService.getTypeservice());
                Typevehiculee.setText(selectedService.getTypeVehicule());
                decription.setText(selectedService.getDescription());
                idprix.setText(String.valueOf(selectedService.getPrix()));
                idstatut.setText(selectedService.getStatut());
                idduree.setText(selectedService.getDuree());
                idhoraire.getValueFactory().setValue(Integer.parseInt(selectedService.getHoraire()));
                horairejours.getValueFactory().setValue(Integer.parseInt(selectedService.getHorairedujour()));
                joursrepos.setText(selectedService.getJoursderepos());
            }
        });
    }


    private BadWordAPIService badWordAPIService = new BadWordAPIService();

    @FXML
    void addBtn(ActionEvent event) {

        try {
            if (NomService.getText().isEmpty() || decription.getText().isEmpty() ||
                    joursrepos.getText().isEmpty() || idprix.getText().isEmpty()) {
                showAlert("Information manquante", "Vous devez remplir tous les détails concernant le service.", Alert.AlertType.WARNING);
            } else if (Float.parseFloat(idprix.getText()) <= 0) {
                showAlert("Prix invalide", "Le prix doit être un nombre positif.", Alert.AlertType.WARNING);
            } else if (Integer.parseInt(idhoraire.getValue().toString()) <= Integer.parseInt(horairejours.getValue().toString())) {
                showAlert("Erreur dans les horaires", "L'horaire d ouverture doit être avant à l'horaire de fermeture.", Alert.AlertType.WARNING);
            }
            else if (badWordAPIService.containsBadWord(decription.getText())) {
                showAlert("Description invalide", "La description contient des mots inappropriés.", Alert.AlertType.WARNING);
            } else if (!isValidPhoneNumber(idduree.getText())) {
                showAlert("Numéro de téléphone invalide", "Veuillez entrer un numéro de téléphone tunisien valide.", Alert.AlertType.WARNING);

            }  else {
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
    private void ajouterService() {


        try {

            // Récupérer les valeurs des champs du formulaire
            String description = decription.getText();
            Float prix = Float.parseFloat(idprix.getText());
            String duree = idduree.getText();
            String nomService = NomService.getText();
            String horaire = idhoraire.getValue().toString();
            String typeVehicule = Typevehiculee.getText(); // Assurez-vous d'avoir un getter pour Typevehiculee
            String image = ""; // Mettez ici le chemin de l'image si vous en avez un
            String typeservice = Typeservice.getText(); // Assurez-vous d'avoir un getter pour Typeservice
            String horairedujour = horairejours.getValue().toString(); // Mettez ici l'horaire du jour si nécessaire
            String joursderepos = joursrepos.getText();
            String statut = idstatut.getText(); // Assurez-vous d'avoir un getter pour statut
            // String numeroTelephone = "NUMERO_TEL_SAISI_PAR_UTILISATEUR";
            String numeroTelephone = idduree.getText();

            // Créer un objet Servicerep avec les valeurs récupérées
            Servicerep newService = new Servicerep();
            newService.setDescription(description);
            newService.setPrix(prix);
            newService.setDuree(duree);
            newService.setNomservice(nomService);
            newService.setHoraire(horaire);
            newService.setTypeVehicule(typeVehicule);
            newService.setImage(image);
            newService.setTypeservice(typeservice);
            newService.setHorairedujour(horairedujour);
            newService.setJoursderepos(joursderepos);
            newService.setStatut(statut);

            // Utiliser le service pour ajouter le nouvel objet Servicerep à la base de données
            ServicerepServices service = new ServicerepServices();
            service.addEntity(newService);

            // Envoyer un SMS
            String message = "Votre service:" + newService.getNomservice()+" a été ajouté avec succès. ID : " + newService.getId();
            SendSms sendSms = new SendSms();
            sendSms.sendSms(message, numeroTelephone);



            // Réinitialiser les champs après l'ajout
            // Afficher une confirmation à l'utilisateur
            ServiceNotification.showNotif("Ajout réussi", "Le service a été ajouté avec succès.");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ID envoyer");
            alert.setHeaderText(null);
            alert.setContentText("Le ID a été envoyé avec succès a votre numero.");
            alert.showAndWait();
            showServicerep();
            clearFieldsService();


        } catch (Exception e) {
            e.printStackTrace();
            // Gérer les exceptions qui pourraient survenir lors de l'ajout du service
            // Afficher un message d'erreur à l'utilisateur ou gérer l'exception de manière appropriée
        }
    }

    private void clearFieldsService() {
        // Effacer les champs du formulaire après l'ajout
        decription.clear();
        idprix.clear();
        idduree.clear();
        NomService.clear();
        idhoraire.getValueFactory().setValue(0); // Assurez-vous d'avoir un getter pour horaire
        horairejours.getValueFactory().setValue(0); // Assurez-vous d'avoir un getter pour horaire
        Typevehiculee.setText(""); // Assurez-vous d'avoir un getter pour Typevehiculee

        // Effacer l'image si nécessaire
        Typeservice.setText(""); // Assurez-vous d'avoir un getter pour Typeservice
        joursrepos.clear();
        idstatut.setText("");// Assurez-vous d'avoir un getter pour statut



    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
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
    void clearbtn(ActionEvent event) {
        clearFieldsService();
    }


    @FXML
    void deleteBtn(ActionEvent event) {
        Servicerep selectedService = tvServicerep.getSelectionModel().getSelectedItem();
        if (selectedService != null) {
            ServicerepServices service = new ServicerepServices();
            service.deleteEntity(selectedService); // Passer l'objet Servicerep à la méthode deleteEntity
            showServicerep(); // Actualiser l'affichage
            // Appel de la méthode showNotif pour afficher une notification de suppression
            ServiceNotification.showNotif("Suppression réussie", "Le service a été supprimé avec succès.");


        } else {
            showAlert("Sélectionner un service", "Veuillez sélectionner un service à supprimer.", Alert.AlertType.WARNING);
        }
    }



    @FXML
    void gotostat(ActionEvent event) {
        try {
            // Charger le fichier FXML
            Parent addCivilisationParent = FXMLLoader.load(getClass().getResource("stattypeservicerep.fxml"));
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

    @FXML
    void verspartenaireback(ActionEvent event) {
        try {
            // Récupérer l'ID du service sélectionné
            Servicerep selectedService = tvServicerep.getSelectionModel().getSelectedItem();
            int serviceId = Math.toIntExact(selectedService.getId());

            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/partenaireback.fxml"));
            Parent addCivilisationParent = loader.load();
            Scene addCivilisationScene = new Scene(addCivilisationParent);

            // Passer l'ID du service à la vue PartenaireBack
            PartenaireBack controller = loader.getController();
            controller.setServiceId(serviceId);
            controller.showDemandeServicerep();


            // Obtenir la scène actuelle à partir de l'événement
            Stage window = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Changer la scène pour afficher la nouvelle vue
            window.setScene(addCivilisationScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace(); // Gérer l'erreur de chargement du fichier FXML
        }
    }

    @FXML
    void updateBtn(ActionEvent event) {
        Servicerep selectedService = tvServicerep.getSelectionModel().getSelectedItem();
        if (selectedService != null) {
            try {
                if (NomService.getText().isEmpty() || decription.getText().isEmpty() ||
                        joursrepos.getText().isEmpty() || idprix.getText().isEmpty()) {
                    showAlert("Information manquante", "Vous devez remplir tous les détails concernant le service.", Alert.AlertType.WARNING);
                } else if (Float.parseFloat(idprix.getText()) <= 0) {
                    showAlert("Prix invalide", "Le prix doit être un nombre positif.", Alert.AlertType.WARNING);
                } else if (Integer.parseInt(idhoraire.getValue().toString()) <= Integer.parseInt(horairejours.getValue().toString())) {
                    showAlert("Erreur dans les horaires", "L'horaire d ouverture doit être avant à l'horaire de fermeture.", Alert.AlertType.WARNING);
                } else {
                    // Récupérer les valeurs des champs du formulaire
                    String description = decription.getText();
                    Float prix = Float.parseFloat(idprix.getText());
                    String duree = idduree.getText();
                    String nomService = NomService.getText();
                    String horaire = idhoraire.getValue().toString();
                    String typeVehicule = Typevehiculee.getText(); // Assurez-vous d'avoir un getter pour Typevehiculee
                    String image = ""; // Mettez ici le chemin de l'image si vous en avez un
                    String typeservice = Typeservice.getText(); // Assurez-vous d'avoir un getter pour Typeservice
                    String horairedujour = horairejours.getValue().toString(); // Mettez ici l'horaire du jour si nécessaire
                    String joursderepos = joursrepos.getText();
                    String statut = idstatut.getText(); // Assurez-vous d'avoir un getter pour statut
                    String raison_refus = raisonderefus.getText(); // Assurez-vous d'avoir un getter pour statut

                    // Mettre à jour l'objet Servicerep avec les nouvelles valeurs
                    selectedService.setDescription(description);
                    selectedService.setPrix(prix);
                    selectedService.setDuree(duree);
                    selectedService.setNomservice(nomService);
                    selectedService.setHoraire(horaire);
                    selectedService.setTypeVehicule(typeVehicule);
                    selectedService.setImage(image);
                    selectedService.setTypeservice(typeservice);
                    selectedService.setHorairedujour(horairedujour);
                    selectedService.setJoursderepos(joursderepos);
                    selectedService.setStatut(statut);
                    selectedService.setRaisonderefus(raison_refus);

                    // Utiliser le service pour mettre à jour l'objet Servicerep dans la base de données
                    ServicerepServices service = new ServicerepServices();
                    service.updateEntity(selectedService, selectedService.getId().intValue());
                    // Envoyer un SMS si le statut est "refuser"
                    if (statut.equals("refuser")) {
                        String message = "Votre service " + selectedService.getNomservice() + " a été refusé pour la raison suivante : " + selectedService.getRaisonderefus()
                                + ". Veuillez mettre à jour votre service pour qu'il respecte les règles ou il sera supprimé après 60 minutes.";
                        SendSms sendSms = new SendSms();
                        sendSms.sendSms(message, selectedService.getDuree());
                        // Afficher une confirmation à l'utilisateur
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("message envoyer");
                        alert.setHeaderText(null);
                        alert.setContentText("Le message  a été envoyer  avec succès.");
                        alert.showAndWait();

                    }

                    // Appel de la méthode showNotif pour afficher une notification de succès
                    ServiceNotification.showNotif("Mise à jour réussie", "Le service a été mis à jour avec succès.");

                    // Réinitialiser les champs après la mise à jour
                    clearFieldsService();
                    // Actualiser l'affichage des services
                    showServicerep();
                }
            } catch (NumberFormatException e) {
                showAlert("Format invalide", "Veuillez entrer une valeur numérique valide pour le prix.", Alert.AlertType.WARNING);
            } catch (Exception e) {
                showAlert("Erreur", "Une erreur s'est produite lors de la mise à jour du service.", Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        } else {
            showAlert("Sélectionner un service", "Veuillez sélectionner un service à mettre à jour.", Alert.AlertType.WARNING);
        }
    }


    public void adminboutique(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource("Gestiondemandeservadmin.fxml"));
            Parent root = loader.load();


            // Set any necessary data or parameters for the controller

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Admin Dashboard");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adminproduits(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource("Gestiondemandeservadmin.fxml"));
            Parent root = loader.load();


            // Set any necessary data or parameters for the controller

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Admin Dashboard");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void admincommandes(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource("Gestiondemandeservadmin.fxml"));
            Parent root = loader.load();


            // Set any necessary data or parameters for the controller

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Admin Dashboard");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
