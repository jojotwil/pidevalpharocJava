package Entities;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String email;
    private String password;
    private String nom;
    private String prenom;
    private String image;
    private boolean isVerified;
    private boolean isBlocked;

    // Constructors
    public User() {
    }
    public User(int id, String email) {
        this.id = id;
        email = email;
    }

    public User(String email, String password, String nom, String prenom, String image) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.image = image;
    }

    public User(String senderId) {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    // Override toString() method
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", image='" + image + '\'' +
                ", isVerified=" + isVerified +
                ", isBlocked=" + isBlocked +
                '}';
    }


    private List<Message> sentMessages;
    private List<Message> receivedMessages;



    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public void addSentMessage(Message message) {
        this.sentMessages.add(message);
    }

    public void removeSentMessage(Message message) {
        this.sentMessages.remove(message);
    }

    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void addReceivedMessage(Message message) {
        this.receivedMessages.add(message);
    }

    public void removeReceivedMessage(Message message) {
        this.receivedMessages.remove(message);
    }


}
