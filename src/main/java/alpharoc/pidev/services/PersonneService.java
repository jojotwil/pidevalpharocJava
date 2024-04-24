package alpharoc.pidev.services;

import alpharoc.pidev.entities.Personne;
import alpharoc.pidev.interfaces.IService;
import alpharoc.pidev.tools.MyConnexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonneService implements IService<Personne> {
    @Override
    public void addEntity(Personne personne)  {
        String requete = "INSERT INTO personne (nom,prenom)"
                + "VALUES('"+personne.getNom()+"','"+personne.getPrenom()+"') ";

        try {
            Statement st=  MyConnexion.getInstance().getCnx().createStatement();
            st.execute(requete);
            System.out.println("ajout");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void addEntity2(Personne personne)  {
        String requete = "INSERT INTO personne (nom,prenom)"
                + "VALUES(? , ?) ";
        try {
            PreparedStatement pst =
                    MyConnexion.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, personne.getNom());
            pst.setString(2, personne.getPrenom());
            pst.executeUpdate();
            System.out.println("personne added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    @Override
    public void deleteEntity(Personne personne) {

    }

    @Override
    public void updateEntity(Personne personne, int id) {

    }

    @Override
    public List<Personne> getAllData() {
        List<Personne> data= new ArrayList();
        String requete = "SELECT * FROM personne";
        try {
            Statement st =  MyConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next())
            {
                Personne p= new Personne();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                data.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
}