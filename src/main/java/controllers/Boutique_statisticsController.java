package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import services.BoutiqueService;


import java.net.URL;
import java.util.ResourceBundle;

public class Boutique_statisticsController implements Initializable {
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    private BoutiqueService bs = new BoutiqueService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set labels for x and y axes
        //xAxis.setLabel("Product Name");
       // yAxis.setLabel("Quantity");

        // Fetch data from the service
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("Product Statistics");

        // Populate the series with data
        try {
            series.getData().addAll(bs.chartBoutiqueStatistics());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add series to the chart
        barChart.getData().add(series);
    }
}
