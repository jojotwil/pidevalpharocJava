package controllers;

import entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.ProduitService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class ListProduitController implements Initializable {


    @FXML
    private BorderPane listProduitPane;

    @FXML
    private VBox vBox;

    @FXML
    private TextField searchbar_id;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int column=0;
        int row=0;
        searchbar_id.textProperty().addListener((observable, oldValue, newValue) -> {
            SearchForProduit();
        });

        try {
            ProduitService ps = new ProduitService();
            List<Produit> produits = ps.getAllData();

            for(int i=0;i<produits.size();i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ItemProduit.fxml"));

                try {
                    AnchorPane borderPan = fxmlLoader.load();
                    HBox hBox = (HBox) borderPan.getChildren().get(0);
                    ItemProduitController itemController = fxmlLoader.getController();
                    itemController.setData(produits.get(i));
                    vBox.getChildren().add(hBox);

                } catch (IOException ex) {
                    Logger.getLogger(itemBoutiqueController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    void SearchForProduit() {
        ProduitService bs = new ProduitService();
        try {
            vBox.getChildren().clear(); // Clear previous content

            String searchText = searchbar_id.getText().toLowerCase(); // Convert to lowercase for case-insensitive search

            ObservableList<Produit> observableList = FXCollections.observableList(bs.getAllData());

            // Filter the list based on the search text
            List<Produit> filteredList = observableList.stream()
                    .filter(e -> e.getTitre().toLowerCase().contains(searchText))
                    .collect(Collectors.toList());

            // Load and display filtered data
            for (Produit f : filteredList) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ItemProduit.fxml"));
                Parent root = fxmlLoader.load();
                System.out.println("Root type: " + root.getClass().getName());
                AnchorPane cardBox = (AnchorPane) root; // This line is causing the error
                ItemProduitController cardController = fxmlLoader.getController();
                cardController.setData(f);
                vBox.getChildren().add(cardBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
