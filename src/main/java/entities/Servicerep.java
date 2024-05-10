package Entities;
import javafx.scene.control.Spinner;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Servicerep {
    private Long id;
    private String description;
    private Float prix;
    private String duree;
    private String nomservice;
    private String horaire;
    private String typeVehicule;
    private String image;
    private String typeservice;
    private String horairedujour;
    private String joursderepos;
    private String statut;
    private String raisonderefus;
    private Integer nbvue = 0;
    private User user;
    private List<Demandeserv> demande = new ArrayList<>();
    private Integer likes = 0;
    private Integer dislikes = 0;

    // Constructeurs
    public Servicerep() {}

    public Servicerep(int idS, String desc, float prixValue, String dureeValue, String nom, String s, String typeVehicule, Object o, String statutValue, Object o1, String jourRepos, Object o2, int idHoraireDuJourValue, String typeService, int i, int i1) {
    }

    public Servicerep(int idS, String nom, String image, TextField description, TextField duree, Spinner<Integer> horaire, Spinner<Integer> idhorairedejour, TextField jourrepos, SplitMenuButton statut, SplitMenuButton typedeservice, SplitMenuButton typedevehicule, int userId) {
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getNomservice() {
        return nomservice;
    }

    public void setNomservice(String nomservice) {
        this.nomservice = nomservice;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public String getTypeVehicule() {
        return typeVehicule;
    }

    public void setTypeVehicule(String typeVehicule) {
        this.typeVehicule = typeVehicule;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTypeservice() {
        return typeservice;
    }

    public void setTypeservice(String typeservice) {
        this.typeservice = typeservice;
    }

    public String getHorairedujour() {
        return horairedujour;
    }

    public void setHorairedujour(String horairedujour) {
        this.horairedujour = horairedujour;
    }

    public String getJoursderepos() {
        return joursderepos;
    }

    public void setJoursderepos(String joursderepos) {
        this.joursderepos = joursderepos;
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

    public Integer getNbvue() {
        return nbvue;
    }

    public void setNbvue(Integer nbvue) {
        this.nbvue = nbvue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Demandeserv> getDemande() {
        return demande;
    }

    public void setDemande(List<Demandeserv> demande) {
        this.demande = demande;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    public void incrementLikes() {
        this.likes++;
    }

    public void incrementDislikes() {
        this.dislikes++;
    }

    public boolean checkAndDeleteIfRequired() {
        return this.dislikes - this.likes >= 2;
    }

    @Override
    public String toString() {
        return "Servicerep{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", duree='" + duree + '\'' +
                ", nomservice='" + nomservice + '\'' +
                ", horaire='" + horaire + '\'' +
                ", typeVehicule='" + typeVehicule + '\'' +
                ", image='" + image + '\'' +
                ", typeservice='" + typeservice + '\'' +
                ", horairedujour='" + horairedujour + '\'' +
                ", joursderepos='" + joursderepos + '\'' +
                ", statut='" + statut + '\'' +
                ", raisonderefus='" + raisonderefus + '\'' +
                ", nbvue=" + nbvue +
                ", user=" + user +
                ", demande=" + demande +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                '}';
    }
    // Constructeur avec paramètres
    public Servicerep(Long id, String description, Float prix, String duree, String nomservice, String horaire,
                      String typeVehicule, String image, String typeservice, String horairedujour,
                      String joursderepos, String statut, String raisonderefus, Integer nbvue, User user,
                      List<Demandeserv> demande, Integer likes, Integer dislikes) {
        this.id = id;
        this.description = description;
        this.prix = prix;
        this.duree = duree;
        this.nomservice = nomservice;
        this.horaire = horaire;
        this.typeVehicule = typeVehicule;
        this.image = image;
        this.typeservice = typeservice;
        this.horairedujour = horairedujour;
        this.joursderepos = joursderepos;
        this.statut = statut;
        this.raisonderefus = raisonderefus;
        this.nbvue = nbvue;
        this.user = user;
        this.demande = demande;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    // Getters et Setters (omis pour des raisons de concision)

    // Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servicerep that = (Servicerep) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // toString (omis pour des raisons de concision)

}
