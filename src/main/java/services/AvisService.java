package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.Avis;
import Utils.DataSource;

public class AvisService {

    private Connection connection;

    public AvisService() {
        this.connection = DataSource.getInstance().getCnx();
    }

    // Méthode pour créer un nouvel avis dans la base de données
    public void create(Avis avis) {
        PreparedStatement preparedStatement = null;

        try {
            // Définir la requête SQL pour insérer un nouvel avis dans la table des avis
            String query = "INSERT INTO avis (event_id, note, commentaire) VALUES (?, ?, ?)";

            // Préparer la déclaration SQL avec les valeurs à insérer
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, avis.getEvent_id());
            preparedStatement.setInt(2, avis.getNote());
            preparedStatement.setString(3, avis.getCommentaire());

            // Exécuter la requête pour insérer l'avis dans la base de données
            preparedStatement.executeUpdate();

            // Afficher un message indiquant que l'avis a été inséré avec succès
            System.out.println("Avis ajouté avec succès dans la base de données.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer la déclaration SQL
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Méthode pour récupérer tous les avis associés à un événement spécifique
    public List<Avis> getAvisByEventId(int eventId) {
        List<Avis> avisList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Définir la requête SQL pour récupérer les avis associés à un événement spécifique
            String query = "SELECT * FROM avis WHERE event_id = ?";

            // Préparer la déclaration SQL avec la valeur de l'ID de l'événement
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, eventId);

            // Exécuter la requête SQL
            resultSet = preparedStatement.executeQuery();

            // Parcourir les résultats et créer des objets Avis correspondants
            while (resultSet.next()) {
                Avis avis = new Avis();
                avis.setId(resultSet.getInt("id"));
                avis.setEvent_id(resultSet.getInt("event_id"));
                avis.setNote(resultSet.getInt("note"));
                avis.setCommentaire(resultSet.getString("commentaire"));
                avisList.add(avis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer la déclaration SQL et le ResultSet
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return avisList;
    }

    public List<Avis> getAvisForEvenement(int eventId) {
        List<Avis> avisList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Définir la requête SQL pour récupérer les avis associés à un événement spécifique
            String query = "SELECT * FROM avis WHERE event_id = ?";

            // Préparer la déclaration SQL avec la valeur de l'ID de l'événement
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, eventId);

            // Exécuter la requête SQL
            resultSet = preparedStatement.executeQuery();

            // Parcourir les résultats et créer des objets Avis correspondants
            while (resultSet.next()) {
                Avis avis = new Avis();
                avis.setId(resultSet.getInt("id"));
                avis.setEvent_id(resultSet.getInt("event_id"));
                avis.setNote(resultSet.getInt("note"));
                avis.setCommentaire(resultSet.getString("commentaire"));
                avisList.add(avis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer la déclaration SQL et le ResultSet
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return avisList;
    }

}
