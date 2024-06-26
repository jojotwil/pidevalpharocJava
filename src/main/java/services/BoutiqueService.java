package Services;

import Entities.User;
import com.example.demo.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import Tools.MyConnection;
import Entities.Boutique;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class BoutiqueService implements IBoutique<Boutique> {

    String loggedInUserEmail = DBUtils.getLoggedInUserEmail();
    UserService serviceuser=new UserService();
    User user= serviceuser.getuserfromemail(loggedInUserEmail);
    public void addEntity(Boutique boutique) {
        String requete = "INSERT INTO boutique (nom, image, description, num_telephone, gouvernorat, ville, user_id)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst =
                    MyConnection.getInstace().getCnx().prepareStatement(requete);
            pst.setString(1, boutique.getNom());
            pst.setString(2, boutique.getImage());
            pst.setString(3, boutique.getDescription());
            pst.setInt(4, boutique.getNum_telephone());
            pst.setString(5, boutique.getGouvernorat());
            pst.setString(6, boutique.getVille());
            pst.setInt(7, user.getId());

            pst.executeUpdate();
            System.out.println("Boutique added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void deleteEntity(int id) throws SQLException {
        String requete = "DELETE FROM boutique WHERE id = ?";
        try {
            PreparedStatement pst = MyConnection.getInstace().getCnx().prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Boutique suprimée !");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }




    @Override
    public void updateEntity(Boutique boutique ) {

        try {
          //  String requete = "UPDATE boutique SET nom = ?, image = ?, description = ?, num_telephone = ?, gouvernorat = ?, ville = ?, user_id = ? WHERE id = ?";
            String requete = "UPDATE boutique SET nom = ?, description = ?, num_telephone = ?, gouvernorat = ?, ville = ?, user_id = ? WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstace().getCnx().prepareStatement(requete);
            pst.setString(1, boutique.getNom());
           // pst.setString(2, boutique.getImage());
            pst.setString(2, boutique.getDescription());
            pst.setInt(3, boutique.getNum_telephone());
            pst.setString(4, boutique.getGouvernorat());
            pst.setString(5, boutique.getVille());
            pst.setInt(6, boutique.getUser_id());
            pst.setInt(7, boutique.getId());
            pst.executeUpdate();
            System.out.println("Boutique Modifiée !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    public Boutique getBoutiqueById(int id) {
        Boutique boutique = new Boutique();

        String requete = "SELECT * FROM `boutique` WHERE `id` = '" + id + "'";

        try {
            Statement st = MyConnection.getInstace().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                boutique.setId(rs.getInt("id"));
                boutique.setNom(rs.getString("nom"));
                boutique.setImage(rs.getString("image"));
                boutique.setDescription(rs.getString("description"));
                boutique.setNum_telephone(rs.getInt("num_telephone"));
                boutique.setGouvernorat(rs.getString("gouvernorat"));
                boutique.setVille(rs.getString("ville"));
                boutique.setUser_id(rs.getInt("user_id"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return boutique;
    }
    @Override
    public List<Boutique> getAllData() {
        List<Boutique> list = new ArrayList<>();
        String requete = "SELECT * FROM boutique";
        try {
            Statement st = MyConnection.getInstace().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Boutique b = new Boutique();
                b.setId(rs.getInt("id"));
                b.setNom(rs.getString("nom"));
                b.setImage(rs.getString("image"));
                b.setDescription(rs.getString("description"));
                b.setNum_telephone(rs.getInt("num_telephone"));
                b.setGouvernorat(rs.getString("gouvernorat"));
                b.setVille(rs.getString("ville"));
                b.setUser_id(rs.getInt("user_id"));
                list.add(b);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }


    @Override
    public String getNomBoutique(int id) {
        String nom = "";
        String requete = "SELECT * FROM `boutique`  WHERE `boutique`.`id` =' " + id + "'";
        try {

            Statement st = MyConnection.getInstace().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                nom = rs.getString("nom");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(nom);
        return nom;
    }

    public ObservableList<XYChart.Data<String, Integer>> chartBoutiqueStatistics() throws SQLException {
        String requete = "SELECT b.nom, COUNT(b.id) as boutique_count " +
                "FROM boutique b ";
        Statement st = MyConnection.getInstace().getCnx().createStatement();
        ResultSet rs = st.executeQuery(requete);

        ObservableList<XYChart.Data<String, Integer>> barChartData = FXCollections.observableArrayList();
        while (rs.next()) {
            String boutiqueNom = rs.getString("nom");
            int boutique_count = rs.getInt("boutique_count");
            barChartData.add(new XYChart.Data<>(boutiqueNom, boutique_count));
        }

        rs.close();
        st.close();

        return barChartData;
    }
}

