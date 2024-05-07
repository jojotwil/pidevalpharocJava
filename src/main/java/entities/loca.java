package Entities;

import java.time.LocalDate;
import java.util.Date;

public class loca {
    private int id;
    private int VehichuleLouerid;
    private String 	description;
    private String 	localisation;
    private Date date_debut;
    private Date date_fin;

    public loca() {
    }

    public loca(int id, int vehichuleLouerid, String description, String localisation, Date date_debut, Date date_fin) {
        this.id = id;
        this.VehichuleLouerid = vehichuleLouerid;
        this.description = description;
        this.localisation = localisation;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }
    public loca( int vehichuleLouerid, String description, String localisation, Date date_debut, Date date_fin) {

        this.VehichuleLouerid = vehichuleLouerid;
        this.description = description;
        this.localisation = localisation;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public loca(int vehichuleLouerid, String descr, String locati, LocalDate datedeb, LocalDate datefin) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehichuleLouerid() {
        return VehichuleLouerid;
    }

    public void setVehichuleLouerid(int vehichuleLouerid) {
        this.VehichuleLouerid = vehichuleLouerid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "loca{" +
                "id=" + id +
                ", VehichuleLouerid=" + VehichuleLouerid +
                ", description='" + description + '\'' +
                ", localisation='" + localisation + '\'' +
                ", date_debut=" + date_debut +
                ", date_fin=" + date_fin +
                '}';
    }
}
