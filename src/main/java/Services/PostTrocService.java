package Services;

import Entities.*;
import Interfaces.PosttrocService;
import Tools.MyConnection;
import com.example.demo.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PostTrocService implements PosttrocService <PostTroc> {
    String loggedInUserEmail = DBUtils.getLoggedInUserEmail();
    UserService serviceuser=new UserService();
    User user= serviceuser.getuserfromemail(loggedInUserEmail);

    @Override
    public void addPost(PostTroc post) {
        String requete = "INSERT INTO vehiculetroc (annee, kilometrage, description, localisation, image, mail, matricule, marque, modele, typecarburant, categorievehicule, typeboitevitesse,usertroc_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        try {
            PreparedStatement pst = MyConnection.getInstace().getCnx().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            pst.setDate(1, new java.sql.Date(post.getAnnee().getTime()));
            pst.setDouble(2, post.getKilometrage());
            pst.setString(3, post.getDescription());
            pst.setString(4, post.getLocalisation());
            pst.setString(5, post.getImage());
            pst.setString(6, post.getMail());
            pst.setString(7, post.getMatricule());
            pst.setString(8, post.getMarque());
            pst.setString(9, post.getModele());
            pst.setString(10, post.getTypecarburant() );
            pst.setString(11, post.getCategorievehicule());
            pst.setString(12, post.getTypeboitevitesse());
            pst.setInt(13, user.getId());



            pst.executeUpdate();

            // Récupérer l'identifiant auto-incrémenté généré par la base de données
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                int usertroc_id = rs.getInt(user.getId());
                System.out.println("L'identifiant de l'utilisateur connecté est : " +user.getId() );
            }

            System.out.println("PostTroc ajouté avec succès");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du PostTroc : " + e.getMessage());
        }
    }
    @Override
    public void deletePost(PostTroc postTroc) {
        try {
            // Supprimer les enregistrements associés dans la table demandetroc
            String deleteDemandeQuery = "DELETE FROM demandetroc WHERE idposttroc_id = ?";
            try (PreparedStatement pstDemande = MyConnection.getInstace().getCnx().prepareStatement(deleteDemandeQuery)) {
                pstDemande.setInt(1, postTroc.getId());
                pstDemande.executeUpdate();
            }

            // Supprimer l'enregistrement dans la table vehiculetroc
            String deletePostTrocQuery = "DELETE FROM vehiculetroc WHERE id = ?";
            try (PreparedStatement pstPostTroc = MyConnection.getInstace().getCnx().prepareStatement(deletePostTrocQuery)) {
                pstPostTroc.setInt(1, postTroc.getId());
                pstPostTroc.executeUpdate();
            }

            System.out.println("PostTroc supprimé avec succès : " + postTroc.getId());
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du PostTroc : " + e.getMessage());
        }
    }

    @Override
    // Méthode pour mettre à jour un PostTroc
    public void updatePost(PostTroc postTroc) {
        String requete="UPDATE vehiculetroc SET annee=?, kilometrage=?, description=?, localisation=?, image=?, mail=?, matricule=?, marque=?, modele=?, typecarburant=?, categorievehicule=?, typeboitevitesse=?, usertroc_id=? WHERE id=?";
        try{
            PreparedStatement pst = MyConnection.getInstace().getCnx().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);

            pst.setDate(1, new java.sql.Date(postTroc.getAnnee().getTime()));
            pst.setDouble(2, postTroc.getKilometrage());
            pst.setString(3, postTroc.getDescription());
            pst.setString(4, postTroc.getLocalisation());
            pst.setString(5, postTroc.getImage());
            pst.setString(6, postTroc.getMail());
            pst.setString(7, postTroc.getMatricule());
            pst.setString(8, postTroc.getMarque());
            pst.setString(9, postTroc.getModele());
            pst.setString(10, postTroc.getTypecarburant());
            pst.setString(11, postTroc.getCategorievehicule());
            pst.setString(12, postTroc.getTypeboitevitesse());
            pst.setInt(13, 1); // Utilisation de l'ID pour la clause WHERE
            pst.setInt(14, postTroc.getId()); // Utilisation de l'ID pour la clause WHERE

            pst.execute();


            System.out.println("Mise à jour du PostTroc effectuée avec succès");
            pst.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du PostTroc : " + e.getMessage());
        }
    }



    @Override
    public  ObservableList<PostTroc> getAllpostes() {
        ObservableList<PostTroc> Data = FXCollections.observableArrayList();
        String requete ="SELECT * FROM vehiculetroc ";
        try {
            Statement st = MyConnection.getInstace().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()){
                PostTroc p = new PostTroc();
                p.setId(rs.getInt("id"));
                p.setAnnee(rs.getDate("annee"));
                p.setKilometrage(rs.getDouble("kilometrage"));
                p.setDescription(rs.getString("description"));
                p.setLocalisation(rs.getString("localisation"));
                p.setImage(rs.getString("image"));
                p.setMail(rs.getString("mail"));
                p.setMatricule(rs.getString("matricule"));
                p.setMarque(rs.getString("marque"));
                p.setModele(rs.getString("modele"));
                p.setTypecarburant(rs.getString("typecarburant"));
                p.setCategorievehicule(rs.getString("categorievehicule"));
                p.setTypeboitevitesse(rs.getString("typeboitevitesse"));
                p.setUser(rs.getInt("usertroc_id"));
                Data.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Data;
    }


    public static ObservableList<PostTroc> getAllposte() {
        ObservableList<PostTroc> Data = FXCollections.observableArrayList();
        String requete ="SELECT * FROM vehiculetroc ";
        try {
            Statement st = MyConnection.getInstace().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()){
                PostTroc p = new PostTroc();
                p.setId(rs.getInt("id"));
                p.setAnnee(rs.getDate("annee"));
                p.setKilometrage(rs.getDouble("kilometrage"));
                p.setDescription(rs.getString("description"));
                p.setLocalisation(rs.getString("localisation"));
                p.setImage(rs.getString("image"));
                p.setMail(rs.getString("mail"));
                p.setMatricule(rs.getString("matricule"));
                p.setMarque(rs.getString("marque"));
                p.setModele(rs.getString("modele"));
                p.setTypecarburant(rs.getString("typecarburant"));
                p.setCategorievehicule(rs.getString("categorievehicule"));
                p.setTypeboitevitesse(rs.getString("typeboitevitesse"));
                Data.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Data;
    }
    public static ObservableList<PostTroc> getAllPostByIdUser(int userId) {
        ObservableList<PostTroc> data = FXCollections.observableArrayList();
        String query = "SELECT * FROM vehiculetroc WHERE usertroc_id = ?";

        try {
            Connection connection = MyConnection.getInstace().getCnx();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                PostTroc post = new PostTroc();
                post.setId(resultSet.getInt("id"));
                post.setAnnee(resultSet.getDate("annee"));
                post.setKilometrage(resultSet.getDouble("kilometrage"));
                post.setDescription(resultSet.getString("description"));
                post.setLocalisation(resultSet.getString("localisation"));
                post.setImage(resultSet.getString("image"));
                post.setMail(resultSet.getString("mail"));
                post.setMatricule(resultSet.getString("matricule"));
                post.setMarque(resultSet.getString("marque"));
                post.setModele(resultSet.getString("modele"));
                post.setTypecarburant(resultSet.getString("typecarburant"));
                post.setCategorievehicule(resultSet.getString("categorievehicule"));
                post.setTypeboitevitesse(resultSet.getString("typeboitevitesse"));
                post.setUser(resultSet.getInt("usertroc_id"));
                data.add(post);
            }

            // Fermer les ressources
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    public PostTroc getPostById(int id) {
        PostTroc postTroc = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Établir une connexion à la base de données
            connection = MyConnection.getInstace().getCnx();

            // Requête SQL pour sélectionner un PostTroc par son ID
            String query = "SELECT * FROM vehiculetroc WHERE id=?";

            // Préparer la requête avec le paramètre d'ID
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            // Exécuter la requête
            resultSet = statement.executeQuery();

            // Vérifier s'il y a un résultat
            if (resultSet.next()) {
                // Créer un objet PostTroc avec les données du résultat
                postTroc = new PostTroc();
                postTroc.setId(resultSet.getInt("id"));
                postTroc.setAnnee(resultSet.getDate("annee"));
                postTroc.setKilometrage(resultSet.getDouble("kilometrage"));
                postTroc.setDescription(resultSet.getString("description"));
                postTroc.setLocalisation(resultSet.getString("localisation"));
                postTroc.setImage(resultSet.getString("image"));
                postTroc.setMail(resultSet.getString("mail"));
                postTroc.setMatricule(resultSet.getString("matricule"));
                postTroc.setMarque(resultSet.getString("marque"));
                postTroc.setModele(resultSet.getString("modele"));
                postTroc.setTypecarburant(resultSet.getString("typecarburant"));
                postTroc.setCategorievehicule(resultSet.getString("categorievehicule"));
                postTroc.setTypeboitevitesse(resultSet.getString("typeboitevitesse"));
                postTroc.setUser(resultSet.getInt("usertroc_id"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du PostTroc : " + e.getMessage());

        }

        return postTroc;
    }
}
