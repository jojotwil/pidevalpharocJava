package Services;

import Entities.Evenement;
import Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EvenementService implements IServices<Evenement> {
    private Connection conn;

    public EvenementService() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(Evenement o) {
        String requete = "INSERT INTO event (titre, theme, localisation, description, date_debut, date_fin) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, o.getTitre());
            pst.setString(2, o.getTheme());
            pst.setString(3, o.getLocalisation());
            pst.setString(4, o.getDescription());
            pst.setDate(5, new java.sql.Date(o.getDate_debut().getTime()));
            pst.setDate(6, new java.sql.Date(o.getDate_fin().getTime()));
            pst.executeUpdate();
            System.out.println("Événement ajouté avec succès !");
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deletePastEvent() {
        try {
            // Get the current date
            java.util.Date currentDate = new java.util.Date();

            // Start transaction
            conn.setAutoCommit(false);

            // Prepare the delete statement for related rows in the reservation table
            String deleteReservationsQuery = "DELETE FROM reservation WHERE event_id IN (SELECT id FROM event WHERE date_fin < ?)";
            PreparedStatement pstReservations = conn.prepareStatement(deleteReservationsQuery);
            pstReservations.setDate(1, new java.sql.Date(currentDate.getTime()));
            int reservationsDeleted = pstReservations.executeUpdate();
            System.out.println(reservationsDeleted + " past reservations deleted from the database.");

            // Prepare the delete statement for related rows in the avis table
            String deleteAvisQuery = "DELETE FROM avis WHERE event_id IN (SELECT id FROM event WHERE date_fin < ?)";
            PreparedStatement pstAvis = conn.prepareStatement(deleteAvisQuery);
            pstAvis.setDate(1, new java.sql.Date(currentDate.getTime()));
            int avisDeleted = pstAvis.executeUpdate();
            System.out.println(avisDeleted + " past avis deleted from the database.");

            // Prepare the delete statement for past events
            String deleteEventsQuery = "DELETE FROM event WHERE date_fin < ?";
            PreparedStatement pstEvents = conn.prepareStatement(deleteEventsQuery);
            pstEvents.setDate(1, new java.sql.Date(currentDate.getTime()));
            int eventsDeleted = pstEvents.executeUpdate();
            System.out.println(eventsDeleted + " past events deleted from the database.");

            // Commit transaction
            conn.commit();
            System.out.println("Transaction committed successfully.");
        } catch (SQLException e) {
            System.err.println("Error deleting past plannings: " + e.getMessage());
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("Transaction is rolled back.");
                }
            } catch (SQLException excep) {
                System.err.println("Error during transaction rollback: " + excep.getMessage());
            }
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true); // Resetting auto commit to true
                }
            } catch (SQLException ex) {
                System.err.println("Error resetting auto commit: " + ex.getMessage());
            }
        }
    }
    @Override
    public void update(Evenement o) {
        String requete = "UPDATE event SET titre=?, theme=?, localisation=?, description=?, date_debut=?, date_fin=? WHERE id=?";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, o.getTitre());
            pst.setString(2, o.getTheme());
            pst.setString(3, o.getLocalisation());
            pst.setString(4, o.getDescription());
            pst.setDate(5, new java.sql.Date(o.getDate_debut().getTime()));
            pst.setDate(6, new java.sql.Date(o.getDate_fin().getTime()));
            pst.setInt(7, o.getId());
            pst.executeUpdate();
            System.out.println("Événement mis à jour avec succès !");
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        String requete1="DELETE FROM reservation WHERE event_id = ?";

        try {
            PreparedStatement pst1 = conn.prepareStatement(requete1);
            pst1.setInt(1, id);
            pst1.executeUpdate();
            System.out.println("Tickets de l'event supprimés avec succès !");
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        String deleteAvisQuery = "DELETE FROM avis WHERE event_id = ?";
        try {
            PreparedStatement deleteAvisStatement = conn.prepareStatement(deleteAvisQuery);
            deleteAvisStatement.setInt(1, id);
            int deletedRows = deleteAvisStatement.executeUpdate();
            System.out.println("Nombre d'avis supprimés : " + deletedRows);
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }



        String requete = "DELETE FROM event WHERE id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Événement supprimé avec succès !");
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Evenement readById(int id) {
        String requete = "SELECT * FROM event WHERE id = ?";
        Evenement e = null;
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                e = new Evenement(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("theme"),
                        rs.getString("localisation"),
                        rs.getString("description"),
                        rs.getDate("date_debut"),
                        rs.getDate("date_fin")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
    }

    @Override
    public ArrayList<Evenement> readAll() {
        String requete = "SELECT * FROM event";
        ArrayList<Evenement> list = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Evenement e = new Evenement(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("theme"),
                        rs.getString("localisation"),
                        rs.getString("description"),
                        rs.getDate("date_debut"),
                        rs.getDate("date_fin")
                );
                list.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
