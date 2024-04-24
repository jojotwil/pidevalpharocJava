package alpharoc.pidev.services;

import alpharoc.pidev.entities.demandeloca;
import alpharoc.pidev.interfaces.Idemandeloca;
import alpharoc.pidev.tools.MyConnexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class demandelocaService implements Idemandeloca<demandeloca> {
    public void addEntity2(demandeloca demandeloca) {
        String requete = "INSERT INTO demandeloca (loca_id, user_id, datedebut, datefin, description)"
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst =
                    MyConnexion.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, demandeloca.getLoca_id());
            pst.setInt(2, demandeloca.getUser_id());
            pst.setDate(3, new java.sql.Date(demandeloca.getDatedebut().getTime()));
            pst.setDate(4, new java.sql.Date(demandeloca.getDatefin().getTime()));
            pst.setString(5, demandeloca.getDescription());
            pst.executeUpdate();
            System.out.println("demandeloca added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(demandeloca demandeloca) {
        if (!demandelocaExists(demandeloca.getId())) {
            return;
        }
        String query = "UPDATE demandeloca SET loca_id = ?, user_id = ?, datedebut = ?, datefin = ?, description = ? WHERE id = ?";
        try (PreparedStatement ps = MyConnexion.getInstance().getCnx().prepareStatement(query)) {
            ps.setInt(1, demandeloca.getLoca_id());
            ps.setInt(2, demandeloca.getUser_id());
            ps.setDate(3, new java.sql.Date(demandeloca.getDatedebut().getTime()));
            ps.setDate(4, new java.sql.Date(demandeloca.getDatefin().getTime()));
            ps.setString(5, demandeloca.getDescription());
            ps.setInt(6, demandeloca.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String requete = "DELETE FROM demandeloca WHERE id = ?";
        try {
            PreparedStatement pst = MyConnexion.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("demandeloca with id " + id + " deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<demandeloca> getAllData() {
        List<demandeloca> data = new ArrayList<>();
        String requete = "SELECT * FROM demandeloca";
        try {
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                demandeloca demandeloca = new demandeloca();
                demandeloca.setId(rs.getInt(1));
                demandeloca.setLoca_id(rs.getInt("loca_id"));
                demandeloca.setUser_id(rs.getInt("user_id"));
                demandeloca.setDatedebut(rs.getDate("datedebut"));
                demandeloca.setDatefin(rs.getDate("datefin"));
                demandeloca.setDescription(rs.getString("description"));
                data.add(demandeloca);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    public boolean demandelocaExists(int id) {
        String query = "SELECT COUNT(*) FROM demandeloca WHERE id = ?";
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
}
