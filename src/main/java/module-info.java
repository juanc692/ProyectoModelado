module pack.proyectomodelado {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires javafx.graphics;

    opens pack.proyectomodelado to javafx.fxml;
    exports pack.proyectomodelado;
    exports pack.proyectomodelado.front;
    opens pack.proyectomodelado.front to javafx.fxml;
}