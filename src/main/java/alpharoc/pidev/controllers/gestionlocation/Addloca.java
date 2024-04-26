package alpharoc.pidev.controllers.gestionlocation;
import alpharoc.pidev.controllers.gestionlocation.Showvl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import alpharoc.pidev.entities.loca;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import alpharoc.pidev.services.locaService;

public class Addloca implements Initializable {

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
       // int selectedId =  setSelectedId; // Assuming you have a method to get the selected ID
        String descr = tfdescrp.getText();
        String locati = tfloca.getText();
        LocalDate datedeb = tfdatedebut.getValue(); // Assuming tfdatedebut is a DatePicker
        LocalDate datefin = tfdatefin.getValue();
        //loca l=new loca(selectedId,descr,locati,datedeb,datefin);
        locaService ls= new locaService();
    //ls.addEntity2(l);
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

}
