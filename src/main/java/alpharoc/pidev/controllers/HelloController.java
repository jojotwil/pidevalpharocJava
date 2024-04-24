package alpharoc.pidev.controllers;

import alpharoc.pidev.entities.Personne;
import alpharoc.pidev.services.PersonneService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    //button affiche
    /*PersonneService ps = new PersonneService();
    List<Personne> a= new ArrayList<>();
    @FXML
    protected void onHelloButtonClick() {

        a.addAll(ps.getAllData());
        welcomeText.setText(a.get(0).getNom());


    }
     */
}