package Services;

import Entities.DemandeTroc;
import Entities.PostTroc;
import Interfaces.DemandetrocService;
import Tools.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;

public class DemnadeTrocService implements DemandetrocService <DemandeTroc> {
    @Override
    public void addDemande(DemandeTroc demande) {
        String requete = "INSERT INTO demandetroc (idposttroc_id, annee, daterdv, heurerdv, kilometrage, description, image, mail, matricule, marque, modele, typedecarburant, categorievehicule, typeboitevitesse,userdemande_id) \n" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);"; // Ajoutez une colonne pour le chemin d'accès de l'image
        try {
            PreparedStatement pst = MyConnection.getInstace().getCnx().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, demande.getPostid()); // Assuming getId() returns the ID of the related PostTroc
            pst.setDate(2, new java.sql.Date(demande.getAnnee().getTime()));
            pst.setDate(3, new java.sql.Date(demande.getDaterdv().getTime()));
            pst.setTime(4, demande.getHeurerdv());
            pst.setDouble(5, demande.getKilometrage());
            pst.setString(6, demande.getDescription());
            pst.setString(7, demande.getImage());
            pst.setString(8, demande.getMail());
            pst.setString(9, demande.getMatricule());
            pst.setString(10, demande.getMarque());
            pst.setString(11, demande.getModele());
            pst.setString(12, demande.getTypedecarburant());
            pst.setString(13, demande.getCategorievehicule());
            pst.setString(14, demande.getTypeboitevitesse());
            pst.setInt(15, 1); // Remplacez ceci par l'ID de l'utilisateur connecté (si vous en avez un)

            pst.executeUpdate();

