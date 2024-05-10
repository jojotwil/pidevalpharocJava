package com.example.demo;

import Entities.CalendarActivity;
import Services.FullCalederService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class crudcalanderController implements Initializable {
    public void boutique(ActionEvent event){
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listBoutiqueFront.fxml"));
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
    public void location(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/frontvl.fxml"));
            Parent newContent = loader.load();



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
    private Button troc;
    @FXML
    private MenuItem button_logout;

    public void event(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("index-evenement-front.fxml"));
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
    private CalendarActivity rdv; // Garder une référence à l'objet CalendarActivity



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
        calander(event);
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
    private void deleteCalendarActivity(ActionEvent event) {
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
                calander(event);

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
    void mouseClicked(CalendarActivity rdv) {
        try {
            this.rdv = rdv;
            if (rdv != null) {
                debut.setValue(rdv.getStart().toLocalDate());
                fin.setValue(rdv.getEnd().toLocalDate());
                description.setText(rdv.getDescription());
                titre.setText(rdv.getTitle());
                allday.setSelected(rdv.isAllday());
                text.setValue(rdv.getText_color());
                border.setValue(rdv.getBorder_color());
                // Assurez-vous d'ajouter la logique pour initialiser la couleur de fond si nécessaire
                // backgroundColor.setValue(rdv.getBackground_color());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void updaterdv(ActionEvent event) {
        // Validation des données saisies
        if (debut.getValue() == null || fin.getValue() == null || description.getText().isEmpty() || titre.getText().isEmpty()) {
            // Afficher une alerte pour informer l'utilisateur des champs manquants
            showAlert(Alert.AlertType.ERROR, "Champs manquants", "Veuillez remplir tous les champs obligatoires.");
            return;
        }

        // Convertir les dates en ZonedDateTime
        ZonedDateTime startDateTime = debut.getValue().atStartOfDay(ZoneId.systemDefault());
        ZonedDateTime endDateTime = fin.getValue().atStartOfDay(ZoneId.systemDefault());

        // Validation spécifique pour les dates (par exemple, s'assurer qu'elles sont dans le futur)

        // Récupérer les autres valeurs des champs
        String descriptionValue = description.getText();
        String titreValue = titre.getText();
        boolean isAllDay = allday.isSelected();

        // Vérifier si les champs obligatoires sont vides
        if (debut.getValue() == null || fin.getValue() == null || descriptionValue.isEmpty() || titreValue.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Champs obligatoires", "Veuillez remplir tous les champs obligatoires.");
            return;
        }


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
        // Assurez-vous d'ajouter la logique pour récupérer la couleur de fond si nécessaire
        // Color backgroundColor = backgroundColor.getValue();

        // Mettre à jour les valeurs de rdv avec les nouvelles valeurs
        rdv.setStart(startDateTime);
        rdv.setEnd(endDateTime);
        rdv.setDescription(descriptionValue);
        rdv.setTitle(titreValue);
        rdv.setAllday(isAllDay);
        rdv.setText_color(textColor);
        rdv.setBorder_color(borderColor);
        // Assurez-vous d'ajouter la logique pour définir la couleur de fond si nécessaire
        // rdv.setBackground_color(backgroundColor);

        // Mettre à jour le rendez-vous dans la base de données en utilisant le service approprié
        FullCalederService service = new FullCalederService();
        service.updatedate(rdv);
        System.out.println(rdv);

        calander(event);
        // Faites quelque chose avec l'objet calendar, comme l'enregistrement dans la base de données
        showAlert(Alert.AlertType.INFORMATION, "Succès", "Le rendez-vous a été mis à jour avec succès.");
    }







    @FXML
    public void troc(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-viewtroc.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"
            HelloControllertroc controller = loader.getController();

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
    public void monprofil1(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profil.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "profil.fxml"
            ProfilController controller = loader.getController();

            // Créer une nouvelle scène avec le nouveau contenu
            Scene scene = new Scene(newContent);

            // Obtenir la fenêtre principale (stage)
            Stage mainStage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();

            // Obtenir les dimensions de l'écran
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            // Définir la taille de la fenêtre sur les dimensions de l'écran
            mainStage.setWidth(screenBounds.getWidth());
            mainStage.setHeight(screenBounds.getHeight());

            // Définir la nouvelle scène sur la fenêtre principale
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void calander(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("testcalander.fxml"));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScence(actionEvent, "authentifier.fxml", "Log In", null, null,button_logout);
            }
        });
    }
}