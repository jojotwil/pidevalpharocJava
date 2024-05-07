package com.example.demo;


import Entities.Boutique;
import Services.ProduitService;
import Tools.MyConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Boutique_statisticsController implements Initializable {
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    private ProduitService ps = new ProduitService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set labels for x and y axes
        xAxis.setLabel("Boutique");
        yAxis.setLabel("Nombre de produits");

        // Fetch data from the service
        List<Boutique> boutiques = getAllBoutiques(); // Supposons que vous ayez une méthode pour récupérer toutes les boutiques

        // Populate the chart with data for each boutique
        for (Boutique boutique : boutiques) {
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            series.setName(boutique.getNom()); // Assurez-vous d'avoir une méthode getNom() dans votre classe Boutique

            int numberOfProducts = ps.countProductsInBoutique(boutique.getId());
            series.getData().add(new XYChart.Data<>(boutique.getNom(), numberOfProducts));

            barChart.getData().add(series);
        }
    }

    // Méthode fictive pour obtenir toutes les boutiques, à remplacer par votre propre logique d'accès aux données
    private List<Boutique> getAllBoutiques() {
        List<Boutique> boutiques = new ArrayList<>();
        String query = "SELECT * FROM boutique"; // Assurez-vous que la table s'appelle "boutique" dans votre base de données
        try {
            PreparedStatement pst = MyConnection.getInstace().getCnx().prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Boutique boutique = new Boutique();
                boutique.setId(rs.getInt("id"));
                boutique.setNom(rs.getString("nom"));
                // Vous pouvez également définir d'autres attributs de la boutique si nécessaire
                boutiques.add(boutique);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return boutiques;
    }

}
