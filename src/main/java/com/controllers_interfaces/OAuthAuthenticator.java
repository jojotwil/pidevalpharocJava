package com.controllers_interfaces;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public abstract class OAuthAuthenticator {
    private JSONObject accessedJsonData;

    private boolean gotData = false;
    private boolean attemptRecieved = false;
    private boolean loginAttempted = false;

    private String accessToken;
    private String accessCode;

    private String clientID;
    private String redirectUri;
    private String clientSecret;

    private Stage stage;


    public OAuthAuthenticator (String clientID, String redirectUri, String clientSecret) {
        this.clientID = clientID;
        this.redirectUri = redirectUri;
        this.clientSecret = clientSecret;
    }

    public String getClientID() {
        return clientID;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getRedirectUri(){
        return redirectUri;
    }

    public void startLogin() {
        // Check if a login attempt has already been made

        if (loginAttempted) {
            return;
        }
        loginAttempted = true;
        System.out.println("hello");
        stage = new Stage();
        WebView root = new WebView();
        WebEngine engine = root.getEngine();
        engine.load(getWebUrl()); // Load the login URL

        // Set a listener for status changes on the web engine
        engine.setOnStatusChanged(event -> {
            // Return if data has been obtained or an attempt has been received
            if (gotData || attemptRecieved) {
                return;
            }

            // Check if the event source is a WebEngine instance
            if (event.getSource() instanceof WebEngine) {
                WebEngine we = (WebEngine) event.getSource();
                String location = we.getLocation();

                // Check if the URL contains the authorization code
                if (location.contains("code") && location.startsWith(getRedirectUri())) {
                    attemptRecieved = true;
                    closeStage(); // Close the login stage
                    accessCode = location.substring(location.indexOf("code=") + 5);
                    accessToken = doGetAccessTokenRequest(accessCode); // Request the access token
                    String returnedJson = doGetAccountInfo(accessToken); // Get account info
                    accessedJsonData = new JSONObject(returnedJson); // Parse the JSON data
                    System.out.println(returnedJson);
                    gotData = true;
                    // Notify that the login view has completed
                    try {
                        notifyLoginViewCompleted();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); // Display the login stage
    }

    abstract String getWebUrl();

    abstract String getApiTokenUrl();

    abstract String getApiAccessUrl();

    abstract String getApiAccessParams();

    public String getAccessToken() {
        return accessToken;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public boolean hasFinishedSuccessfully() {
        return gotData;
    }

    public JSONObject getJsonData() {
        if(gotData) {
            return accessedJsonData;
        } else {
            return null;

        }
    }

    private void closeStage() {
        stage.close();
    }

    void notifyLoginViewCompleted() throws SQLException, IOException {
        if(gotData) {
            //LoginView.getInstance().completedOAuthLogin(this);
        }
    }

    private String doGetAccountInfo(String accessToken) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(getApiTokenUrl());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            System.out.println("URL: " + getApiTokenUrl());

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    return response.toString();
                }
            } else {
                System.out.println("Error retrieving API data!: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("####### ERROR GETTING ACCOUNT INFO ##############");
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    private String doGetAccessTokenRequest(String code) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(getApiAccessUrl());
            String urlParams = getApiAccessParams();

            byte[] postData = urlParams.getBytes(StandardCharsets.UTF_8);
            int postDataLength = postData.length;

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);

            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.write(postData);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    String fullResponse = response.toString();
                    JSONObject json = new JSONObject(fullResponse);
                    String accessToken = json.getString("access_token");
                    return accessToken;
                }
            } else {
                System.err.println("Error getting access token for OAuth Login: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("####### ERROR GETTING ACCESS TOKEN ##############");
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;

    }
    }
