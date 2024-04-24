package alpharoc.pidev.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Details {

    @FXML
    private TextField lbnom;

    @FXML
    private TextField lbprenom;

    public void setLbnom(String lbnom) {
        this.lbnom .setText(lbnom);
    }

    public void setLbprenom(String lbprenom) {
        this.lbprenom.setText(lbprenom);
    }
}
