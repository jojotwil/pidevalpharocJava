package com.example.demo;
import Entities.PostTroc;
import Services.PostTrocService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Chartjscontroller implements Initializable {
    @FXML
    private PieChart pieChart;


    /*public void initialize() {
        // Récupérer les données des véhicules à partir de PostTroc
        ObservableList<PostTroc> postTrocs = PostTrocService.getAllposte();

        // Créer une map pour stocker le nombre de véhicules par catégorie
        Map<String, Integer> vehicleCounts = new HashMap<>();
        for (PostTroc postTroc : postTrocs) {
            String vehicleType = postTroc.getCategorievehicule();
            vehicleCounts.put(vehicleType, vehicleCounts.getOrDefault(vehicleType, 0) + 1);
        }

        // Créer une liste pour les catégories
        List<String> categories = new ArrayList<>(vehicleCounts.keySet());

        // Créer un tableau de valeurs
        double[] values = new double[categories.size()];
        for (int i = 0; i < categories.size(); i++) {
            values[i] = vehicleCounts.get(categories.get(i));
        }

        // Créer le graphique
        CategoryChart chart = new CategoryChartBuilder()
                .width(800)
                .height(600)
                .title("Statistiques sur les types de véhicules")
                .xAxisTitle("Catégories")
                .yAxisTitle("Nombre de véhicules")
                .build();

        // Ajouter les données au graphique
        chart.addSeries("Nombre de véhicules", categories, values);
        chart.

        // Créer un panneau XChartPanel pour afficher le graphique
        XChartPanel<CategoryChart> chartPanel = new XChartPanel<>(chart);

        // Ajouter le panneau de graphique à votre interface utilisateur
        chartContainer.getChildren().add(chartPanel);
    }*/
    public void user(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource("admindashboard.fxml"));
            Parent root = loader.load();
            TrocbackController adminDashboardController = loader.getController();

            // Set any necessary data or parameters for the controller

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Admin Dashboard");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void calculateStatistics() {
        ObservableList<PostTroc> data = PostTrocService.getAllposte();
        Map<String, Integer> stats = new HashMap<>();

        for (PostTroc post : data) {
            String category = post.getCategorievehicule();
            stats.put(category, stats.getOrDefault(category, 0) + 1);
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }

        pieChart.setData(pieChartData);
    }

    // Your existing code


   /* @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            // Récupérer les données des véhicules à partir de PostTroc
            List<PostTroc> postTrocs = PostTrocService.getAllposte();

            // Créer une map pour stocker le nombre de véhicules par catégorie
            Map<String, Integer> vehicleCounts = new HashMap<>();
            for (PostTroc postTroc : postTrocs) {
                String vehicleType = postTroc.getCategorievehicule();
                vehicleCounts.put(vehicleType, vehicleCounts.getOrDefault(vehicleType, 0) + 1);
            }
        new PieChart.Data(entry.getKey(), entry.getValue())


        // Créer une liste observable pour les données du graphique
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

            // Ajouter les catégories et leurs valeurs au graphique
            for (Map.Entry<String, Integer> entry : vehicleCounts.entrySet()) {
                pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
            }

            // Ajouter les données au PieChart
            pieChart.setData(pieChartData);
            pieChart.set

    }*/


    @FXML
    public void trocs(ActionEvent event){
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("trocback.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"

            // Créer une nouvelle scène avec le nouveau contenu
            Scene scene = new Scene(newContent);

            // Obtenir la fenêtre principale (stage)
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Obtenir les dimensions de l'écran
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            // Obtenez les dimensions de l'écran
            Screen screen = Screen.getPrimary();
            double screenWidth = screen.getBounds().getWidth();
            double screenHeight = screen.getBounds().getHeight();

// Définissez la taille de la fenêtre sur les dimensions de l'écran
            mainStage.setWidth(screenWidth);
            mainStage.setHeight(screenHeight);

            // Définir la nouvelle scène sur la fenêtre principale
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void chart(ActionEvent event){
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chartjs.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"

            // Créer une nouvelle scène avec le nouveau contenu
            Scene scene = new Scene(newContent);

            // Obtenir la fenêtre principale (stage)
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Obtenir les dimensions de l'écran
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            // Obtenez les dimensions de l'écran
            Screen screen = Screen.getPrimary();
            double screenWidth = screen.getBounds().getWidth();
            double screenHeight = screen.getBounds().getHeight();

// Définissez la taille de la fenêtre sur les dimensions de l'écran
            mainStage.setWidth(screenWidth);
            mainStage.setHeight(screenHeight);

            // Définir la nouvelle scène sur la fenêtre principale
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


        public void logout(ActionEvent actionEvent) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource("authentifier.fxml"));
                Parent root = loader.load();
                TrocbackController adminDashboardController = loader.getController();

                // Set any necessary data or parameters for the controller

                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setTitle("Admin Dashboard");
                stage.setScene(new Scene(root, 1000, 600));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        calculateStatistics();
    }
}
