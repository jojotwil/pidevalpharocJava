package com.example.demo;

import Entities.Servicerep;
import Services.ServicerepServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
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

public class UpdateService implements Initializable {

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
    private Label idService;

    public int id;




  /*  public void setUpdateData() {


        System.out.println(servicerepToUpdate);
        //this.servicerepToUpdate = servicerep;
        //Afficher les données dans les champs de saisie

        nomservice.setText(getServicerepToUpdate().getNomservice());
        description.setText(servicerepToUpdate.getDescription());
        duree.setText(servicerepToUpdate.getDuree());
        horaire.getValueFactory().setValue(Integer.parseInt(servicerepToUpdate.getHoraire()));
        idhorairedejour.getValueFactory().setValue(Integer.parseInt(servicerepToUpdate.getHorairedujour()));
        jourrepos.setText(servicerepToUpdate.getJoursderepos());
        prix.setText(String.valueOf(servicerepToUpdate.getPrix()));
        statut.setText(servicerepToUpdate.getStatut());
        typedeservice.setText(servicerepToUpdate.getTypeservice());
        typedevehicule.setText(servicerepToUpdate.getTypeVehicule());
    }*/


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServicerepServices bs = new ServicerepServices();
        Servicerep servicerepToUpdate = bs.getservicebyId(id); // Méthode à implémenter dans ServicerepServices

        if (servicerepToUpdate != null) {
            // Initialiser les champs avec les valeurs du service à mettre à jour
            description.setText(servicerepToUpdate.getDescription());
            duree.setText(servicerepToUpdate.getDuree());
            //horaire.getValueFactory().setValue(Integer.parseInt(servicerepToUpdate.getHoraire()));
            //idhorairedejour.getValueFactory().setValue(Integer.parseInt(servicerepToUpdate.getHorairedujour()));
            jourrepos.setText(servicerepToUpdate.getJoursderepos());
            nomservice.setText(servicerepToUpdate.getNomservice());
            prix.setText(String.valueOf(servicerepToUpdate.getPrix()));
            statut.setText(servicerepToUpdate.getStatut());
            typedeservice.setText(servicerepToUpdate.getTypeservice());
            typedevehicule.setText(servicerepToUpdate.getTypeVehicule());
            //idService.setText(String.valueOf(servicerepToUpdate.getId()));
        }
    }



    @FXML
    void updateService(ActionEvent event) {
        if (nomservice.getText().isEmpty() || prix.getText().isEmpty() || description.getText().isEmpty()
                || duree.getText().isEmpty() || horaire.getValue() == null || idhorairedejour.getValue() == null
                || jourrepos.getText().isEmpty() || statut.getText().isEmpty() || typedeservice.getText().isEmpty()
                || typedevehicule.getText().isEmpty()) {
            showAlert("Information manquante", "Vous devez remplir tous les détails concernant votre Service.", Alert.AlertType.WARNING);
        } else {
            modifierservice();
            showAlert("Modifié avec succès", "Votre service a été modifié avec succès.", Alert.AlertType.CONFIRMATION);
        }
    }

    public void setServicerepToUpdate(Servicerep servicerep) {
        this.servicerepToUpdate = servicerep;
    }
    public void modifierservice() {
        try {
            int idS = Integer.parseInt(idService.getText());
            String nom = nomservice.getText();
            float prixValue = Float.parseFloat(prix.getText());
            String desc = description.getText();
            String dureeValue = duree.getText();
            int horaireValue = horaire.getValue();
            int idHoraireDuJourValue = idhorairedejour.getValue();
            String jourRepos = jourrepos.getText();
            String statutValue = statut.getText();
            String typeService = typedeservice.getText();
            String typeVehicule = typedevehicule.getText();

            Servicerep servicerep = new Servicerep(idS, desc, prixValue, dureeValue, nom, String.valueOf(horaireValue),
                    typeVehicule, null, statutValue, null, jourRepos, null, idHoraireDuJourValue, typeService, 0, 0);

            ServicerepServices servicerepServices = new ServicerepServices();
            servicerepServices.updateEntity(servicerep, idS);

            clearFieldsService();
        } catch (NumberFormatException e) {
            showAlert("Erreur", "Le prix doit être un nombre entier.", Alert.AlertType.ERROR);
        }
    }


  /*  public void modifierservice() {
        try {
            String nom = nomservice.getText();
            float prixValue = Float.parseFloat(prix.getText());
            String desc = description.getText();
            String dureeValue = duree.getText();
            int horaireValue = horaire.getValue();
            int idHoraireDuJourValue = idhorairedejour.getValue();
           // int idS=Integer.parseInt(idService)
            String jourRepos = jourrepos.getText();
            String statutValue = statut.getText();
            String typeService = typedeservice.getText();
            String typeVehicule = typedevehicule.getText();
          ///  Servicerep servicerep = new Servicerep(idS, id, desc, prixValue, duree, nomservice, horaire, typeVehicule, image, , , , statut);


            // Modifier le servicerep
            servicerep.setNomservice(nom);
            servicerep.setPrix(prixValue);
            servicerep.setDescription(desc);
            servicerep.setDuree(dureeValue);
            servicerep.setHoraire(String.valueOf(horaireValue));
            servicerep.setHorairedujour(String.valueOf(idHoraireDuJourValue));
            servicerep.setJoursderepos(jourRepos);
            servicerep.setStatut(statutValue);
            servicerep.setTypeservice(typeService);
            servicerep.setTypeVehicule(typeVehicule);

            // Appeler le service pour effectuer la modification
            ServicerepServices servicerepServices = new ServicerepServices();
            servicerepServices.updateEntity(servicerep, servicerep.getId().intValue());


            // Vider les champs après la modification
            clearFieldsService();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le prix doit être un nombre entier.");
            alert.showAndWait();
        }
    }

*/

    private void clearFieldsService() {
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

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
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
    void   backfromupdateserv(ActionEvent event) throws  Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/listeservice.fxml"));
        Parent adminRoot = loader.load();

        Scene adminScene = new Scene(adminRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(adminScene);
        window.show();

    }



    @FXML
    private TextField textFieldNomService;

    // Autres champs de saisie


}