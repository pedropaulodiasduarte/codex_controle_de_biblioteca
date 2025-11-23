package br.com.codexcb.application.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class GerenciarClientesController {
    @FXML
    private TextField txtpesquisa;

    @FXML
    private Button buttoncadastrarcliente;

    @FXML
    private Button btnMenuinicial;

    @FXML
    private Button btnGerenciaremprestimo;

    @FXML
    private Button btnGerenciarclientes;

    @FXML
    private Button btnConfiguracoesusuario;

    @FXML
    private Button btnDeslogar;

    @FXML
    private Button btnAcervo;

    @FXML
    private TableView tabelageral;

    @FXML
    private TableColumn columnnomecliente;

    @FXML
    private TableColumn columncodigocliente;

    @FXML
    private TableColumn columncpfcliente;

    @FXML
    private TableColumn columndatacadastro;

    @FXML
    private TableColumn columntotalemprestimos;

    @FXML
    private void onClickBtnCadastrarLeitor(ActionEvent event) {
        alternaTela(event, "cadastrarleitor-view.fxml", "Cadastrar Leitor");
    }

    private void alternaTela(ActionEvent event, String nomeArquivo, String tituloJanela) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeArquivo));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(tituloJanela);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela de cadastro de cliente.");
        }
    }

}