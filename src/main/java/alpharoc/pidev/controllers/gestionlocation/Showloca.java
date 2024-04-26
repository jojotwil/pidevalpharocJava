package alpharoc.pidev.controllers.gestionlocation;

import alpharoc.pidev.entities.VehiculeLouer;
import alpharoc.pidev.entities.loca;
import alpharoc.pidev.services.VehiculeLouerServie;
import alpharoc.pidev.services.locaService;
import alpharoc.pidev.tools.MyConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class Showloca implements Initializable {

    @FXML
    private TableColumn<loca, Integer> colid;

    @FXML
    private TableColumn<loca, Integer> colidvl;

    @FXML
    private Button btnajouter;

    @FXML
    private Button btnmodifier;

    @FXML
    private Button btnsuprimer;

    @FXML
    private TableColumn<loca, Date> colDaDebut;

    @FXML
    private TableColumn<loca, Date> coldatefin;

    @FXML
    private TableColumn<loca, String> coldes;

    @FXML
    private TableColumn<loca, String> colloca;

    @FXML
    private TableView<loca> table;

    @FXML
    private DatePicker tfdatedebut;

    @FXML
    private DatePicker tfdatefin;

    @FXML
    private TextField tfdescrp;

    @FXML
    private TextField tfloca;

    @FXML
    void Ajout(ActionEvent event) {

    }

    @FXML
    void modifier(ActionEvent event) {

    }

    @FXML
    void supprimer(ActionEvent event) {
        VehiculeLouerServie rs= new VehiculeLouerServie();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert!");
        alert.setContentText("This is an alert");
        Optional<ButtonType> result = alert.showAndWait();

        int ligne = table.getSelectionModel().getSelectedIndex();

        // Vérifier si un élément a bien été sélectionné.
        if (ligne >= 0) {
            // Obtenir l'élément sélectionné.
            // showAlert(Alert.AlertType.CONFIRMATION, "  ", "Formation was successfully updated.");
            if(result.isEmpty()){
                System.out.println("Alert closed");
            } else if(result.get() == ButtonType.OK){
                System.out.println("OK!");
                loca f = table.getItems().remove(ligne);
                rs.delete(f.getId());
                System.out.println("supprime avec succes " + f);
            }else if(result.get() == ButtonType.CANCEL){
                System.out.println("Never!");
            }

        } else {

            System.out.println("Aucune formation n'est sélectionnée pour la suppression.");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showloca() ;
    }
    ObservableList<loca> initialData(){
        locaService V = new locaService();
        return  FXCollections.observableArrayList(V.getloca());
    }

    public ObservableList<loca> getloca(){
        ObservableList<loca> data= FXCollections.observableArrayList();
        //  con = ;
        String requete = "SELECT * FROM loca";
        try {
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()){

                loca loca = new loca();
                loca.setId(rs.getInt(1));
                loca.setVehichuleLouerid(rs.getInt("vehiculelouer_id"));
                loca.setDescription(rs.getString("description"));
                loca.setLocalisation(rs.getString("localisation"));
                loca.setDate_debut(rs.getDate("date_debut"));
                loca.setDate_fin(rs.getDate("date_fin"));
                data.add(loca);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }


    public void showloca() {




        ObservableList<loca> list = getloca();

        colid.setCellValueFactory(new PropertyValueFactory<loca,Integer>("id"));
        colidvl.setCellValueFactory(new PropertyValueFactory<>("vehiculelouer_id "));
        coldes.setCellValueFactory(new PropertyValueFactory<loca,String>("description"));
       colloca.setCellValueFactory(new PropertyValueFactory<loca,String>("localisation"));
        colDaDebut.setCellValueFactory(new PropertyValueFactory<loca, Date>("date_debut"));
        coldatefin.setCellValueFactory(new PropertyValueFactory<loca, Date>("date_fin"));


        table.setItems(initialData());
    }


}
