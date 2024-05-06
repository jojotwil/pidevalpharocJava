module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.net.http;
    requires org.knowm.xchart;
    requires java.mail;
<<<<<<< Updated upstream

=======
    requires restfb;
    requires org.json;
    requires javafx.web;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires java.prefs;

=======
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
>>>>>>> Stashed changes

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports Entities;
    opens Entities to javafx.base, javafx.fxml;

    // Exporter et ouvrir le package View à javafx.fxml
    exports View to javafx.fxml;
    opens View to javafx.fxml;
}
