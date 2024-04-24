package com.example.demo;

import Entities.DemandeTroc;
import Entities.PostTroc;
import Interfaces.DemandetrocService;
import Services.DemnadeTrocService;
import Services.PostTrocService;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AlldemandesController implements Initializable {

    private static PostTroc postTroc;

    @FXML
    private Button para;

    @FXML
    private Button close;

    @FXML
    private AnchorPane paneslide;

    @FXML
    private GridPane offres;

    DemandetrocService demandetrocService=new DemnadeTrocService();
    //ObservableList<DemandeTroc> demandeTrocList = (ObservableList<DemandeTroc>) demandetrocService.getDemandeByPostid(getPost().getId());

    ObservableList<DemandeTroc> ListeData= FXCollections.observableArrayList();

    @FXML
    void runn2(MouseEvent event) {
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
    @FXML
    public void troc(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"
            HelloController controller = loader.getController();

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
            mainStage.setWidth(screenWidth-1);
            mainStage.setHeight(screenHeight);

            // Définir la nouvelle scène sur la fenêtre principale
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public void menuDisplay(PostTroc postTroc) {
        System.out.println(offres + "menu");
        ListeData.clear();
        ListeData.addAll(demandetrocService.getDemandesByPostid(postTroc.getId()));
        System.out.println("les demandes  :  " + demandetrocService.getDemandeByPostid(postTroc.getId()));

        int row = 0;
        int column = 0;
        for (int q = 0; q < ListeData.size(); q++) {
            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("cardindemandes.fxml"));
                AnchorPane pane = load.load();
                cardindemandesController menuu = load.getController();

                menuu.setDemandeTroc(ListeData.get(q));
                System.out.println(ListeData.get(q) + "list ALL 113");
                menuu.postdata();

                if (column == 1) {
                    column = 0;
                    row += 1;
                }
                offres.add(pane, column++, row);
            } catch (IOException e) {
                // Gérer l'exception IOException
                e.printStackTrace();
            } catch (NullPointerException e) {
                // Gérer l'exception NullPointerException
                e.printStackTrace();
            }
        }
    }

    @FXML
    void runn1(MouseEvent event) {
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


    public void setPostTroc(PostTroc posttroc) {

        this.postTroc = posttroc;
        System.out.println(this.postTroc+"set");

    }
    public PostTroc getPostTroc() {
        System.out.println(postTroc+"get");

        return postTroc;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paneslide.setTranslateX(+200);
        close.setVisible(false);
        para.setVisible(true);
       //System.out.println(getPostTroc());
        menuDisplay(postTroc);
        System.out.println(offres+"m");

    }
}
