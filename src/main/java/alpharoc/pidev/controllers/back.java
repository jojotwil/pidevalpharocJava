package alpharoc.pidev.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.HashMap;
import java.util.ResourceBundle;

public class back implements Initializable {

    @FXML
    private TableView<HashMap<String, Object>> tableView_user;

    @FXML
    private TableColumn<HashMap<String, Object>, Integer> id;

    @FXML
    private TableColumn<HashMap<String, Object>, String> email;

    @FXML
    private TableColumn<HashMap<String, Object>, String> roles;

    @FXML
    private TableColumn<HashMap<String, Object>, String> nom;

    @FXML
    private TableColumn<HashMap<String, Object>, String> prenom;

    @FXML
    private TableColumn<HashMap<String, Object>, String> image;

    @FXML
    private TableColumn<HashMap<String, Object>, Boolean> is_verified;

    @FXML
    private TableColumn<HashMap<String, Object>, Boolean> isblocked;
    @FXML
    private Pagination pagination;
    @FXML
    private TextField searchField;


    private final int rowsPerPage = 10;
    private ObservableList<HashMap<String, Object>> data = FXCollections.observableArrayList();
    // Method to initialize the controller
    @FXML
    public void initialize() {
        // Initialize the columns with corresponding properties
        // Initialize the columns with corresponding properties
        id.setCellValueFactory(cellData -> new SimpleIntegerProperty((Integer) cellData.getValue().get("id")).asObject());
        email.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get("email")));
        roles.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get("roles")));
        nom.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get("nom")));
        prenom.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get("prenom")));
        image.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get("image")));
        is_verified.setCellValueFactory(cellData -> new SimpleBooleanProperty((Boolean) cellData.getValue().get("is_verified")));
        isblocked.setCellValueFactory(cellData -> new SimpleBooleanProperty((Boolean) cellData.getValue().get("isblocked")));


        // Call a method to populate the table
        populateTable();
    }

    // Method to populate the table with user data
    private void populateTable() {
        // Clear existing items in the table
        tableView_user.getItems().clear();

        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/alphatroc";
        String username = "root";
        String password = "";

        try {
            // Establish connection to the database
            Connection connection = DriverManager.getConnection(url, username, password);

            // SQL query to select users excluding admins
            String query = "SELECT * FROM user WHERE roles != 'admine'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Iterate through the result set and add users to the table
            while (resultSet.next()) {
                HashMap<String, Object> userData = new HashMap<>();
                userData.put("id", resultSet.getInt("id"));
                userData.put("email", resultSet.getString("email"));
                userData.put("roles", resultSet.getString("roles"));
                userData.put("nom", resultSet.getString("nom"));
                userData.put("prenom", resultSet.getString("prenom"));
                userData.put("image", resultSet.getString("image"));
                userData.put("is_verified", resultSet.getBoolean("is_verified"));
                userData.put("isblocked", resultSet.getBoolean("isblocked"));

                // Add user data to the table
                tableView_user.getItems().add(userData);
            }

            // Close the connections
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void logout(ActionEvent actionEvent) {
        try {
            // Load the HelloView FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();

            // Create a new scene with HelloView as the root
            Scene scene = new Scene(root);

            // Get the current stage (window)
            Stage stage = (Stage) tableView_user.getScene().getWindow();

            // Set the new scene
            stage.setScene(scene);

            // Show the stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception if FXMLLoader fails to load the HelloView FXML file
        }
    }

    public void blockUserAction(ActionEvent actionEvent) {
        // Get the selected item from the table view
        HashMap<String, Object> selectedUser = tableView_user.getSelectionModel().getSelectedItem();

        if (selectedUser != null) {
            // Update the user's status in the database
            int userId = (int) selectedUser.get("id");
            updateStatus(userId, true); // Update status to blocked

            // Refresh the table view to reflect the changes
            populateTable();
        } else {
            // Inform the user to select a user from the table
            // You can show an alert or a message in the UI
            System.out.println("Please select a user to block.");
        }
    }

    public void unblockUserAction(ActionEvent actionEvent) {
        // Get the selected item from the table view
        HashMap<String, Object> selectedUser = tableView_user.getSelectionModel().getSelectedItem();

        if (selectedUser != null) {
            // Update the user's status in the database
            int userId = (int) selectedUser.get("id");
            updateStatus(userId, false); // Update status to unblocked

            // Refresh the table view to reflect the changes
            populateTable();
        } else {
            // Inform the user to select a user from the table
            // You can show an alert or a message in the UI
            System.out.println("Please select a user to unblock.");
        }
    }

    private void updateStatus(int userId, boolean isBlocked) {
        // Perform the database update operation to change the user's status
        // You need to implement this method according to your database schema
        // Example SQL update statement:
        // UPDATE user SET isblocked = ? WHERE id = ?
        String url = "jdbc:mysql://localhost:3306/alphatroc";
        String username = "root";
        String password = "";
        String query = "UPDATE user SET isblocked = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, isBlocked);
            statement.setInt(2, userId);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 1) {
                System.out.println("User status updated successfully.");
            } else {
                System.out.println("Failed to update user status.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void searchByName(ActionEvent event) {
        String searchTerm = searchField.getText().trim();
        String url = "jdbc:mysql://localhost:3306/alphatroc";
        String username = "root";
        String password = "";

        if (!searchTerm.isEmpty()) {
            // Clear the table data
            tableView_user.getItems().clear();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                String query = "SELECT * FROM user WHERE roles != 'admine' AND nom LIKE ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, "%" + searchTerm + "%");
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            HashMap<String, Object> userData = new HashMap<>();
                            userData.put("id", resultSet.getInt("id"));
                            userData.put("email", resultSet.getString("email"));
                            userData.put("roles", resultSet.getString("roles"));
                            userData.put("nom", resultSet.getString("nom"));
                            userData.put("prenom", resultSet.getString("prenom"));
                            userData.put("image", resultSet.getString("image"));
                            userData.put("is_verified", resultSet.getBoolean("is_verified"));
                            userData.put("isblocked", resultSet.getBoolean("isblocked"));
                            tableView_user.getItems().add(userData);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // If search term is empty, repopulate the table with all users
            populateTable();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}