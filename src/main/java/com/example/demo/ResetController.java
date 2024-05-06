package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class ResetController {
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
    private TextField emailField;

    @FXML
    private void resetPassword(ActionEvent event) {
        String email = emailField.getText().trim();
        if (!email.isEmpty()) {
            // Option 1: DBUtils returns the new password
            String newPassword = DBUtils.resetPassword(email);

            // Option 2: Handle null return from DBUtils
            if (newPassword == null) {
                // Handle password reset failure
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Failed to reset password. Please try again later.");
                alert.show();
                return;
            }

            if (sendEmailWithNewPassword(email, newPassword)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("A new password has been sent to your email.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Failed to send reset email. Please try again later.");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter your email.");
            alert.show();
        }
    }

    // ... (sendEmailWithNewPassword meth

    // Method to send an email with the new password
    private boolean sendEmailWithNewPassword(String email, String newPassword) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");  // Replace with your SMTP server host
        props.put("mail.smtp.port", "587"); // Replace with your SMTP server port

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("jarayatef@gmail.com", "alfkcmkhsazhlryt"); // Replace with your email credentials
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("jarayatef@gmail.com")); // Replace with your email address
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Reset Password");

            String emailBody = "Your new password is: " + newPassword;
            message.setContent(emailBody, "text/plain");

            Transport.send(message);
            System.out.println("Reset password email sent to " + email);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error sending reset password email: " + e.getMessage());
            return false;
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        // Implement navigation back to hello-view.fxml
        DBUtils.changeScence(event, "authentifier.fxml", "Login", null, null);
    }
}
