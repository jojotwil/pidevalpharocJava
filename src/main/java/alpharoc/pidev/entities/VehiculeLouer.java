package alpharoc.pidev.entities;

import java.util.Date;

public class VehiculeLouer {
    private int id;
    private String marque;
    private String modele;
    private String description;

    private Date periode_dispo;
    private String type_carburant;
    private String categorie_vehicule;

    public VehiculeLouer() {
    }

    public VehiculeLouer(int id, String marque, String modele, String description, Date periode_dispo, String type_carburant, String categorie_vehicule) {
        this.id = id;
        this.marque = marque;
        this.modele = modele;
        this.description = description;
        this.periode_dispo = periode_dispo;
        this.type_carburant = type_carburant;
        this.categorie_vehicule = categorie_vehicule;
    }

    public VehiculeLouer(String marque, String modele, String description, Date periode_dispo, String type_carburant, String categorie_vehicule) {
        this.marque = marque;
        this.modele = modele;
        this.description = description;
        this.periode_dispo = periode_dispo;
        this.type_carburant = type_carburant;
        this.categorie_vehicule = categorie_vehicule;
    }

    public VehiculeLouer(String toyota, String corolla, String sedan, String petrol, String car) {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPeriode_dispo() {
        return periode_dispo;
    }

    public void setPeriode_dispo(Date periode_dispo) {
        this.periode_dispo = periode_dispo;
    }

    public String getType_carburant() {
        return type_carburant;
    }

    public void setType_carburant(String type_carburant) {
        this.type_carburant = type_carburant;
    }

    public String getCategorie_vehicule() {
        return categorie_vehicule;
    }

    public void setCategorie_vehicule(String categorie_vehicule) {
        this.categorie_vehicule = categorie_vehicule;
    }

    @Override
    public String toString() {
        return "VehiculeLouer{" +
                "id=" + id +
                ", marque='" + marque + '\'' +
                ", modele='" + modele + '\'' +
                ", description='" + description + '\'' +
                ", periode_dispo=" + periode_dispo +
                ", type_carburant='" + type_carburant + '\'' +
                ", categorie_vehicule='" + categorie_vehicule + '\'' +
                '}';
    }
}
