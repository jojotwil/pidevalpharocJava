package alpharoc.pidev.services;

import alpharoc.pidev.entities.loca;
import alpharoc.pidev.interfaces.Iloca;
import alpharoc.pidev.tools.MyConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class locaService implements Iloca<loca> {
    @Override
    public void addEntity2(loca loca) {
        String requete = "INSERT INTO loca (vehiculelouer_id, description, localisation, date_debut, date_fin)"
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst =
                    MyConnexion.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, loca.getVehichuleLouerid());
            pst.setString(2, loca.getDescription());
            pst.setString(3, loca.getLocalisation());
            pst.setDate(4, new java.sql.Date(loca.getDate_debut().getTime()));
            pst.setDate(5, new java.sql.Date(loca.getDate_fin().getTime()));
            pst.executeUpdate();
            System.out.println("loca added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(loca loca) {
        if (!locaExists(loca.getId())) {
            return;
        }
        String query = "UPDATE loca SET vehiculelouer_id = ?, description = ?, localisation = ?, date_debut = ?, date_fin = ? WHERE id = ?";
        try (PreparedStatement ps = MyConnexion.getInstance().getCnx().prepareStatement(query)) {
            ps.setInt(1, loca.getVehichuleLouerid());
            ps.setString(2, loca.getDescription());
            ps.setString(3, loca.getLocalisation());
            ps.setDate(4, new java.sql.Date(loca.getDate_debut().getTime()));
            ps.setDate(5, new java.sql.Date(loca.getDate_fin().getTime()));
            ps.setInt(6, loca.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String requete = "DELETE FROM loca WHERE id = ?";
        try {
            PreparedStatement pst = MyConnexion.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("loca with id " + id + " deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public boolean locaExists(int id) {
        String query = "SELECT COUNT(*) FROM loca WHERE id = ?";
        try (PreparedStatement ps = MyConnexion.getInstance().getCnx().prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    if (count > 0) {
                        System.out.println("exist");
                    } else {
                        System.out.println("doesn't exist");
                    }
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public List<loca> getAllData() {
        List<loca> data = new ArrayList<>();
        String requete = "SELECT * FROM loca";
        try {
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                loca loca = new loca();
                loca.setId(rs.getInt(1));
                loca.setVehichuleLouerid(rs.getInt("vehiculelouer_id"));
                loca.setDescription(rs.getString("description"));
                loca.setLocalisation(rs.getString("localisation"));
                loca.setDate_debut(rs.getDate("date_debut"));
                loca.setDate_fin(rs.getDate("date_fin"));
                data.add(loca);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    public ObservableList<loca> getloca(){
        ObservableList<loca> data= FXCollections.observableArrayList();
        //  con = ;
        String requete = "SELECT * FROM loca";
        try {
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()){

                loca loca = new loca();
                loca.setId(rs.getInt(1));
                loca.setVehichuleLouerid(rs.getInt("vehiculelouer_id"));
                loca.setDescription(rs.getString("description"));
                loca.setLocalisation(rs.getString("localisation"));
                loca.setDate_debut(rs.getDate("date_debut"));
                loca.setDate_fin(rs.getDate("date_fin"));
                data.add(loca);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
}
