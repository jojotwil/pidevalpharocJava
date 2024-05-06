package Services;

import Entities.Ticket;
import Entities.User;
import Utils.DataSource;
import com.example.demo.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicketService {
    private Connection conn;
    String loggedInUserEmail = DBUtils.getLoggedInUserEmail();
    UserService serviceuser=new UserService();
    User user= serviceuser.getuserfromemail(loggedInUserEmail);


    public TicketService() {
        conn = DataSource.getInstance().getCnx();
    }

    public void insert(Ticket t) {
        String requete = "INSERT INTO reservation (event_id, user_id, prix, type_tichet) VALUES (?, ?, ?, ?)";
        System.out.println(t.getEvent_id());
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, t.getEvent_id());
            pst.setInt(2, user.getId());
            pst.setDouble(3, t.getPrix());
            pst.setString(4, t.getType_ticket());
            pst.executeUpdate();
            System.out.println("Ticket ajouté avec succès !");
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Ticket t) {
        String requete = "UPDATE reservation SET event_id=?, user_id=?, prix=?, type_tichet=? WHERE id=?";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, t.getEvent_id());
            pst.setInt(2, user.getId());
            pst.setDouble(3, t.getPrix());
            pst.setString(4, t.getType_ticket());
            pst.setInt(5, t.getId());
            pst.executeUpdate();
            System.out.println("Ticket mis à jour avec succès !");
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int id) {
        String requete = "DELETE FROM reservation WHERE id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Ticket supprimé avec succès !");
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Ticket readById(int id) {
        String requete = "SELECT * FROM reservation WHERE id = ?";
        Ticket t = null;
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                t = new Ticket(
                        rs.getInt("id"),
                        rs.getInt("event_id"),
                        rs.getInt("user_id"),
                        rs.getDouble("prix"),
                        rs.getString("type_ticket")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    public ArrayList<Ticket> readAll() {
        String requete = "SELECT * FROM reservation";
        ArrayList<Ticket> list = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Ticket t = new Ticket(
                        rs.getInt("id"),
                        rs.getInt("event_id"),
                        rs.getInt("user_id"),
                        rs.getDouble("prix"),
                        rs.getString("type_ticket")
                );
                list.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Ticket> getAllByEventId(int eventId) {
        String requete = "SELECT * FROM reservation WHERE event_id = ?";
        ArrayList<Ticket> tickets = new ArrayList<>();
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, eventId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Ticket t = new Ticket(
                        rs.getInt("id"),
                        rs.getInt("event_id"),
                        rs.getInt("user_id"),
                        rs.getDouble("prix"),
                        rs.getString("type_tichet")
                );
                tickets.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tickets;
    }
}
