package controllers;

import entities.Commande;
import entities.Produit;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;

public class CommandeDetailsWindow {



    public void display(Commande commande) {
        Stage window = new Stage();
        window.setTitle("Détails de la commande");
        window.setMinWidth(800);
        window.setMinHeight(600);

        VBox layout = new VBox();
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(70));
        layout.setSpacing(70);

        GridPane content = new GridPane();
        content.setAlignment(Pos.TOP_CENTER);
        content.setHgap(10);
        content.setVgap(10);

        Label nomLabel = new Label("          Commande");
        BorderPane.setAlignment(nomLabel, Pos.TOP_CENTER);
        nomLabel.getStyleClass().add("textme");
        content.add(nomLabel, 0, 1);

        Label titleLabel = new Label("Votre Commande est Ajoutée ");
        titleLabel.getStyleClass().add("text-style");
        titleLabel.getStyleClass().add("fade-out");
        BorderPane.setAlignment(titleLabel, Pos.TOP_LEFT);
        content.add(titleLabel, 0, 0);

        // Animation pour faire disparaître le texte après 3 secondes
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(3), new KeyValue(titleLabel.opacityProperty(), 0))
        );
        timeline.play();

        Label idLabel = new Label("ID Commande: " + commande.getCommande_id());
        idLabel.setVisible(false);
        content.add(idLabel, 0, 2);

        Label titreLabel;
        Label prixLabel;
        Collection<Produit> produits = commande.getProduits();

        if (produits != null && !produits.isEmpty()) {
            Produit premierProduit = produits.iterator().next();
            titreLabel = new Label("     Produit :   " + premierProduit.getTitre());
            titreLabel.getStyleClass().add("text-style1");
            BorderPane.setAlignment(titreLabel, Pos.TOP_CENTER);
            content.add(titreLabel, 0, 3);

            prixLabel = new Label("     Prix       :   " + premierProduit.getPrix());
            prixLabel.getStyleClass().add("text-style1");
            BorderPane.setAlignment(prixLabel, Pos.TOP_CENTER);

            content.add(prixLabel, 0, 4);


            prixLabel = new Label("     Prix       :   " + premierProduit.getPrixAvecRemise());


            ImageView imageView = new ImageView();
            if (premierProduit.getImage() != null) {
                InputStream imageStream = getClass().getResourceAsStream("/uploads/" + premierProduit.getImage());
                if (imageStream != null) {
                    Image image = new Image(imageStream);
                    imageView.setImage(image);

                    imageView.setFitWidth(200);
                    imageView.setFitHeight(200);
                    content.add(imageView, 0, 5);
                    BorderPane.setAlignment(imageView, Pos.TOP_CENTER);
                    BorderPane.setMargin(imageView, new Insets(20, 0, 0, 0));
                }
            }
        }



        Button payerButton = new Button("Payer");
        payerButton.getStyleClass().add("pay-button");
        payerButton.setOnAction(event -> payerCommande(commande));
        BorderPane.setAlignment(payerButton, Pos.TOP_CENTER);
        BorderPane.setMargin(payerButton, new Insets(80, 10, 0, 0));


        layout.getChildren().addAll(content, payerButton);

        StackPane root = new StackPane();
        root.getChildren().add(layout);
        StackPane.setAlignment(layout, Pos.CENTER);

        Scene scene = new Scene(root);
        window.setScene(scene);

        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        window.centerOnScreen();
        window.show();
    }

        private void payerCommande(Commande commande) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Payment.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage paymentStage = new Stage();
            paymentStage.setScene(scene);
            paymentStage.setTitle("Paiement");
            paymentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}