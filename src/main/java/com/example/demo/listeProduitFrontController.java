package com.example.demo;

import Entities.Produit;
import controllers.ProdCardFrontController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import Services.ProduitService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class listeProduitFrontController implements Initializable {

    @FXML
    private Button btnAjouterBoutique;
    @FXML
    private AnchorPane listProduitFront;
    @FXML
    private TextField searchbar_id;
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
        loadAllProducts();
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
                        fxmlLoader.setLocation(getClass().getResource("ProdCardFront.fxml"));
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

        }


        catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadAllProducts() {
        try {
            ProduitService bs = new ProduitService();
            produits = bs.getAllData();
            displayProducts(produits);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void displayProducts(List<Produit> productList) {
        pag.setPageCount((int) Math.ceil(productList.size() / 1.0));
        pag.setPageFactory(pageIndex -> {
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            grid.setVgap(10);
            int itemsPerPage = 2;
            int page = pageIndex * itemsPerPage;
            for (int i = page; i < Math.min(page + itemsPerPage, productList.size()); i++) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("ProdCardFront.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    anchorPane.getStyleClass().add("ct");
                    ProdCardFrontController cardController = fxmlLoader.getController();
                    cardController.setData(productList.get(i), myListener);
                    grid.add(anchorPane, (i - page) % itemsPerPage, (i - page) / itemsPerPage);
                } catch (IOException ex) {
                    Logger.getLogger(listBoutiqueCardFrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return grid;
        });}
    @FXML
    private void searchProduct(ActionEvent event) {
        String keyword = searchbar_id.getText().toLowerCase().trim();
        List<Produit> filteredList = produits.stream()
                .filter(produit -> produit.getTitre().toLowerCase().contains(keyword))
                .collect(Collectors.toList());
        displayFilteredProducts(filteredList);
    }

    private void displayFilteredProducts(List<Produit> filteredList) {
        pag.setPageCount((int) Math.ceil(filteredList.size() / 1.0));
        pag.setPageFactory(pageIndex -> {
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            grid.setVgap(10);
            int itemsPerPage = 2;
            int page = pageIndex * itemsPerPage;
            for (int i = page; i < Math.min(page + itemsPerPage, filteredList.size()); i++) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("ProdCardFront.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    anchorPane.getStyleClass().add("ct");
                    ProdCardFrontController cardController = fxmlLoader.getController();
                    cardController.setData(filteredList.get(i), myListener);
                    grid.add(anchorPane, (i - page) % itemsPerPage, (i - page) / itemsPerPage);
                } catch (IOException ex) {
                    Logger.getLogger(listBoutiqueCardFrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return grid;
        });
    }

    @FXML
    private void refreshData() {

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
                        fxmlLoader.setLocation(getClass().getResource("ProdCardFront.fxml"));
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

    public void previous(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listBoutiqueFront.fxml"));
        // 'this' faisant référence à l'instance du contrôleur
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Liste des Boutiques");
        stage.show();

    }
}


