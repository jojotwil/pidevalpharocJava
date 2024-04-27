package controllers;

import entities.Boutique;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.BoutiqueService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class listBoutiqueController implements Initializable {

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
            SearchForBoutique();
        });

        try {
            BoutiqueService bs = new BoutiqueService();
            List<Boutique> boutiques = bs.getAllData();

            for(int i=0;i<boutiques.size();i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/itemBoutique.fxml"));

                try {
                    AnchorPane borderPan = fxmlLoader.load();
                    HBox hBox = (HBox) borderPan.getChildren().get(0);
                    itemBoutiqueController itemController = fxmlLoader.getController();
                    itemController.setData(boutiques.get(i));
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
    void SearchForBoutique() {
        BoutiqueService bs = new BoutiqueService();
        try {
            vBox.getChildren().clear(); // Clear previous content

            String searchText = searchbar_id.getText().toLowerCase(); // Convert to lowercase for case-insensitive search

            ObservableList<Boutique> observableList = FXCollections.observableList(bs.getAllData());

            // Filter the list based on the search text
            List<Boutique> filteredList = observableList.stream()
                    .filter(e -> e.getNom().toLowerCase().contains(searchText))
                    .collect(Collectors.toList());

            // Load and display filtered data
            for (Boutique f : filteredList) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/itemBoutique.fxml"));
                Parent root = fxmlLoader.load();
                System.out.println("Root type: " + root.getClass().getName());
                AnchorPane cardBox = (AnchorPane) root; // This line is causing the error
                itemBoutiqueController cardController = fxmlLoader.getController();
                cardController.setData(f);
                vBox.getChildren().add(cardBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
