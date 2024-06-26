package com.example.demo;

import Entities.Produit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import Services.ProduitService;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemProduitController implements Initializable {

    @FXML
    private Button btnModifierProduit;

    @FXML
    private Button btnSupprimerProduit;

    @FXML
    private AnchorPane itemProduitPane;


    @FXML
    private Label labelDescriptionProduit;
    @FXML
    private Label labelTypeProduit;

    @FXML
    private Label  labelTitreProduit;

    @FXML
    private Label labelcategory;

    @FXML
    private Label labelPrix;


    @FXML
    private Label labelIdProduit;
    @FXML
    private ImageView imageItem;




  Produit prod;


    public void setData(Produit prod) {
        this.prod = prod;
        labelIdProduit.setText(String.valueOf(prod.getId()));
        labelTitreProduit.setText(prod.getTitre());
        labelDescriptionProduit.setText(prod.getDescription());
        labelPrix.setText(String.valueOf(prod.getPrix()));
        labelTypeProduit.setText(prod.getType());
        labelcategory.setText(prod.getCategory());


        String imagePath = "/uploads/" + prod.getImage();
        try {
            ImageView imageView = new ImageView(getClass().getResource(imagePath).toExternalForm());
            imageItem.setImage(imageView.getImage());
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());

        }

    }




    public Produit getData(Produit prod) {
        this.prod = prod;
        return this.prod;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getData(prod);
    }}
