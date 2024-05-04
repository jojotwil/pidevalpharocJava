package com.example.demo;

import Entities.Message;
import Entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class MessageController {

    @FXML
    private ListView<Message> messageListView;

    @FXML
    private TextField recipientTextField;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextArea contentTextArea;

    public void sendMessage() {
        // Récupérer les informations du message depuis l'interface utilisateur
        String recipient = recipientTextField.getText();
        String title = titleTextField.getText();
        String content = contentTextArea.getText();

        // Créer l'objet utilisateur et le message
        User sender = new User("Sender"); // Remplacez "Sender" par le nom de l'utilisateur actuel
        User recipientUser = new User(recipient);
        Message message = new Message(title, content, sender.getId(), recipientUser.getId());

        // Ajouter le message à la liste des messages
        messageListView.getItems().add(message);

        // Effacer les champs de saisie après l'envoi
        recipientTextField.clear();
        titleTextField.clear();
        contentTextArea.clear();
    }
}
