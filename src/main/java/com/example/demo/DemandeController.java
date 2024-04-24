package com.example.demo;

import Entities.DemandeTroc;
import Entities.PostTroc;
import Interfaces.DemandetrocService;
import Services.DemnadeTrocService;
import Services.PostTrocService;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DemandeController implements Initializable {
    @FXML
    private Button close;

    @FXML
    private Button para;

    @FXML
    private AnchorPane paneslide;
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
    @FXML
    private TextField daterdv;

    @FXML
    private TextField heurerdv;

    @FXML
    private TextField message;

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
    public void adddemande() throws ParseException {
        System.out.println(postTroc + "  add");

        // Vérifier si tous les champs sont remplis
        if (!areAllFieldsFilled()) {
            showAlert(AlertType.ERROR, "Champs vides", "Veuillez remplir tous les champs.");
            return;
        }

        // Obtenir les valeurs depuis les champs de texte
        double kilometrageValue;
        try {
            kilometrageValue = Double.parseDouble(kilometrage.getText());
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Format invalide", "Le format du kilométrage n'est pas valide.");
            return;
        }
        String descriptionValue = description.getText();
        String imageValue = image.getText();
        String mailValue = mail.getText();
        String matriculeValue = matricule.getText();
        String marqueValue = marque.getValue();
        String modeleValue = model.getValue();
        String typedecarburantValue = typedecarburant.getValue();
        String categorievehiculeValue = typevehicule.getValue();
        String typeboitevitesseValue = typedeboitevitesse.getValue();

        // Obtenir la date actuelle
        String datetext = daterdv.getText();
        if (!isValidDateFormat(datetext)) {
            showAlert(AlertType.ERROR, "Format invalide", "Le format de la date n'est pas valide.Exemple 12-12-2024");
            return;
        }
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date dateRdv = sf.parse(datetext);

        // Récupérer la date à partir du champ de texte "annee"
        String anneetext = annee.getText();
        if (!isValidDateFormat(anneetext)) {
            showAlert(AlertType.ERROR, "Format invalide", "Le format de l'année n'est pas valide.Exemple 12-12-2024");
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = sdf.parse(anneetext);

        // Récupérer l'heure à partir du champ de texte "heurerdv"
        String heureText = heurerdv.getText();
        if (!isValidTimeFormat(heureText)) {
            showAlert(AlertType.ERROR, "Format invalide", "Le format de l'heure n'est pas valide.Exemple 12:12");
            return;
        }
        Time heureRdv = Time.valueOf(heureText + ":00"); // Ajouter les secondes si nécessaire
        // Vérification de l'heureRdv entre 7:00 et 23:00
        LocalTime heureRdvLocalTime = heureRdv.toLocalTime();
        if (heureRdvLocalTime.isBefore(LocalTime.of(7, 0)) || heureRdvLocalTime.isAfter(LocalTime.of(23, 0))) {
            showAlert(AlertType.ERROR, "Heure invalide", "L'heure du rendez-vous doit être comprise entre 7:00h du matin à 23:00h.");
            return;
        }
        // Convertir la Date en LocalDate
        LocalDate dateAsLocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dateRdvAsLocalDate = dateRdv.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Récupérer la date actuelle
        // Vérification que l'année est dans le passé
        LocalDate currentDate = LocalDate.now();
        if (dateAsLocalDate.isAfter(currentDate)) {
            showAlert(AlertType.ERROR, "Date invalide", "La date de mise en circulation doit être dans le passé.");
            return;
        }

// Vérification que la date de rendez-vous est dans le futur
        if (dateRdvAsLocalDate.isBefore(currentDate)) {
            showAlert(AlertType.ERROR, "Date invalide", "La date du rendez-vous doit être dans le futur.");
            return;
        }

        // Validation de l'email
        if (!isValidEmail(mailValue)) {
            showAlert(AlertType.ERROR, "Format invalide", "L'email n'est pas valide.");
            return;
        }

        // Validation de la matricule
        if (!isValidMatricule(matriculeValue)) {
            showAlert(AlertType.ERROR, "Format invalide", "La matricule n'est pas valide.");
            return;
        }

        // Créer un objet DemandeTroc
        DemandeTroc demandeTroc = new DemandeTroc(postTroc.getId(), new java.sql.Date(date.getTime()), new java.sql.Date(dateRdv.getTime()), heureRdv, kilometrageValue, descriptionValue, imageValue, mailValue, matriculeValue, marqueValue, modeleValue, typedecarburantValue, categorievehiculeValue, typeboitevitesseValue);

        // Ajouter la demande
        DemandetrocService demandetrocService = new DemnadeTrocService();
        demandetrocService.addDemande(demandeTroc);
    }

    // Méthode pour valider le format de la date
    private boolean isValidDateFormat(String dateText) {
        String dateRegex = "^\\d{2}-\\d{2}-\\d{4}$";
        return dateText.matches(dateRegex);
    }

    // Méthode pour valider le format de l'heure
    private boolean isValidTimeFormat(String timeText) {
        String timeRegex = "^\\d{2}:\\d{2}$";
        return timeText.matches(timeRegex);
    }

    // Méthode pour valider l'email
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    // Méthode pour valider la matricule
    private boolean isValidMatricule(String matricule) {
        String matriculeRegex = "^\\d{3}TUN\\d{4}$";
        return matricule.matches(matriculeRegex);
    }

    // Méthode utilitaire pour afficher une alerte
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Méthode pour vérifier si tous les champs sont remplis
    private boolean areAllFieldsFilled() {
        return !kilometrage.getText().isEmpty() &&
                !description.getText().isEmpty() &&
                !image.getText().isEmpty() &&
                !mail.getText().isEmpty() &&
                !matricule.getText().isEmpty() &&
                marque.getValue() != null &&
                model.getValue() != null &&
                typedecarburant.getValue() != null &&
                typevehicule.getValue() != null &&
                typedeboitevitesse.getValue() != null &&
                !daterdv.getText().isEmpty() &&
                !annee.getText().isEmpty() &&
                !heurerdv.getText().isEmpty();
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
            ;

            PostTrocService postTrocService=new PostTrocService();
            PostTroc postTroc=new PostTroc(this.postTroc.getId(),new Date(),Integer.parseInt(kilometrage.getText()),description.getText(),localisation.getText(),image.getText(), mail.getText(), matricule.getText(), marque.getValue(),model.getValue(),typedecarburant.getValue(),typevehicule.getValue(),typedeboitevitesse.getValue());
            postTrocService.updatePost(postTroc);

            btndelete.setDisable(true);
            btnupdate.setDisable(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }   @FXML
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
    public void setPostTroc(PostTroc posttroc){
        //System.out.println(posttroc);
        this.postTroc=posttroc;
    }
    public PostTroc getPostTroc(){
        //System.out.println(postTroc);

        return postTroc;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paneslide.setTranslateX(+200);
        close.setVisible(false);
        para.setVisible(true);
        //showpoststroc();
        marque.getItems().addAll(marques);
        model.getItems().addAll(models);
        typedeboitevitesse.getItems().addAll(typesdeboitesvitesse);
        typedecarburant.getItems().addAll(typesdecarburant);
        typevehicule.getItems().addAll(typesdevehicule);
        //System.out.println(postTroc);
        mouseClicked(this.postTroc);
    }
}
