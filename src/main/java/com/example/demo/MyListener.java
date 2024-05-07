package com.example.demo;





import Entities.Boutique;
import Entities.Produit;
import Entities.Servicerep;

public interface MyListener {
    public void onClickListener(Servicerep servicerep);
    void onServiceDeleted(Servicerep servicerep);
    public void onClickListener(Boutique boutique);

    public void onClickListener(Produit prod);
    void onBoutiqueDeleted(Boutique boutique);
    void onProduitDeleted(Produit prod);

    void onBoutiqueModified(String nouveauNom, String nouvelleDescription, String nouvelleVille, String nouveauGouvernorat, int nouveauNumeroTel, int user_id);

}
