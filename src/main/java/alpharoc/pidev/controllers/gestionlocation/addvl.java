package alpharoc.pidev.controllers.gestionlocation;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import alpharoc.pidev.entities.VehiculeLouer;
import alpharoc.pidev.services.VehiculeLouerServie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.time.chrono.Chronology;
import java.util.*;
import java.util.regex.Pattern;

public class addvl implements Initializable {

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
    void addvl(ActionEvent event) {




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
           smsAPI.init();
        smsAPI.sendSMS("+21625281990", "+16598370014", "Salut le véhicule a été ajouté!");

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
