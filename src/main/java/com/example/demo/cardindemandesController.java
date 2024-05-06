package com.example.demo;

import Entities.DemandeTroc;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import Entities.PostTroc;
import Interfaces.DemandetrocService;
import Services.DemnadeTrocService;
import Services.PostTrocService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class cardindemandesController implements Initializable {
    @FXML
    private ImageView image;


    @FXML
    private Label marque;

    @FXML
    private Label model;

    @FXML
    private Label carburant;

    @FXML
    private Label boite;

    @FXML
    private Label description;

    @FXML
    private Button Accepter;

    @FXML
    private Button Refuser;
    private Image imagee;
    private DemandeTroc DemandeTroc;
    public static void sendEmail(String to, String subject, String body) {
        // Configure email properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // Replace with your SMTP server host
        props.put("mail.smtp.port", "587"); // Replace with your SMTP server port

        // Create session with authenticator
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("jarayatef@gmail.com", "alfkcmkhsazhlryt"); // Replace with your email credentials
            }
        });

        try {
            // Create MimeMessage object
            MimeMessage message = new MimeMessage(session);
            // Set From: header field
            message.setFrom(new InternetAddress("mouna.benrebah@esprit.tn")); // Your email address
            // Set To: header field
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // Set Subject: header field
            message.setSubject(subject);
            // Set actual message
            message.setText(body);

            // Send message
            Transport.send(message);
            System.out.println("Message sent successfully...");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    public void accepterDemande(ActionEvent event) {
        System.out.println(DemandeTroc);

        // Test d'envoi d'un e-mail
        String destinataire = "mouna1ben1rebah@gmail.com";
        String sujet = "Test d'e-mail";
        String contenu = "Ceci est un test d'e-mail.";
        String smtpHost = "smtp.gmail.com"; // Serveur SMTP Gmail
        String smtpPort = "587"; // Port SMTP pour Gmail avec STARTTLS
        String senderEmail = "mouna.benrebah@esprit.tn";


        String to = "mouna1ben1rebah@gmail.com"; // Receiver's email address
        String subject = "Acceptation du demande Troc"; // Subject of the email
        PostTrocService service=new PostTrocService();
        System.out.println(DemandeTroc.getPostid());


        String body = "Le post  :"+service.getPostById(DemandeTroc.getPostid()).toString() + "votre Demande est : "+DemandeTroc.toString(); // Body of the email
        sendEmail(to, subject, body);
        showAlert("le  demande est accepté ",event);
        // Autres actions à effectuer après l'envoi de l'email, par exemple la suppression de la demande, etc.
    }
    public void RefuserDemande(ActionEvent event) {
        System.out.println(DemandeTroc);

        String destinataire = "mouna1ben1rebah@gmail.com";
        String sujet = "Test d'e-mail";
        String contenu = "Ceci est un test d'e-mail.";
        String smtpHost = "smtp.gmail.com"; // Serveur SMTP Gmail
        String smtpPort = "587"; // Port SMTP pour Gmail avec STARTTLS
        String senderEmail = "mouna.benrebah@esprit.tn";

        String to = "mouna1ben1rebah@gmail.com"; // Receiver's email address
        String subject = "refus du demande Troc"; // Subject of the email
        PostTrocService service = new PostTrocService();
        System.out.println(DemandeTroc.getPostid());
        DemnadeTrocService servicedemande = new DemnadeTrocService();
        try {
            // Supprimer la demande de troc
            servicedemande.deleteDemande(DemandeTroc);

            // Rafraîchir la liste des demandes de troc en supprimant les éléments associés à l'ID de post
            ObservableList<DemandeTroc> demandeTrocList = servicedemande.getDemandesByPostid(DemandeTroc.getPostid());
            demandeTrocList.removeIf(demande -> demande.getPostid() == DemandeTroc.getPostid());

            showAlert("le demande est refusé", event);
        } catch (Exception e) {
            System.out.println(e);
        }

        String body = "Le post  :" + service.getPostById(DemandeTroc.getPostid()).toString() + "votre Demande est : " + DemandeTroc.toString();
        sendEmail(to, subject, body);
    }

    private void showAlert(String message, ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Ajouter un événement handler pour le bouton "OK"
        alert.setOnCloseRequest(e -> {
            // Naviguer vers la page "trocback"
            //demoi(event);
        });

        alert.showAndWait();
    }

    public void setDemandeTroc(Entities.DemandeTroc demandeTroc) {
        if (demandeTroc != null) {
            DemandeTroc = demandeTroc;
            System.out.println(demandeTroc + "in set");
        } else {
            // Gérer le cas où demandeTroc est null
            System.out.println("La demandeTroc passée à setDemandeTroc est null");
        }
    }

    public Entities.DemandeTroc getDemandeTroc() {
        return DemandeTroc;
    }

    @FXML
    private AnchorPane anchorpane;

    public void postdata() {
        if (this.DemandeTroc != null) {
            System.out.println(getDemandeTroc() + "gettt");

            System.out.println(this.DemandeTroc + "demande");
            model.setText(this.DemandeTroc.getModele());
            marque.setText(this.DemandeTroc.getMarque());
            carburant.setText(this.DemandeTroc.getTypedecarburant());
            boite.setText(this.DemandeTroc.getTypeboitevitesse());
            description.setText(this.DemandeTroc.getDescription());

            try {
                imagee = new Image( this.DemandeTroc.getImage());
                image.setImage(imagee);
            } catch (Exception e) {
                // Gérer l'erreur, par exemple afficher un message d'erreur ou une image par défaut
                System.out.println("Erreur lors du chargement de l'image : " + e.getMessage());
            }


        } else {
            // Gérer le cas où DemandeTroc est null
            System.out.println("DemandeTroc est null dans postdata()");
        }
    }


    @FXML
    public void deletepost(){
        try {
            DemandeTroc demandeTroc=this.DemandeTroc;
            DemandetrocService demandetrocService=new DemnadeTrocService();
            System.out.println(this.DemandeTroc.getId());
            demandetrocService.deleteDemande(demandetrocService);
        }catch (Exception e){
            e.printStackTrace();
        }
    }








    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        postdata();
    }
}
