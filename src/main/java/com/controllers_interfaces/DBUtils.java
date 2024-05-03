package com.controllers_interfaces;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


public class DBUtils {

    private static String loggedInUserEmail;
    private static Map<String, String> verificationCodes = new HashMap<>();
    public static String getUserPassword(String userEmail) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String password = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/alphatroc", "root", "");
            preparedStatement = connection.prepareStatement("SELECT password FROM user WHERE email = ?");
            preparedStatement.setString(1, userEmail);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                password = resultSet.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return password;
    }

    public static String getUserImagePath(String userEmail) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String imagePath = null;

        try {
            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/alphatroc", "root", "");

            // Prepare SQL statement to select image path based on user email
            String query = "SELECT image FROM user WHERE email = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userEmail);

            // Execute query
            resultSet = preparedStatement.executeQuery();

            // If user with the provided email exists, retrieve the image path
            if (resultSet.next()) {
                imagePath = resultSet.getString("image");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close all resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return imagePath; // Return the image path
    }
    // Other methods...

    public static String getLoggedInUserEmail() {
        return loggedInUserEmail;
    }

    public static boolean updateUserPassword(String email, String newPassword) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Get a connection to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/alphatroc", "root", "");

            // SQL statement to update the user's password
            String sql = "UPDATE user SET password = ? WHERE email = ?";

            // Create a prepared statement
            statement = connection.prepareStatement(sql);

            // Set parameters for the query
            statement.setString(1, newPassword);
            statement.setString(2, email);

            // Execute the update statement
            int rowsUpdated = statement.executeUpdate();

            // Check if the update was successful
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Close the connection and statement
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setLoggedInUserEmail(String email) {
        loggedInUserEmail = email;
    }

    public static void changeScence(ActionEvent event, String fxmlFile, String title, String username, String note) {
        Parent root = null;
        if (username != null && note != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();

                LoggedInController loggedInController = loader.getController();
                // loggedInController.setUserInformation(username,note);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 1000, 600));
        stage.show();

    }

    public static void loadAdminDashboard(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource("admindashboard.fxml"));
            Parent root = loader.load();
            AdminDashboardController adminDashboardController = loader.getController();

            // Set any necessary data or parameters for the controller

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Admin Dashboard");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void signupUser(ActionEvent event, String email, String plainPassword, String roles, String nom, String prenom, String image) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        if (!isValidEmail(email)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid email address!");
            alert.show();
            return;
        }

        // Validate password strength
        if (!isStrongPassword(plainPassword)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Password is not strong enough!");
            alert.show();
            return;
        }

        // Validate nom and prenom
        if (!isValidName(nom) || !isValidName(prenom)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid nom or prenom!");
            alert.show();
            return;
        }
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/alphatroc", "root", "");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM user WHERE email = ?");
            psCheckUserExists.setString(1, email);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username!");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO user (email,roles, password,nom,prenom, image, is_verified,isblocked) VALUES (?,?,?,?,?,?,0,0)");
                psInsert.setString(1, email);
                psInsert.setString(2, roles);
                psInsert.setString(3, hashedPassword);
                psInsert.setString(4, nom);
                psInsert.setString(5, prenom);
                psInsert.setString(6, image);
                psInsert.executeUpdate();
                // sendVerificationEmail(email, verificationToken);
                // Send confirmation email with a verification token
                String verificationToken = generateVerificationToken(email);
                verificationCodes.put(email, verificationToken);
                sendVerificationEmail(email, verificationToken);

                loggedInUserEmail = email;
                changeScence(event, "logged-In.fxml", "Welcome", email, nom);
                showVerificationCodeEntryScene(event, email);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    private static void showVerificationCodeEntryScene(ActionEvent event, String email) {
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource("VerificationCodeEntry.fxml"));
            Parent root = loader.load();
            VerificationCodeEntryController controller = loader.getController();
            controller.setUserEmail(email);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide(); // Hide the current window
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void verifyAccount(String email, String verificationToken) {
        // Check if verification code matches the one stored for the user
        String storedVerificationCode = verificationCodes.get(email);
        if (storedVerificationCode != null && storedVerificationCode.equals(verificationToken)) {
            // Verification successful, remove verification code from temporary storage
            verificationCodes.remove(email);
            // Update user's account as verified (you can implement this logic as per your requirements)
            if (updateUserVerified(email)) {
                // User account successfully updated as verified
                // Redirect the user to the dashboard or main application window
                // You can implement your own method for redirection
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("account verified");
            } else {
                // Failed to update user account as verified
                // Show an error message to the user
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("account verified");
                alert.show();
            }
        } else {
            // Verification failed, show an error message to the user
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("account verified");
            alert.show();
        }
    }
    public static void logInUser(ActionEvent event, String email, String plainPassword,boolean rememberMe) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/alphatroc", "root", "");
            preparedStatement = connection.prepareStatement("SELECT password, roles, isblocked FROM user WHERE email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found in the database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are in correct!");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String hashedPassword = resultSet.getString("password");
                    String userRole = resultSet.getString("roles");
                    boolean isBlocked = resultSet.getBoolean("isblocked");
                    if (BCrypt.checkpw(plainPassword, hashedPassword)) {
                        if (isBlocked) {
                            // User is blocked, display an alert
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Your account has been blocked. Please contact the admin for assistance.");
                            alert.show();
                        } else {
                            loggedInUserEmail = email;

                            if (userRole.equals("[\"ROLE_ADMIN\"]")) {
                                loadAdminDashboard(event);
                            } else {
                                changeScence(event, "logged-In.fxml", "Welcome", email, "");
                            }
                        }
                    } else {
                        System.out.println("password did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are in correct!");
                        alert.show();
                    }
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Method to validate strong password
    public static boolean isStrongPassword(String plainPassword) {
        // Implement criteria for strong password (e.g., minimum length, uppercase, lowercase, digits, special characters)
        return plainPassword.length() >= 8 &&
                plainPassword.matches(".*[A-Z].*") &&
                plainPassword.matches(".*[a-z].*") &&
                plainPassword.matches(".*\\d.*") &&
                plainPassword.matches(".*[!@#$%^&*()-_=+\\\\|[{]};:'\",<.>/?].*");
    }

    // Method to validate nom and prenom (only alphabetic characters and not empty)
    public static boolean isValidName(String name) {
        return !name.isEmpty() && name.length() >= 4 && name.matches("^[a-zA-Z]+$");
    }

    public static String[] getUserInformation(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String[] userInfo = new String[2]; // Array to store user information: email and note

        try {
            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/alphatroc", "root", "");

            // Prepare SQL statement to select user information based on email
            preparedStatement = connection.prepareStatement("SELECT email FROM user WHERE email = ?");
            preparedStatement.setString(1, email);

            // Execute query
            resultSet = preparedStatement.executeQuery();

            // If user with the provided email exists, retrieve user information
            if (resultSet.next()) {
                userInfo[0] = resultSet.getString("email");
                userInfo[1] = resultSet.getString("note");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close all resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return userInfo; // Return user information array
    }

    public static void deleteUserProfile(ActionEvent event, String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/alphatroc", "root", "");
            preparedStatement = connection.prepareStatement("DELETE FROM user WHERE email = ?");
            preparedStatement.setString(1, email);
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("User profile deleted successfully.");
                changeScence(event, "hello-view.fxml", "Login", null, null);
                ;
            } else {
                System.out.println("No user profile found with the provided email.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static String generateVerificationToken(String email) {
        // You can use a UUID generator or any other secure random string generation method
        return java.util.UUID.randomUUID().toString();
    }

    // Method to send a verification email with the token
    private static void sendVerificationEmail(String email, String verificationToken) {
        // Configure email properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // Replace with your SMTP server host
        props.put("mail.smtp.port", "587"); // Replace with your SMTP server port

        // Set authentication credentials (if required by your SMTP server)
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("jarayatef@gmail.com", "alfkcmkhsazhlryt"); // Replace with your email credentials
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("jarayatef@gmail.com")); // Replace with your email address
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Account Verification");

            // Create email content with verification link
           // String verificationLink = "http://localhost/alpharocc/public/index.php/verify/email?token=" + verificationToken; // Replace with your verification link format
            String emailBody = "Your verification code is: " + verificationToken;
            message.setContent(emailBody, "text/plain");

            Transport.send(message);
            System.out.println("Verification email sent to " + email);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error sending verification email: " + e.getMessage());
        }
    }
    public static boolean updateUserVerified(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/alphatroc", "root", "");
            preparedStatement = connection.prepareStatement("UPDATE user SET is_verified = 1 WHERE email = ?");
            preparedStatement.setString(1, email);
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated == 1; // True if exactly one row was updated
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
        }

        return false;
    }
    public static void sendForgotPasswordEmail(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/alphatroc", "root", "");
            preparedStatement = connection.prepareStatement("SELECT email FROM user WHERE email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                String verificationToken = generateVerificationToken(email);
                sendResetPasswordEmail(email, verificationToken);
                System.out.println("Reset password email sent to " + email);
            } else {
                System.out.println("User not found in the database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("The email address you entered is not registered.");
                alert.show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
        }
    }
    private static void sendResetPasswordEmail(String email, String verificationToken) {
        // ... (existing code to configure email properties)
        // Configure email properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // Replace with your SMTP server host
        props.put("mail.smtp.port", "587"); // Replace with your SMTP server port

        // Set authentication credentials (if required by your SMTP server)
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("jarayatef@gmail.com", "alfkcmkhsazhlryt"); // Replace with your email credentials
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("jarayatef@gmail.com")); // Replace with your email address
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Reset Password");

            // Create email content with password reset link
            String resetLink = "http://localhost/reset-password?token=" + verificationToken; // Replace with your reset password link format
            String emailBody = "Click the following link to reset your password:\n" + resetLink;
            message.setContent(emailBody, "text/plain");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error sending reset password email: " + e.getMessage());
        }
    }
    public static String resetPassword(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String newPassword = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/alphatroc", "root", "");

            // Check if user exists
            preparedStatement = connection.prepareStatement("SELECT email FROM user WHERE email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found in the database!");
                return null; // User not found, return null
            }

            // Generate a random, secure password
            newPassword = generateRandomPassword();

            // Hash the new password
            String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

            // Update the password in the database
            preparedStatement = connection.prepareStatement("UPDATE user SET password = ? WHERE email = ?");
            preparedStatement.setString(1, hashedPassword);
            preparedStatement.setString(2, email);
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated == 1) {
                // Password reset successful, return the new password
                return newPassword;
            } else {
                // Password reset failed
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private static String generateRandomPassword() {
        int length = 10; // Length of the random password
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * characters.length());
            password.append(characters.charAt(index));
        }
        return password.toString();
    }
    private static int calculatePasswordStrength(String password) {
        int strength = 0;

        // Criteria 1: Check password length
        if (password.length() >= 8) {
            strength++;
        }

        // Criteria 2: Check for presence of uppercase letters
        if (password.matches(".*[A-Z].*")) {
            strength++;
        }

        // Criteria 3: Check for presence of lowercase letters
        if (password.matches(".*[a-z].*")) {
            strength++;
        }

        // Criteria 4: Check for presence of digits
        if (password.matches(".*\\d.*")) {
            strength++;
        }

        // Criteria 5: Check for presence of special characters
        if (password.matches(".*[!@#$%^&*()-_=+\\\\|[{]};:'\",<.>/?].*")) {
            strength++;
        }

        return strength;
    }


}

