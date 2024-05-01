package com.controllers_interfaces;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
public class HelloController implements Initializable {

    @FXML
    private Button button_login;
    @FXML
    private Button button_signup;
    @FXML
    private Button resetPasswordButton;
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField tf_password;
    @FXML
    private CheckBox rememberMeCheckBox;
    private Preferences preferences;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        preferences = Preferences.userNodeForPackage(HelloController.class);
        // Load stored credentials if "Remember Me" was checked previously
        if (preferences.getBoolean("rememberMe", false)) {
            String username = preferences.get("username", "");
            String password = preferences.get("password", "");
            tf_username.setText(username);
            tf_password.setText(password);
            rememberMeCheckBox.setSelected(true); // Make sure to set the checkbox selected
        }
        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean rememberMe = rememberMeCheckBox.isSelected();
                DBUtils.logInUser(event,tf_username.getText(),tf_password.getText(), rememberMe);
                // If Remember Me is checked, store the credentials
                if (rememberMe) {
                    preferences.putBoolean("rememberMe", true);
                    preferences.put("username", tf_username.getText());
                    preferences.put("password", tf_password.getText());
                } else {
                    // If Remember Me is unchecked, clear the stored credentials
                    preferences.remove("rememberMe");
                    preferences.remove("username");
                    preferences.remove("password");
                }
            }
        });

        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScence(event,"sign-up.fxml","Sign-up",null,null);
            }
        });
        resetPasswordButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScence(event,"reset.fxml","reset",null,null);
            }
        });
    }


}