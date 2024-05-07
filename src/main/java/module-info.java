module javafx {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires itextpdf;
    requires com.jfoenix;
    requires stripe.java;
    requires AnimateFX;



    opens controllers;
    opens tests;
}