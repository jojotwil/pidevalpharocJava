package services;

import tools.MyConnexion;
import entities.Produit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProduitService implements IProduit<Produit> {

public static int xyz=0;
    @Override
    public void addEntity(Produit produit) {
        String requete = "INSERT INTO produit (titre, prix, image, description, category, type, boutique_id, commande_id)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst =
                    MyConnexion.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, produit.getTitre());
            pst.setFloat(2, produit.getPrix());
            pst.setString(3, produit.getImage());
            pst.setString(4, produit.getDescription());
            pst.setString(5, produit.getCategory());
            pst.setString(6, produit.getType());
            pst.setInt(7, produit.getBoutique_id());
            pst.setInt(8, produit.getCommande_id());
            pst.executeUpdate();
            System.out.println("Produit added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteEntity(int id) throws SQLException {
        String requete = "DELETE FROM produit WHERE id = ?";
        try {
            PreparedStatement pst = MyConnexion.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Produit suprimée !");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void updateEntity(Produit produit) {
        try {
            String requete = "UPDATE produit  SET titre=?, prix=?, description=?, category=?, type=?, boutique_id=?, commande_id=? WHERE id=?";
            PreparedStatement pst = MyConnexion.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, produit.getTitre());
            pst.setFloat(2, produit.getPrix());
            pst.setString(3, produit.getDescription());
            pst.setString(4, produit.getCategory());
            pst.setString(5, produit.getType());
            pst.setInt(6, produit.getBoutique_id());
            pst.setInt(7, produit.getCommande_id());
            pst.setInt(8, produit.getId());
            pst.executeUpdate();
            System.out.println("Produit updated");
            pst.executeUpdate();
            System.out.println("Produit Modifiée !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public List<Produit> getAllData() {

        List<Produit> data = new ArrayList<>();
        String requete = "SELECT * FROM produit";
        try {
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Produit p = new Produit();
                p.setId(rs.getInt("id"));
                p.setTitre(rs.getString("titre"));
                p.setPrix(rs.getFloat("prix"));
                p.setImage(rs.getString("image"));
                p.setDescription(rs.getString("description"));
                p.setCategory(rs.getString("category"));
                p.setType(rs.getString("type"));
                p.setBoutique_id(rs.getInt("boutique_id"));
                p.setCommande_id(rs.getInt("commande_id"));
                data.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return data;
    }
    public String getTitreProduit(int idProduit) {
        String titreProduit = null;
        String requete = "SELECT titre FROM produit WHERE id = ?";
        try {
            PreparedStatement pst = MyConnexion.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, idProduit);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                titreProduit = rs.getString("titre");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return titreProduit;
    }

    public Produit getProduitById(int id) {

        Produit prod = null;
        String requete = "SELECT * FROM `produit` WHERE `id` = '" + id + "'";

        try {
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                 prod = new Produit();
                prod.setId(rs.getInt("id"));
                prod.setTitre(rs.getString("titre"));
                prod.setPrix(rs.getFloat("prix"));
                prod.setImage(rs.getString("image"));
                prod.setDescription(rs.getString("description"));
                prod.setCategory(rs.getString("category"));
                prod.setType(rs.getString("type"));
                prod.setBoutique_id(rs.getInt("boutique_id"));
                System.out.println(("****"+rs.getInt("boutique_id")));
                xyz=rs.getInt("boutique_id");
                prod.setCommande_id(rs.getInt("commande_id"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return prod;
    }

    @Override
    public String getNomProduit(int id) {
        String nom = "";
        String requete = "SELECT * FROM `produit`  WHERE `produit`.`id` =' " + id + "'";
        try {

            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                nom = rs.getString("titre");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(nom);
        return nom;
    }

    public int countProductsInBoutique(int boutiqueId) {
        int count = 0;
        String query = "SELECT COUNT(*) AS count FROM produit WHERE boutique_id = ?";
        try {
            PreparedStatement pst = MyConnexion.getInstance().getCnx().prepareStatement(query);
            pst.setInt(1, boutiqueId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return count;
    }

    public int countProductsInBoutiques(int boutiqueId) {
        int count = 0;
        String query = "SELECT COUNT(*) AS count FROM produit WHERE boutique_id = ?";
        try {
            PreparedStatement pst = MyConnexion.getInstance().getCnx().prepareStatement(query);
            pst.setInt(1, boutiqueId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return count;
    }
}
