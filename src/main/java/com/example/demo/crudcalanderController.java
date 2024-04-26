package com.example.demo;

import Entities.CalendarActivity;
import Services.FullCalederService;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class crudcalanderController implements Initializable {
    @FXML
    private Button troc;

    @FXML
    private Button para;

    @FXML
    private Button close;

    @FXML
    private AnchorPane paneslide;

    @FXML
    private AnchorPane menu_form;

    @FXML
    private ColorPicker back;

    @FXML
    private ColorPicker text;

    @FXML
    private ColorPicker border;

    @FXML
    private RadioButton allday;

    @FXML
    private TextField titre;

    @FXML
    private TextField description;

    @FXML
    private DatePicker debut;

    @FXML
    private DatePicker fin;

    @FXML
    private Button btnsave;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnupdate;



    @FXML
    void addrdv(ActionEvent event) {
        // Récupérer les valeurs des champs du formulaire
        LocalDate startDate = debut.getValue();
        LocalDate endDate = fin.getValue();
        String descriptionValue = description.getText();
        String titreValue = titre.getText();
        boolean isAllDay = allday.isSelected();

        // Vérifier si les champs obligatoires sont vides
        if (startDate == null || endDate == null || descriptionValue.isEmpty() || titreValue.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Champs obligatoires", "Veuillez remplir tous les champs obligatoires.");
            return;
        }

        // Convertir les dates en ZonedDateTime
        ZonedDateTime startDateTime = startDate.atStartOfDay(ZoneId.systemDefault());
        ZonedDateTime endDateTime = endDate.atStartOfDay(ZoneId.systemDefault());

        // Vérifier si les dates sont dans le futur
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        if (startDateTime.isBefore(now) || endDateTime.isBefore(now)) {
            showAlert(Alert.AlertType.ERROR, "Erreur de date", "Les dates doivent être dans le futur.");
            return;
        }

        // Vérifier si la date de fin est postérieure à la date de début
        if (endDateTime.isBefore(startDateTime)) {
            showAlert(Alert.AlertType.ERROR, "Erreur de date", "La date de fin doit être postérieure à la date de début.");
            return;
        }

        // Vérifier si les couleurs sont sélectionnées
        Color backgroundColor = back.getValue();
        Color textColor = text.getValue();
        Color borderColor = border.getValue();
        if (backgroundColor == null || textColor == null || borderColor == null) {
            showAlert(Alert.AlertType.ERROR, "Couleurs non sélectionnées", "Veuillez sélectionner toutes les couleurs.");
            return;
        }

        // Créer une instance de CalendarActivity en utilisant les valeurs récupérées
        CalendarActivity calendar = new CalendarActivity(startDateTime, endDateTime, descriptionValue, titreValue, backgroundColor, textColor, borderColor, isAllDay);
        System.out.println(calendar);
        FullCalederService service = new FullCalederService();
        service.adddate(calendar);
        // Faites quelque chose avec l'objet calendar, comme l'enregistrement dans la base de données
    }

    // Méthode utilitaire pour afficher une alerte
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }




    @FXML
    private void deleteCalendarActivity(CalendarActivity rdv) {
        try {
            // Récupérer l'activité sélectionnée dans votre interface utilisateur
            // CalendarActivity selectedActivity = ; // Code pour récupérer l'activité sélectionnée dans votre interface utilisateur ;

            // Vérifier si une activité est sélectionnée
            if (rdv == null) {
                // Afficher un message d'erreur si aucune activité n'est sélectionnée
                showAlert(Alert.AlertType.ERROR, "Sélection manquante", "Veuillez sélectionner une activité à supprimer.");
                return;
            }

            // Afficher une boîte de dialogue de confirmation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Voulez-vous vraiment supprimer cette activité ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // L'utilisateur a confirmé la suppression
                // Appelez votre service pour supprimer l'activité de la base de données
                FullCalederService service = new FullCalederService();
                service.deletedate(rdv);

                // Afficher un message de succès
                showAlert(Alert.AlertType.INFORMATION, "Suppression réussie", "L'activité a été supprimée avec succès.");
            } else {
                // L'utilisateur a annulé la suppression
                // Ne faites rien
            }
        } catch (Exception e) {
            // Gérer toute exception qui pourrait survenir
            e.printStackTrace();
        }
    }



    @FXML
    void updaterdv(ActionEvent event) {
        // Récupérer les valeurs des champs du formulaire
        LocalDate startDate = debut.getValue();
        LocalDate endDate = fin.getValue();
        String descriptionValue = description.getText();
        String titreValue = titre.getText();
        boolean isAllDay = allday.isSelected();

        // Vérifier si les champs obligatoires sont vides
        if (startDate == null || endDate == null || descriptionValue.isEmpty() || titreValue.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Champs obligatoires", "Veuillez remplir tous les champs obligatoires.");
            return;
        }

        // Convertir les dates en ZonedDateTime
        ZonedDateTime startDateTime = startDate.atStartOfDay(ZoneId.systemDefault());
        ZonedDateTime endDateTime = endDate.atStartOfDay(ZoneId.systemDefault());

        // Vérifier si les dates sont dans le futur
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        if (startDateTime.isBefore(now) || endDateTime.isBefore(now)) {
            showAlert(Alert.AlertType.ERROR, "Erreur de date", "Les dates doivent être dans le futur.");
            return;
        }

        // Vérifier si la date de fin est postérieure à la date de début
        if (endDateTime.isBefore(startDateTime)) {
            showAlert(Alert.AlertType.ERROR, "Erreur de date", "La date de fin doit être postérieure à la date de début.");
            return;
        }

        // Vérifier si les couleurs sont sélectionnées
        Color backgroundColor = back.getValue();
        Color textColor = text.getValue();
        Color borderColor = border.getValue();
        if (backgroundColor == null || textColor == null || borderColor == null) {
            showAlert(Alert.AlertType.ERROR, "Couleurs non sélectionnées", "Veuillez sélectionner toutes les couleurs.");
            return;
        }

        // Créer une instance de CalendarActivity en utilisant les valeurs récupérées
        CalendarActivity calendar = new CalendarActivity(startDateTime, endDateTime, descriptionValue, titreValue, backgroundColor, textColor, borderColor, isAllDay);
        System.out.println(calendar);
        // Mettre à jour l'activité dans la base de données en utilisant le service approprié
        // Supposons que vous avez un service appelé FullCalederService avec une méthode updateDate
        FullCalederService service = new FullCalederService();
        service.updatedate(calendar);
        // Faites quelque chose avec l'objet calendar, comme l'enregistrement dans la base de données
    }




    @FXML
    void run1(javafx.scene.input.MouseEvent event) {
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





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paneslide.setTranslateX(+200);
        close.setVisible(false);
        para.setVisible(true);
    }
}