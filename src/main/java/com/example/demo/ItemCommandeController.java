package com.example.demo;

import Entities.Boutique;
import Entities.Commande;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemCommandeController  implements Initializable  {

    @FXML
    private Label labelIdCommande ;

Commande commande;

public void setData(Commande commande) {
        this.commande = commande;
        labelIdCommande.setText(String.valueOf(commande.getCommande_id()));}


    public Commande getData(Commande commande) {
        this.commande = commande;
        return this.commande;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
