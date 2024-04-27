package controllers;

import entities.Boutique;
import entities.Produit;

public interface MyListener {
    public void onClickListener(Boutique boutique);

    public void onClickListener(Produit prod);
        void onBoutiqueDeleted(Boutique boutique);
    void onProduitDeleted(Produit prod);

    void onBoutiqueModified(String nouveauNom, String nouvelleDescription, String nouvelleVille, String nouveauGouvernorat, int nouveauNumeroTel, int user_id);
    }




