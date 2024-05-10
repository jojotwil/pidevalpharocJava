package com.example.demo;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;


import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PaymentController {


    @FXML
    private TextField client_name;

    @FXML
    private TextField email;

    @FXML
    private TextField num_card;

    @FXML
    private TextField cvc;

    @FXML
    private TextField MM;
    @FXML
    private TextField YY;
    @FXML
    private void processPayment() {
        if (validateInputs()) {
            try {
                // Set your secret key here
                Stripe.apiKey = "sk_test_51PCkTGIxYIBwh0lasQ3Ur1y6P2tIgqzxTLGNachA9riqTfKGFwFE1yRfT8Mw3QyGFFoKUEqnyMyW2TcTL4pqz7M900fg0Bl1my";

                // Create a PaymentIntent with other payment details
                PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                        .setAmount(1000L) // Amount in cents (e.g., $10.00)
                        .setCurrency("usd")
                        .build();

                PaymentIntent intent = PaymentIntent.create(params);

                // Afficher une alerte de succès si le paiement a réussi
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Paiement réussi");
                alert.setHeaderText(null);
                alert.setContentText("Le paiement a été effectué avec succès. ID du PaymentIntent: " + intent.getId());
                alert.showAndWait();
            } catch (StripeException e) {
                // Si une erreur s'est produite lors du traitement du paiement, affichez une alerte d'erreur
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de paiement");
                alert.setHeaderText(null);
                alert.setContentText("Le paiement a échoué. Erreur: " + e.getMessage());
                alert.showAndWait();
            }
        }
    }

    private boolean validateInputs() {
        if (client_name.getText().isEmpty()) {
            showAlert("You need to input your Name");
            applyErrorStyle(client_name);
            return false;
        } else if (email.getText().isEmpty()) {
            showAlert("You need to input your Email");
            applyErrorStyle(email);
            return false;
        } else if (!isValidEmail(email.getText())) {
            showAlert("Please enter a valid Email address.");
            applyErrorStyle(email);
            return false;
        } else if (num_card.getText().isEmpty()) {
            showAlert("You need to input your Card Number");
            applyErrorStyle(num_card);
            return false;
        } else {
            Integer cvcValue = Integer.valueOf(cvc.getText());
            if (cvcValue == null || cvcValue < 100 || cvcValue > 999) {
                showAlert("Please enter a valid CVC number.");
                applyErrorStyle(cvc);
                return false;
            }
        }

        if (MM.getText() == null || YY.getText() == null) {
            showAlert("Please enter a valid expiration date");
            applyErrorStyle(MM);
            applyErrorStyle(YY);
            return false;
        }

        clearErrorStyles();
        return true;
    }


    private boolean isValidEmail(String email) {
        // Votre logique de validation d'email ici
        return true; // Placeholder
    }
    private boolean check_expDate(String year, String month) {
        // Votre logique de validation date d'expiration ici
        return true; // Placeholder
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.setTitle("Problem");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private void applyErrorStyle(Control control) {
        control.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        new animatefx.animation.Shake(control).play();
    }

    private void clearErrorStyles() {
        client_name.setStyle(null);
        email.setStyle(null);
        num_card.setStyle(null);
        cvc.setStyle(null);
        MM.setStyle(null);
        YY.setStyle(null);
    }
}
