package Services;

import Entities.User;
import Interfaces.UserServiceInterface;
import Tools.MyConnection;
import Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService implements UserServiceInterface<User> {
    private Connection conn;

    public UserService()
    {
        conn = DataSource.getInstance().getCnx();
    }

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

        }

        return user;
    }
    public ArrayList<User> readAll() {
        String requete = "SELECT * FROM user";
        ArrayList<User> list = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("email")

                );
                list.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
