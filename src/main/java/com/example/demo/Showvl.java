package com.example.demo;

import Entities.VehiculeLouer;
import Services.VehiculeLouerServie;
import Tools.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import Entities.VehiculeLouer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Showvl implements Initializable {
    public void demandeserv(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource("Gestiondemandeservadmin.fxml"));
            Parent root = loader.load();


            // Set any necessary data or parameters for the controller

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Admin Dashboard");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void servicerep(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource("Gestionadminservicerep.fxml"));
            Parent root = loader.load();
            //TrocbackController adminDashboardController = loader.getController();

            // Set any necessary data or parameters for the controller

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Admin Dashboard");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    int id;
    @FXML
    private Button btngolist;

    @FXML
    private TextField search;

    @FXML
    private Button btnpdf;


    @FXML
    private final String[]choix1={"car","yacht","moto"};
    @FXML
    private final String[]choix2={"diesel","essence"};
    @FXML
    private Button btnsave;
    @FXML
    private ComboBox<String> Rescato;



    @FXML
    private TextField Resdescrp;

    @FXML
    private TextField Resmarque;

    @FXML
    private TextField Resmodele;

    @FXML
    private ComboBox<String> Restypecarb;




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

    ObservableList<VehiculeLouer> initialData() {
        VehiculeLouerServie V = new VehiculeLouerServie();
        return V.getVl();
    }

    public ObservableList<VehiculeLouer> getVl(){
        ObservableList<VehiculeLouer> Vl= FXCollections.observableArrayList();
        //  con = ;
        String requete = "SELECT * FROM vehicule_loue";
        try {
            Statement st = MyConnection.getInstace().getCnx().createStatement();
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


        FXMLLoader loader = new FXMLLoader(getClass().getResource("Addvl.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Ajout Vehicule");
        stage.setScene(new Scene(root));
        stage.show();
        ((Button) actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void supprimer(ActionEvent actionEvent) {
        VehiculeLouerServie rs= new VehiculeLouerServie();
        Alert alert = new Alert(AlertType.CONFIRMATION);
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

                Date today = new Date();
                String message = "Salut, votre véhicule  a été supprimer aujourd'hui le "+today+" !";
                smsAPI.sendSMS("+21625281990", "+16598370014", message);
                System.out.println("supprime avec succes " + f);
            }else if(result.get() == ButtonType.CANCEL){
                System.out.println("Never!");
            }

        } else {

            System.out.println("Aucune formation n'est sélectionnée pour la suppression.");
        }
    }
    public void event(ActionEvent event){
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("index-evenement.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"

            // Créer une nouvelle scène avec le nouveau contenu
            Scene scene = new Scene(newContent);

            // Obtenir la fenêtre principale (stage)
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Obtenir les dimensions de l'écran
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            // Obtenez les dimensions de l'écran
            Screen screen = Screen.getPrimary();
            double screenWidth = screen.getBounds().getWidth();
            double screenHeight = screen.getBounds().getHeight();

// Définissez la taille de la fenêtre sur les dimensions de l'écran
            mainStage.setWidth(screenWidth);
            mainStage.setHeight(screenHeight);

            // Définir la nouvelle scène sur la fenêtre principale
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void logout(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource("authentifier.fxml"));
            Parent root = loader.load();
            //TrocbackController adminDashboardController = loader.getController();

            // Set any necessary data or parameters for the controller

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Admin Dashboard");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void chart(ActionEvent event){
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chartjs.fxml"));
            Parent newContent = loader.load();

            // Créer une nouvelle scène avec le nouveau contenu
            Scene scene = new Scene(newContent);

            // Obtenir la fenêtre principale (stage)
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Obtenir les dimensions de l'écran
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            // Obtenez les dimensions de l'écran
            Screen screen = Screen.getPrimary();
            double screenWidth = screen.getBounds().getWidth();
            double screenHeight = screen.getBounds().getHeight();

            // Définissez la taille de la fenêtre sur les dimensions de l'écran
            mainStage.setWidth(screenWidth);
            mainStage.setHeight(screenHeight);

            // Définir la nouvelle scène sur la fenêtre principale
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void user(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource("admindashboard.fxml"));
            Parent root = loader.load();
            TrocbackController adminDashboardController = loader.getController();

            // Set any necessary data or parameters for the controller

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Admin Dashboard");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void trocs(ActionEvent event){
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("trocback.fxml"));
            Parent newContent = loader.load();

            // Créer une nouvelle scène avec le nouveau contenu
            Scene scene = new Scene(newContent);

            // Obtenir la fenêtre principale (stage)
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Obtenir les dimensions de l'écran
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            // Obtenez les dimensions de l'écran
            Screen screen = Screen.getPrimary();
            double screenWidth = screen.getBounds().getWidth();
            double screenHeight = screen.getBounds().getHeight();

            // Définissez la taille de la fenêtre sur les dimensions de l'écran
            mainStage.setWidth(screenWidth);
            mainStage.setHeight(screenHeight);

            // Définir la nouvelle scène sur la fenêtre principale
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void location(ActionEvent event){
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Showvl.fxml"));
            Parent newContent = loader.load();

            // Créer une nouvelle scène avec le nouveau contenu
            Scene scene = new Scene(newContent);

            // Obtenir la fenêtre principale (stage)
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Obtenir les dimensions de l'écran
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            // Obtenez les dimensions de l'écran
            Screen screen = Screen.getPrimary();
            double screenWidth = screen.getBounds().getWidth();
            double screenHeight = screen.getBounds().getHeight();

            // Définissez la taille de la fenêtre sur les dimensions de l'écran
            mainStage.setWidth(screenWidth);
            mainStage.setHeight(screenHeight);

            // Définir la nouvelle scène sur la fenêtre principale
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
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

                showAlert(AlertType.INFORMATION, "supprimer vehicule  ", "Vehicule suprimer");

            } catch (Exception e) {
                e.printStackTrace();
                showAlert(AlertType.ERROR, "Database Erreur", "Error updating Formation: " + e.getMessage());
            }
        } else {
            showAlert(AlertType.WARNING, "No Selection", "No Course Selected. Please select a course in the table.");
        }
    }
    @FXML
    private void showAlert(AlertType alertType, String title, String message) {
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

                showAlert(AlertType.INFORMATION, "PDF Generated", "PDF file generated successfully.");

                // Open the PDF file using the default PDF viewer
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(AlertType.ERROR, "Error", "An error occurred while generating the PDF.");
            }
        } else {
            showAlert(AlertType.WARNING, "No Selection", "Please select a row from the table.");
        }
    }
    private void drawTable(PDPage page, PDPageContentStream contentStream, float y, float width, float margin, String[][] content, PDFont font, float rowHeight) throws IOException {
        final int rows = content.length;
        final int cols = content[0].length;
        final float tableHeight = rowHeight * rows;
        final float cellMargin = 2f;

        // Draw the logo
     /*   try (InputStream in = getClass().getResourceAsStream("logo.png")) {
            PDImageXObject logo = PDImageXObject.createFromByteArray(document, IOUtils.toByteArray(in), "logo");
            contentStream.drawImage(logo, margin, y - 20, 100, 40); // Adjust the coordinates and size as needed
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        // Draw the description
        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.newLineAtOffset(margin, y - 60); // Adjust the y-offset as needed
        contentStream.showText("This is a description of the table:"); // Add your description here
        contentStream.endText();

        // Draw the rows
        float nexty = y - 100; // Adjust the y-offset as needed
        for (int i = 0; i <= rows; i++) {
            contentStream.moveTo(margin, nexty);
            contentStream.lineTo(margin + width, nexty);
            contentStream.stroke();
            nexty -= rowHeight;
        }

        // Draw the columns
        float yStart = y - 100; // Adjust the y-offset as needed
        float yEnd = y - tableHeight - 100; // Adjust the y-offset as needed
        float nextx = margin;
        for (int i = 0; i <= cols; i++) {
            contentStream.moveTo(nextx, yStart);
            contentStream.lineTo(nextx, yEnd);
            contentStream.stroke();
            nextx += width / cols;
        }

        // Set the blue color
        contentStream.setNonStrokingColor(0, 0, 255); // Blue color

        // Now add the text
        contentStream.setFont(font, 12);
        float textx = margin + cellMargin;
        float texty = y - 115; // Adjust the y-offset as needed
        for (String[] aContent : content) {
            for (int j = 0; j < aContent.length; j++) {
                if (j == 0) {
                    contentStream. setNonStrokingColor(0, 0, 255); // Black color for the first column
                } else {
                    contentStream.setNonStrokingColor(0, 0, 0);
                    // Blue color for other columns
                }

                contentStream.beginText();
                contentStream.newLineAtOffset(textx, texty);
                contentStream.showText(aContent[j]);
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
