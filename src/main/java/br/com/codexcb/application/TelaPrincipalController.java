package br.com.codexcb.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaPrincipalController {

    @FXML
    private void abrirCadastroCliente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cadastrarcliente-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Cadastro de Cliente");

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela de cadastro de cliente.");
        }
    }
}