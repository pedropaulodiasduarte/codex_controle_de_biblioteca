module br.com.clientefiel.clientefiel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires mysql.connector.j;

    opens br.com.clientefiel.application to javafx.fxml;
    exports br.com.clientefiel.application;
}