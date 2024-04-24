package alpharoc.pidev.tests;

import alpharoc.pidev.entities.Personne;
import alpharoc.pidev.entities.VehiculeLouer;
import alpharoc.pidev.entities.demandeloca;
import alpharoc.pidev.entities.loca;
import alpharoc.pidev.services.PersonneService;
import alpharoc.pidev.services.VehiculeLouerServie;
import alpharoc.pidev.services.demandelocaService;
import alpharoc.pidev.services.locaService;
import alpharoc.pidev.tools.MyConnexion;

import java.util.Date;

public class MainClass {
    public static void main(String[] args) {

        //test crud demandeloca
        Date date_debut = new Date(); // Assuming you want to set the current date/time
        Date date_fin = new Date(); // Assuming you want to set the current date/time

        // Creating a new demandeloca object and adding it to the database
        demandeloca demandeloca = new demandeloca(3, 2, date_debut, date_fin,"Description");
        demandelocaService ls = new demandelocaService();
        ls.addEntity2(demandeloca);
        System.out.println(ls.getAllData());

       /* // Updating the description of an existing demandeloca entry
        demandeloca updatedDemandeloca = new demandeloca(3, 1, date_debut, date_fin,"  Updated Description");
        ls.update(updatedDemandeloca);
        System.out.println(ls.getAllData());

        // Deleting a demandeloca entry
        ls.delete(4);
        System.out.println(ls.getAllData());*/
/*
        //test crud loca
        Date date_debut = new Date(); // Assuming you want to set the current date/time
        Date date_fin = new Date(); // Assuming you want to set the current date/time
       loca l = new loca(3, "Description", "Localisation", date_debut, date_fin);
        locaService ls = new locaService();
// ls.addEntity2(l);
    System.out.println(ls.getAllData());
        loca updatedLoca = new loca( 4, "Updated Description", "Updated Localisation", date_debut, date_fin);
        ls.update(updatedLoca);
        System.out.println(ls.getAllData());
// ls.delete(4);
*/
        //test crud VL
     /*   Date periode_dispo = new Date(); // Assuming you want to set the current date/time
        VehiculeLouer p = new VehiculeLouer("Toyota", "Corolla", "Sedan", periode_dispo, "Essence", "Voiture");
        VehiculeLouerServie ps = new VehiculeLouerServie();
       // ps.addEntity2(p);
        System.out.println(ps.getAllData());
        VehiculeLouer a = new VehiculeLouer(2,"Toyota", "dada", "Sedan", periode_dispo, "Essence", "Voiture");

        //ps.delete(a.getId());


        ps.update(a);
        System.out.println(ps.getAllData());
*/
    }
}
