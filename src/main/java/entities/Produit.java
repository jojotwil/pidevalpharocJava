package entities;

public class Produit {
    private int id;
    private String titre;
    private float prix;
    private String image;
    private String description;
    private String category;
    private String type;
    private int boutique_id ;
    private int commande_id ;



    private final float remise = 10;
    public float getRemise() {
        return remise;
    }


    public float getPrixAvecRemise() {
        float prix = getPrix();
        if (getRemise() > 0) {
            return prix - (prix * getRemise() / 100);
        } else {
            return prix;
        }
    }

    public Produit() {
    }

    public Produit(int commande_id) {
        this.commande_id = commande_id;
    }

    public Produit(int id, String titre, float prix, String image, String description, String category, String type, int boutique_id, int commande_id) {
        this.id = id;
        this.titre = titre;
        this.prix = prix;
        this.image = image;
        this.description = description;
        this.category = category;
        this.type = type;
        this.boutique_id = boutique_id;
        this.commande_id = commande_id;
    }

    public Produit(String titre, float prix, String image, String description, String category, String type, int boutique_id, int commande_id) {
        this.titre = titre;
        this.prix = prix;
        this.image = image;
        this.description = description;
        this.category = category;
        this.type = type;
        this.boutique_id = boutique_id;
        this.commande_id = commande_id;
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

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBoutique_id() {
        return boutique_id;
    }

    public void setBoutique_id(int idBoutique) {
        this.boutique_id = boutique_id;
    }

    public int getCommande_id() {
        return commande_id;
    }

    public void setCommande_id(int idCommande) {
        this.commande_id = idCommande;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", prix=" + prix +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", boutique_id=" + boutique_id +
                ", commande_id=" + commande_id +
                '}';
    }

    public void setCommande(Commande commande) {
    }
}