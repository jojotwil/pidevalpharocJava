package Services;

import Entities.Message;
import Entities.PostTroc;
import Entities.User;
import Tools.MyConnection;
import com.example.demo.DBUtils;
import com.example.demo.DetailsController;
import com.example.demo.SendController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class MessageinService implements Interfaces.MessageService<Message> {
    String loggedInUserEmail = DBUtils.getLoggedInUserEmail();
    UserService serviceuser=new UserService();
    SendController controller=new SendController();
    int receptient = controller.getRecipientuser() ;
    User user= serviceuser.getuserfromemail(loggedInUserEmail);
    DetailsController controllerdetails=new DetailsController();
    public static PostTroc  post=new PostTroc();

    public void setPost(PostTroc post) {
        this.post = post;
    }

    @Override
    public void envoyermsg(Message message) {
        String requete = "INSERT INTO messages (sender_id, recipient_id, title, message, created_at, is_read) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = MyConnection.getInstace().getCnx().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, user.getId());
            pst.setInt(2, receptient);
            //System.out.println(receptient+"from service");
            pst.setString(3,  message.getTitle());
            pst.setString(4,  message.getMessage());
            pst.setObject(5,  java.sql.Date.valueOf(message.getCreatedAt().toLocalDate()));
            pst.setBoolean(6, message.isRead());
            pst.executeUpdate();

            // Récupérer l'identifiant auto-incrémenté généré par la base de données
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                int usertroc_id = user.getId();
                System.out.println("L'identifiant de l'utilisateur connecté est : " + user.getId());
            }

            System.out.println("Message envoyé avec succès");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du PostTroc : " + e.getMessage());
        }
    }

    public void envoyermsgfromdetails(Message message) {
        String requete = "INSERT INTO messages (sender_id, recipient_id, title, message, created_at, is_read) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = MyConnection.getInstace().getCnx().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, user.getId());
            pst.setInt(2, post.getUser());
            System.out.println(post+"from service");
            pst.setString(3,  message.getTitle());
            pst.setString(4,  message.getMessage());
            pst.setObject(5,  java.sql.Date.valueOf(message.getCreatedAt().toLocalDate()));
            pst.setBoolean(6, message.isRead());
            pst.executeUpdate();

            // Récupérer l'identifiant auto-incrémenté généré par la base de données
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                int usertroc_id = rs.getInt(1);
                System.out.println("L'identifiant de l'utilisateur connecté est : " + usertroc_id);
            }

            System.out.println("Message envoyé avec succès");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du PostTroc : " + e.getMessage());
        }
    }

    @Override
    public void deletemsg(Message message) {
        String requete = "DELETE FROM messages WHERE id = ?";
        try (PreparedStatement pst = MyConnection.getInstace().getCnx().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, message.getId());
            pst.execute();
            pst.close();
            System.out.println("Message supprimé avec succès : " + message.getId());
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du Message : " + e.getMessage());
        }

    }

    @Override
    public List<Message> getAllmsg() {
        ObservableList<Message> data = FXCollections.observableArrayList();
        String requete = "SELECT * FROM messages ";
        try {
            Statement st = MyConnection.getInstace().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Message message = new Message();
                message.setId(rs.getInt("id"));
                message.setMessage(rs.getString("message"));
                message.setRead(rs.getBoolean("is_read"));
                LocalDateTime localDateTime = rs.getTimestamp("created_at").toLocalDateTime();
                ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
                message.setCreatedAt(zonedDateTime);
                message.setTitle(rs.getString("title"));
                // Supposons que vous avez un constructeur dans la classe User pour créer un utilisateur à partir de son identifiant
                User sender = new User(rs.getString("sender_id"));
                User recipient = new User(rs.getString("recipient_id"));
                //message.setSender(sender);
                //message.setRecipient(recipient);

                data.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<Message> getsentmsg(int id) {
        ObservableList<Message> data = FXCollections.observableArrayList();
        String requete = "SELECT * FROM messages WHERE sender_id = ?";
        try (PreparedStatement st = MyConnection.getInstace().getCnx().prepareStatement(requete)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Message message = new Message();
                    message.setId(rs.getInt("id"));
                    message.setMessage(rs.getString("message"));
                    message.setRead(rs.getBoolean("is_read"));
                    LocalDateTime localDateTime = rs.getTimestamp("created_at").toLocalDateTime();
                    ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
                    message.setCreatedAt(zonedDateTime);
                    message.setTitle(rs.getString("title"));
                    // Supposons que vous avez un constructeur dans la classe User pour créer un utilisateur à partir de son identifiant
                    //User sender = new User(rs.getString("sender_id"));
                    //User recipient = new User(rs.getString("recipient_id"));
                    message.setSender(rs.getInt("sender_id"));
                    message.setRecipient(rs.getInt("recipient_id"));

                    data.add(message);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<Message> getAllreceptmsg(int id) {
        ObservableList<Message> data = FXCollections.observableArrayList();
        String requete = "SELECT * FROM messages WHERE recipient_id = ?";
        try (PreparedStatement st = MyConnection.getInstace().getCnx().prepareStatement(requete)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Message message = new Message();
                    message.setId(rs.getInt("id"));
                    message.setMessage(rs.getString("message"));
                    message.setRead(rs.getBoolean("is_read"));
                    LocalDateTime localDateTime = rs.getTimestamp("created_at").toLocalDateTime();
                    ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
                    message.setCreatedAt(zonedDateTime);
                    message.setTitle(rs.getString("title"));
                    // Supposons que vous avez un constructeur dans la classe User pour créer un utilisateur à partir de son identifiant
                    message.setSender(rs.getInt("sender_id"));
                    message.setRecipient(rs.getInt("recipient_id"));
                    data.add(message);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public Message getmessageById(int id) {
        Message message = null; // Déclarer la variable en dehors du bloc try
        String requete = "SELECT * FROM demandetroc WHERE id = ?";
        try (PreparedStatement st = MyConnection.getInstace().getCnx().prepareStatement(requete)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) { // Vérifier si le résultat contient des lignes
                    Message messages = new Message();
                    messages.setId(rs.getInt("id"));
                    messages.setMessage(rs.getString("message"));
                    messages.setRead(rs.getBoolean("is_read"));
                    LocalDateTime localDateTime = rs.getTimestamp("created_at").toLocalDateTime();
                    ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
                    messages.setCreatedAt(zonedDateTime);
                    messages.setTitle(rs.getString("title"));
                    // Supposons que vous avez un constructeur dans la classe User pour créer un utilisateur à partir de son identifiant
                    message.setSender(rs.getInt("sender_id"));
                    message.setRecipient(rs.getInt("recipient_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
}}
