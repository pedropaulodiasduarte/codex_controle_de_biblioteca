package br.com.codexcb.application.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class InformacaoLivroController {

    @FXML
    private TextField txtCampoBusca;

    @FXML
    private ImageView imglivro;
    @FXML
    private Text txtnomelivro;
    @FXML
    private Text txtnomeautor;
    @FXML
    private Text txtdatapubli;
    @FXML
    private Text txtisbn;
    @FXML
    private Text txtgenero;
    @FXML
    private Text txtquantidade;
    @FXML
    private Text txtqtdedisponivel;
    @FXML
    private Text txtlocalizacao;
    @FXML
    private Text txteditora;
    @FXML
    private Text txtidioma;

    @FXML
    private Button btnsalvar;
    @FXML
    private Button btncancelar;

    @FXML
    public void initialize() {
    }

    @FXML
    public void btnsalvar(ActionEvent event) {
        System.out.println("Lógica de salvar livro acionada.");
    }

    @FXML
    public void btncancelar(ActionEvent event) {
        System.out.println("Cancelamento acionado. Fechando a janela...");
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}