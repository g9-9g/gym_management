module com.framja.gymmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.framja.gymmanagement to javafx.fxml;
    opens com.framja.gymmanagement.controller to javafx.fxml;
    exports com.framja.gymmanagement.controller;
    exports com.framja.gymmanagement;

}