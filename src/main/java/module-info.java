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

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports Entities;
    opens Entities to javafx.base, javafx.fxml;
}