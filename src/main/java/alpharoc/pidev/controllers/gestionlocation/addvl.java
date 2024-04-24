package alpharoc.pidev.controllers.gestionlocation;

import alpharoc.pidev.entities.VehiculeLouer;
import alpharoc.pidev.services.VehiculeLouerServie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.chrono.Chronology;
import java.util.Date;
import java.util.regex.Pattern;

public class addvl {

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

   /* private boolean validmarque() {
        String nomText =tfmarque.getText();

        if (Pattern.matches("^[a-zA-Z0-9]+$", nomText)) {
            errorLabel3.setVisible(false);
            return true;
        } else {
            errorLabel3.setVisible(true);

        }
        return false;
    }*/
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
    @FXML
    void addvl(ActionEvent event) {
        String resmarque = tfmarque.getText();
        if (!isValidmarque(resmarque)) {
            showAlert(Alert.AlertType.ERROR, "Erreur de saisie", " Doit contenir que des lettres.");
            return;
        }
        String resmodele = tfmodele.getText();
        String resdescr= tfdescrp.getText();
      // Date resdate= tfdate.getDate();
        //nked fazet ta6ou date men form
        Date periode_dispo = new Date();

        String restype = tftypecarb.getText();
        String rescato = tfcato.getText();
        VehiculeLouer p = new VehiculeLouer(resmarque,resmodele,resdescr,periode_dispo,restype,rescato);
        VehiculeLouerServie ps = new VehiculeLouerServie();
        ps.addEntity2(p);
    }

    @FXML
    void annuler(ActionEvent event) {

        tfmarque.setText(""); // Efface le contenu du champ
        tfmodele.setText("");
        tfdescrp.setText("");
        //tfdate.setText("");
        tftypecarb.setText("");
        tfcato.setText("");
    }

}
