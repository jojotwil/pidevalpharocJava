package Services;


import Entities.Servicerep;
import Entities.User;
import Tools.MyConnection;
import com.example.demo.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicerepServices implements IService<Servicerep> {
    String loggedInUserEmail = DBUtils.getLoggedInUserEmail();
    UserService serviceuser=new UserService();
    User user= serviceuser.getuserfromemail(loggedInUserEmail);
    private static Connection conx;


    public ServicerepServices() {
        conx = MyConnection.getInstace().getCnx();
        if (conx == null) {
            System.out.println("Erreur lors de la récupération de la connexion à la base de données.");
        }
    }

    @Override
    public void addEntity(Servicerep servicerep) {
        String requete = "INSERT INTO servicerep (description, prix, duree, nomservice, horaire, type_vehicule, image, typeservice, horairedujour, joursderepos, statut, raisonderefus, nbvue, user_id, likes, dislikes,user_id)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conx = MyConnection.getInstace().getCnx();
            PreparedStatement pst = conx.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, servicerep.getDescription());
            pst.setFloat(2, servicerep.getPrix());
            pst.setString(3, servicerep.getDuree());
            pst.setString(4, servicerep.getNomservice());
            pst.setString(5, servicerep.getHoraire());
            pst.setString(6, servicerep.getTypeVehicule());
            pst.setString(7, servicerep.getImage());
            pst.setString(8, servicerep.getTypeservice());
            pst.setString(9, servicerep.getHorairedujour());
            pst.setString(10, servicerep.getJoursderepos());
            pst.setString(11, servicerep.getStatut());
            pst.setString(12, servicerep.getRaisonderefus());
            pst.setInt(13, servicerep.getNbvue());
            pst.setInt(14, user.getId());
            // Vérifiez si l'objet User n'est pas null avant d'accéder à son ID
            if (servicerep.getUser() != null) {
                pst.setLong(14, servicerep.getUser().getId());
            } else {
                pst.setNull(14, Types.NULL); // Si l'objet User est null, on définit la valeur à NULL en base de données
            }
            pst.setInt(15, servicerep.getLikes());
            pst.setInt(16, servicerep.getDislikes());

            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                // Récupérez l'ID généré pour le servicerep
                Long id = rs.getLong(1);
                servicerep.setId(id); // Définissez l'ID de l'objet Servicerep avec l'ID généré
            }
            System.out.println("Service added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void deleteEntity(Servicerep servicerep) {
        if (servicerep.getId() == null) {
            System.out.println("L'ID du servicerep est null, impossible de supprimer");
            return;
        }

        String requete = "DELETE FROM servicerep WHERE id = ?";
        try {
            conx = MyConnection.getInstace().getCnx();
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setLong(1, servicerep.getId());
            pst.executeUpdate();
            System.out.println("Service deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
/*
    public Servicerep getservicebyId(int id) {
        return getById(id);
    }
*/
public void updateStatut(long id, String statut) throws SQLException {
    String query = "UPDATE servicerep SET statut = ? WHERE id = ?";
    conx = MyConnection.getInstace().getCnx();

    try (PreparedStatement preparedStatement = conx.prepareStatement(query)) {
        preparedStatement.setString(1, statut);
        preparedStatement.setLong(2, id);
        preparedStatement.executeUpdate();
    }
}

    public Servicerep getservicebyId(long id) {
    String query = "SELECT * FROM servicerep WHERE id = ?";
    try {
        conx = MyConnection.getInstace().getCnx();
        PreparedStatement statement = conx.prepareStatement(query);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Servicerep servicerep = new Servicerep();
            servicerep.setId(resultSet.getLong("id"));
            servicerep.setDescription(resultSet.getString("description"));
            servicerep.setPrix(resultSet.getFloat("prix"));
            servicerep.setDuree(resultSet.getString("duree"));
            servicerep.setNomservice(resultSet.getString("nomservice"));
            servicerep.setHoraire(resultSet.getString("horaire"));
            servicerep.setTypeVehicule(resultSet.getString("type_vehicule"));
            servicerep.setImage(resultSet.getString("image"));
            servicerep.setTypeservice(resultSet.getString("typeservice"));
            servicerep.setHorairedujour(resultSet.getString("horairedujour"));
            servicerep.setJoursderepos(resultSet.getString("joursderepos"));
            servicerep.setStatut(resultSet.getString("statut"));
            servicerep.setRaisonderefus(resultSet.getString("raisonderefus"));
            servicerep.setNbvue(resultSet.getInt("nbvue"));
            servicerep.setLikes(resultSet.getInt("likes"));
            servicerep.setDislikes(resultSet.getInt("dislikes"));
            // Ajoutez d'autres champs si nécessaire
            return servicerep;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

    @Override
    public void updateEntity(Servicerep servicerep, int id) {
        String requete = "UPDATE servicerep SET description=?, prix=?, duree=?, nomservice=?,  typeservice=?, joursderepos=?, statut=? WHERE id=?";
        try {
            conx = MyConnection.getInstace().getCnx();
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setString(1, servicerep.getDescription());
            pst.setFloat(2, servicerep.getPrix());
            pst.setString(3, servicerep.getDuree());
            pst.setString(4, servicerep.getNomservice());
            //pst.setString(5, servicerep.getHoraire());
            //pst.setString(5, servicerep.getTypeVehicule());
           // pst.setString(7, servicerep.getImage());
            pst.setString(5, servicerep.getTypeservice());
            //pst.setString(8, servicerep.getHorairedujour());
            pst.setString(6, servicerep.getJoursderepos());
            pst.setString(7, servicerep.getStatut());
            // Vérifiez si l'objet User n'est pas null avant d'accéder à son ID
            pst.setLong(8, servicerep.getId());


            pst.executeUpdate();
            System.out.println("Service updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public List<Servicerep> getAllData() {
        List<Servicerep> services = new ArrayList<>();
        String query = "SELECT * FROM servicerep";
        try {
            conx = MyConnection.getInstace().getCnx();
            Statement statement = conx.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Servicerep service = new Servicerep();
                service.setId(resultSet.getLong("id"));
                service.setDescription(resultSet.getString("description"));
                service.setPrix(resultSet.getFloat("prix"));
                service.setDuree(resultSet.getString("duree"));
                service.setNomservice(resultSet.getString("nomservice"));
                service.setHoraire(resultSet.getString("horaire"));

                service.setImage(resultSet.getString("image"));
                service.setTypeservice(resultSet.getString("typeservice"));
                service.setTypeVehicule(resultSet.getString("type_vehicule"));
                service.setHorairedujour(resultSet.getString("horairedujour"));
                service.setJoursderepos(resultSet.getString("joursderepos"));
                service.setStatut(resultSet.getString("statut"));
               service.setRaisonderefus(resultSet.getString("raisonderefus"));
               service.setNbvue(resultSet.getInt("nbvue"));
               service.setLikes(resultSet.getInt("likes"));
               service.setDislikes(resultSet.getInt("dislikes"));
                // Ajoutez d'autres champs si nécessaire

                services.add(service);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return services;
    }
    public Servicerep getById(int id) {
        String query = "SELECT * FROM servicerep WHERE id = ?";
        try {
            conx = MyConnection.getInstace().getCnx();
            PreparedStatement statement = conx.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Servicerep servicerep = new Servicerep();
                servicerep.setId(resultSet.getLong("id"));
                servicerep.setDescription(resultSet.getString("description"));
                servicerep.setPrix(resultSet.getFloat("prix"));
                // Set other properties as needed
                return servicerep;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Servicerep> Show() {
        List<Servicerep> list = new ArrayList<>();

        try {
            String req = "SELECT * FROM servicerep";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Servicerep servicerep = new Servicerep();
                servicerep.setId(rs.getLong("id"));
                servicerep.setDescription(rs.getString("description"));
                servicerep.setPrix(rs.getFloat("prix"));
                servicerep.setDuree(rs.getString("duree"));
                servicerep.setNomservice(rs.getString("nomservice"));
                servicerep.setHoraire(rs.getString("horaire"));
                servicerep.setTypeVehicule(rs.getString("type_vehicule"));
                servicerep.setImage(rs.getString("image"));
                servicerep.setTypeservice(rs.getString("typeservice"));
                servicerep.setHorairedujour(rs.getString("horairedujour"));
                servicerep.setJoursderepos(rs.getString("joursderepos"));
                servicerep.setStatut(rs.getString("statut"));
                servicerep.setLikes(rs.getInt("likes"));
                servicerep.setDislikes(rs.getInt("dislikes"));
            //   servicerep.setRaisonderefus(rs.getString("raisonderefus"));
               //servicerep.setNbvue(rs.getInt("nbvue"));
                // Set other properties as needed
                list.add(servicerep);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    public ObservableList<PieChart.Data> getDemandeServStatsByType() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        String query = "SELECT typeservice, COUNT(*) as count FROM servicerep GROUP BY typeservice";
        try {
            conx = MyConnection.getInstace().getCnx();
            PreparedStatement statement = conx.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String typeService = resultSet.getString("typeservice");
                int count = resultSet.getInt("count");
                pieChartData.add(new PieChart.Data(typeService, count));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pieChartData;
    }

    public static void updateLikess(long id, int newLikes) throws SQLException {
        String query = "UPDATE servicerep SET likes = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = conx.prepareStatement(query)) {
            preparedStatement.setInt(1, newLikes);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        }
    }


    public static void updateDislikess(long id, int newDislikes) throws SQLException {
        String query = "UPDATE servicerep SET dislikes = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = conx.prepareStatement(query)) {
            preparedStatement.setInt(1, newDislikes);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        }
    }

    public static void updateViews(long id, int newViews) throws SQLException {
        String query = "UPDATE servicerep SET nbvue = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = conx.prepareStatement(query)) {
            preparedStatement.setInt(1, newViews);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        }
    }

}