package controllers;



import Entities.Boutique;
import Entities.Commande;
import Entities.Produit;
import com.example.demo.CommandeDetailsWindow;
import com.example.demo.DetailsProduitController;
import com.example.demo.MyListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import Tools.MyConnection;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;


public class ProdCardFrontController implements Initializable {

    @FXML
    private Label labelTitreProd;
    @FXML
    private Label labelTitrePrixRemise;

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
        System.out.println(produit.getImage());
        try {
            imagee = new Image( produit.getImage());
            System.out.println(produit.getImage());
            imgProd.setImage(imagee);

        } catch (Exception e) {
            // Gérer l'erreur, par exemple afficher un message d'erreur ou une image par défaut
            System.out.println("Erreur lors du chargement de l'image : " + e.getMessage());
        }
    }

    // Méthode pour traduire le titre du produit



    private List<Commande> commandes;

    public List<Commande> getCommandes() {
        return this.commandes;
    }

Commande commande;


    @FXML
    public void ajouterACommande(ActionEvent event) {
        // Créez une nouvelle instance de Commande sans spécifier l'ID
        Commande c = new Commande();

        Produit produit = prod;

        // Affichez le titre et le prix juste après leur définition pour vérifier les valeurs
        System.out.println("Titre du produit : " + produit.getTitre());
        System.out.println("Prix du produit : " + produit.getPrix());
        c.addProduit(produit);

        // Maintenant, vous pouvez récupérer le titre et le prix du produit
        String titreProduit = produit.getTitre();
        float prixProduit = produit.getPrix();


        // Affichez à nouveau le titre et le prix pour vérifier s'ils ont été correctement récupérés
        System.out.println("Titre du produit récupéré : " + titreProduit);
        System.out.println("Prix du produit récupéré : " + prixProduit);

        // Ici, vous insérez une nouvelle commande sans spécifier de détails de produit
        String requete = "INSERT INTO commande () VALUES ()";
        try {
            PreparedStatement pst = MyConnection.getInstace().getCnx().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                c.setCommande_id(generatedId);

                // Ouvrir une nouvelle fenêtre et afficher les détails de la commande
                CommandeDetailsWindow detailsWindow = new CommandeDetailsWindow();
                detailsWindow.display(c);
            }
            System.out.println("Commande ajoutée");

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la commande : " + e.getMessage());
        }
    }


    @FXML
    void showDetails(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/DetailsProduit.fxml"));
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
    private Image imagee;

}
