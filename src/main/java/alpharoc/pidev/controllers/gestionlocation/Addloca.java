package alpharoc.pidev.controllers.gestionlocation;
import alpharoc.pidev.controllers.gestionlocation.Showvl;
import alpharoc.pidev.services.VehiculeLouerServie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import alpharoc.pidev.entities.loca;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import alpharoc.pidev.services.locaService;
import javafx.stage.Stage;

public class Addloca implements Initializable {

    @FXML
    private Button Btnback;

    @FXML
    private Button btannuler;

    @FXML
    private Button btnsave;

    @FXML
    private DatePicker tfdatedebut;

    @FXML
    private DatePicker tfdatefin;

    @FXML
    private TextField tfdescrp;

    @FXML
    private TextField tfloca;

    @FXML
    void ajouter(ActionEvent event) {
      /*  String Marque;
        VehiculeLouerServie vq = new VehiculeLouerServie();
            int id =vq.getFormationIdB(Marque);*/
        int id= 13;
        if ((isInputValid())) {
            // int selectedId =  setSelectedId; // Assuming you have a method to get the selected ID
            String descr = tfdescrp.getText();
            if (!isValidmarque(descr)) {
                showAlert(Alert.AlertType.ERROR, "Erreur de saisie de marque", " Doit contenir que des lettres.");
                return;
            }
            String locati = tfloca.getText();
            if (!isValidmarque(locati)) {
                showAlert(Alert.AlertType.ERROR, "Erreur de saisie de marque", " Doit contenir que des lettres.");
                return;
            }
            LocalDate datedeb = tfdatedebut.getValue(); // Assuming tfdatedebut is a DatePicker
            LocalDate datefin = tfdatefin.getValue();
            loca l=new loca(id,descr,locati,datedeb,datefin);
            locaService ls = new locaService();
            ls.addEntity2(l);
        }
    }

    // Use this method to perform any actions with the selected ID


    @FXML
    void annuler(ActionEvent event) {
tfdescrp.setText("");
tfloca.setText("");

    }

    private int selectedId;

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Selected ID: " + selectedId);
        // Perform other operations using the selectedId
    }

    public void goback(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/showloca.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Ajout Vehicule");
        stage.setScene(new Scene(root));
        stage.show();
        ((Button) actionEvent.getSource()).getScene().getWindow().hide();
    }
    private boolean isValidmarque(String marque) {
        return marque.matches("[a-zA-Z]+");
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
    private boolean isInputValid() {
        String errorMessage = "";

        if ( tfloca.getText() == null ||tfloca.getText().isEmpty()) {
            errorMessage += "Le champ de localisation est vide !\n";
        }

        if (tfdatedebut.getValue() == null) {
            errorMessage += "Aucune date de debut sélectionnée !\n";
        }
        if (tfdatefin.getValue() == null) {
            errorMessage += "Aucune date de fin sélectionnée !\n";
        } else if (tfdatedebut.getValue() != null && tfdatefin.getValue().isBefore(tfdatedebut.getValue())) {
            errorMessage += "La date de fin ne peut pas être avant la date de début !\n";
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
}
