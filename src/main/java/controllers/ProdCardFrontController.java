package controllers;
import entities.Commande;
import entities.Produit;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.ProduitService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tools.MyConnexion;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class ProdCardFrontController implements Initializable {

    @FXML
    private Label labelTitreProd;

    @FXML
    private Button btnAjouterCommande;

    @FXML
    private Label labelPrixProd;

    @FXML
    private ImageView imgProd;
    @FXML
    private Button
    btnDetailsBoutique ;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    Produit prod;
    MyListener myListener;

    public void setData (Produit produit, MyListener myListener){
        this.prod = produit ;
        this.myListener = myListener;

        labelTitreProd.setText(produit.getTitre());
        labelPrixProd.setText(String.valueOf(produit.getPrix()));
        if (produit.getImage() != null) {
            InputStream imageStream = getClass().getResourceAsStream("/uploads/" + produit.getImage());
            if (imageStream != null) {
                imgProd.setImage(new Image(imageStream));
            }
        }
    }



    public void ajouterACommande(ActionEvent event) {
        Produit p = new Produit();
        Commande c = new Commande(6);

        String requete = "INSERT INTO commande (id) VALUES (?)"; // Remplacez "champs" par les champs appropriés
        try {
            PreparedStatement pst = MyConnexion.getInstance().getCnx().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);

            // Remplacez les ? par les valeurs appropriées
            pst.setInt(1, c.getCommande_id());

            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                c.setCommande_id(rs.getInt(1));
            }
            System.out.println("Commande added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void showDetails(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DetailsProduit.fxml"));
            Parent root = loader.load();
            DetailsProduitController controller = loader.getController();
            controller.setProduit(prod);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Détails du Produit : " + prod.getTitre());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Gérer l'erreur de chargement de la vue DetailsBoutique.fxml
        }
    }
}
