    package com.controllers_interfaces;

    import javafx.fxml.FXML;
    import javafx.scene.control.Label;

    public class VerificationController {
        @FXML

        private Label verificationMessage; // Reference to a label in your FXML for displaying messages

        public void handleVerification(String email) {
            // Extract email address from the URL parameter
            String userEmail = email;

            // Retrieve user information using DBUtils
            String[] userInfo = DBUtils.getUserInformation(userEmail);

            // Check if user exists and isn't already verified
            if (userInfo != null && userInfo.length > 0 && userInfo[0].equals("0")) { // Assuming "0" indicates unverified user
                // Update user's verified flag to true
                if (DBUtils.updateUserVerified(userEmail)) {
                    verificationMessage.setText("Your email has been verified successfully!");
                    // Optionally, redirect to login page or show success message
                } else {
                    verificationMessage.setText("An error occurred while verifying your email. Please try again.");
                }
            } else {
                verificationMessage.setText("Invalid verification link or user already verified.");
            }
        }
    }
