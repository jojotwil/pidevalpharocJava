module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.net.http;
    requires org.knowm.xchart;
    requires java.mail;

=======
    requires restfb;
    requires org.json;
    requires javafx.web;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires java.prefs;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports Entities;
    opens Entities to javafx.base, javafx.fxml;
}