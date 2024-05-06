package com.example.demo;

import Entities.PostTroc;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class MarketController implements Initializable {
    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Label fruitNameLable;

    @FXML
    private Label fruitPriceLabel;

    @FXML
    private ImageView fruitImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<PostTroc> fruits = new ArrayList<>();
    private Image image;
    private MyListener myListener;

    private List<PostTroc> getData() {
        List<PostTroc> post = new ArrayList<>();
        PostTroc postTroc;

        postTroc = new PostTroc();
        postTroc.setDescription("hi");
        postTroc.setAnnee(new Date());
        postTroc.setCategorievehicule("voiture");
        postTroc.setKilometrage(1223);
        fruits.add(postTroc);
        postTroc = new PostTroc();
        postTroc.setDescription("hi");
        postTroc.setAnnee(new Date());
        postTroc.setCategorievehicule("voiture");
        postTroc.setKilometrage(1223);
        fruits.add(postTroc);


        postTroc = new PostTroc();
        postTroc.setDescription("hi");
        postTroc.setAnnee(new Date());
        postTroc.setCategorievehicule("voiture");
        postTroc.setKilometrage(1223);
        fruits.add(postTroc);

        return fruits;
    }

    private void setChosenFruit(PostTroc fruit) {
        fruitNameLable.setText(fruit.getCategorievehicule());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fruits.addAll(getData());


    }

}
