package com.example.demo;

import Entities.PostTroc;
import Entities.User;
import Services.PostTrocService;
import Services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class UpdateProfileController{
    @FXML
    private MenuItem button_logout;
    @FXML
    private TextField tfName;

    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf_image;
    private Runnable onProfileUpdatedListener;
    @FXML
    private ImageView image;

    @FXML
    private Label usernam;
    @FXML
    private GridPane offre;
    String loggedInUserEmail = DBUtils.getLoggedInUserEmail();
    UserService serviceuser=new UserService();
    User user= serviceuser.getuserfromemail(loggedInUserEmail);

    PostTrocService postTrocService=new PostTrocService();
    ObservableList<PostTroc> Liste=postTrocService.getAllpostes();
    ObservableList<PostTroc> ListeData= FXCollections.observableArrayList();
    public void initialize() {
        System.out.println(loggedInUserEmail+" email user cnt");


        System.out.println(user);
        usernam.setText(user.getPrenom()+" "+user.getNom());
        try {
            Image imagee = new Image( user.getImage());
            image.setImage(imagee);
        } catch (Exception e) {
            // Gérer l'erreur, par exemple afficher un message d'erreur ou une image par défaut
            System.out.println("Erreur lors du chargement de l'image : " + e.getMessage());
        }
        fetchUserInfo(loggedInUserEmail);
        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScence(actionEvent, "authentifier.fxml", "Log In", null, null , button_logout);
            }
        });
    }
    private static Stage currentStage;

    public static void setCurrentStage(Stage stage) {
        currentStage = stage;
    }
    // Method to handle the update button click
    @FXML
    void handleUpdateButtonClick(ActionEvent event ) {
        String nom = tfName.getText();
        String email = tfEmail.getText();
        String prenom = tf_prenom.getText();
        String image = tf_image.getText();
        // Perform validation checks if required
// Perform validation checks
        if (!isValidEmail(email)) {
            showAlert("Error", "Invalid Email", "Please enter a valid email address.");
            return;
        }

        if (!isValidName(nom)) {
            showAlert("Error", "Invalid Name", "Please enter a valid name.");
            return;
        }

        if (!isValidName(prenom)) {
            showAlert("Error", "Invalid Surname", "Please enter a valid surname.");
            return;
        }

        if (image.isEmpty()) {
            showAlert("Error", "No Image Selected", "Please select an image.");
            return;
        }
        // Update the user's profile in the database
        boolean success = updateUserProfile(email, nom,prenom,image, loggedInUserEmail,event);

        if (success) {


            // Display success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Profile updated successfully");
            alert.showAndWait();
            // Close the current stage
            // Close the current stage
            if (currentStage != null) {
                currentStage.close();
            }


            // Call the listener to navigate to sign-in page
            if (onProfileUpdatedListener != null) {
                onProfileUpdatedListener.run();
            }




        } else {
            // Display error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to update profile!");
            alert.showAndWait();
        }

    }
    @FXML
    void calander(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("testcalander.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"
            testcalandercontroller controller = loader.getController();

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
    public void amoi(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("boitedereception.fxml"));
            Parent newContent = loader.load();

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
    public void addposttroc(ActionEvent event) {
        try {
            // Charger la nouvelle interface dans un Node
            FXMLLoader loader = new FXMLLoader(getClass().getResource("postcrud.fxml"));
            Parent newContent = loader.load();

            // Accéder au contrôleur de la vue "posttroccrud.fxml"
            postcrudController controller = loader.getController();
            controller.add();

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
    public void setOnProfileUpdatedListener(Runnable listener) {
        this.onProfileUpdatedListener = listener;
    }

    public void browseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");

        // Set initial directory for file chooser (optional)
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // Filter files to show only image files (optional)
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        // Show open file dialog
        File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

        // If user selects a file, set its path to the TextField
        if (selectedFile != null) {
            tf_image.setText(selectedFile.getAbsolutePath());
        }
    }
    @FXML

    void handleUpdateProfileButtonClick(ActionEvent event) {
        try {
            String loggedInUserEmail = DBUtils.getLoggedInUserEmail();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateProfile.fxml"));
            Pane updateProfilePane = loader.load();
            UpdateProfileController updateProfileController = loader.getController();

            // Pass any necessary information to the update profile controller

            Stage stage = new Stage();
            stage.setScene(new Scene(updateProfilePane));
            stage.setTitle("Update Profile");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to update user's profile in the database
    // Method to update user's profile in the database
    private boolean updateUserProfile(String email, String nom , String prenom , String image , String loggedInUserEmail,ActionEvent event) {
        // Database connection and update logic
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/alphatroc", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET email = ?, nom= ? , prenom= ?, image= ? WHERE email = ?");
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,nom);
            preparedStatement.setString(3,prenom);
            preparedStatement.setString(4,image);
            preparedStatement.setString(5, loggedInUserEmail); // Use email to identify the user to update
            int rowsAffected = preparedStatement.executeUpdate();
            // Update loggedInUserEmail if the update operation was successful
            ProfilController profilController=new ProfilController();
            monprofil(event);
            profilController.handleRefreshButtonClick(event);
            if (rowsAffected > 0) {
                DBUtils.setLoggedInUserEmail(email);
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // Method to validate email format
    private boolean isValidEmail(String email) {
        // Implement your email validation logic here
        // You can use regex or any other method to validate email format
        return email.matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b");
    }

    // Method to validate name and surname
    private boolean isValidName(String name) {
        // Implement your name validation logic here
        // For example, check if the name contains only letters and is not empty
        return !name.isEmpty() && name.matches("[a-zA-Z]+");
    }

    // Method to display an alert
    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    private void fetchUserInfo(String email) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/alphatroc", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT nom, email, prenom, image FROM user WHERE email = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String emailResult = resultSet.getString("email");
                String prenom = resultSet.getString("prenom");
                String image = resultSet.getString("image");

                tfName.setText(nom);
                tfEmail.setText(emailResult);
                tf_prenom.setText(prenom);
                tf_image.setText(image);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void handleResetPasswordButtonClick(ActionEvent event) {
        try {
            String loggedInUserEmail = DBUtils.getLoggedInUserEmail();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ResetPassword.fxml"));
            Pane updateProfilePane = loader.load();
            //UpdateProfileController updateProfileController = loader.getController();

            // Pass any necessary information to the update profile controller

            Stage stage = new Stage();
            stage.setScene(new Scene(updateProfilePane));
            stage.setTitle("Update Profile");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
