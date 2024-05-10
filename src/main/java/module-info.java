module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.net.http;
    requires org.knowm.xchart;
    requires java.mail;
    requires java.prefs;
    requires jbcrypt;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires javafx.web;
    requires org.json;
    requires java.desktop;
    requires java.datatransfer;
    requires com.google.zxing.javase;
    requires com.google.zxing;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.pdfbox;
    requires freetts;
    requires qrgen;
    requires twilio;
    requires itextpdf;
    requires com.jfoenix;
    exports App;
    requires stripe.java;
    requires AnimateFX;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports Entities;
    opens Entities to javafx.base, javafx.fxml;

    // Exporter et ouvrir le package View à javafx.fxml
    exports View to javafx.fxml;
    opens View to javafx.fxml;
    exports controllers;
    opens controllers to javafx.fxml;
}
