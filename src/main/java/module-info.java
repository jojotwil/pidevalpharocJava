module alpharoc.pidev {
    requires  javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    opens alpharoc.pidev.entities to javafx.base;

    requires com.almasb.fxgl.all;
    requires java.sql;

    opens alpharoc.pidev to javafx.fxml;
    exports alpharoc.pidev;
    exports alpharoc.pidev.controllers;
   opens alpharoc.pidev.controllers to javafx.fxml;
    exports alpharoc.pidev.tests;
    opens alpharoc.pidev.tests to javafx.fxml;
    exports alpharoc.pidev.controllers.gestionlocation;
    opens alpharoc.pidev.controllers.gestionlocation to javafx.fxml;
    requires javafx.base;
}