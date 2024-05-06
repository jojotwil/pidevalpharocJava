package Entities;


import java.util.Date;

public class Evenement {
    private int id;
    private String titre,theme,localisation,description;
    public Date date_debut,date_fin;

    public Evenement() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Evenement(String titre, String theme, String localisation, String description, Date date_debut, Date date_fin) {
        this.titre = titre;
        this.theme = theme;
        this.localisation = localisation;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Evenement(int id, String titre, String theme, String localisation, String description, Date date_debut, Date date_fin) {
        this.id = id;
        this.titre = titre;
        this.theme = theme;
        this.localisation = localisation;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "Evenement{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", theme='" + theme + '\'' +
                ", localisation='" + localisation + '\'' +
                ", description='" + description + '\'' +
                ", date_debut=" + date_debut +
                ", date_fin=" + date_fin +
                '}';
    }
}
