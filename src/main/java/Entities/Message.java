package Entities;

import Entities.User;
import javafx.scene.paint.Color;

import java.time.ZonedDateTime;

public class Message {
    private int id;
    private ZonedDateTime createdAt;
    private String title;
    private String message;
    private boolean isRead;
    private int sender;
    private int recipient;

    public Message(String title, String message, int sender, int recipient) {
        this.createdAt = ZonedDateTime.now();
        this.title = title;
        this.message = message;
        this.sender = sender;
        this.recipient = recipient;
        this.isRead = false;
    }

    public Message() {

    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getRecipient() {
        return recipient;
    }

    public void setRecipient(int recipient) {
        this.recipient = recipient;
    }

    @Override
    public String toString() {
        return "Message{" +
                "createdAt=" + createdAt +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", isRead=" + isRead +
                ", sender=" + sender +
                ", recipient=" + recipient +
                '}';
    }
}
