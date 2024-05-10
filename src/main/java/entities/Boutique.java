package Entities;

public class Boutique {

    private int id ;
    private String nom ;
    private String image ;
    private String description ;
    private int num_telephone ;
    private String gouvernorat ;
    private String ville ;
    private int user_id ;

    public Boutique() {

    }

    public Boutique(int id, String nom, String description, int num_telephone, String gouvernorat, String ville, int user_id) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.num_telephone = num_telephone;
        this.gouvernorat = gouvernorat;
        this.ville = ville;
        this.user_id = user_id;
    }

    public Boutique(int id, String nom, String image, String description, int num_telephone, String gouvernorat, String ville, int user_id) {
        this.id = id;
        this.nom = nom;
        this.image = image;
        this.description = description;
        this.num_telephone = num_telephone;
        this.gouvernorat = gouvernorat;
        this.ville = ville;
        this.user_id = user_id;
    }

    public Boutique(String nom, String image, String description, int num_telephone, String gouvernorat, String ville, int user_id) {
        this.nom = nom;
        this.image = image;
        this.description = description;
        this.num_telephone = num_telephone;
        this.gouvernorat = gouvernorat;
        this.ville = ville;
        this.user_id = user_id;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNum_telephone() {
        return num_telephone;
    }

    public void setNum_telephone(int num_telephone) {
        this.num_telephone = num_telephone;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Boutique{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", num_telephone=" + num_telephone +
                ", gouvernorat='" + gouvernorat + '\'' +
                ", ville='" + ville + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}






