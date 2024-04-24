package com.example.demo;

import Entities.DemandeTroc;
import Entities.PostTroc;
import Interfaces.DemandetrocService;
import Services.DemnadeTrocService;
import Services.PostTrocService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class cardindemandesController implements Initializable {
    @FXML
    private ImageView image;


    @FXML
    private Label marque;

    @FXML
    private Label model;

    @FXML
    private Label carburant;

    @FXML
    private Label boite;

    @FXML
    private Label description;

    @FXML
    private Button Accepter;

    @FXML
    private Button Refuser;
    private Image imagee;
    private DemandeTroc DemandeTroc;

    public void setDemandeTroc(Entities.DemandeTroc demandeTroc) {
        if (demandeTroc != null) {
            DemandeTroc = demandeTroc;
            System.out.println(demandeTroc + "in set");
        } else {
            // Gérer le cas où demandeTroc est null
            System.out.println("La demandeTroc passée à setDemandeTroc est null");
        }
    }

    public Entities.DemandeTroc getDemandeTroc() {
        return DemandeTroc;
    }

    @FXML
    private AnchorPane anchorpane;

    public void postdata() {
        if (this.DemandeTroc != null) {
            System.out.println(getDemandeTroc() + "gettt");

            System.out.println(this.DemandeTroc + "demande");
            model.setText(this.DemandeTroc.getModele());
            marque.setText(this.DemandeTroc.getMarque());
            carburant.setText(this.DemandeTroc.getTypedecarburant());
            boite.setText(this.DemandeTroc.getTypeboitevitesse());
            description.setText(this.DemandeTroc.getDescription());
            imagee = new Image("file:" + this.DemandeTroc.getImage(), 190, 94, false, true);
            image.setImage(imagee);
        } else {
            // Gérer le cas où DemandeTroc est null
            System.out.println("DemandeTroc est null dans postdata()");
        }
    }


    @FXML
    public void deletepost(){
        try {
            DemandeTroc demandeTroc=this.DemandeTroc;
            DemandetrocService demandetrocService=new DemnadeTrocService();
            System.out.println(this.DemandeTroc.getId());
            demandetrocService.deleteDemande(demandetrocService);
        }catch (Exception e){
            e.printStackTrace();
        }
    }








    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    postdata();
    }
}
