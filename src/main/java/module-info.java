module com.logindemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;
    requires java.mail;
    requires restfb;
    requires org.json;
    requires javafx.web;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires java.prefs;


    opens com.controllers_interfaces to javafx.fxml;
    exports com.controllers_interfaces;
}