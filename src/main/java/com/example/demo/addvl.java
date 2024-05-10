package com.example.demo;


import Entities.VehiculeLouer;
import Services.VehiculeLouerServie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class addvl implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private Button Btnback;
    @FXML
    private ComboBox<String> tfcato1;
    @FXML
    private ComboBox<String> tftypecarb1;
    @FXML
    private final String[]choix1={"car","yacht","moto"};
    @FXML
    private final String[]choix2={"diesel","essence"};
    @FXML
    private Button btannuler;

    @FXML
    private Button btnsave;

    @FXML
    private TextField tfcato;

    @FXML
    private DatePicker tfdate;

    @FXML
    private TextField tfdescrp;

    @FXML
    private TextField tfmarque;

    @FXML
    private TextField tfmodele;

    @FXML
    private TextField tftypecarb;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfcato1.getItems().addAll(choix1);
        tftypecarb1.getItems().addAll(choix2);
    }

    private void setupAutocomplete() {
        List<String> words = Arrays.asList("word1", "word2", "word3"); // Your list of words

        TextFields.bindAutoCompletion(((TextField) vbox.lookup("#tfmarque")), words);
    }
    private void showAlertDialog(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType); // Crée une nouvelle boîte de dialogue de type alerte
        alert.setTitle(title); // Définit le titre de la boîte de dialogue
        alert.setHeaderText(null); // Définit le texte d'en-tête de la boîte de dialogue à null
        alert.setContentText(message); // Définit le message de la boîte de dialogue
        alert.showAndWait(); // Affiche la boîte de dialogue et attend la réponse de l'utilisateur
    }
    private boolean isValidmarque(String marque) {
        return marque.matches("[a-zA-Z]+");
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if ( tfmarque.getText() == null ||tfmarque.getText().isEmpty()) {
            errorMessage += "Le champ de marque est vide !\n";
        }

        if (tfdate.getValue() == null) {
            errorMessage += "Aucune date sélectionnée !\n";
        }



        if (tfmodele.getText() == null || tfmodele.getText().isEmpty()) {
            errorMessage += "Le champ de modele est vide !\n";
        }

        if (tfdescrp.getText() == null || tfdescrp.getText().isEmpty()) {
            errorMessage += "Le champ de descreption est vide !\n";
        }

        if (!errorMessage.isEmpty()) {
            showAlertDialog(Alert.AlertType.ERROR, "Erreur de validation", "Veuillez corriger les erreurs suivantes", errorMessage);
            return false;
        }
        return true;
    }
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
    private boolean containsBadWord(String description) {
        // Define the bad words
        String[] badWords = {"fuck", "twat", "cunt"};

        // Check if any of the bad words are present in the description
        for (String word : badWords) {
            if (description.toLowerCase().contains(word)) {
                return true;
            }
        }
        return false;
    }



    @FXML
    void addvl() {



        String resdescr= tfdescrp.getText();
        if (!isValidmarque(resdescr)) {
            showAlert(Alert.AlertType.ERROR, "Erreur de saisie de descreption", " Doit contenir que des lettres.");
            return;
        }
        if (containsBadWord(resdescr)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Description contains a bad word.");
            alert.showAndWait();
            return;
        }
        if ((isInputValid())){
            String resmarque = tfmarque.getText();
            if (!isValidmarque(resmarque)) {
                showAlert(Alert.AlertType.ERROR, "Erreur de saisie de marque", " Doit contenir que des lettres.");
                return;
            }
            String resmodele = tfmodele.getText();
            if (!isValidmarque(resmodele)) {
                showAlert(Alert.AlertType.ERROR, "Erreur de saisie de modele", " Doit contenir que des lettres.");
                return;
            }
            // Date resdate= tfdate.getDate();
            //nked fazet ta6ou date men form
            Date periode_dispo = new Date();

            String restype = tftypecarb1.getValue();
            String rescato = tfcato1.getValue();
            VehiculeLouer p = new VehiculeLouer(resmarque,resmodele,resdescr,periode_dispo,restype,rescato);
            VehiculeLouerServie ps = new VehiculeLouerServie();
            ps.addEntity2(p);


            //sms
            smsAPI.init();
            String marSms = tfmarque.getText();
            String modSms = tfmodele.getText();
            Date today = new Date();
            String message = "Salut, votre véhicule  de marque " + marSms +" et modele " + modSms+ " a été ajouté aujourd'hui le "+today+" !";
            smsAPI.sendSMS("+21625281990", "+16598370014", message);

        }}

    @FXML

    void annuler(ActionEvent event) {

        tfmarque.setText(""); // Efface le contenu du champ
        tfmodele.setText("");
        tfdescrp.setText("");
        //tfdate.setText("");
        //tftypecarb.setText("");
        //tfcato.setText("");
    }



    public void goback(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Showvl.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Ajout Vehicule");
        stage.setScene(new Scene(root));
        stage.show();
        ((Button) actionEvent.getSource()).getScene().getWindow().hide();
    }

}

