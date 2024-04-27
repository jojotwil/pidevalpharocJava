package tests;

import entities.Boutique;
import entities.Produit;
import services.BoutiqueService;
import services.ProduitService;
import tools.MyConnexion;

public class MainClass {
    public static void main(String[] args) {
        Boutique b = new Boutique("hayfa", "chemin/vers/image.jpg", "Description de ma boutique", 12345678, "Gouvernorat", "Ville", 1);
        BoutiqueService bs =new BoutiqueService();

}}
