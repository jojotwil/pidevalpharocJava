package com.example.demo;

import Entities.*;
import Services.PostTrocService;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PosttroccrudController implements Initializable {

    @FXML
    private TextField annee;

    @FXML
    private TextField matricule;

    @FXML
    private ChoiceBox<String> marque;
    private String[] marques={"abarth", "audi", "bentley", "bmw", "bugatti", "buick", "cadillac", "chevrolet", "chrysler", "citroen", "dodge", "ferrari", "fiat", "ford", "genesis", "gmc", "Alfa_Romeo", "AstonMartin", "LandRover", "MercedesBenz", "RollsRoyce", "honda", "hyundai", "infiniti", "jaguar", "jeep", "kia", "koenigsegg", "lamborghini", "lexus", "lincoln", "lotus", "maserati", "mazda", "mclaren", "mini", "mitsubishi", "nissan", "pagani", "peugeot", "porsche", "ram", "renault", "saab", "subaru", "suzuki", "tesla", "toyota", "volkswagen", "volvo"};

    @FXML
    private ChoiceBox<String> model;
    private String[] models={"A1", "A3", "A4", "A5", "A6", "A7", "A8", "Q2", "Q3", "Q5", "Q7", "Q8", "R8", "TT", "i3", "i4", "iX", "M2", "M3", "M4", "M5", "M6", "X1", "X2", "X3", "X4", "X5", "X6", "X7", "Z4", "Cullinan", "Phantom", "Ghost", "Wraith", "Aventador", "Huracán", "Urus", "Defender", "CT", "ES", "GS", "IS", "LC", "LS", "LX", "NX", "RC", "RX", "UX", "Bentayga", "Mulsanne", "Escalade", "CT4", "CT5", "XT4", "XT5", "XT6", "Cascada", "Enclave", "Encore", "Envision", "Camaro", "Corvette", "Blazer", "Equinox", "Malibu", "Silverado", "Tahoe", "Trailblazer", "Traverse", "Trax", "Challenger", "Charger", "Durango", "Journey", "Portofino", "X500", "L500", "Tipo", "Fiesta", "Focus", "Mustang", "Bronco", "Escape", "Expedition", "Explorer", "SérieF", "Ranger", "Edge", "EcoSport", "Flex", "Fusion", "GT", "Civic", "Accord", "Pilot", "Odyssey", "Passport", "Ridgeline", "Sonata", "Elantra", "Ioniq", "Kona", "Nexo", "Palisade", "Tucson", "Venue", "Q50", "Q60", "Q70", "QX50", "QX60", "QX80", "XE", "XF", "XJ", "Sprinter", "Mini", "Clubman", "Countryman", "Outlander", "Pajero", "Lancer", "Rogue", "Altima", "Maxima", "Armada", "Kicks", "Titan", "Frontier", "GTR", "Taycan", "Panamera", "Cayenne", "Macan", "Tiguan", "Atlas", "Golf", "Passat", "Jetta", "Arteon", "XC40", "XC60", "XC90", "FPace", "ePace", "iPace", "gClass", "glClass", "gleClass", "glcClass", "glkClass", "aClass", "bClass", "cClass", "eClass", "sClass", "slClass", "slkClass", "claClass", "clsClass", "amgGt", "santaFe", "crV", "hrV", "corvetteStingray", "Series1", "Series2", "Series3", "Series4", "Series5", "Series6", "Series7", "Series8", "Discovery", "range_rover", "range_rover_sport", "range_rover_evoque", "RangeRoverVelar", "continentalGt", "flyingSpur", "sf90Stradale", "grandCaravan", "f8Tributo", "eclipseCross"};

    @FXML
    private ChoiceBox<String> typedeboitevitesse;
    private String[] typesdeboitesvitesse={ "Automatique" ,"Manuelle"};
    @FXML
    private ChoiceBox<String> typevehicule;
    private String[] typesdevehicule={"voiture", "moto", "yacht", "velo", "trottinette"};

    @FXML
    private ChoiceBox<String> typedecarburant;
    private String[] typesdecarburant={ "essence", "diesel", "E85", "GNC", "GPL", "hydrogene"};

    @FXML
    private TextField description;

    @FXML
    private TextField mail;

    @FXML
    private TextField kilometrage;

    @FXML
    private TextField image;

    @FXML
    private TextField localisation;
    @FXML
    private Button btnnew;

    @FXML
    private Button btnsave;

    @FXML
    private Button btnupdate;

    @FXML
    private Button btndelete;
    @FXML
    private Button show;
    @FXML
    private Button close;

    @FXML
    private Button para;

    @FXML
    private AnchorPane paneslide;
    @FXML
    private TableView<PostTroc> tableView;
    @FXML
    private TableColumn<PostTroc,Integer> idtable;
    @FXML
    private TableColumn<PostTroc,String> Modeltable;
    @FXML
    private TableColumn<PostTroc,String> Marquetable;
    @FXML
    private TableColumn<PostTroc,String> Matriculetable;
    @FXML
    private TableColumn<PostTroc,String> typedevehiculetable;
    @FXML
    private TableColumn<PostTroc,String> descriptionstable;
    @FXML

    private TableColumn<PostTroc,Integer> kilometragestable;
    private PostTroc postTroc;

    @FXML

    public void addposttroc(){

        PostTroc postTroc=new PostTroc(new Date(),Integer.parseInt(kilometrage.getText()),description.getText(),localisation.getText(),image.getText(), mail.getText(), matricule.getText(), marque.getValue(),model.getValue(),typedecarburant.getValue(),typevehicule.getValue(),typedeboitevitesse.getValue());
        PostTrocService postTrocService=new PostTrocService();
        postTrocService.addPost(postTroc);
    }
    @FXML
    public void showpoststroc(){
        PostTrocService postTrocService=new PostTrocService();
        ObservableList<PostTroc> Liste=postTrocService.getAllpostes();
        idtable.setCellValueFactory(new PropertyValueFactory<PostTroc,Integer>("id"));
        kilometragestable.setCellValueFactory(new PropertyValueFactory<PostTroc,Integer>("kilometrage"));
        descriptionstable.setCellValueFactory(new PropertyValueFactory<PostTroc,String>("description"));
        Marquetable.setCellValueFactory(new PropertyValueFactory<PostTroc,String>("marque"));
        Modeltable.setCellValueFactory(new PropertyValueFactory<PostTroc,String>("modele"));
        Matriculetable.setCellValueFactory(new PropertyValueFactory<PostTroc,String>("matricule"));
        typedevehiculetable.setCellValueFactory(new PropertyValueFactory<PostTroc,String>("categorievehicule"));
        tableView.setItems( Liste);

    }
    @FXML
    public void mouseClicked(MouseEvent e){
        try {
            PostTroc postTroc=tableView.getSelectionModel().getSelectedItem();
            postTroc=new PostTroc(postTroc.getAnnee(),postTroc.getKilometrage(),postTroc.getDescription(),postTroc.getLocalisation(),postTroc.getImage(),postTroc.getMail(),postTroc.getMatricule(),postTroc.getMarque(),postTroc.getModele(),postTroc.getTypecarburant(),postTroc.getCategorievehicule(),postTroc.getTypeboitevitesse());
            this.postTroc=postTroc;
            kilometrage.setText(""+postTroc.getKilometrage());
            description.setText(postTroc.getDescription());
            matricule.setText(postTroc.getMatricule());
            annee.setText(""+postTroc.getAnnee());
            mail.setText(postTroc.getMail());
            image.setText(postTroc.getImage());
            localisation.setText(postTroc.getLocalisation());
            marque.setValue(postTroc.getMarque());
            // Si vous voulez sélectionner un modèle spécifique, vous pouvez définir sa valeur sélectionnée
            model.setValue(postTroc.getModele());
            typevehicule.setValue(postTroc.getCategorievehicule());
            typedeboitevitesse.setValue(postTroc.getTypeboitevitesse());
            typedecarburant.setValue(postTroc.getTypecarburant());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void mouseClicked(PostTroc troc) {
        try {
            this.postTroc=troc;
            if (postTroc != null) {
                kilometrage.setText(""+postTroc.getKilometrage());
                description.setText(postTroc.getDescription());
                matricule.setText(postTroc.getMatricule());
                annee.setText(""+postTroc.getAnnee());
                mail.setText(postTroc.getMail());
                image.setText(postTroc.getImage());
                localisation.setText(postTroc.getLocalisation());
                marque.setValue(postTroc.getMarque());
                model.setValue(postTroc.getModele());
                typevehicule.setValue(postTroc.getCategorievehicule());
                typedeboitevitesse.setValue(postTroc.getTypeboitevitesse());
                typedecarburant.setValue(postTroc.getTypecarburant());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void updatepost(){
        try {


            PostTrocService postTrocService=new PostTrocService();
            PostTroc postTroc=new PostTroc(this.postTroc.getId(),new Date(),Integer.parseInt(kilometrage.getText()),description.getText(),localisation.getText(),image.getText(), mail.getText(), matricule.getText(), marque.getValue(),model.getValue(),typedecarburant.getValue(),typevehicule.getValue(),typedeboitevitesse.getValue());
            postTrocService.updatePost(postTroc);

            btndelete.setDisable(true);
            btnupdate.setDisable(true);
            }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void deletepost(){
        try {
           // PostTroc postTrocid=tableView.getSelectionModel().getSelectedItem();

            PostTrocService postTrocService=new PostTrocService();
// Supposons que kilometrage.getText() récupère une valeur de type String représentant le kilométrage
            String kilometrageText = kilometrage.getText();

            try {
                // Convertissez la chaîne en double
                double kilometrageValue = Double.parseDouble(kilometrageText);

                // Créez l'objet PostTroc avec le kilométrage converti en double
                PostTroc postTroc = new PostTroc(
                        this.postTroc.getId(),
                        new Date(),
                        kilometrageValue, // Utilisez le kilométrage converti en double
                        description.getText(),
                        localisation.getText(),
                        image.getText(),
                        mail.getText(),
                        matricule.getText(),
                        marque.getValue(),
                        model.getValue(),
                        typedecarburant.getValue(),
                        typevehicule.getValue(),
                        typedeboitevitesse.getValue()
                );
            } catch (NumberFormatException e) {
                // Gérez l'exception si la chaîne n'est pas un nombre valide
                System.out.println("La chaîne n'est pas un nombre valide.");
                e.printStackTrace();
            }
           System.out.println(this.postTroc.getId());
            postTrocService.deletePost(this.postTroc);
            btndelete.setDisable(true);
            btnupdate.setDisable(true);
            }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void modifier(ActionEvent event) {
        // Récupérer le post sélectionné

        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("posttroccrud.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"
            PosttroccrudController controller = loader.getController();
            //System.out.println(postTroc);

            // Envoyer le post au contrôleur


            // Remplacer le contenu actuel de la fenêtre par le nouveau contenu
            AnchorPane root = (AnchorPane) ((Node) event.getSource()).getScene().getRoot();
            root.getChildren().setAll(newContent);
        } catch (IOException e) {
            e.printStackTrace();
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




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showpoststroc();
        marque.getItems().addAll(marques);
        model.getItems().addAll(models);
        typedeboitevitesse.getItems().addAll(typesdeboitesvitesse);
        typedecarburant.getItems().addAll(typesdecarburant);
        typevehicule.getItems().addAll(typesdevehicule);
       //System.out.println(postTroc);
        mouseClicked(this.postTroc);

    }
    public void setPostTroc(PostTroc posttroc){
        //System.out.println(posttroc);
        this.postTroc=posttroc;
    }
    public PostTroc getPostTroc(){
        //System.out.println(postTroc);

        return postTroc;
    }
}
