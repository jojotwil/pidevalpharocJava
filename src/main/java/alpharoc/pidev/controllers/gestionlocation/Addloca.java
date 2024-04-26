package alpharoc.pidev.controllers.gestionlocation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class Addloca {

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
        //setidvehicule

    }

    @FXML
    void annuler(ActionEvent event) {
tfdescrp.setText("");
tfloca.setText("");

    }

    private int selectedId;

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }

}
