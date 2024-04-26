package alpharoc.pidev.controllers.gestionlocation;

import alpharoc.pidev.entities.VehiculeLouer;
import alpharoc.pidev.services.VehiculeLouerServie;
import alpharoc.pidev.tools.MyConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class Showvl implements Initializable {
int id;
    @FXML
    private final String[]choix1={"car","yacht","moto"};
    @FXML
    private final String[]choix2={"diesel","essence"};
    @FXML
    private Button btnclear;
    @FXML
    private Button btnsave;
    @FXML
    private ComboBox<String> Rescato;

    @FXML
    private DatePicker Resdate;

    @FXML
    private TextField Resdescrp;

    @FXML
    private TextField Resmarque;

    @FXML
    private TextField Resmodele;

    @FXML
    private ComboBox<String> Restypecarb;


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
    private TableColumn<VehiculeLouer, Integer> colid;
    @FXML
    private TableView<VehiculeLouer> table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      /*  colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colmarque.setCellValueFactory(new PropertyValueFactory<VehiculeLouer,String>("marque"));
        colmodele.setCellValueFactory(new PropertyValueFactory<VehiculeLouer,String>("modele"));
        coldesc.setCellValueFactory(new PropertyValueFactory<VehiculeLouer,String>("description"));
        colperiode.setCellValueFactory(new PropertyValueFactory<VehiculeLouer, Date>("periode_dispo"));
        colctype.setCellValueFactory(new PropertyValueFactory<VehiculeLouer,String>("type_carburant"));
        colcategorie.setCellValueFactory(new PropertyValueFactory<VehiculeLouer,String>("categorie_vehicule"));
        table.setItems(initialData());*/
        showvehicule();
        Rescato.getItems().addAll(choix1);
        Restypecarb.getItems().addAll(choix2);
    }
ObservableList<VehiculeLouer>initialData(){
        VehiculeLouerServie V = new VehiculeLouerServie();
        return  FXCollections.observableArrayList(V.getVl());
}

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
    }



    public void showvehicule() {




        ObservableList<VehiculeLouer> list = getVl();

        colid.setCellValueFactory(new PropertyValueFactory<VehiculeLouer,Integer>("id"));
        colmarque.setCellValueFactory(new PropertyValueFactory<VehiculeLouer,String>("marque"));
        colmodele.setCellValueFactory(new PropertyValueFactory<VehiculeLouer,String>("modele"));
        coldesc.setCellValueFactory(new PropertyValueFactory<VehiculeLouer,String>("description"));
        colperiode.setCellValueFactory(new PropertyValueFactory<VehiculeLouer, Date>("periode_dispo"));
        colctype.setCellValueFactory(new PropertyValueFactory<VehiculeLouer,String>("type_carburant"));
        colcategorie.setCellValueFactory(new PropertyValueFactory<VehiculeLouer,String>("categorie_vehicule"));
        table.setItems(initialData());
    }




@FXML
    public void getData(MouseEvent mouseEvent) {

        VehiculeLouer vehiculeLouer=table.getSelectionModel().getSelectedItem();
       colid.setText(String.valueOf(vehiculeLouer.getId()));
  //colid=vehiculeLouer.getId();
    Resmarque.setText(vehiculeLouer.getMarque());
        Resmodele.setText(vehiculeLouer.getModele());
        Resdescrp.setText(vehiculeLouer.getDescription());
        //Resdate.setChronology(vehiculeLouer.getPeriode_dispo());
        //Restypecarb.setItems(vehiculeLouer.getType_carburant());
        //colctype.setText(vehiculeLouer.getCategorie_vehicule());

    btnsave.setDisable(true);
    }


    public void Ajout(ActionEvent actionEvent) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Addvl.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Ajout Vehicule");
        stage.setScene(new Scene(root));
        stage.show();
        ((Button) actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void supprimer(ActionEvent actionEvent) {
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
                VehiculeLouer f = table.getItems().remove(ligne);
                rs.delete(f.getId());
                System.out.println("supprime avec succes " + f);
            }else if(result.get() == ButtonType.CANCEL){
                System.out.println("Never!");
            }

        } else {

            System.out.println("Aucune formation n'est sélectionnée pour la suppression.");
        }
    }

    public void modifier(ActionEvent actionEvent) {

        VehiculeLouer selectedCours = table.getSelectionModel().getSelectedItem();
        if (selectedCours != null) {

            try {

            String marque=Resmarque.getText();
                String modele=Resmodele.getText();
                String description=Resdescrp.getText();
                Date periode_dispo = new Date();

                String type=Restypecarb.getValue();
                String cato= Rescato.getValue();

                VehiculeLouer V = new VehiculeLouer(selectedCours.getId(), marque, modele, description,periode_dispo , type, cato);
                VehiculeLouerServie fs = new VehiculeLouerServie();

                fs.update(V);

                showAlert(Alert.AlertType.INFORMATION, "supprimer vehicule  ", "Vehicule suprimer");

            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Database Erreur", "Error updating Formation: " + e.getMessage());
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "No Selection", "No Course Selected. Please select a course in the table.");
        }
    }
    @FXML
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void louer(ActionEvent actionEvent) {
        // Get the selected ID from the TableView
        int selectedId = getIdFromSelectedRow();

        // Load the Addloca FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Addloca.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Set the controller for the Addloca FXML file
        Addloca controller = loader.getController();
        controller.setSelectedId(selectedId);

        // Create a new Scene and show it
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private int getIdFromSelectedRow() {
        // Get the selected item from the TableView
        VehiculeLouer selectedObject = table.getSelectionModel().getSelectedItem();

        // Assuming YourObject has a method getId() to get the ID
        if (selectedObject != null) {
            return selectedObject.getId();
        } else {
            // Handle case where no row is selected
            return -1; // Or whatever default value you want to use
        }
    }


}
