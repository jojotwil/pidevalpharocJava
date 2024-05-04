package com.controllers_interfaces;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import org.mindrot.jbcrypt.BCrypt;

public class ResetPasswordController {

    @FXML
    private PasswordField oldPasswordField;

    @FXML
    private PasswordField newPasswordField;

    String loggedInUserEmail = DBUtils.getLoggedInUserEmail();

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
}
