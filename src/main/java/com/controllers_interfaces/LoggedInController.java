package com.controllers_interfaces;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {
    private AuthenticationService authService;
    private String loggedInUserEmail;
    @FXML
    private ImageView Exit;
    @FXML
    private Label label_welcome;
    @FXML
    private Label label_email;
    @FXML
    private Label Menu;

    @FXML
    private Label MenuClose;

    @FXML
    private AnchorPane slider;
    @FXML
    private BorderPane borderPane;
    private ProfilePageController profilePageController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
        slider.setTranslateX(-176);
        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-176);

            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(false);
                MenuClose.setVisible(true);
            });
        });

        MenuClose.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-176);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(true);
                MenuClose.setVisible(false);
            });
        });
    }
    public void setLoggedInUserEmail(String email) {
        loggedInUserEmail = email;
    }

    @FXML
    void handleProfileButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilePage.fxml"));
            Pane profilePane = loader.load();
            ProfilePageController profileController = loader.getController();

            // Pass logged-in user's email to fetch user information
            fetchUserInformation(profileController);

            //
            borderPane.setCenter(profilePane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    private void fetchUserInformation(ProfilePageController profileController) {
        String[] userInformationArray = DBUtils.getUserInformation(loggedInUserEmail);
        if (userInformationArray != null && userInformationArray.length >= 1) {
            String userEmail = userInformationArray[0]; // Extract email from the array
            profileController.setUserInformation(userEmail);
        }
    }
    @FXML
    void handleUpdateProfileButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateProfile.fxml"));
            Pane updateProfilePane = loader.load();
            UpdateProfileController updateProfileController = loader.getController();
            // Set the ProfilePageController instance in UpdateProfileController
            FXMLLoader profileLoader = new FXMLLoader(getClass().getResource("ProfilePage.fxml"));
            Parent profilePane = profileLoader.load();
            ProfilePageController profileController = profileLoader.getController();
         //   UpdateProfileController.setProfilePageController(profileController);
            // Pass any necessary information to the update profile controller

            // Pass any necessary information to the update profile controller

            Stage stage = new Stage();
            stage.setScene(new Scene(updateProfilePane));
            stage.setTitle("Update Profile");
            stage.show();

            // Set the navigation to sign-in page after profile update
            updateProfileController.setOnProfileUpdatedListener(() -> {
                navigateToSignInPage(event);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void navigateToSignInPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Sign In");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
