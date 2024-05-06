package Main;

import Entities.Evenement;
import Entities.Ticket;
import Entities.User;
import Services.EvenementService;
import Services.TicketService;
import Services.UserService;


import java.text.SimpleDateFormat;


import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        TicketService us = new TicketService();

        EvenementService es = new EvenementService();

        es.delete(3);

        List<Ticket> xx =us.getAllByEventId(1);

        for (Ticket i: xx){

            System.out.println(i);
        }


        
        





                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date dateDebut = sdf.parse("2024-05-01");
                    Date dateFin = sdf.parse("2024-05-05");

                    // Créer un nouvel événement
                    Evenement newEvenement = new Evenement(
                            "Concert de Printemps",
                            "Musique",
                            "Paris, France",
                            "Un concert incroyable pour célébrer le printemps.",
                            new java.sql.Date(dateDebut.getTime()),
                            new java.sql.Date(dateFin.getTime())
                    );

                    // Instancier EvenementService pour interagir avec la base de données
                    EvenementService evenementService = new EvenementService();

                    // Insérer l'événement dans la base de données
                    evenementService.insert(newEvenement);
                    System.out.println("Nouvel événement inséré avec succès!");

                } catch (Exception e) {
                    System.out.println("Erreur lors de la création de l'événement: " + e.getMessage());
                }
            }

    
}
