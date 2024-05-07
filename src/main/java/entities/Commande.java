package entities;

import java.util.Collection;
import java.util.HashSet;

public class Commande {
    private int id;

    public Commande(int id) {
        this.id = id;
    }

    public Commande(int commandeId, String titre, float prix) {
    }

    public int getCommande_id() {
        return id;
    }

    public void setCommande_id(int commande_id) {
        this.id = commande_id;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                '}';
    }


    private Collection<Produit> produits = new HashSet<>();

    public Commande() {
        // Initialisation de la collection
    }


    public Collection<Produit> getProduits() {
        return produits;
    }

    public Commande addProduit(Produit produit) {
        if (!produits.contains(produit)) {
            produits.add(produit);
            produit.setCommande(this);
        }
        return this;
    }
    Produit produit ;
    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    }








