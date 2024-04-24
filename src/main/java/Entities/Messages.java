package Entities;

import java.util.Date;

public class Messages {
    private int id;
    private int sender_id;
    private int recipient_id;
    private String title;
    private String message;
    private Date created_at;
    private boolean is_read;

    //constructeur sans paramétre
    public Messages() {}

    //constructeur avec parametre

    public Messages(String title, String message, Date created_at, boolean is_read) {
        this.title = title;
        this.message = message;
        this.created_at = created_at;
        this.is_read = is_read;
    }
    //geters


    public int getId() {
        return id;
    }

    public int getSender_id() {
        return sender_id;
    }

    public int getRecipient_id() {
        return recipient_id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public boolean isIs_read() {
        return is_read;
    }
    //seters

    public void setId(int id) {
        this.id = id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public void setRecipient_id(int recipient_id) {
        this.recipient_id = recipient_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setIs_read(boolean is_read) {
        this.is_read = is_read;
    }
    //tostring

    @Override
    public String toString() {
        return "Messages{" +
                "id=" + id +
                ", sender_id=" + sender_id +
                ", recipient_id=" + recipient_id +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", created_at=" + created_at +
                ", is_read=" + is_read +
                '}';
    }
}
