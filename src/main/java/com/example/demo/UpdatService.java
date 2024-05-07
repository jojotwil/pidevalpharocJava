package com.example.demo;


import Entities.Servicerep;
import Services.ServicerepServices;
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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;

public class UpdatService implements Initializable {
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
    private Button IDBUTupdateEntity;

    @FXML
    private TextField description;

    @FXML
    private TextField duree;

    @FXML
    private Spinner<Integer> horaire;

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

    private Servicerep servicerepToUpdate;
    @FXML
    private TextField labid;
    @FXML
    private Label idService;

    public int id;
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
        // Supposons que vous ayez l'identifiant de la boutique à modifier dans la variable id
        ServicerepServices ss = new ServicerepServices();

        this.servicerepToUpdate = ss.getservicebyId(id); // Méthode à implémenter dans BoutiqueService
      /*  Long serviceId = 123L; // Récupérer l'ID depuis un autre endroit
        this.servicerepToUpdate = ss.getservicebyId(Math.toIntExact(serviceId));*/
        if (this.servicerepToUpdate != null) {
            idService.setVisible(false);
            // Initialiser les champs avec les valeurs du service à mettre à jour
            labid.setText(String.valueOf(this.servicerepToUpdate.getId().intValue()));
            nomservice.setText(this.servicerepToUpdate.getNomservice());

            description.setText(this.servicerepToUpdate.getDescription());
                duree.setText(this.servicerepToUpdate.getDuree());
                horaire.getValueFactory().setValue(Integer.parseInt(this.servicerepToUpdate.getHoraire()));
                idhorairedejour.getValueFactory().setValue(Integer.parseInt(this.servicerepToUpdate.getHorairedujour()));
                jourrepos.setText(this.servicerepToUpdate.getJoursderepos());
                prix.setText(String.valueOf(this.servicerepToUpdate.getPrix()));
                statut.setText(this.servicerepToUpdate.getStatut());
                typedeservice.setText(this.servicerepToUpdate.getTypeservice());
                typedevehicule.setText(this.servicerepToUpdate.getTypeVehicule());
            }
        else {
            System.out.println("Impossible de récupérer le Servicerep avec l'ID : " + id);
        }


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

        private void clearFieldsService () {
            nomservice.clear();
            description.clear();
            duree.clear();
            horaire.getValueFactory().setValue(0);
            idhorairedejour.getValueFactory().setValue(0);
            jourrepos.clear();
            prix.clear();
            statut.setText("");
            typedeservice.setText("");
            typedevehicule.setText("");
        }

        private File selectedImageFile; // Déclaration de la variable selectedImageFile

        private String imageName = null;

        @FXML
        void ajouterImage (ActionEvent event){
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
    void updateService(ActionEvent event) {
        if (nomservice.getText().isEmpty() || prix.getText().isEmpty() || description.getText().isEmpty()
                || duree.getText().isEmpty()
                || jourrepos.getText().isEmpty() || statut.getText().isEmpty() || typedeservice.getText().isEmpty()
        ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information manquante");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez remplir tous les détails concernant votre Service.");
            Optional<ButtonType> option = alert.showAndWait();
        } else if (this.servicerepToUpdate == null) {
            System.out.println("Le service à mettre à jour est null.");
        } else if (this.servicerepToUpdate.getId() == null) {
            System.out.println("L'ID du service à mettre à jour est null.");
        } else {
            modifierservice();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Modifié avec succès");
            alert.setHeaderText(null);
            alert.setContentText("Votre service a été modifié avec succès.");
            Optional<ButtonType> option = alert.showAndWait();
        }
    }

    @FXML
    public void modifierservice() {
        try {
            float prixValue = Float.parseFloat(prix.getText());
            int horaireValue = horaire.getValue();
            int idHoraireDuJourValue = idhorairedejour.getValue();

            // Passer les valeurs aux setters de Servicerep
            this.servicerepToUpdate.setId(Long.valueOf(labid.getText()));
            this.servicerepToUpdate.setNomservice(nomservice.getText());

            this.servicerepToUpdate.setPrix(prixValue);
            this.servicerepToUpdate.setDescription(description.getText());
            this.servicerepToUpdate.setDuree(duree.getText());
            this.servicerepToUpdate.setHoraire(String.valueOf(horaireValue));
            this.servicerepToUpdate.setHorairedujour(String.valueOf(idHoraireDuJourValue));
            this.servicerepToUpdate.setJoursderepos(jourrepos.getText());
            this.servicerepToUpdate.setStatut(statut.getText());
            this.servicerepToUpdate.setTypeservice(typedeservice.getText());
            this.servicerepToUpdate.setTypeVehicule(typedevehicule.getText());

            // Modifier le service
            ServicerepServices rs = new ServicerepServices();
            rs.updateEntity(this.servicerepToUpdate,this.servicerepToUpdate.getId().intValue());

            // Afficher un message de confirmation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Modifié avec succès");
            alert.setHeaderText(null);
            alert.setContentText("Votre produit a été modifié avec succès.");
            Optional<ButtonType> option = alert.showAndWait();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez saisir un prix valide.");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }


        Servicerep servicerep;
    public void setData(Servicerep servicerep) {
        if (servicerep != null) {
            this.servicerepToUpdate = servicerep;
            labid.setText(String.valueOf(servicerep.getId().intValue()));
            nomservice.setText(servicerep.getNomservice());
            description.setText(servicerep.getDescription());
            duree.setText(servicerep.getDuree());
            jourrepos.setText(servicerep.getJoursderepos());
            prix.setText(String.valueOf(servicerep.getPrix()));
            statut.setText(servicerep.getStatut());
            typedeservice.setText(servicerep.getTypeservice());
            typedevehicule.setText(servicerep.getTypeVehicule());
        } else {
            System.out.println("Le service à mettre à jour est null.");
        }

    }

    @FXML
    void   backfromupdateserv(ActionEvent event) throws  Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/listeservice.fxml"));
        Parent adminRoot = loader.load();

        Scene adminScene = new Scene(adminRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(adminScene);
        window.show();

    }

    public void setServicerep(Servicerep servicerep) {
    }
}

