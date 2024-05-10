package Services;

import Tools.MyConnection;
import Entities.Commande;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommandeService implements IService<Commande> {

    @Override
    public void addEntity(Commande commande) {
        String requete = "INSERT INTO commande () VALUES ()"; // Requête d'insertion à compléter selon votre modèle de données
        try {
            PreparedStatement pst = MyConnection.getInstace().getCnx().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                commande.setCommande_id(rs.getInt(1));
            }
            System.out.println("Commande added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteEntity(Commande commande) {
        String requete = "DELETE FROM commande WHERE id = ?";
        try {
            PreparedStatement pst = MyConnection.getInstace().getCnx().prepareStatement(requete);
            pst.setInt(1, commande.getCommande_id());
            pst.executeUpdate();
            System.out.println("Commande deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateEntity(Commande commande, int id) {
        String requete = "UPDATE commande SET id = ? WHERE id = ?";
        try {
            PreparedStatement pst = MyConnection.getInstace().getCnx().prepareStatement(requete);
            pst.setInt(1, commande.getCommande_id()); // Nouvelle valeur de l'ID
            pst.setInt(2, id); // Ancienne valeur de l'ID à mettre à jour
            pst.executeUpdate();
            System.out.println("Commande updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public  List<Commande> getAllData() {
        List<Commande> data = new ArrayList<>();
        String requete = "SELECT * FROM commande";
        try {
            Statement st = MyConnection.getInstace().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Commande c = new Commande(6);
                c.setCommande_id(rs.getInt("id"));
                data.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return data;
    }

}
