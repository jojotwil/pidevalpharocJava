package alpharoc.pidev.controllers.gestionlocation;

import javafx.scene.paint.Color;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.awt.Desktop;
import java.io.File;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class Showvl implements Initializable {
int id;
    @FXML
    private Button btngolist;

    @FXML
    private TextField search;

    @FXML
    private Button btnpdf;

    @FXML
    private PieChart pieChart;

    @FXML
    private Button btnstats;
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
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colmarque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        colmodele.setCellValueFactory(new PropertyValueFactory<>("modele"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colperiode.setCellValueFactory(new PropertyValueFactory<>("periode_dispo"));
        colctype.setCellValueFactory(new PropertyValueFactory<>("type_carburant"));
        colcategorie.setCellValueFactory(new PropertyValueFactory<>("categorie_vehicule"));

        table.setItems(initialData());
        Rescato.getItems().addAll(choix1);
        Restypecarb.getItems().addAll(choix2);

        // Add listener to search TextField
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filterTable(newValue);
        });
    }

    private void filterTable(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            table.setItems(initialData());
        } else {
            ObservableList<VehiculeLouer> filteredList = FXCollections.observableArrayList();
            for (VehiculeLouer vehicule : table.getItems()) {
                if (vehicule.getMarque().toLowerCase().contains(keyword.toLowerCase())) {
                    filteredList.add(vehicule);
                }
            }
            table.setItems(filteredList);
        }
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
                smsAPI.init();
                smsAPI.sendSMS("+21625281990", "+16598370014", "Bonjour le véhicule a été supprimé !");

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

    }

    @FXML
    private void handleRowSelection() throws IOException {
        int selectedId = getIdFromSelectedRow();

        if (selectedId != -1) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/addloca.fxml"));
            Parent root = loader.load();
            Addloca l = loader.getController();

            l.setSelectedId(selectedId);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
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


    public void golist(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/showloca.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Ajout Vehicule");
        stage.setScene(new Scene(root));
        stage.show();
        ((Button) actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void generatepdf(ActionEvent actionEvent) {
        VehiculeLouer selectedRow = table.getSelectionModel().getSelectedItem();
        if (selectedRow != null) {
            try (PDDocument document = new PDDocument()) {
                PDPage page = new PDPage();
                document.addPage(page);

                PDFont font = PDType1Font.HELVETICA;
                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    float margin = 50;
                    float yStart = page.getMediaBox().getHeight() - margin;
                    float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
                    float yPosition = yStart;
                    float rowHeight = 20;
                    float tableMargin = 10;

                    String[][] content = {
                            {"ID", String.valueOf(selectedRow.getId())},
                            {"Marque", selectedRow.getMarque()},
                            {"Modele", selectedRow.getModele()},
                            {"Description", selectedRow.getDescription()},
                            {"Periode Dispo", String.valueOf(selectedRow.getPeriode_dispo())},
                            {"Type Carburant", selectedRow.getType_carburant()},
                            {"Categorie Vehicule", selectedRow.getCategorie_vehicule()}
                    };

                    drawTable(page, contentStream, yStart, tableWidth, tableMargin, content, font, rowHeight);

                    contentStream.setFont(font, 12);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin, yPosition);
                    contentStream.endText();
                }

                File file = new File("selectedRow.pdf");
                document.save(file);

                showAlert(Alert.AlertType.INFORMATION, "PDF Generated", "PDF file generated successfully.");

                // Open the PDF file using the default PDF viewer
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while generating the PDF.");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a row from the table.");
        }
    }

    private void drawTable(PDPage page, PDPageContentStream contentStream, float y, float width, float margin, String[][] content, PDFont font, float rowHeight) throws IOException {
        final int rows = content.length;
        final int cols = content[0].length;
        final float tableHeight = rowHeight * rows;
        final float cellMargin = 2f;

        //draw the rows
        float nexty = y;
        for (int i = 0; i <= rows; i++) {
            contentStream.moveTo(margin, nexty);
            contentStream.lineTo(margin + width, nexty);
            contentStream.stroke();
            nexty -= rowHeight;
        }

        //draw the columns
        float yStart = y;
        float yEnd = y - tableHeight;
        float nextx = margin;
        for (int i = 0; i <= cols; i++) {
            contentStream.moveTo(nextx, yStart);
            contentStream.lineTo(nextx, yEnd);
            contentStream.stroke();
            nextx += width / cols;
        }

        // now add the text
        contentStream.setFont(font, 12);
        float textx = margin + cellMargin;
        float texty = y - 15; // y - cellMargin; // y - 15; // y - cellMargin; // y - 15; // y - 15; // y - cellMargin - rowHeight;
        for (String[] aContent : content) {
            for (String column : aContent) {
                contentStream.beginText();
                contentStream.newLineAtOffset(textx, texty);
                contentStream.showText(column);
                contentStream.endText();
                textx += width / cols;
            }
            texty -= rowHeight;
            textx = margin + cellMargin;
        }
    }




    public void calculateStatistics(ActionEvent actionEvent) {

            ObservableList<VehiculeLouer> data = table.getItems();
            Map<String, Integer> stats = new HashMap<>();

            for (VehiculeLouer vehicule : data) {
                String category = vehicule.getCategorie_vehicule();
                stats.put(category, stats.getOrDefault(category, 0) + 1);
            }

            // Create a BarChart
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();
            BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
            barChart.setTitle("Vehicle Category Statistics");
            xAxis.setLabel("Category");
            yAxis.setLabel("Count");

            // Add data to the chart
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            for (Map.Entry<String, Integer> entry : stats.entrySet()) {
                series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            }
            barChart.getData().add(series);

            // Show the chart in a new window
            Scene scene = new Scene(barChart, 800, 600);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Vehicle Category Statistics");
            stage.show();
    }

}
