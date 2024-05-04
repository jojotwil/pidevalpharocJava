package Services;

import Entities.User;
import Interfaces.UserServiceInterface;
import Tools.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService implements UserServiceInterface<User> {
    //String loggedInUserEmail = DBUtils.getLoggedInUserEmail();
    @Override
    public User getuserfromemail(String email) {
        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Établir une connexion à la base de données
            connection = MyConnection.getInstace().getCnx();

            // Requête SQL pour sélectionner un PostTroc par son ID
            String query = "SELECT * FROM user WHERE email=?";

            // Préparer la requête avec le paramètre d'ID
            statement = connection.prepareStatement(query);
            statement.setString(1, email);

            // Exécuter la requête
            resultSet = statement.executeQuery();

            // Vérifier s'il y a un résultat
            if (resultSet.next()) {
                // Créer un objet PostTroc avec les données du résultat
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setNom(resultSet.getString("nom"));
                user.setPassword(resultSet.getString("password"));
                user.setPrenom(resultSet.getString("prenom"));
                user.setImage(resultSet.getString("image"));

            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du PostTroc : " + e.getMessage());

        }

        return user;
    }


    public User getUserById(int id) {
        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Établir une connexion à la base de données
            connection = MyConnection.getInstace().getCnx();

            // Requête SQL pour sélectionner un PostTroc par son ID
            String query = "SELECT * FROM user WHERE id=?";

            // Préparer la requête avec le paramètre d'ID
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            // Exécuter la requête
            resultSet = statement.executeQuery();

            // Vérifier s'il y a un résultat
            if (resultSet.next()) {
                // Créer un objet PostTroc avec les données du résultat
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setNom(resultSet.getString("nom"));
                user.setPassword(resultSet.getString("password"));
                user.setPrenom(resultSet.getString("prenom"));
                user.setImage(resultSet.getString("image"));

            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du PostTroc : " + e.getMessage());
        } finally {
            // Fermer les ressources JDBC dans le bloc finally
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture des ressources JDBC : " + e.getMessage());
            }
        }

        return user;
    }
}
