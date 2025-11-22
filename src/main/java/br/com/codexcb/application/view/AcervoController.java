package br.com.codexcb.application.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AcervoController {
    @FXML
    private TextField txtBuscaAcervo;

    @FXML
    private Button btncadastrarlivro;

    @FXML
    private Button btnMenuinicial;

    @FXML
    private Button btnAcervo;

    @FXML
    private Button btnGerenciaremprestimo;

    @FXML
    private Button btnGerenciarclientes;

    @FXML
    private Button btnConfiguracoesusuario;

    @FXML
    private Button btnDeslogar;

    @FXML
    private void onClickBtnAdicionarLivroAcervoView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cadastrarlivro-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cadastro de Cliente");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela!");
        }

    }

    @FXML
    private void onClickBtnMenuInicialAcervoView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("telaprincipal-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Tela Inicial - Codex");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela!");
        }


    }
}