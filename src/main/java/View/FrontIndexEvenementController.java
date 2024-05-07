package View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

import Entities.PostTroc;
import com.example.demo.DBUtils;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.awt.*;
import java.net.URL;

import Entities.Avis;
import Entities.Evenement;
import Services.AvisService;
import Services.EvenementService;
import Services.IServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.*;
import java.util.List;


public class FrontIndexEvenementController implements Initializable {
    public void boutique(ActionEvent event){
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listBoutiqueFront.fxml"));
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

    @FXML
    private MenuItem button_logout;







    private String imagePath;
    @FXML
    private ImageView imageview;

    @FXML
    private Button btnimg;



    @FXML
    private void browseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Fichiers images", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Tous les fichiers", "*.*")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imageview.setImage(image);
            System.out.println(selectedFile.toURI().toString());
            PostTroc post=new PostTroc();
            post.setImage(selectedFile.toURI().toString());
            imagePath=selectedFile.toURI().toString();


        }
    }



// Autres méthodes de validation et d'affichage d'alerte comme dans la méthode addposttroc()



    @FXML
    public void troc(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/hello-viewtroc.fxml"));
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
    @FXML
    public void event(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/index-evenement-front.fxml"));
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
    @FXML
    public void monprofil(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/profil.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"
            //ProfilController controller = loader.getController();

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
    @FXML
    public void monprofil1(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/profil.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "profil.fxml"
           // ProfilController controller = loader.getController();

            // Créer une nouvelle scène avec le nouveau contenu
            Scene scene = new Scene(newContent);

            // Obtenir la fenêtre principale (stage)
            Stage mainStage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();

            // Obtenir les dimensions de l'écran
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            // Définir la taille de la fenêtre sur les dimensions de l'écran
            mainStage.setWidth(screenBounds.getWidth());
            mainStage.setHeight(screenBounds.getHeight());

            // Définir la nouvelle scène sur la fenêtre principale
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EvenementService ps = new EvenementService();
        ps.deletePastEvent();
        refreshCardView();
        afficherMeilleurEvenement();


        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScence(actionEvent, "authentifier.fxml", "Log In", null, null,button_logout);
            }
        });


    }

    @FXML
    private VBox cardViewContainer;


    @FXML
    private Label meilleurevenement;
    private ObservableList<Evenement> evenements = FXCollections.observableArrayList();
    private IServices<Evenement> evenementService = new EvenementService();

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Label[] starLabels = new Label[5];
    private int selectedNote = 0;




    @FXML
    void exportToExcel() {
        try {
            File file = new File("evenementExcel.xlsx");

            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Evenements");

                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("ID");
                headerRow.createCell(1).setCellValue("Titre");
                headerRow.createCell(2).setCellValue("Theme");
                headerRow.createCell(3).setCellValue("Localisation");
                headerRow.createCell(4).setCellValue("Description");
                headerRow.createCell(5).setCellValue("Date Debut");
                headerRow.createCell(6).setCellValue("Date Fin");

                // Instanciation du service EvenementService
                EvenementService evenementService = new EvenementService();

                // Récupération de la liste des événements
                ArrayList<Evenement> evenementsList = evenementService.readAll();

                for (int i = 0; i < evenementsList.size(); i++) {
                    Row row = sheet.createRow(i + 1);
                    Evenement evenement = evenementsList.get(i);

                    row.createCell(0).setCellValue(evenement.getId());
                    row.createCell(1).setCellValue(evenement.getTitre());
                    row.createCell(2).setCellValue(evenement.getTheme());
                    row.createCell(3).setCellValue(evenement.getLocalisation());
                    row.createCell(4).setCellValue(evenement.getDescription());
                    row.createCell(5).setCellValue(evenement.getDate_debut().toString()); // You may want to format this date
                    row.createCell(6).setCellValue(evenement.getDate_fin().toString()); // You may want to format this date
                }

                try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                    workbook.write(fileOutputStream);
                }

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Exportation réussie");
                successAlert.setHeaderText("Exportation Excel");
                successAlert.setContentText("Les données ont été exportées avec succès dans le fichier 'evenementExcel.xlsx' !");
                successAlert.showAndWait();
                Desktop.getDesktop().open(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur d'exportation");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de l'exportation des données.");
            alert.showAndWait();
        }
    }



    public void refreshCardView() {
        cardViewContainer.getChildren().clear();
        evenements.clear();
        evenements.addAll(evenementService.readAll());

        for (Evenement evenement : evenements) {
            AnchorPane card = createCardView(evenement);
            cardViewContainer.getChildren().add(card);
        }
    }

    private AnchorPane createCardView(Evenement evenement) {
        AnchorPane card = new AnchorPane();
        card.getStyleClass().add("card-view");

        Label titreLabel = new Label(evenement.getTitre());
        titreLabel.setFont(new Font("System Bold", 16));

        Label dateDebutLabel = new Label("Date de début: " + dateFormat.format(evenement.getDate_debut()));
        Label dateFinLabel = new Label("Date de fin: " + dateFormat.format(evenement.getDate_fin()));
        Label themeLabel = new Label("Thème: " + evenement.getTheme());
        Label localisationLabel = new Label("Localisation: " + evenement.getLocalisation());
        Label descriptionLabel = new Label("Description: " + evenement.getDescription());

        VBox content = new VBox(titreLabel, dateDebutLabel, dateFinLabel, themeLabel, localisationLabel, descriptionLabel);
        content.setSpacing(5);
        content.setPadding(new Insets(10));

        Button donnerAvisButton = new Button("Donner avis");
        donnerAvisButton.setOnAction(event -> openAvisDialog(evenement));

        Button voirAvisButton = new Button("Voir les avis");
        voirAvisButton.setOnAction(event -> showAvisForEvenement(evenement));


        HBox buttonsBox = new HBox(donnerAvisButton, voirAvisButton);
        buttonsBox.setSpacing(10); // Espacement entre les boutons

        VBox cardContent = new VBox(content, buttonsBox); // Ajouter les boutons à la carte

        card.getChildren().addAll(cardContent);
        card.setEffect(new javafx.scene.effect.DropShadow(10, Color.GRAY));

        card.setOnMouseClicked(event -> {
            showEvenementDetails(evenement);
            FrontAddTicketController controller=new FrontAddTicketController();
            controller.setEvenement(evenement);
            System.out.println(evenement);
        });

        return card;
    }



    private void showEvenementDetails(Evenement evenement) {

    }

    private void showAlertDialog(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void launchAddTicketForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/reserver-front.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Ajouter un nouveau ticket");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (Exception e) {
            showAlertDialog(Alert.AlertType.ERROR, "Erreur", "Erreur lors du chargement du formulaire", e.getMessage());
        }
    }
    private void openAvisDialog(Evenement evenement) {
        // Initialiser les étoiles pour la note
        AnchorPane starPane = new AnchorPane();
        starPane.setPrefHeight(30);
        starPane.setPrefWidth(150);
        initStarRating(starPane);

        // Créez une boîte de dialogue de type INFORMATION
        Alert avisDialog = new Alert(Alert.AlertType.INFORMATION);
        avisDialog.setTitle("Donner avis");
        avisDialog.setHeaderText("Donner un avis pour : " + evenement.getTitre());

        // Créez des champs de saisie pour la note et le commentaire
        TextArea commentaireArea = new TextArea();
        commentaireArea.setPromptText("Commentaire");

        // Ajoutez les champs à la boîte de dialogue
        VBox vbox = new VBox(starPane, commentaireArea);
        vbox.setSpacing(10);
        avisDialog.getDialogPane().setContent(vbox);

        // Ajoutez des boutons personnalisés
        avisDialog.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

        // Attendez la réponse de l'utilisateur
        avisDialog.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                // Récupérez les valeurs saisies par l'utilisateur
                String commentaire = commentaireArea.getText();
                int note = selectedNote; // Utilisez la note sélectionnée

                // Créez un nouvel objet Avis avec les données saisies par l'utilisateur
                Avis avis = new Avis();
                avis.setEvent_id(evenement.getId()); // ID de l'événement associé à cet avis
                avis.setNote(note);
                avis.setCommentaire(commentaire);

                // Enregistrez l'avis dans la base de données en utilisant votre service AvisService
                AvisService avisService = new AvisService();
                avisService.create(avis);

                // Affichez un message de confirmation à l'utilisateur
                Alert confirmationDialog = new Alert(Alert.AlertType.INFORMATION);
                confirmationDialog.setTitle("Confirmation");
                confirmationDialog.setHeaderText(null);
                confirmationDialog.setContentText("Avis enregistré avec succès.");
                confirmationDialog.showAndWait();
            }
        });
    }


    private void showAvisForEvenement(Evenement evenement) {
        // Récupérez les avis pour cet événement à l'aide d'un service AvisService
        AvisService avisService = new AvisService();
        List<Avis> avisList = avisService.getAvisForEvenement(evenement.getId());

        // Créez une boîte de dialogue ou une nouvelle fenêtre pour afficher les avis
        // Vous pouvez utiliser une ListView ou tout autre composant pour afficher les avis
        // Voici un exemple de boîte de dialogue simple avec une ListView :
        ListView<String> avisListView = new ListView<>();
        avisList.forEach(avis -> {
            String avisText = "Note: " + avis.getNote() + ", Commentaire: " + avis.getCommentaire();
            avisListView.getItems().add(avisText);
        });

        Alert avisDialog = new Alert(Alert.AlertType.INFORMATION);
        avisDialog.setTitle("Avis pour " + evenement.getTitre());
        avisDialog.setHeaderText(null);
        avisDialog.getDialogPane().setContent(avisListView);
        avisDialog.showAndWait();
    }
    private void initStarRating(AnchorPane parentPane) {
        // Créez des étiquettes pour chaque étoile et configurez leur apparence
        for (int i = 0; i < 5; i++) {
            Label starLabel = new Label("☆");
            starLabel.setFont(new Font("Arial", 24));
            starLabels[i] = starLabel;
            int finalI = i + 1; // Notez que finalI est effectivement une variable finale pour utilisation dans la lambda

            // Gestionnaire pour la survol de la souris
            starLabel.setOnMouseEntered(event -> {
                if (finalI <= selectedNote) {
                    updateStarRating(finalI, "yellow"); // Si la note est déjà sélectionnée, changez la couleur en jaune
                } else {
                    updateStarRating(finalI, "black"); // Sinon, changez la couleur en noir
                }
            });

            // Gestionnaire pour cliquer sur l'étoile
            starLabel.setOnMouseClicked(event -> {
                selectedNote = finalI;
                updateStarRating(finalI, "yellow"); // Mettre à jour la couleur des étoiles jusqu'à la note sélectionnée
            });

            // Ajouter l'étiquette d'étoile au conteneur parent
            parentPane.getChildren().add(starLabel);

            // Définir les positions x et y de l'étoile
            AnchorPane.setLeftAnchor(starLabel, i * 30.0);
            AnchorPane.setTopAnchor(starLabel, 0.0);
        }
    }

    // Méthode pour mettre à jour l'apparence des étoiles jusqu'à la note sélectionnée
    private void updateStarRating(int selectedNote, String color) {
        for (int i = 0; i < 5; i++) {
            if (i < selectedNote) {
                starLabels[i].setText("★"); // Étoile pleine pour les notes sélectionnées
            } else {
                starLabels[i].setText("☆"); // Étoile vide pour les notes non sélectionnées
            }
            starLabels[i].setTextFill(Color.web(color)); // Mettre à jour la couleur de l'étoile
        }
    }



    private void afficherMeilleurEvenement() {
        double meilleureMoyenne = 0;
        Evenement meilleurEvenement = null;

        // Parcourir tous les événements
        for (Evenement evenement : evenements) {
            // Récupérer les avis associés à cet événement
            AvisService avisService = new AvisService();
            List<Avis> avisList = avisService.getAvisForEvenement(evenement.getId());

            // Calculer la moyenne des notes pour cet événement
            double moyenne = calculerMoyenneNotes(avisList);

            // Mettre à jour l'événement avec la meilleure moyenne si nécessaire
            if (moyenne > meilleureMoyenne) {
                meilleureMoyenne = moyenne;
                meilleurEvenement = evenement;
            }
        }

        // Afficher les détails du meilleur événement dans le label "meilleurevenement"
        if (meilleurEvenement != null) {
            meilleurevenement.setText("Meilleur événement : " + meilleurEvenement.getTitre() + ", Moyenne des notes : " + meilleureMoyenne);
        } else {
            meilleurevenement.setText("Aucun événement trouvé");
        }
    }

    private double calculerMoyenneNotes(List<Avis> avisList) {
        if (avisList.isEmpty()) {
            return 0; // Retourner 0 si aucun avis n'est disponible
        }

        double totalNotes = 0;
        for (Avis avis : avisList) {
            totalNotes += avis.getNote();
        }

        return totalNotes / avisList.size(); // Calculer la moyenne
    }
    @FXML
    private TextField searchField;

    @FXML
    private void searchByName() {
        String searchQuery = searchField.getText().trim().toLowerCase();

        if (searchQuery.isEmpty()) {
            refreshCardView(); // Si la recherche est vide, afficher tous les événements
        } else {
            // Filtrer les événements par nom
            ObservableList<Evenement> filteredList = FXCollections.observableArrayList();
            for (Evenement evenement : evenements) {
                if (evenement.getTitre().toLowerCase().contains(searchQuery)) {
                    filteredList.add(evenement);
                }
            }
            // Afficher les événements filtrés
            cardViewContainer.getChildren().clear();
            for (Evenement evenement : filteredList) {
                AnchorPane card = createCardView(evenement);
                cardViewContainer.getChildren().add(card);
            }
        }
    }



}