package com.example.demo;

import Entities.Servicerep;
import Entities.User;
import Services.ServicerepServices;
import Services.UserService;
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

public class Listefront implements Initializable {
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
    String loggedInUserEmail = DBUtils.getLoggedInUserEmail();
    UserService serviceuser=new UserService();
    User user= serviceuser.getuserfromemail(loggedInUserEmail);

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
    public void location(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/addloca.fxml"));
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
    private Button search_id;

    @FXML
    private TextField searchbar_id;

    @FXML
    private VBox vBox;


    @FXML
    private Button home;
    @FXML
    private Pagination pag;


    private static final int ITEMS_PER_PAGE = 4; // Nombre d'éléments par page


    private ObservableList<Servicerep> dataList = FXCollections.observableArrayList();
    private FilteredList<Servicerep> filteredData = new FilteredList<>(dataList, p -> true);
   // private ObservableList<Servicerep> originalData = FXCollections.observableArrayList();
   @FXML
   private MenuItem button_logout;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        filteredData = new FilteredList<>(dataList, p -> true);

        loadData(); // Charger les données
        filteredData.setPredicate(service -> service.getStatut().equalsIgnoreCase("accepter")); // Filtrer les données pour afficher uniquement les services avec un statut 'accepté'
        pag.setPageFactory(this::createPage); // Afficher les services filtrés dans la pagination
        bindSearchField(); // Activer la recherche


        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScence(actionEvent, "authentifier.fxml", "Log In", null, null,button_logout);
            }
        });
    }
    @FXML
    void Refreshliste() {
       dataList.clear(); // Effacer les données existantes
        loadData(); // Recharger les données
        pag.setPageFactory(this::createPage); // Recréer les pages
    }


  private VBox createPage(int pageIndex) {
      int fromIndex = pageIndex * ITEMS_PER_PAGE;
      int toIndex = Math.min(fromIndex + ITEMS_PER_PAGE, filteredData.size());
      List<Servicerep> pageServices = filteredData.sorted((s1, s2) -> {
          // Trier par likes puis par dislikes
          if (s1.getLikes() != s2.getLikes()) {
              return s2.getLikes() - s1.getLikes(); // Trie les likes par ordre décroissant
          } else {
              return s2.getDislikes() - s1.getDislikes(); // Si les likes sont égaux, trie les dislikes par ordre décroissant
          }
      }).subList(fromIndex, toIndex);

      VBox pageBox = new VBox();
      for (Servicerep service : pageServices) {
          try {
              FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("itemfront.fxml"));
              AnchorPane borderPan = fxmlLoader.load();
              ItemFront itemController = fxmlLoader.getController();
              itemController.setData(service);
              pageBox.getChildren().add(borderPan);
          } catch (IOException ex) {
              Logger.getLogger(ItemServicerep.class.getName()).log(Level.SEVERE, null, ex);
          }
      }

      return pageBox;
  }

    private void loadData() {
        ServicerepServices se = new ServicerepServices();
        dataList.addAll(se.getAllData());


    }

    private void updateView() {
        vBox.getChildren().clear();
        for (Servicerep prod : filteredData) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("itemfront.fxml"));
                AnchorPane borderPan = fxmlLoader.load();
                ItemFront itemController = fxmlLoader.getController();
                itemController.setData(prod);
                vBox.getChildren().add(borderPan);
            } catch (IOException ex) {
                Logger.getLogger(ItemFront.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

   private void bindSearchField() {
       searchbar_id.textProperty().addListener((observable, oldValue, newValue) -> {
           filteredData.setPredicate(service -> {
               if (newValue == null || newValue.isEmpty()) {
                   return service.getStatut().equalsIgnoreCase("accepter"); // Si la recherche est vide, afficher uniquement les services avec un statut 'accepté'
               }

               String searchText = newValue.toLowerCase();
               return service.getNomservice().toLowerCase().contains(searchText) && service.getStatut().equalsIgnoreCase("accepter"); // Filtrer les services par nom et statut 'accepté'
           });
           updateView();
       });
   }

@FXML
void SearchForProduit(ActionEvent event) {
    try {
        String searchText = searchbar_id.getText().toLowerCase();

        // Filtrer les données en fonction du texte de recherche
        filteredData.setPredicate(service -> {
            if (searchText.isEmpty()) {
                return service.getStatut().equalsIgnoreCase("accepter"); // Si le champ de recherche est vide, afficher uniquement les services avec un statut 'accepté'
            }
            return service.getNomservice().toLowerCase().contains(searchText) && service.getStatut().equalsIgnoreCase("accepter"); // Filtrer les services par nom et statut 'accepté'
        });

        // Mettre à jour la vue avec les services filtrés
        updateView();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    private void refreshServiceList() {
        vBox.getChildren().clear();
        initialize(null, null); // Recharge les services
    }

    @FXML
    void home(ActionEvent event) {

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
