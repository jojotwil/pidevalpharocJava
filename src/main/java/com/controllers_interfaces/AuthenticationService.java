package com.controllers_interfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationService {

    private final String url;
    private final String email;
    private final String password;

    public AuthenticationService(String url, String email, String password) {
        this.url = url;
        this.email = email;
        this.password = password;
    }

    // Method to authenticate the user
    public boolean authenticateUser(String email, String password) {
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
        try (Connection connection = DriverManager.getConnection(url, this.email, this.password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // If result set has at least one row, authentication is successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false in case of any exception
        }
    }

    // Method to retrieve the current user's email
    public String getCurrentUserEmail(String email) {
        String sql = "SELECT email FROM user WHERE email = ?";
        try (Connection connection = DriverManager.getConnection(url, this.email, this.password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("email");
                } else {
                    return null; // User not found
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Return null in case of any exception
        }
    }

    // Method to retrieve the current user's photo URL
    // Method to retrieve the current user's photo URL
    public String getCurrentUserPhotoUrl(String email) {
        String sql = "SELECT image FROM user WHERE email = ?";
        try (Connection connection = DriverManager.getConnection(url, this.email, this.password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("image");
                } else {
                    return null; // User not found
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Return null in case of any exception
        }
    }

}
