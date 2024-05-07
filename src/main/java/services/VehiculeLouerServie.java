package Services;


import Entities.VehiculeLouer;
import Interfaces.IVL;
import Tools.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VehiculeLouerServie implements IVL<VehiculeLouer> {


    @Override
    public void addEntity2(VehiculeLouer vehiculeLouer) {
        String requete = "INSERT INTO vehicule_loue (marque, modele, description, periode_dispo, type_carburant, categorie_vehicule)"
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst =
                    MyConnection.getInstace().getCnx().prepareStatement(requete);
            pst.setString(1, vehiculeLouer.getMarque());
            pst.setString(2, vehiculeLouer.getModele());
            pst.setString(3, vehiculeLouer.getDescription());
            pst.setDate(4, new java.sql.Date(vehiculeLouer.getPeriode_dispo().getTime()));
            pst.setString(5, vehiculeLouer.getType_carburant());
            pst.setString(6, vehiculeLouer.getCategorie_vehicule());
            pst.executeUpdate();
            System.out.println("VehiculeLouer added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void update(VehiculeLouer vehiculeLouer) {
        if (vehiculeLouerExists(vehiculeLouer.getId()))
        {return ;}
        String query = "UPDATE vehicule_loue SET marque = ?, modele = ?, description = ?, periode_dispo = ?, type_carburant = ?, categorie_vehicule = ? WHERE id = ?";
        try (PreparedStatement ps =MyConnection.getInstace().getCnx().prepareStatement(query)) {
            ps.setString(1, vehiculeLouer.getMarque());
            ps.setString(2, vehiculeLouer.getModele());
            ps.setString(3, vehiculeLouer.getDescription());
            ps.setDate(4, new java.sql.Date(vehiculeLouer.getPeriode_dispo().getTime()));
            ps.setString(5, vehiculeLouer.getType_carburant());
            ps.setString(6, vehiculeLouer.getCategorie_vehicule());
            ps.setInt(7, vehiculeLouer.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            // Handle the exception appropriately
            e.printStackTrace();
        }
    }


    @Override
    public void delete(int id) {
        String req= "DELETE FROM loca WHERE vehiculelouer_id = ?";
        try {
            PreparedStatement ps =MyConnection.getInstace().getCnx().prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String requete = "DELETE FROM vehicule_loue WHERE id = ?";
        try {
            PreparedStatement ps =MyConnection.getInstace().getCnx().prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("VehiculeLouer with id " + id + " deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public boolean vehiculeLouerExists(int id) {
        String query = "SELECT COUNT(*) FROM vehicule_loue WHERE id = ?";
        try (PreparedStatement ps =MyConnection.getInstace().getCnx().prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    if (count>0)
                    {System.out.println("exist");}
                    else
                    {System.out.println("dosen t exist");}
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            // Handle the exception appropriately
            e.printStackTrace();
        }
        return false;
    }
    public List<VehiculeLouer> getAllData() {
        List<VehiculeLouer> data = new ArrayList<>();
        String requete = "SELECT * FROM vehicule_loue";
        try {
            Statement st = MyConnection.getInstace().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                VehiculeLouer vehiculeLouer = new VehiculeLouer();
                vehiculeLouer.setId(rs.getInt(1));
                vehiculeLouer.setMarque(rs.getString("marque"));
                vehiculeLouer.setModele(rs.getString("modele"));
                vehiculeLouer.setDescription(rs.getString("description"));
                vehiculeLouer.setPeriode_dispo(rs.getDate("periode_dispo"));
                vehiculeLouer.setType_carburant(rs.getString("type_carburant"));
                vehiculeLouer.setCategorie_vehicule(rs.getString("categorie_vehicule"));
                data.add(vehiculeLouer);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }


    public ObservableList<VehiculeLouer> getVl(){
        ObservableList<VehiculeLouer> Vl= FXCollections.observableArrayList();
        //  con = ;
        String requete = "SELECT * FROM vehicule_loue";
        try {
            Statement st = MyConnection.getInstace().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()){
                VehiculeLouer vehiculeLouer = new VehiculeLouer();
                vehiculeLouer.setId(rs.getInt(1));
                vehiculeLouer.setMarque(rs.getString("marque"));
                vehiculeLouer.setModele(rs.getString("modele"));
                vehiculeLouer.setDescription(rs.getString("description"));
                vehiculeLouer.setPeriode_dispo(rs.getDate("periode_dispo"));
                vehiculeLouer.setType_carburant(rs.getString("type_carburant"));
                vehiculeLouer.setCategorie_vehicule(rs.getString("categorie_vehicule"));
                Vl.add(vehiculeLouer);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Vl;
    }
    public int getFormationIdB(String marqueV) {
        int vehiculz_id = -1; // Valeur par défaut
        try {
            // Préparez requête SQL pour récupérer l'ID de la formation à partir de son nom
            String sql = "SELECT id FROM vehicule_loue WHERE marque = ?";
            PreparedStatement statement = MyConnection.getInstace().getCnx().prepareStatement(sql);
            statement.setString(1, marqueV);

            // Exécution de  la requête SQL
            ResultSet resultSet = statement.executeQuery();

            // Vérifiez si une formation correspondante est trouvée
            if (resultSet.next()) {
                vehiculz_id= resultSet.getInt("id"); // Récupérez l'ID de la formation
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Gérez l'erreur de connexion ou de requête SQL
        }
        return vehiculz_id;
    }


}
