module com.logindemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;
    requires java.mail;


    opens com.controllers_interfaces to javafx.fxml;
    exports com.controllers_interfaces;
}