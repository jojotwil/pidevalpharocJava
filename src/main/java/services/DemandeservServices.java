package Services;

import Entities.Demandeserv;
import Entities.Servicerep;
import Entities.User;
import Tools.MyConnection;
import com.example.demo.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DemandeservServices implements IService<Demandeserv> {

    @Override
    public void addEntity(Demandeserv entity) {
        String query = "INSERT INTO demandeserv (description, duree, horaire, marque, modele, image, type_carburant, categorie_vehicule, nom, statut, raisonderefus, typeservice, localisationdemettrelavehicule, servicerep_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = MyConnection.getInstace().getCnx();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getDescription());
            statement.setFloat(2, entity.getDuree());
            statement.setObject(3, entity.getHoraire());
            statement.setString(4, entity.getMarque());
            statement.setString(5, entity.getModele());
            statement.setString(6, entity.getImage());
            statement.setString(7, entity.getTypeCarburant());
            statement.setString(8, entity.getCategorieVehicule());
            statement.setString(9, entity.getNom());
            statement.setString(10, entity.getStatut());
            statement.setString(11, entity.getRaisonderefus());
            statement.setString(12, entity.getTypeservice());
            statement.setString(13, entity.getLocalisationdemettrelavehicule());
            statement.setInt(14, entity.getServicerep_id());

            statement.executeUpdate();
            System.out.println("Demandeserv added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteEntity(Demandeserv entity) {
        String query = "DELETE FROM demandeserv WHERE id = ?";
        try {
            Connection connection = MyConnection.getInstace().getCnx();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, entity.getId());
            statement.executeUpdate();
            System.out.println("Demandeserv deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateEntity(Demandeserv entity, int id) {
        String query = "UPDATE demandeserv SET description = ?, duree = ?, horaire = ?, marque = ?, modele = ?, image = ?, type_carburant = ?, categorie_vehicule = ?, nom = ?, statut = ?, raisonderefus = ?, typeservice = ?, localisationdemettrelavehicule = ?, servicerep_id = ? WHERE id = ?";
        try {
            Connection connection = MyConnection.getInstace().getCnx();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, entity.getDescription());
            statement.setFloat(2, entity.getDuree());
            statement.setObject(3, entity.getHoraire());
            statement.setString(4, entity.getMarque());
            statement.setString(5, entity.getModele());
            statement.setString(6, entity.getImage());
            statement.setString(7, entity.getTypeCarburant());
            statement.setString(8, entity.getCategorieVehicule());
            statement.setString(9, entity.getNom());
            statement.setString(10, entity.getStatut());
            statement.setString(11, entity.getRaisonderefus());
            statement.setString(12, entity.getTypeservice());
            statement.setString(13, entity.getLocalisationdemettrelavehicule());
            statement.setInt(14, entity.getServicerep_id());
            statement.setInt(15, id);
            statement.executeUpdate();
            System.out.println("Demandeserv updated");
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        id = generatedKeys.getInt(1);
                        entity.setId(id);
                        System.out.println("Demandeserv ajouté avec l'ID : " + id);
                    } else {
                        throw new SQLException("Création de l'entité demandeserv a échoué, aucun ID généré.");
                    }
                }
            } else {
                throw new SQLException("Création de l'entité demandeserv a échoué.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());


        }

        }
    public List<Demandeserv> getDemandesbyServiceId(int serviceId) {
        List<Demandeserv> demandeservList = new ArrayList<>();
        String query = "SELECT * FROM demandeserv WHERE servicerep_id = ?";
        try (Connection connection = MyConnection.getInstace().getCnx();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, serviceId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Demandeserv demandeserv = new Demandeserv();
                demandeserv.setId(resultSet.getInt("id"));
                demandeserv.setDescription(resultSet.getString("description"));
                demandeserv.setDuree(resultSet.getFloat("duree"));
                demandeserv.setHoraire(resultSet.getObject("horaire", LocalDate.class));
                demandeserv.setMarque(resultSet.getString("marque"));
                demandeserv.setModele(resultSet.getString("modele"));
                demandeserv.setImage(resultSet.getString("image"));
                demandeserv.setTypeCarburant(resultSet.getString("type_Carburant"));
                demandeserv.setCategorieVehicule(resultSet.getString("categorie_vehicule"));
                demandeserv.setNom(resultSet.getString("nom"));
                demandeserv.setStatut(resultSet.getString("statut"));
                demandeserv.setRaisonderefus(resultSet.getString("raisonderefus"));
                demandeserv.setTypeservice(resultSet.getString("typeservice"));
                demandeserv.setLocalisationdemettrelavehicule(resultSet.getString("localisationdemettrelavehicule"));
                demandeserv.setServicerep_id(resultSet.getInt("servicerep_id"));
                demandeservList.add(demandeserv);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return demandeservList;
    }



    @Override
    public List<Demandeserv> getAllData() {
        List<Demandeserv> demandeservList = new ArrayList<>();
        String query = "SELECT * FROM demandeserv";
        try {
            Connection connection = MyConnection.getInstace().getCnx();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Demandeserv demandeserv = new Demandeserv();
                demandeserv.setId(resultSet.getInt("id"));
                demandeserv.setDescription(resultSet.getString("description"));
                demandeserv.setDuree(resultSet.getFloat("duree"));
                demandeserv.setHoraire(resultSet.getObject("horaire", LocalDate.class));
                demandeserv.setMarque(resultSet.getString("marque"));
                demandeserv.setModele(resultSet.getString("modele"));
                demandeserv.setImage(resultSet.getString("image"));
                demandeserv.setTypeCarburant(resultSet.getString("type_Carburant"));
                demandeserv.setCategorieVehicule(resultSet.getString("categorie_vehicule"));
                demandeserv.setNom(resultSet.getString("nom"));
                demandeserv.setStatut(resultSet.getString("statut"));
                demandeserv.setRaisonderefus(resultSet.getString("raisonderefus"));
                demandeserv.setTypeservice(resultSet.getString("typeservice"));
                demandeserv.setLocalisationdemettrelavehicule(resultSet.getString("localisationdemettrelavehicule"));
                // Récupérez l'entité Servicerep associée à la demande de service
                ServicerepServices servicerepServices = new ServicerepServices();
                Servicerep servicerep = servicerepServices.getById(resultSet.getInt("servicerep_id"));
                demandeserv.setServicerep_id(resultSet.getInt("servicerep_id")); // Mise à jour de l'ID du service
                demandeservList.add(demandeserv);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return demandeservList;
    }

    public ObservableList<PieChart.Data> getDemandeServStatsByCategorie() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        String query = "SELECT categorie_vehicule, COUNT(*) as count FROM demandeserv GROUP BY categorie_vehicule";
        try {
            Connection connection = MyConnection.getInstace().getCnx();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String categorieVehicule = resultSet.getString("categorie_vehicule");
                int count = resultSet.getInt("count");
                pieChartData.add(new PieChart.Data(categorieVehicule, count));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pieChartData;
    }

    public ObservableList<PieChart.Data> getDemandeServStatsByCarburant() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        String query = "SELECT type_carburant, COUNT(*) as count FROM demandeserv GROUP BY type_carburant";
        try {
            Connection connection = MyConnection.getInstace().getCnx();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String typeCarburant = resultSet.getString("type_carburant");
                int count = resultSet.getInt("count");
                pieChartData.add(new PieChart.Data(typeCarburant, count));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pieChartData;
    }

    public List<Demandeserv> getDemandeservWithServiceInfo() {
        List<Demandeserv> demandeservs = new ArrayList<>();
        String query = "SELECT * FROM demandeserv";
        try {
            Connection connection = MyConnection.getInstace().getCnx();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Demandeserv demandeserv = new Demandeserv();
                demandeserv.setId(resultSet.getInt("id"));
                demandeserv.setDescription(resultSet.getString("description"));
                demandeserv.setDuree(resultSet.getFloat("duree"));
                demandeserv.setHoraire(resultSet.getObject("horaire", LocalDate.class));
                demandeserv.setMarque(resultSet.getString("marque"));
                demandeserv.setModele(resultSet.getString("modele"));
                demandeserv.setImage(resultSet.getString("image"));
                demandeserv.setTypeCarburant(resultSet.getString("type_Carburant"));
                demandeserv.setCategorieVehicule(resultSet.getString("categorie_vehicule"));
                demandeserv.setNom(resultSet.getString("nom"));
                demandeserv.setStatut(resultSet.getString("statut"));
                demandeserv.setRaisonderefus(resultSet.getString("raisonderefus"));
                demandeserv.setTypeservice(resultSet.getString("typeservice"));
                demandeserv.setLocalisationdemettrelavehicule(resultSet.getString("localisationdemettrelavehicule"));

                // Récupérer les informations sur le service associé
                ServicerepServices servicerepServices = new ServicerepServices();
                Servicerep servicerep = servicerepServices.getById(resultSet.getInt("servicerep_id"));
                demandeserv.setServicerep(servicerep);

                demandeservs.add(demandeserv);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return demandeservs;
    }




}
