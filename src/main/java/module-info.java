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


    //export: torna classes de um pacote visivéis para que outros módulos possam compilar contra elas. - Adiciona permissão para outros módulos usarem as classes do módulo que coloquei como export
    exports br.com.codexcb.application;
    opens br.com.codexcb.application to javafx.fxml;

    exports br.com.codexcb.application.dao;

    opens br.com.codexcb.application.dao to javafx.fxml;

    exports br.com.codexcb.application.view;

    opens br.com.codexcb.application.view to javafx.fxml;

    /*
    preciso da linha abaixo, pois o  o PropertyValueFactory, precisa fazer reflexão em meu model
    Reflexão: permissão para inspecionar e manipular classes e métodos de outro código em tempo de execução
    por segurança, o JPMS tem encapsulamento forte, precisando asssim informar que um módulo acessará outro
    */
    opens br.com.codexcb.application.model to javafx.base;

}