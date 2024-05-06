package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfilePageController implements Initializable {
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

    private boolean isDarkTheme = false; // Track the current theme
    @FXML
    private Label label_welcome;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Label label_email;
    @FXML
    private Button button_logout;
    @FXML
    private ImageView image_photo;
    @FXML
    private ImageView profileImage;
    // You need to provide a method to set the user's email and photo
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScence(actionEvent, "hello-view.fxml", "Log In", null, null);
            }
        });

    }
    // Method to refresh displayed email
    public void refreshDisplayedEmail() {
        String loggedInUserEmail = DBUtils.getLoggedInUserEmail();
        label_email.setText("Email: " + loggedInUserEmail);
    }
    public void setUserInformation(String userEmail) {
        String loggedInUserEmail = DBUtils.getLoggedInUserEmail();
        label_email.setText("Email: " + loggedInUserEmail);

        // Load user's photo from URL and set it to the image_photo ImageView
        // Example:
        // Image image = new Image(userPhotoUrl);
        // image_photo.setImage(image);
    }

    @FXML

    void handleUpdateProfileButtonClick(ActionEvent event) {
        try {
            String loggedInUserEmail = DBUtils.getLoggedInUserEmail();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateProfile.fxml"));
            Pane updateProfilePane = loader.load();
            UpdateProfileController updateProfileController = loader.getController();

            // Pass any necessary information to the update profile controller

            Stage stage = new Stage();
            stage.setScene(new Scene(updateProfilePane));
            stage.setTitle("Update Profile");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleDeleteProfile(ActionEvent event) {
        String userEmail = DBUtils.getLoggedInUserEmail();// You need to implement this method to get the logged-in user's email
        DBUtils.deleteUserProfile(event,userEmail);
        // You may need to redirect the user to a different page after profile deletion
    }
    @FXML
    void handleResetPasswordButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ResetPassword.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Reset Password");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleRefreshButtonClick(ActionEvent actionEvent) {
        refreshDisplayedEmail();
    }

}
