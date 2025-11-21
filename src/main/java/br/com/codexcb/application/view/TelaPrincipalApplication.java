package br.com.codexcb.application.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaPrincipalApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        // Carrega a tela principal
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cadastrarlivro-view.fxml"));

        // Define o tamanho inicial da janela
        Scene scene = new Scene(fxmlLoader.load(), 700, 540);

        stage.setTitle("Sistema Cliente Fiel - Menu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}