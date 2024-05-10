package Entities;
import java.time.LocalDate;
import java.util.Objects;

public class Demandeserv {
    private Integer id;
    private String description;
    private Float duree;
    private LocalDate horaire;
    private String marque;
    private String modele;
    private String image;
    private String type_carburant;
    private String categorie_vehicule	;
    private String nom;
    private String statut;
    private String raisonderefus;
    private String typeservice;
    private String localisationdemettrelavehicule;
    private int servicerep_id;

    // Constructeur par défaut
    public Demandeserv() {
    }

    // Constructeur avec paramètres
    public Demandeserv(int id, String description, Float duree, LocalDate horaire, String marque, String modele,
                       String image, String type_carburant, String categorie_vehicule	, String nom, String statut,
                       String raisonderefus, String typeservice, String localisationdemettrelavehicule,
                       int servicerep_id) {
        this.id = id;
        this.description = description;
        this.duree = duree;
        this.horaire = horaire;
        this.marque = marque;
        this.modele = modele;
        this.image = image;
        this.type_carburant	 = type_carburant	;
        this.categorie_vehicule	 = categorie_vehicule	;
        this.nom = nom;
        this.statut = statut;
        this.raisonderefus = raisonderefus;
        this.typeservice = typeservice;
        this.localisationdemettrelavehicule = localisationdemettrelavehicule;
        this.servicerep_id = servicerep_id;
       // this.servicerep = null;
    }
    private Servicerep servicerep;

    public Servicerep getServicerep() {
        return servicerep;
    }

    public void setServicerep(Servicerep servicerep) {
        this.servicerep = servicerep;
    }



    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getDuree() {
        return duree;
    }

    public void setDuree(Float duree) {
        this.duree = duree;
    }

    public LocalDate getHoraire() {
        return horaire;
    }

    public void setHoraire(LocalDate horaire) {
        this.horaire = horaire;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTypeCarburant() {
        return type_carburant;
    }

    public void setTypeCarburant(String type_carburant	) {
        this.type_carburant = type_carburant;
    }

    public String getCategorieVehicule() {
        return categorie_vehicule	;
    }

    public void setCategorieVehicule(String categorie_vehicule	) {
        this.categorie_vehicule	 = categorie_vehicule	;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getRaisonderefus() {
        return raisonderefus;
    }

    public void setRaisonderefus(String raisonderefus) {
        this.raisonderefus = raisonderefus;
    }

    public String getTypeservice() {
        return typeservice;
    }

    public void setTypeservice(String typeservice) {
        this.typeservice = typeservice;
    }

    public String getLocalisationdemettrelavehicule() {
        return localisationdemettrelavehicule;
    }

    public void setLocalisationdemettrelavehicule(String localisationdemettrelavehicule) {
        this.localisationdemettrelavehicule = localisationdemettrelavehicule;
    }

    public Demandeserv(String description, Float duree, LocalDate horaire, String marque, String modele, String image, String type_carburant, String categorie_vehicule	, String nom, String statut, String raisonderefus, String typeservice) {
        this.description = description;
        this.duree = duree;
        this.horaire = horaire;
        this.marque = marque;
        this.modele = modele;
        this.image = image;
        this.type_carburant	= type_carburant;
        this.categorie_vehicule	 = categorie_vehicule	;
        this.nom = nom;
        this.statut = statut;
        this.raisonderefus = raisonderefus;
        this.typeservice = typeservice;
    }

    public int getServicerep_id() {
        return servicerep_id;
    }

    public void setServicerep_id(int servicerep_id) {
        this.servicerep_id = servicerep_id;
    }

    // Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Demandeserv that = (Demandeserv) o;
        return Objects.equals(id, that.id);
    }

}