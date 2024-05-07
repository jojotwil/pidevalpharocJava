package controllers;

import entities.Boutique;
import entities.Commande;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.BoutiqueService;
import services.CommandeService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListCommandeController implements Initializable {
    @FXML
    private BorderPane listProduitPane;

    @FXML
    private VBox vBox;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int column=0;
        int row=0;


        try {
            CommandeService cs = new CommandeService();
            List<Commande> commandes = cs.getAllData();

            for(int i=0;i<commandes.size();i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ItemCommande.fxml"));

                try {
                    AnchorPane borderPan = fxmlLoader.load();
                    HBox hBox = (HBox) borderPan.getChildren().get(0);
                    ItemCommandeController itemController = fxmlLoader.getController();
                    itemController.setData(commandes.get(i));
                    vBox.getChildren().add(hBox);

                } catch (IOException ex) {
                    Logger.getLogger(itemBoutiqueController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
