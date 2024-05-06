    package com.example.demo;

    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.geometry.Rectangle2D;
    import javafx.scene.Node;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.Label;
    import javafx.stage.Screen;
    import javafx.stage.Stage;

    import java.io.IOException;

    public class VerificationController {
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

        private Label verificationMessage; // Reference to a label in your FXML for displaying messages

        public void handleVerification(String email) {
            // Extract email address from the URL parameter
            String userEmail = email;

            // Retrieve user information using DBUtils
            String[] userInfo = DBUtils.getUserInformation(userEmail);

            // Check if user exists and isn't already verified
            if (userInfo != null && userInfo.length > 0 && userInfo[0].equals("0")) { // Assuming "0" indicates unverified user
                // Update user's verified flag to true
                if (DBUtils.updateUserVerified(userEmail)) {
                    verificationMessage.setText("Your email has been verified successfully!");
                    // Optionally, redirect to login page or show success message
                } else {
                    verificationMessage.setText("An error occurred while verifying your email. Please try again.");
                }
            } else {
                verificationMessage.setText("Invalid verification link or user already verified.");
            }
        }
    }
