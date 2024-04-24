package com.example.demo;

import Entities.Messages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.Date;

public class chatBoxController {

    @FXML
    private TextArea chatArea;

    @FXML
    private TextField messageField;

    @FXML
    private Button sendButton;

    // ID de l'utilisateur connecté (à remplacer par la vraie logique d'authentification)
    private int loggedInUserId = 1; // Exemple : utilisateur avec ID 1

    // Méthode appelée lorsqu'un message est envoyé
    @FXML
    private void sendMessage(ActionEvent event) {
        String messageText = messageField.getText(); // Récupérer le message saisi
        if (!messageText.isEmpty()) { // Vérifier si le message n'est pas vide
            // Créer un objet Messages avec les données du message
            Messages message = new Messages("New Message", messageText, new Date(), false);
            message.setSender_id(loggedInUserId); // Définir l'ID de l'utilisateur connecté comme sender_id
            // Enregistrer le message dans la base de données (à implémenter)
            saveMessageToDatabase(message);

            // Ajouter le message à la zone de chat
            appendMessage("Moi", messageText);
            messageField.clear(); // Effacer le champ de saisie après l'envoi du message
        }
    }

    // Méthode pour ajouter un message à la zone de chat
    private void appendMessage(String sender, String message) {
        chatArea.appendText(sender + ": " + message + "\n");
    }

    // Méthode pour enregistrer un message dans la base de données (à implémenter)
    private void saveMessageToDatabase(Messages message) {
        // Ici, vous pouvez ajouter le code pour enregistrer le message dans la base de données
        System.out.println("Message saved to database: " + message);
        System.out.println(message.getId());
    }
}
