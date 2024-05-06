package com.controllers_interfaces;


import org.json.JSONArray;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;

public class OAuthGoogleAuthenticator extends OAuthAuthenticator{

    private String GOOGLE_apiScope = "https://www.googleapis.com/auth/userinfo.profile";
    public OAuthGoogleAuthenticator(String clientID, String redirectUri, String clientSecret, String apiScope) {
        super(clientID, redirectUri, clientSecret);
        GOOGLE_apiScope = apiScope;
    }

    @Override
    String getWebUrl() {
        return "https://accounts.google.com/o/oauth2/v2/auth?scope=" + GOOGLE_apiScope + "&access_type=offline&redirect_uri=" + getRedirectUri() + "&response_type=code&client_id=" + getClientID();
    }

    @Override
    String getApiTokenUrl() {
        return "https://www.googleapis.com/oauth2/v2/userinfo?access_token=" + getAccessToken();
    }

    @Override
    String getApiAccessUrl() {
        return "https://www.googleapis.com/oauth2/v4/token";
    }

    @Override
    String getApiAccessParams() {
        return "client_id=" + getClientID() + "&redirect_uri=" + getRedirectUri() + "&client_secret=" + getClientSecret() + "&grant_type=authorization_code&code=" + getAccessCode();
    }
    @Override
    protected void notifyLoginViewCompleted() throws SQLException, IOException {
        Connection connection = null;
        PreparedStatement psInsert = null;
        super.notifyLoginViewCompleted();

        if (hasFinishedSuccessfully()) {
            JSONObject jsonData = getJsonData();
            if (jsonData != null) {
                String email = jsonData.getString("email");
                String nom = jsonData.optString("given_name", "");
                String prenom = jsonData.optString("family_name", "");
                String googleId = jsonData.getString("id");
                String image = jsonData.optString("picture", "");
                String roles = "client"; // Default role
                String hashedPassword = ""; // No password provided by OAuth
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/alphatroc", "root", "");
                    psInsert = connection.prepareStatement("INSERT INTO user (email,roles, password,nom,prenom, image, is_verified,isblocked) VALUES (?,?,?,?,?,?,0,0)");

                    psInsert.setString(1, email);
                    psInsert.setString(2, roles);
                    psInsert.setString(3, hashedPassword);
                    psInsert.setString(4, nom);
                    psInsert.setString(5, prenom);
                    psInsert.setString(6, image);

                    int rowsInserted = psInsert.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("User from Google signed up successfully!");

                    } else {
                        System.out.println("Failed to sign up user.");
                    }
                } catch (SQLException e) {
                    System.out.println("Error inserting user into database: " + e.getMessage());
                } finally {
                    if (psInsert != null) {
                        psInsert.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                }
            }
        }
    }
    

}