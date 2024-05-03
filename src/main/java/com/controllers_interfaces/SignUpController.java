package com.controllers_interfaces;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Node;

public class SignUpController implements Initializable {

    @FXML
    private Button button_register;
    @FXML
    private Button button_login;
    @FXML
    private RadioButton rb_note1;
    @FXML
    private RadioButton rb_note2;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;
    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf_image;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup = new ToggleGroup();
        rb_note1.setToggleGroup(toggleGroup);
        rb_note2.setToggleGroup(toggleGroup);
        rb_note1.setSelected(true);

        button_register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String note;
                if(rb_note1.isSelected()) {
                    note = "[\"ROLE_CLIENT\"]";
                } else {
                    note = "[\"ROLE_EMPLOYEE\"]";
                }
                if(!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()){
                    DBUtils.signupUser(event,tf_username.getText(), tf_password.getText(),note , tf_nom.getText(),tf_prenom.getText(),tf_image.getText());
                } else {
                    System.out.println("Please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all the information to register!");
                    alert.show();
                }
            }
        });

        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScence(event, "hello-view.fxml", "log-in", null,null);
            }
        });

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

    public void signupWithGoogle(MouseEvent mouseEvent) throws IOException {
        OAuthGoogleAuthenticator googleAuthenticator = new OAuthGoogleAuthenticator(
                "608781898479-vi1qo5fmfvobhltv6g55f77e5lqf92ql.apps.googleusercontent.com",
                "http://localhost/dashboard",
                "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",
                "https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email"
        );
        googleAuthenticator.startLogin();
    }
}
