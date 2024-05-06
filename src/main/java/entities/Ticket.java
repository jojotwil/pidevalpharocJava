package Entities;

public class Ticket {


    private int id, event_id,user_id;
    private double prix;
    private String type_ticket;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getType_ticket() {
        return type_ticket;
    }

    public void setType_ticket(String type_ticket) {
        this.type_ticket = type_ticket;
    }

    public Ticket() {
    }

    public Ticket(int id, int event_id, int user_id, double prix, String type_ticket) {
        this.id = id;
        this.event_id = event_id;
        this.user_id = user_id;
        this.prix = prix;
        this.type_ticket = type_ticket;
    }

    public Ticket(int event_id, int user_id, double prix, String type_ticket) {
        this.event_id = event_id;
        this.user_id = user_id;
        this.prix = prix;
        this.type_ticket = type_ticket;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", event_id=" + event_id +
                ", user_id=" + user_id +
                ", prix=" + prix +
                ", type_ticket='" + type_ticket + '\'' +
                '}';
    }
}
