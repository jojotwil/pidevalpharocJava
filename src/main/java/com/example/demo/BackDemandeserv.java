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
import Entities.Demandeserv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Services.BadWordAPIService;
import Services.DemandeservServices;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class BackDemandeserv {
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
    private TextField Marque;

    @FXML
    private TextField Nom;

    @FXML
    private SplitMenuButton Typeservice;

    @FXML
    private SplitMenuButton Typevehiculee;

    @FXML
    private TableColumn<Demandeserv, String> colEcoltypeservicemail;

    @FXML
    private TableColumn<Demandeserv, Integer> colId;

    @FXML
    private TableColumn<Demandeserv, String> colNom;

    @FXML
    private TableColumn<Demandeserv, LocalDate> coldatedemettre;

    @FXML
    private TableColumn<Demandeserv, String> coldescription;

    @FXML
    private TableColumn<Demandeserv, Float> coleduree;

    @FXML
    private TableColumn<Demandeserv, Integer> colidservice;

    @FXML
    private TableColumn<Demandeserv, String> collocalisation;

    @FXML
    private TableColumn<Demandeserv, String> colmarque;

    @FXML
    private TableColumn<Demandeserv, String> colmodel;

    @FXML
    private TableColumn<Demandeserv, String> colstatut;

    @FXML
    private TableColumn<Demandeserv, String> coltypecarburant;

    @FXML
    private TableColumn<Demandeserv, String> coltypevehicule;

    @FXML
    private TextField decription;

    @FXML
    private TextField duree;

    @FXML
    private DatePicker horaire;

    @FXML
    private ImageView image;

    @FXML
    private TextField localisationdemettreveh;

    @FXML
    private TextField model;

    @FXML
    private SplitMenuButton statut;

    @FXML
    private TextField tfId;

    @FXML
    private TableView<Demandeserv> tvdemandeservice;

    @FXML
    private SplitMenuButton typecarburant;

    public void initialize() {
        MenuItem enAttenteItem = new MenuItem("En attente");
        MenuItem accepterItem = new MenuItem("accepter");
        MenuItem refuserItem = new MenuItem("refuser");


        enAttenteItem.setOnAction(e -> statut.setText("En attente"));
        accepterItem.setOnAction(e -> statut.setText("accepter"));
        refuserItem.setOnAction(e -> statut.setText("refuser"));

        statut.getItems().addAll( enAttenteItem,accepterItem,refuserItem);


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

        // Ajouter des éléments au menu du SplitMenuButton
        MenuItem essenceItem = new MenuItem("Essence");
        MenuItem dieselItem = new MenuItem("Diesel");
        MenuItem electriqueItem = new MenuItem("Électrique");

        typecarburant.getItems().addAll(essenceItem, dieselItem, electriqueItem);

        // Définir un gestionnaire d'événements pour chaque élément du menu
        essenceItem.setOnAction(event -> {
            typecarburant.setText("Essence");
            // Autre traitement à effectuer
        });

        dieselItem.setOnAction(event -> {
            typecarburant.setText("Diesel");
            // Autre traitement à effectuer
        });

        electriqueItem.setOnAction(event -> {
            typecarburant.setText("Électrique");
            // Autre traitement à effectuer
        });
        showDemandeServicerep();
        setupUserTableSelection();
    }
    @FXML
    void showDemandeServicerep() {
        try {
            // Créer une instance de DemandeservServices
            DemandeservServices demandeservServices = new DemandeservServices();
            // Récupérer la liste des demandes de service
            List<Demandeserv> demandes = demandeservServices.getAllData();

            ObservableList<Demandeserv> observableList = FXCollections.observableArrayList(demandes);

            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            colmarque.setCellValueFactory(new PropertyValueFactory<>("marque"));
            colmodel.setCellValueFactory(new PropertyValueFactory<>("modele"));
            coltypecarburant.setCellValueFactory(new PropertyValueFactory<>("typeCarburant"));
            coltypevehicule.setCellValueFactory(new PropertyValueFactory<>("categorieVehicule"));
            colEcoltypeservicemail.setCellValueFactory(new PropertyValueFactory<>("typeservice"));
            colstatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
            coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            coleduree.setCellValueFactory(new PropertyValueFactory<>("duree"));
            coldatedemettre.setCellValueFactory(new PropertyValueFactory<>("horaire"));
            collocalisation.setCellValueFactory(new PropertyValueFactory<>("localisationdemettrelavehicule"));
            colidservice.setCellValueFactory(new PropertyValueFactory<>("servicerep_id"));

            tvdemandeservice.setItems(observableList);
            tvdemandeservice.refresh();
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer les exceptions qui pourraient survenir lors de la récupération des demandes de service
            // Afficher un message d'erreur à l'utilisateur ou gérer l'exception de manière appropriée
        }
    }
    private void setupUserTableSelection() {
        tvdemandeservice.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Récupérer l'élément sélectionné
                Demandeserv selectedDemande = newSelection;
                // Afficher les détails de l'élément sélectionné dans les autres éléments de l'interface utilisateur
                tfId.setText(String.valueOf(selectedDemande.getId()));
                Nom.setText(selectedDemande.getNom());
                Marque.setText(selectedDemande.getMarque());
                model.setText(selectedDemande.getModele());
                typecarburant.setText(selectedDemande.getTypeCarburant());
                Typevehiculee.setText(selectedDemande.getCategorieVehicule());
                Typeservice.setText(selectedDemande.getTypeservice());
                statut.setText(selectedDemande.getStatut());
                decription.setText(selectedDemande.getDescription());
                duree.setText(String.valueOf(selectedDemande.getDuree()));
                horaire.setValue(selectedDemande.getHoraire());
                localisationdemettreveh.setText(selectedDemande.getLocalisationdemettrelavehicule());
            } else {
                // Réinitialiser les champs si aucun élément n'est sélectionné
                tfId.setText("");
                Nom.setText("");
                Marque.setText("");
                model.setText("");
                typecarburant.setText("");
                Typevehiculee.setText("");
                Typeservice.setText("");
                statut.setText("");
                decription.setText("");
                duree.setText("");
                horaire.setValue(null);
                localisationdemettreveh.setText("");
            }
        });
    }



    private BadWordAPIService badWordAPIService = new BadWordAPIService();
    @FXML
    void addBtn(ActionEvent event) {
        try {
            if (Nom.getText().isEmpty() || Marque.getText().isEmpty() || model.getText().isEmpty() ||
                    decription.getText().isEmpty() || duree.getText().isEmpty() || horaire.getValue() == null ||
                    statut.getText().isEmpty() || typecarburant.getText().isEmpty() || Typevehiculee.getText().isEmpty()
                    || Typeservice.getText().isEmpty() || localisationdemettreveh.getText().isEmpty() ) {

                showAlert("Information manquante", "Vous devez remplir tous les détails concernant la demande de service.", Alert.AlertType.WARNING);
            } else if (Float.parseFloat(duree.getText()) <= 0) {
                showAlert("Durée invalide", "La durée doit être un nombre positif.", Alert.AlertType.WARNING);
            }
            else if (badWordAPIService.containsBadWord(decription.getText())) {
                showAlert("Description invalide", "La description contient des mots inappropriés.", Alert.AlertType.WARNING);
            }else {
                LocalDate selectedDate = horaire.getValue();
                if (selectedDate.isBefore(LocalDate.now())) {
                    showAlert("Date invalide", "La date choisie doit être dans le futur.", Alert.AlertType.WARNING);
                } else {
                    ajouterDemandeService();
                }
            }
        } catch (NumberFormatException e) {
            showAlert("Format invalide", "Veuillez entrer une valeur numérique valide pour la durée.", Alert.AlertType.WARNING);
        } catch (Exception e) {
            showAlert("Erreur", "Une erreur s'est produite lors de l'ajout de la demande de service.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void ajouterDemandeService() {
        try {
            // Créer un nouvel objet Demandeserv avec les valeurs récupérées
            Demandeserv demandeserv = new Demandeserv();
            demandeserv.setNom(Nom.getText());
            demandeserv.setMarque(Marque.getText());
            demandeserv.setModele(model.getText());
            demandeserv.setDescription(decription.getText());
            demandeserv.setDuree(Float.parseFloat(duree.getText()));
            demandeserv.setHoraire(horaire.getValue());
            demandeserv.setStatut(statut.getText());
            demandeserv.setTypeCarburant(typecarburant.getText());
            demandeserv.setTypeservice(Typeservice.getText());
            demandeserv.setCategorieVehicule(Typevehiculee.getText());
            demandeserv.setLocalisationdemettrelavehicule(localisationdemettreveh.getText());

            // Créer une instance de DemandeservServices
            DemandeservServices demandeservServices = new DemandeservServices();
            // Appeler addEntity avec l'objet demandeserv
            demandeservServices.addEntity(demandeserv);
            // Afficher une confirmation à l'utilisateur
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Demande de service ajoutée");
            alert.setHeaderText(null);
            alert.setContentText("La demande de service a été ajoutée avec succès.");
            alert.showAndWait();
            showDemandeServicerep();
            clearFieldsDemandeService();
            // Réinitialiser les champs après l'ajout

        } catch (Exception e) {
            e.printStackTrace();
            // Gérer les exceptions qui pourraient survenir lors de l'ajout de la demande de service
            // Afficher un message d'erreur à l'utilisateur ou gérer l'exception de manière appropriée
        }

    }
    private void clearFieldsDemandeService() {
        Nom.clear();
        Marque.clear();
        model.clear();
        decription.clear();
        localisationdemettreveh.clear();
        duree.clear();
        horaire.setValue(null);
        statut.setText("");
        typecarburant.setText("");
        Typevehiculee.setText("");

        Typeservice.setText(""); // Clear the type service field
        // Clear other fields as needed
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
        clearFieldsDemandeService();

    }

    @FXML
    void deleteBtn(ActionEvent event) {
        try {
            // Récupérer l'élément sélectionné dans le TableView
            Demandeserv selectedDemande = tvdemandeservice.getSelectionModel().getSelectedItem();

            if (selectedDemande == null) {
                showAlert("Aucune sélection", "Veuillez sélectionner une demande de service à supprimer.", Alert.AlertType.WARNING);
                return;
            }

            // Créer une instance de DemandeservServices
            DemandeservServices demandeservServices = new DemandeservServices();
            // Supprimer l'élément sélectionné en utilisant son ID
            demandeservServices.deleteEntity(selectedDemande);

            // Afficher une confirmation à l'utilisateur
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Demande de service supprimée");
            alert.setHeaderText(null);
            alert.setContentText("La demande de service a été supprimée avec succès.");
            alert.showAndWait();

            // Rafraîchir la liste des demandes de service dans le TableView
            showDemandeServicerep();
            clearFieldsDemandeService(); // Réinitialiser les champs
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer les exceptions qui pourraient survenir lors de la suppression de la demande de service
            // Afficher un message d'erreur à l'utilisateur ou gérer l'exception de manière appropriée
            showAlert("Erreur", "Une erreur s'est produite lors de la suppression de la demande de service.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void gotostatcarburant(ActionEvent event) {
        try {
            // Charger le fichier FXML
            Parent addCivilisationParent = FXMLLoader.load(getClass().getResource("/statservicerep.fxml"));
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
    void gotostatcategorie(ActionEvent event) {
        try {
            // Charger le fichier FXML
            Parent addCivilisationParent = FXMLLoader.load(getClass().getResource("/stattypecarburantdemande.fxml"));
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
    void updateBtn(ActionEvent event) {
        try {
            // Récupérer l'élément sélectionné dans le TableView
            Demandeserv selectedDemande = tvdemandeservice.getSelectionModel().getSelectedItem();

            if (selectedDemande == null) {
                showAlert("Aucune sélection", "Veuillez sélectionner une demande de service à mettre à jour.", Alert.AlertType.WARNING);
                return;
            }

            // Mettre à jour les champs de l'élément sélectionné avec les valeurs des champs dans l'interface utilisateur
            selectedDemande.setNom(Nom.getText());
            selectedDemande.setMarque(Marque.getText());
            selectedDemande.setModele(model.getText());
            selectedDemande.setDescription(decription.getText());
            selectedDemande.setDuree(Float.parseFloat(duree.getText()));
            selectedDemande.setHoraire(horaire.getValue());
            selectedDemande.setStatut(statut.getText());
            selectedDemande.setTypeCarburant(typecarburant.getText());
            selectedDemande.setTypeservice(Typeservice.getText());
            selectedDemande.setCategorieVehicule(Typevehiculee.getText());
            selectedDemande.setLocalisationdemettrelavehicule(localisationdemettreveh.getText());

            // Créer une instance de DemandeservServices
            DemandeservServices demandeservServices = new DemandeservServices();
            // Mettre à jour l'élément sélectionné
            // Mettre à jour l'élément sélectionné
            demandeservServices.updateEntity(selectedDemande, selectedDemande.getId());

            // Afficher une confirmation à l'utilisateur
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Demande de service mise à jour");
            alert.setHeaderText(null);
            alert.setContentText("La demande de service a été mise à jour avec succès.");
            alert.showAndWait();

            // Rafraîchir la liste des demandes de service dans le TableView
            showDemandeServicerep();
            clearFieldsDemandeService(); // Réinitialiser les champs
        } catch (NumberFormatException e) {
            showAlert("Format invalide", "Veuillez entrer une valeur numérique valide pour la durée.", Alert.AlertType.WARNING);
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer les exceptions qui pourraient survenir lors de la mise à jour de la demande de service
            // Afficher un message d'erreur à l'utilisateur ou gérer l'exception de manière appropriée
            showAlert("Erreur", "Une erreur s'est produite lors de la mise à jour de la demande de service.", Alert.AlertType.ERROR);
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
