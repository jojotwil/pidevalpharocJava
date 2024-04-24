package Services;

import Entities.CalendarActivity;
import Interfaces.FullCallenderService;
import Tools.MyConnection;
import javafx.scene.paint.Color;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class FullCalederService implements FullCallenderService<CalendarActivity> {
    // Méthode pour convertir une couleur en format hexadécimal
    public static String toHex(Color color) {
        int r = (int) (color.getRed() * 255);
        int g = (int) (color.getGreen() * 255);
        int b = (int) (color.getBlue() * 255);
        return String.format("#%02x%02x%02x", r, g, b);
    }
    @Override
    public void adddate(CalendarActivity calendarActivity) {
        String requete = "INSERT INTO calendar (calendaruser_id, title, start, end, description, background_color, border_color, text_color,all_day) VALUES (?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement pst = MyConnection.getInstace().getCnx().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, 1); // Supposons que getId() retourne l'ID du PostTroc associé
            pst.setString(2, calendarActivity.getTitle());

            // Conversion de ZonedDateTime en LocalDateTime
            LocalDateTime startDateTime = calendarActivity.getStart().toLocalDateTime();
            LocalDateTime endDateTime = calendarActivity.getEnd().toLocalDateTime();

            // Utilisation de la méthode valueOf() avec LocalDateTime
            pst.setTimestamp(3, java.sql.Timestamp.valueOf(startDateTime));
            pst.setTimestamp(4, java.sql.Timestamp.valueOf(endDateTime));

            pst.setString(5, calendarActivity.getDescription());
            pst.setString(6, toHex(calendarActivity.getBackground_color())); // Convertir la couleur en hexadécimal
            pst.setString(7, toHex(calendarActivity.getBorder_color()));
            pst.setString(8, toHex(calendarActivity.getText_color()));
            pst.setBoolean(9,calendarActivity.isAllday());

            pst.executeUpdate();

            // Récupérer l'identifiant auto-incrémenté généré par la base de données
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                int calendaruser_id = rs.getInt(1);
                System.out.println("L'identifiant de l'utilisateur connecté est : " + calendaruser_id);
            }

            System.out.println("Rdv ajouté avec succès");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du Rdv : " + e.getMessage());
        }

    }



    @Override
    public void deletedate(CalendarActivity calendarActivity) {
        String requete = "DELETE FROM calendar WHERE id = ?";
        try {
            PreparedStatement pst = MyConnection.getInstace().getCnx().prepareStatement(requete);
            pst.setInt(1, calendarActivity.getId());
            pst.executeUpdate();
            System.out.println("Rendez-vous supprimé avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du rendez-vous : " + e.getMessage());
        }
    }

    @Override
    public void updatedate(CalendarActivity calendarActivity) {
        String requete = "UPDATE calendar SET title = ?, start = ?, end = ?, description = ?, background_color = ?, border_color = ?, text_color = ?, all_day = ? WHERE id = ?";
        try {
            PreparedStatement pst = MyConnection.getInstace().getCnx().prepareStatement(requete);
            pst.setString(1, calendarActivity.getTitle());

            // Conversion de ZonedDateTime en LocalDateTime
            LocalDateTime startDateTime = calendarActivity.getStart().toLocalDateTime();
            LocalDateTime endDateTime = calendarActivity.getEnd().toLocalDateTime();

            // Utilisation de la méthode valueOf() avec LocalDateTime
            pst.setTimestamp(2, java.sql.Timestamp.valueOf(startDateTime));
            pst.setTimestamp(3, java.sql.Timestamp.valueOf(endDateTime));

            pst.setString(4, calendarActivity.getDescription());
            pst.setString(5, toHex(calendarActivity.getBackground_color())); // Convertir la couleur en hexadécimal
            pst.setString(6, toHex(calendarActivity.getBorder_color()));
            pst.setString(7, toHex(calendarActivity.getText_color()));
            pst.setBoolean(8, calendarActivity.isAllday());

            pst.setInt(9, calendarActivity.getId());

            pst.executeUpdate();
            System.out.println("Rendez-vous mis à jour avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du rendez-vous : " + e.getMessage());
        }
    }

    @Override
    public List<CalendarActivity> getAlldates() {
        return null;
    }


    @Override
    public List<CalendarActivity> getAlldates(LocalDate startDate, LocalDate endDate) {
        List<CalendarActivity> calendarActivities = new ArrayList<>();
        String requete = "SELECT * FROM calendar WHERE start >= ? AND end <= ?";
        try {
            PreparedStatement pst = MyConnection.getInstace().getCnx().prepareStatement(requete);

            // Convertir les LocalDate en LocalDateTime pour correspondre au format dans la base de données
            LocalDateTime startDateTime = startDate.atStartOfDay();
            LocalDateTime endDateTime = endDate.atStartOfDay().plusDays(1); // Ajouter un jour à la date de fin pour inclure les rendez-vous de cette journée

            // Convertir LocalDateTime en Timestamp
            java.sql.Timestamp startTimestamp = java.sql.Timestamp.valueOf(startDateTime);
            java.sql.Timestamp endTimestamp = java.sql.Timestamp.valueOf(endDateTime);

            pst.setTimestamp(1, startTimestamp);
            pst.setTimestamp(2, endTimestamp);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                // Récupérer les données du résultat de la requête et créer des objets CalendarActivity
                int calendaruser_id = rs.getInt("calendaruser_id");
                String title = rs.getString("title");

                // Convertir LocalDateTime en ZonedDateTime avec la zone par défaut
                LocalDateTime start = rs.getTimestamp("start").toLocalDateTime();
                ZonedDateTime startZonedDateTime = start.atZone(ZoneId.systemDefault());

                LocalDateTime end = rs.getTimestamp("end").toLocalDateTime();
                ZonedDateTime endZonedDateTime = end.atZone(ZoneId.systemDefault());

                String description = rs.getString("description");
                Color background_color = Color.valueOf(rs.getString("background_color"));
                Color border_color = Color.valueOf(rs.getString("border_color"));
                Color text_color = Color.valueOf(rs.getString("text_color"));
                boolean all_day = rs.getBoolean("all_day");

                CalendarActivity calendarActivity = new CalendarActivity(startZonedDateTime, endZonedDateTime, description, title, background_color, border_color, text_color, all_day);
                calendarActivities.add(calendarActivity);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des rendez-vous : " + e.getMessage());
        }
        return calendarActivities;
    }

    @Override
    public CalendarActivity getdateById(int id) {
        String requete = "SELECT * FROM calendar WHERE id = ?";
        try {
            PreparedStatement pst = MyConnection.getInstace().getCnx().prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                // Récupérer les données du résultat de la requête et créer un objet CalendarActivity
                int calendaruser_id = rs.getInt("calendaruser_id");
                String title = rs.getString("title");

                // Convertir LocalDateTime en ZonedDateTime avec la zone par défaut
                LocalDateTime start = rs.getTimestamp("start").toLocalDateTime();
                ZonedDateTime startZonedDateTime = start.atZone(ZoneId.systemDefault());

                LocalDateTime end = rs.getTimestamp("end").toLocalDateTime();
                ZonedDateTime endZonedDateTime = end.atZone(ZoneId.systemDefault());

                String description = rs.getString("description");
                Color background_color = Color.valueOf(rs.getString("background_color"));
                Color border_color = Color.valueOf(rs.getString("border_color"));
                Color text_color = Color.valueOf(rs.getString("text_color"));
                boolean all_day = rs.getBoolean("all_day");

                return new CalendarActivity(startZonedDateTime, endZonedDateTime, description, title, background_color, border_color, text_color, all_day);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du rendez-vous : " + e.getMessage());
        }
        return null;
    }


}
