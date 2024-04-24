package alpharoc.pidev.controllers;

import alpharoc.pidev.entities.Personne;
import alpharoc.pidev.services.PersonneService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Inscription {

    @FXML
    private Button tfbutton;

    @FXML
    private TextField tfnom;

    @FXML
    private TextField tfprenom;

    @FXML
    void savePerson(ActionEvent event) {
    String resNom = tfnom.getText();
        String resPrenom = tfprenom.getText();
        Personne p = new Personne(resNom,resPrenom);
        PersonneService ps = new PersonneService();
        ps.addEntity2(p);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Details.fxml"));
        try {
            Parent root = loader.load();
            Details dc = loader.getController();
            dc.setLbnom(resNom);
            dc.setLbprenom(resPrenom);
            tfprenom.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
