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
import org.mindrot.jbcrypt.BCrypt;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class ResetPasswordController {

    @FXML
    private PasswordField oldPasswordField;
    @FXML
    private MenuItem button_logout;

    @FXML
    private PasswordField newPasswordField;

    String loggedInUserEmail = DBUtils.getLoggedInUserEmail();
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
        }}
    @FXML
    void resetPassword(ActionEvent event) {
        String oldPassword = oldPasswordField.getText();
        String newPassword = newPasswordField.getText();

        // Here, you can implement your password reset logic
        // For example, you might want to validate the old password and update it with the new one
        if (isValidOldPassword(oldPassword)) {
            // Hash the new password
            String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

            // Code to update the password in the database
            DBUtils.updateUserPassword(loggedInUserEmail, hashedPassword); // Assuming you have a method in DBUtils to update the user's password

            // Show a confirmation message to the user
            showAlert("Password Reset", "Password reset successfully!");
        } else {
            // Show an error message if the old password is invalid
            showAlert("Error", "Invalid old password. Please try again.");
        }
    }

    // Method to validate the old password (Dummy method for demonstration)
    private boolean isValidOldPassword(String oldPassword) {
        // Retrieve the old password associated with the user from the database
        String storedOldPassword = DBUtils.getUserPassword(loggedInUserEmail); // Assuming you have a method in DBUtils to retrieve the user's password

        // Compare the retrieved old password with the input old password
        return BCrypt.checkpw(oldPassword, storedOldPassword); // Assuming you're using BCrypt for password hashing

    }

    // Method to show an alert message
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    private Runnable onProfileUpdatedListener;
    @FXML
    private ImageView image;

    @FXML
    private Label usernam;
    @FXML
    private GridPane offre;

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
        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScence(actionEvent, "authentifier.fxml", "Log In", null, null,button_logout);
            }
        });

    }
    private static Stage currentStage;

    public static void setCurrentStage(Stage stage) {
        currentStage = stage;
    }
    // Method to handle the update button click

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
    private boolean updateUserProfile(String email, String nom , String prenom , String image , String loggedInUserEmail) {
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



}
