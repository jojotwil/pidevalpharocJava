package controllers;

import entities.Produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.ProduitService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class listeProduitFrontController implements Initializable {

    @FXML
    private Button btnAjouterBoutique;
    @FXML
    private AnchorPane listProduitFront;

    @FXML
    private Label MenuClose;

    @FXML
    private Label Menu;
    @FXML
    private AnchorPane slider;

    @FXML
    private HBox hbox;

    @FXML
    private HBox vbox;

    @FXML
    private GridPane grid;

    @FXML
    private Pagination pag;

    private MyListener myListener;

    private List<Produit>produits;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            int user_id = 1;
            ProduitService bs = new ProduitService();
            produits = bs.getAllData();
            pag.setPageCount((int) Math.ceil(produits.size() / 1.0)); // Nombre total de pages nécessaire pour afficher toutes les cartes
            pag.setPageFactory(pageIndex -> {
                GridPane grid = new GridPane(); // Utilisez GridPane au lieu de HBox
                grid.setAlignment(Pos.CENTER);
                grid.setHgap(10);
                grid.setVgap(10);
                int itemsPerPage = 2; // Nombre des produits à afficher par page
                int page = pageIndex * itemsPerPage;
                for (int i = page; i < Math.min(page + itemsPerPage, produits.size()); i++) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/ProdCardFront.fxml"));
                        AnchorPane anchorPane = fxmlLoader.load();
                        anchorPane.getStyleClass().add("ct");
                        ProdCardFrontController cardController = fxmlLoader.getController();
                        cardController.setData(produits.get(i), myListener);
                        grid.add(anchorPane, (i - page) % itemsPerPage, (i - page) / itemsPerPage); // Ajoutez à GridPane
                    } catch (IOException ex) {
                        Logger.getLogger(listBoutiqueCardFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                return grid; // Retourne GridPane
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


