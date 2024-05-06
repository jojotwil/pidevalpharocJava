package com.controllers_interfaces;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.*;

import static com.controllers_interfaces.DBUtils.changeScence;

public class UpdateProfileController  {

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf_image;
    private Runnable onProfileUpdatedListener;
    String loggedInUserEmail = DBUtils.getLoggedInUserEmail();
    public void initialize() {

        fetchUserInfo(loggedInUserEmail);
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
        boolean success = updateUserProfile(email, nom,prenom,image, loggedInUserEmail);

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


}
