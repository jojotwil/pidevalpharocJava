package com.example.demo;

import Entities.PostTroc;
import Services.PostTrocService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
public class HelloController implements Initializable {
    @FXML
    private Button close;

    @FXML
    private Button para;

    @FXML
    private AnchorPane paneslide;
    @FXML
    private ScrollPane menu;

    @FXML
    private GridPane offre;
    @FXML
    private AnchorPane menu_form;
    PostTrocService postTrocService=new PostTrocService();
    ObservableList<PostTroc> Liste=postTrocService.getAllpostes();
    ObservableList<PostTroc> ListeData= FXCollections.observableArrayList();

    @FXML
    void run2(MouseEvent event) {
        TranslateTransition slide =new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(paneslide);
        slide.setToX(+200);
        slide.play();
        paneslide.setTranslateX(0);
        slide.setOnFinished((ActionEvent e) -> {
            para.setVisible(true);
            close.setVisible(false);

        });
    }
    public void menuDisplay(){
        ListeData.clear();
        ListeData.addAll(postTrocService.getAllpostes());
        int row=0;
        int column=0;
        offre.getRowConstraints().clear();
        offre.getColumnConstraints().clear();
        for (int q=0;q<ListeData.size();q++){
            try {
                FXMLLoader load= new FXMLLoader();
                load.setLocation(getClass().getResource("menu.fxml"));
                AnchorPane pane=load.load();
                MenuController menuu=load.getController();
                menuu.postdata(ListeData.get(q));

                if(column ==5){
                    column=0;
                    row+=1;
                }

                offre.add(pane,column++,row);
            }catch (Exception e){
                e.printStackTrace();
            }


        }

    }
    @FXML
    void run1(MouseEvent event) {
        TranslateTransition slide =new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(paneslide);
        slide.setToX(0);
        slide.play();
        paneslide.setTranslateX(+200);
        slide.setOnFinished((ActionEvent e) -> {
            para.setVisible(false);
            close.setVisible(true);

        });
    }
    @FXML
    public void troc(ActionEvent event) {
        // Récupérer le post sélectionné

        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"
            HelloController controller = loader.getController();
            //System.out.println(postTroc);

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
            mainStage.setWidth(screenWidth-1);

            mainStage.setHeight(screenHeight);

            // Définir la nouvelle scène sur la fenêtre principale
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paneslide.setTranslateX(+200);
        close.setVisible(false);
        para.setVisible(true);
        menuDisplay();
    }
}