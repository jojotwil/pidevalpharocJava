package Entities;

import java.sql.Date;
import java.sql.Time;

public class DemandeTroc {
    private int id;
    private int userdemande_id;
    private int idposttroc_id;
    private Date annee;
    private Date daterdv;
    private Time heurerdv;
    private double kilometrage;
    private String description;
    private String image;
    private String mail;
    private String matricule;
    private String marque;
    private String modele;
    private String typedecarburant;
    private String categorievehicule;
    private String typeboitevitesse;
    private PostTroc postTroc;
    private int postid;

    public void setPostTroc(PostTroc postTroc) {
        this.postTroc = postTroc;
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public PostTroc getPostTroc() {
        return postTroc;
    }
//constructeur sans paramétres

    public DemandeTroc() {}


    //constructeur avec paramétres

    public DemandeTroc( int postid,Date annee, Date daterdv, Time heurerdv, double kilometrage, String description, String image, String mail,  String matricule, String marque, String modele, String typedecarburant, String categorievehicule, String typeboitevitesse) {
       this.postid=postid;
        this.annee = annee;
        this.daterdv = daterdv;
        this.heurerdv = heurerdv;
        this.kilometrage = kilometrage;
        this.description = description;
        this.image = image;
        this.mail = mail;
        this.matricule = matricule;
        this.marque = marque;
        this.modele = modele;
        this.typedecarburant = typedecarburant;
        this.categorievehicule = categorievehicule;
        this.typeboitevitesse = typeboitevitesse;
    }


    //geters

    public int getId() {
        return id;
    }

    public int getUserdemande_id() {
        return userdemande_id;
    }

    public int getIdposttroc_id() {
        return idposttroc_id;
    }

    public Date getAnnee() {
        return annee;
    }

    public Date getDaterdv() {
        return daterdv;
    }

    public Time getHeurerdv() {
        return heurerdv;
    }

    public double getKilometrage() {
        return kilometrage;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getMail() {
        return mail;
    }


    public String getMatricule() {
        return matricule;
    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public String getTypedecarburant() {
        return typedecarburant;
    }

    public String getCategorievehicule() {
        return categorievehicule;
    }

    public String getTypeboitevitesse() {
        return typeboitevitesse;
    }

    //seters


    public void setId(int id) {
        this.id = id;
    }

    public void setUserdemande_id(int userdemande_id) {
        this.userdemande_id = userdemande_id;
    }

    public void setIdposttroc_id(int idposttroc_id) {
        this.idposttroc_id = idposttroc_id;
    }

    public void setAnnee(Date annee) {
        this.annee = annee;
    }

    public void setDaterdv(Date daterdv) {
        this.daterdv = daterdv;
    }

    public void setHeurerdv(Time heurerdv) {
        this.heurerdv = heurerdv;
    }

    public void setKilometrage(double kilometrage) {
        this.kilometrage = kilometrage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public void setTypedecarburant(String typedecarburant) {
        this.typedecarburant = typedecarburant;
    }

    public void setCategorievehicule(String categorievehicule) {
        this.categorievehicule = categorievehicule;
    }

    public void setTypeboitevitesse(String typeboitevitesse) {
        this.typeboitevitesse = typeboitevitesse;
    }

    //tostring


    @Override
    public String toString() {
        return
                "  annee=" + annee +
                ",\n daterdv=" + daterdv +
                ",\n heurerdv=" + heurerdv +
                ", \n kilometrage=" + kilometrage +
                ",\n description='" + description + '\'' +
                ", \n mail='" + mail + '\'' +
                ", \n matricule='" + matricule + '\'' +
                ", \n marque=" + marque +
                ", \n modele=" + modele +
                ", \n typedecarburant=" + typedecarburant +
                ", \n categorievehicule=" + categorievehicule +
                ", \n typeboitevitesse=" + typeboitevitesse
                ;
    }
}
