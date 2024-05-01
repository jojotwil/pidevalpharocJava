package com.controllers_interfaces;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class VerificationCodeEntryController {

    @FXML
    private TextField verificationCodeField;

    private String userEmail;

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    @FXML
    void verifyAccount(ActionEvent event) {
        String verificationCode = verificationCodeField.getText();
        // Call the verifyAccount method from your VerificationUtils class passing the verification code
        DBUtils.verifyAccount(userEmail, verificationCode);
    }
}
