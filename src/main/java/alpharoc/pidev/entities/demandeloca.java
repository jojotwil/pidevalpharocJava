package alpharoc.pidev.entities;

import java.util.Date;

public class demandeloca {
    private int id;
    private int loca_id;
    private int user_id;
    private Date datedebut;
    private Date datefin;
    private String description;

    public demandeloca() {
    }

    public demandeloca(int id, int loca_id, int user_id, Date datedebut, Date datefin, String description) {
        this.id = id;
        this.loca_id = loca_id;
        this.user_id = user_id;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.description = description;
    }

    public demandeloca(int loca_id, int user_id, Date datedebut, Date datefin, String description) {

        this.loca_id = loca_id;
        this.user_id = user_id;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoca_id() {
        return loca_id;
    }

    public void setLoca_id(int loca_id) {
        this.loca_id = loca_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "demandeloca{" +
                "id=" + id +
                ", loca_id=" + loca_id +
                ", user_id=" + user_id +
                ", datedebut=" + datedebut +
                ", datefin=" + datefin +
                ", description='" + description + '\'' +
                '}';
    }
}
