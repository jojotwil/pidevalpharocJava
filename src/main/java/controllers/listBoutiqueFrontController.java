package controllers;

import entities.Boutique;
import javafx.animation.TranslateTransition;
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
import javafx.util.Duration;
import services.BoutiqueService;
import com.jfoenix.controls.JFXButton;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class listBoutiqueFrontController implements Initializable {

    @FXML
    private Label MenuClose;

    @FXML
    private Label Menu;
    @FXML
    private AnchorPane slider;

    @FXML
    private JFXButton boutonBoutiques;

    @FXML
    private JFXButton boutonProduits ;

    @FXML
    private Button btnAjouterBoutique;
    @FXML
    private JFXButton refreshData;
    @FXML
    private AnchorPane listProduitFront;


    @FXML
    private HBox hbox;

    @FXML
    private HBox vbox;

    @FXML
    private GridPane grid;

    @FXML
    private Pagination pag;

    @FXML
    private Button btnListeProd ;

    private MyListener myListener;

private List<Boutique>boutiques;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        slider.setTranslateX(-176);
        Menu.setOnMouseClicked(event -> {
            System.exit(0);
        });
        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(0);
            slide.play();
            slider.setTranslateX(-176);
            slide.setOnFinished((ActionEvent e)->{
                Menu.setVisible(false);
                MenuClose.setVisible(true);

            });});
        MenuClose.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition(Duration.seconds(0.4 ));
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(-176);
            slide.play();
            slider.setTranslateX(0);
            slide.setOnFinished((ActionEvent e)->{
                Menu.setVisible(true);
                MenuClose.setVisible(false);

            });});
        try {
            int user_id = 1;
            BoutiqueService bs = new BoutiqueService();
            boutiques = bs.getAllData();
            pag.setPageCount((int) Math.ceil(boutiques.size() / 2.0)); // Nombre total de pages nécessaire pour afficher toutes les cartes
            pag.setPageFactory(pageIndex -> {
                GridPane grid = new GridPane(); // Utilisez GridPane au lieu de HBox
                grid.setAlignment(Pos.CENTER);
                grid.setHgap(10);
                grid.setVgap(10);
                int itemsPerPage = 2; // Nombre des produits à afficher par page
                int page = pageIndex * itemsPerPage;
                for (int i = page; i < Math.min(page + itemsPerPage, boutiques.size()); i++) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/listBoutiqueCardFront.fxml"));
                        AnchorPane anchorPane = fxmlLoader.load();
                        anchorPane.getStyleClass().add("ct");
                        listBoutiqueCardFrontController itemController = fxmlLoader.getController();
                        itemController.setData(boutiques.get(i), myListener);
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
    @FXML
    private void refreshData() {
        try {
            BoutiqueService bs = new BoutiqueService();
            boutiques = bs.getAllData();
            pag.setPageCount((int) Math.ceil(boutiques.size() / 2.0)); // Nombre total de pages nécessaire pour afficher toutes les cartes
            pag.setPageFactory(pageIndex -> {
                GridPane grid = new GridPane(); // Utilisez GridPane au lieu de HBox
                grid.setAlignment(Pos.CENTER);
                grid.setHgap(10);
                grid.setVgap(10);
                int itemsPerPage = 2; // Nombre des produits à afficher par page
                int page = pageIndex * itemsPerPage;
                for (int i = page; i < Math.min(page + itemsPerPage, boutiques.size()); i++) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/listBoutiqueCardFront.fxml"));
                        AnchorPane anchorPane = fxmlLoader.load();
                        anchorPane.getStyleClass().add("ct");
                        listBoutiqueCardFrontController itemController = fxmlLoader.getController();
                        itemController.setData(boutiques.get(i), null); // Remplacer myListener par null car non utilisé ici
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

    @FXML
    private void ouvrirAjouterBoutique(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouterBoutique.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Ajouter Boutique");
        stage.show();
    }
    @FXML
    private void ouvrirAdminBoutiques(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/listBoutique.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Liste des Boutiques");
        stage.show();
    }

    @FXML
    void OuvrirListeProduit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListeProdFront.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Liste des Produits ");
        stage.show();
    }
    @FXML
    private void ouvrirAdminProduit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListeProduit.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Liste des Produits");
        stage.show();
    }

    public void ouvrirAdminCommande(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListCommande.fxml"));
        // 'this' faisant référence à l'instance du contrôleur
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Liste des Commandes");
        stage.show();
    }
}


