module com.example.propertymanagementapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires layout;
    requires kernel;
    requires io;
    requires pdfa;

    opens com.example.propertymanagementapplication to javafx.fxml;
    exports com.example.propertymanagementapplication;
}