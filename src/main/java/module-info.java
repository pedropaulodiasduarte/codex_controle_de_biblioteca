module br.com.codexcb.application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires mysql.connector.j;
    requires javafx.graphics;
    requires javafx.base;


    exports br.com.codexcb.application;

    opens br.com.codexcb.application to javafx.fxml;

    exports br.com.codexcb.application.dao;

    opens br.com.codexcb.application.dao to javafx.fxml;

    exports br.com.codexcb.application.view;

    opens br.com.codexcb.application.view to javafx.fxml;
}