            // Récupérer l'identifiant auto-incrémenté généré par la base de données
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                int usertroc_id = rs.getInt(1);
                System.out.println("L'identifiant de l'utilisateur connecté est : " + usertroc_id);
            }

            System.out.println("PostTroc ajouté avec succès");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du PostTroc : " + e.getMessage());
        }
    }


    @Override
    public void deleteDemande(DemandeTroc demandeTroc) {
        String requete = "DELETE FROM demandetroc WHERE id = ?";
        try (PreparedStatement pst = MyConnection.getInstace().getCnx().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, demandeTroc.getId());
            pst.execute();
            pst.close();
            MyConnection.closeconnection();
            System.out.println("DemandeTroc supprimé avec succès : " + demandeTroc.getId());
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du Demande Troc : " + e.getMessage());
        }
    }

    public void updateDemande(DemandeTroc demande) {
        String requete = "UPDATE demandetroc SET annee=?, daterdv=?, heurerdv=?, kilometrage=?, description=?, image=?, mail=?, message=?, matricule=?, marque=?, modele=?, typedecarburant=?, categorievehicule=?, typeboitevitesse=? WHERE id=?";
        try {
            PreparedStatement pst = MyConnection.getInstace().getCnx().prepareStatement(requete);

            pst.setDate(1, new java.sql.Date(demande.getAnnee().getTime()));
            pst.setDate(2, new java.sql.Date(demande.getDaterdv().getTime()));
            pst.setTime(3, demande.getHeurerdv());
            pst.setDouble(4, demande.getKilometrage());
            pst.setString(5, demande.getDescription());
            pst.setString(6, demande.getImage());
            pst.setString(7, demande.getMail());
            pst.setString(9, demande.getMatricule());
            pst.setString(10, demande.getMarque());
            pst.setString(11, demande.getModele());
            pst.setString(12, demande.getTypedecarburant());
            pst.setString(13, demande.getCategorievehicule());
            pst.setString(14, demande.getTypeboitevitesse());
            pst.setInt(15, demande.getId());

            pst.executeUpdate();

            System.out.println("Mise à jour de la demande de troc effectuée avec succès");
            pst.close();
            MyConnection.closeconnection();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la demande de troc : " + e.getMessage());
        }
    }


    @Override
    public ObservableList<DemandeTroc> getAllDemandes() {
        ObservableList<DemandeTroc> data = FXCollections.observableArrayList();
        String requete = "SELECT * FROM demandetroc";
        try {
            Statement st = MyConnection.getInstace().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                DemandeTroc demande = new DemandeTroc();
                demande.setId(rs.getInt("id"));
                demande.setAnnee(rs.getDate("annee"));
                demande.setDaterdv(rs.getDate("daterdv"));
                demande.setHeurerdv(rs.getTime("heurerdv"));
                demande.setKilometrage(rs.getDouble("kilometrage"));
                demande.setDescription(rs.getString("description"));
                demande.setImage(rs.getString("image"));
                demande.setMail(rs.getString("mail"));
                demande.setMatricule(rs.getString("matricule"));
                demande.setMarque(rs.getString("marque"));
                demande.setModele(rs.getString("modele"));
                demande.setTypedecarburant(rs.getString("typedecarburant"));
                demande.setCategorievehicule(rs.getString("categorievehicule"));
                demande.setTypeboitevitesse(rs.getString("typeboitevitesse"));
                data.add(demande);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public DemandeTroc getDemandeById(int id) {
        DemandeTroc demande = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Établir une connexion à la base de données
            connection = MyConnection.getInstace().getCnx();

            // Requête SQL pour sélectionner une DemandeTroc par son ID
            String query = "SELECT * FROM demandetroc WHERE id=?";

            // Préparer la requête avec le paramètre d'ID
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            // Exécuter la requête
            resultSet = statement.executeQuery();

            // Vérifier s'il y a un résultat
            if (resultSet.next()) {
                // Créer un objet DemandeTroc avec les données du résultat
                demande = new DemandeTroc();
                demande.setId(resultSet.getInt("id"));
                demande.setAnnee(resultSet.getDate("annee"));
                demande.setDaterdv(resultSet.getDate("daterdv"));
                demande.setHeurerdv(resultSet.getTime("heurerdv"));
                demande.setKilometrage(resultSet.getDouble("kilometrage"));
                demande.setDescription(resultSet.getString("description"));
                demande.setImage(resultSet.getString("image"));
                demande.setMail(resultSet.getString("mail"));
                demande.setMatricule(resultSet.getString("matricule"));
                demande.setMarque(resultSet.getString("marque"));
                demande.setModele(resultSet.getString("modele"));
                demande.setTypedecarburant(resultSet.getString("typedecarburant"));
                demande.setCategorievehicule(resultSet.getString("categorievehicule"));
                demande.setTypeboitevitesse(resultSet.getString("typeboitevitesse"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la demande de troc : " + e.getMessage());
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

        return demande;
    }

    @Override
    public ObservableList<DemandeTroc> getDemandesByPostid(int id) {
        ObservableList<DemandeTroc> data = FXCollections.observableArrayList();
        String requete = "SELECT * FROM demandetroc WHERE idposttroc_id = ?";
        try (PreparedStatement st = MyConnection.getInstace().getCnx().prepareStatement(requete)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    DemandeTroc demande = new DemandeTroc();
                    demande.setId(rs.getInt("id"));
                    demande.setAnnee(rs.getDate("annee"));
                    demande.setDaterdv(rs.getDate("daterdv"));
                    demande.setHeurerdv(rs.getTime("heurerdv"));
                    demande.setKilometrage(rs.getDouble("kilometrage"));
                    demande.setDescription(rs.getString("description"));
                    demande.setImage(rs.getString("image"));
                    demande.setMail(rs.getString("mail"));
                    demande.setMatricule(rs.getString("matricule"));
                    demande.setMarque(rs.getString("marque"));
                    demande.setModele(rs.getString("modele"));
                    demande.setTypedecarburant(rs.getString("typedecarburant"));
                    demande.setCategorievehicule(rs.getString("categorievehicule"));
                    demande.setTypeboitevitesse(rs.getString("typeboitevitesse"));
                    data.add(demande);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public DemandeTroc getDemandeByPostid(int id) {
        DemandeTroc demande = null; // Déclarer la variable en dehors du bloc try
        String requete = "SELECT * FROM demandetroc WHERE idposttroc_id = ?";
        try (PreparedStatement st = MyConnection.getInstace().getCnx().prepareStatement(requete)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) { // Vérifier si le résultat contient des lignes
                    demande = new DemandeTroc(); // Instancier l'objet demande
                    demande.setId(rs.getInt("id"));
                    demande.setAnnee(rs.getDate("annee"));
                    demande.setDaterdv(rs.getDate("daterdv"));
                    demande.setHeurerdv(rs.getTime("heurerdv"));
                    demande.setKilometrage(rs.getDouble("kilometrage"));
                    demande.setDescription(rs.getString("description"));
                    demande.setImage(rs.getString("image"));
                    demande.setMail(rs.getString("mail"));
                    demande.setMatricule(rs.getString("matricule"));
                    demande.setMarque(rs.getString("marque"));
                    demande.setModele(rs.getString("modele"));
                    demande.setTypedecarburant(rs.getString("typedecarburant"));
                    demande.setCategorievehicule(rs.getString("categorievehicule"));
                    demande.setTypeboitevitesse(rs.getString("typeboitevitesse"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return demande;
    }
}