package com.example.demo;

import Entities.Demandeserv;
import Services.DemandeservServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListDemandeService implements Initializable {
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
    public void location(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/frontvl.fxml"));
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

    @FXML
    private MenuItem button_logout;


    @FXML
    private Button search_id;

    @FXML
    private TextField searchbar_id;

    @FXML
    private VBox vBox;

    @FXML
    private static ListService instance;

    @FXML
    private Button liste;

    @FXML
    private Pagination pag;

    private static final int ITEMS_PER_PAGE = 3; // Nombre d'éléments par page


    private ObservableList<Demandeserv> dataList = FXCollections.observableArrayList();
    private FilteredList<Demandeserv> filteredData = new FilteredList<>(dataList, p -> true);
    @FXML
    public void monprofil(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profil.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"
            ProfilController controller = loader.getController();

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profil.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "profil.fxml"
            ProfilController controller = loader.getController();

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
    @FXML
    public void event(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("index-evenement-front.fxml"));
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
    public void troc(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-viewtroc.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"
            HelloControllertroc controller = loader.getController();

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
    public void service(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listefront.fxml"));
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
    public void initialize(URL url, ResourceBundle resourceBundle) {


        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScence(actionEvent, "authentifier.fxml", "Log In", null, null,button_logout);
            }
        });



        loadData();
        pag.setPageFactory(this::createPage);
        bindSearchField();


    }

    @FXML
    void Refreshliste(ActionEvent event) {
        dataList.clear(); // Effacer les données existantes
        loadData(); // Recharger les données
        pag.setPageFactory(this::createPage); // Recréer les pages

    }

    private VBox createPage(int pageIndex) {
        int fromIndex = pageIndex * ITEMS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ITEMS_PER_PAGE, filteredData.size());
        List<Demandeserv> pageServices = filteredData.subList(fromIndex, toIndex);

        VBox pageBox = new VBox();
        for (Demandeserv deservice : pageServices) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("itemdemandeservice.fxml"));
                AnchorPane borderPan = fxmlLoader.load();
                ItemDemandeserv itemController = fxmlLoader.getController();
                itemController.setData(deservice);
                pageBox.getChildren().add(borderPan);
            } catch (IOException ex) {
                Logger.getLogger(ItemServicerep.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return pageBox;
    }

    public void loadData() {
        DemandeservServices se = new DemandeservServices();
        dataList.addAll(se.getAllData());

    }

    public void updateView() {
        vBox.getChildren().clear();
        for (Demandeserv prod : filteredData) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("itemdemandeservice.fxml"));
                AnchorPane borderPan = fxmlLoader.load();
                ItemDemandeserv itemController = fxmlLoader.getController();
                itemController.setData(prod);
                vBox.getChildren().add(borderPan);
            } catch (IOException ex) {
                Logger.getLogger(ItemDemandeserv.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void bindSearchField() {
        searchbar_id.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(service -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchText = newValue.toLowerCase();
                return service.getNom().toLowerCase().contains(searchText);
            });
            updateView();
        });
    }

    @FXML
    public static ListService getInstance() {
        if (instance == null) {
            instance = new ListService();
        }
        return instance;
    }



    @FXML
    protected void SearchForProduit(ActionEvent event) {
        try {
            DemandeservServices se = new DemandeservServices();
            List<Demandeserv> prods = se.getAllData();

            vBox.getChildren().clear();

            String searchText = searchbar_id.getText().toLowerCase();

            for (Demandeserv prod : prods) {
                if (prod.getNom().toLowerCase().contains(searchText)) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("itemdemandeservice.fxml"));
                    AnchorPane borderPan = fxmlLoader.load();
                    ItemDemandeserv itemController = fxmlLoader.getController();
                    itemController.setData(prod);
                    vBox.getChildren().add(borderPan);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshServiceList() {
        try {
            DemandeservServices se = new DemandeservServices();
            List<Demandeserv> prods = se.getAllData();

            for (Demandeserv prod : prods) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("itemdemandeservice.fxml"));
                    AnchorPane borderPan = fxmlLoader.load();
                    ItemDemandeserv itemController = fxmlLoader.getController();
                    itemController.setData(prod);
                    vBox.getChildren().add(borderPan);
                } catch (IOException ex) {
                    Logger.getLogger(ItemServicerep.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        loadData();
        bindSearchField();
    }
    @FXML
    void liste(ActionEvent event) {

        try {
            // Charger le fichier FXML
            Parent addCivilisationParent = FXMLLoader.load(getClass().getResource("listefront.fxml"));
            Scene addCivilisationScene = new Scene(addCivilisationParent);

            // Obtenir la scène actuelle à partir de l'événement
            Stage window = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Changer la scène pour afficher la nouvelle vue
            window.setScene(addCivilisationScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace(); // Gérer l'erreur de chargement du fichier FXML


        }
    }




}


