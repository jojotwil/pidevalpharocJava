package services;

import tools.MyConnexion;
import entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IService<User> {

    public void addEntity(User user) {
        String requete = "INSERT INTO user (email, roles, password, nom, prenom, image, isVerified, isblocked)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst =
                    MyConnexion.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, user.getEmail());
            pst.setString(2, String.join(",", user.getRoles()));
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getNom());
            pst.setString(5, user.getPrenom());
            pst.setString(6, user.getImage());
            pst.setBoolean(7, user.getVerified());
            pst.setBoolean(8, user.getIsblocked());
            pst.executeUpdate();
            System.out.println("User added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteEntity(User user) {
        // Implémenter la suppression d'un utilisateur
    }

    @Override
    public void updateEntity(User user, int id) {
        // Implémenter la mise à jour d'un utilisateur
    }

    @Override
    public List<User> getAllData() {
        List<User> data = new ArrayList<>();
        String requete = "SELECT * FROM user";
        try {
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                User u = new User();
                u.setUser_id(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setRoles(rs.getString("roles").split(","));
                u.setPassword(rs.getString("password"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setImage(rs.getString("image"));
                u.setVerified(rs.getBoolean("isVerified"));
                u.setIsblocked(rs.getBoolean("isblocked"));
                data.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return data;
    }
}
