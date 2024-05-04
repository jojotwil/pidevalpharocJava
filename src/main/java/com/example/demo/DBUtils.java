package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBUtils {
    private static String loggedInUserEmail;
    public static String getLoggedInUserEmail() {
        return loggedInUserEmail;
    }

    public static void setLoggedInUserEmail(String loggedInUserEmail) {
        DBUtils.loggedInUserEmail = loggedInUserEmail;
    }

    public static void signupUser(ActionEvent event, String email, String plainPassword, String roles, String nom, String prenom, String image) {
        System.out.println(roles);
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
                psInsert = connection.prepareStatement("INSERT INTO user (email, roles, password, nom, prenom, image, is_verified, isblocked) VALUES (?,?,?,?,?,?,0,0)");
                psInsert.setString(1, email);
                psInsert.setString(2, "[ROLE_" + roles + "]"); // Encapsuler la chaîne roles dans un tableau JSON
                psInsert.setString(3, hashedPassword);
                psInsert.setString(4, nom);
                psInsert.setString(5, prenom);
                psInsert.setString(6, image);
                psInsert.executeUpdate();

                // sendVerificationEmail(email, verificationToken);
                // Send confirmation email with a verification token
               // String verificationToken = generateVerificationToken(email);
                //verificationCodes.put(email, verificationToken);
                //sendVerificationEmail(email, verificationToken);

                loggedInUserEmail = email;
                changeScence(event, "profil.fxml", "Welcome", email, nom);
                //showVerificationCodeEntryScene(event, email);
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
    public static void logInUser(ActionEvent event, String email, String plainPassword, boolean rememberMe) {
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

                            if (userRole.equals("admine")) {
                                loadAdminDashboard(event);
                            } else {
                                changeScence(event, "Profil.fxml", "Welcome", email, "");
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
    public static void loadAdminDashboard(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource("trocback.fxml"));
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

    public static void changeScence(ActionEvent event, String fxmlFile, String title, String username, String note) {
        Parent root = null;
        if (username != null && note != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();

                //LoggedInController loggedInController = loader.getController();
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

}
