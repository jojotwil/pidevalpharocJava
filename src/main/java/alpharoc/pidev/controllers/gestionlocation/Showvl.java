package alpharoc.pidev.controllers.gestionlocation;

import alpharoc.pidev.entities.VehiculeLouer;
import alpharoc.pidev.services.VehiculeLouerServie;
import alpharoc.pidev.tools.MyConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

public class Showvl implements Initializable {

     MyConnexion con = null;
     PreparedStatement st = null;
     ResultSet rs = null;

    @FXML
    private Button btnajouter;

    @FXML
    private Button btnmodifier;

    @FXML
    private Button btnsuprimer;

    @FXML
    private TableColumn<VehiculeLouer, String> colcategorie;

    @FXML
    private TableColumn<VehiculeLouer, String> colctype;

    @FXML
    private TableColumn<VehiculeLouer, String> coldesc;

    @FXML
    private TableColumn<VehiculeLouer, String> colmarque;

    @FXML
    private TableColumn<VehiculeLouer, String> colmodele;

    @FXML
    private TableColumn<VehiculeLouer, Date> colperiode;
    @FXML
    private TableView<VehiculeLouer> table;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colmarque.setCellValueFactory(new PropertyValueFactory<VehiculeLouer,String>("marque"));
        colmodele.setCellValueFactory(new PropertyValueFactory<VehiculeLouer,String>("modele"));
        coldesc.setCellValueFactory(new PropertyValueFactory<VehiculeLouer,String>("description"));
        colperiode.setCellValueFactory(new PropertyValueFactory<VehiculeLouer, Date>("periode_dispo"));
        colctype.setCellValueFactory(new PropertyValueFactory<VehiculeLouer,String>("type_carburant"));
        colcategorie.setCellValueFactory(new PropertyValueFactory<VehiculeLouer,String>("categorie_vehicule"));
        table.setItems(initialData());

    }
ObservableList<VehiculeLouer>initialData(){
        VehiculeLouerServie V = new VehiculeLouerServie();
        return  FXCollections.observableArrayList(V.getVl());
}
    /*
    public ObservableList<VehiculeLouer> getVl(){
        ObservableList<VehiculeLouer> Vl= FXCollections.observableArrayList();
      //  con = ;
        String requete = "SELECT * FROM vehicule_loue";
        try {
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()){
                VehiculeLouer vehiculeLouer = new VehiculeLouer();
                vehiculeLouer.setId(rs.getInt(1));
                vehiculeLouer.setMarque(rs.getString("marque"));
                vehiculeLouer.setModele(rs.getString("modele"));
                vehiculeLouer.setDescription(rs.getString("description"));
                vehiculeLouer.setPeriode_dispo(rs.getDate("periode_dispo"));
                vehiculeLouer.setType_carburant(rs.getString("type_carburant"));
                vehiculeLouer.setCategorie_vehicule(rs.getString("categorie_vehicule"));
                Vl.add(vehiculeLouer);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Vl;
    }*/



    public void showvehicule() {


        VehiculeLouerServie fs = new VehiculeLouerServie();

      //  ObservableList<VehiculeLouer> list = getVl();
        ObservableList<VehiculeLouer> list = FXCollections.observableArrayList(fs.getVl());

        colmarque.setCellValueFactory(new PropertyValueFactory<VehiculeLouer,String>("marque"));
        colmodele.setCellValueFactory(new PropertyValueFactory<VehiculeLouer,String>("modele"));
        coldesc.setCellValueFactory(new PropertyValueFactory<VehiculeLouer,String>("description"));
        colperiode.setCellValueFactory(new PropertyValueFactory<VehiculeLouer, Date>("periode_dispo"));
        colctype.setCellValueFactory(new PropertyValueFactory<VehiculeLouer,String>("type_carburant"));
        colcategorie.setCellValueFactory(new PropertyValueFactory<VehiculeLouer,String>("categorie_vehicule"));
        table.setItems(list);
    }

    @FXML
    void goAdd(MouseEvent event) {

        try {
            root = FXMLLoader.load(getClass().getResource("/Addvl.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }




}
