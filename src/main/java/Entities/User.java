package Entities;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private List<Message> sentMessages;
    private List<Message> receivedMessages;

    public User() {
        this.sentMessages = new ArrayList<>();
        this.receivedMessages = new ArrayList<>();
    }

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

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return username;
    }
}
