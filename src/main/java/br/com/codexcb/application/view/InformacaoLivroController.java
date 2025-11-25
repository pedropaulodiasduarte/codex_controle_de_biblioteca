package br.com.codexcb.application.view;

import br.com.codexcb.application.model.Livro;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class InformacaoLivroController {
    private Livro livro;

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
        alternaTela(event, "acervo-view.fxml", "Acervo");
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

    public void setLivro(Livro dadosLivro) {
        this.livro = dadosLivro;
 txtnomeautor.setText(livro.getTitulo());

    }

}