package br.com.codexcb.application.view;

import java.io.IOException;
import java.time.LocalDate;

import br.com.codexcb.application.dao.LivroDAO;
import br.com.codexcb.application.mensagens.AlertErrorCreator;
import br.com.codexcb.application.mensagens.AlertInformationCreator;
import br.com.codexcb.application.mensagens.MessageCreator;
import br.com.codexcb.application.model.Livro;
import br.com.codexcb.application.service.LivroService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class CadastrarLivroController {
    private MessageCreator messageCreator;
    @FXML
    private Button btnvoltar;

    @FXML
    private Button btnadclivro;

    @FXML
    private Button btncancelar;

    @FXML
    private Button btnadcimg;

    @FXML
    private ImageView imglivro;

    @FXML
    private TextField txttitulo;

    @FXML
    private TextField txtautor;

    @FXML
    private TextField txtisbn;

    @FXML
    private TextField txteditora;

    @FXML
    private DatePicker dtpDataPublicacao;

    @FXML
    private TextField txtqntidade;

    @FXML
    private TextField txtlocalizacao;

    @FXML
    private ComboBox<String> cmbIdioma;

    @FXML
    private ComboBox<String> cmbGenero;


    @FXML
    private void clickAdicionarLivro(ActionEvent event) {
        if (validaDados()) {
            if (insertDadosLivro(extrairDadosView())){
                messageCreator = new AlertInformationCreator();
                messageCreator.messageUser("Sucesso!", "Livro cadastrado com sucesso no acervo.");

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("telaprincipal-view.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Tela Principal");

                } catch (IOException e) {
                    e.printStackTrace();
                    messageCreator = new AlertErrorCreator();
                    messageCreator.messageUser("Erro", "Erro ao abrir página inicial.");
                }
            }
        } else {
            messageCreator = new AlertErrorCreator();
            messageCreator.messageUser("Erro", "Atenção! É necessário preencher todos os dados para cadastrar o livro.");
        }
    }

    private boolean insertDadosLivro(Livro livro) {
        LivroService livroService = new LivroService(new LivroDAO());
        return livroService.cadastrarLivro(livro);
    }

    private Livro extrairDadosView() {
        String titulo = txttitulo.getText();
        String autor = txtautor.getText();
        String isbnCodigo = txtisbn.getText();
        String idioma = cmbIdioma.getValue();
        String editora = txteditora.getText();
        int copia = Integer.parseInt(txtqntidade.getText());
        int idLocalizacao = Integer.parseInt(txtlocalizacao.getText());
        String genero = cmbGenero.getValue();
        LocalDate dataPublicacao = dtpDataPublicacao.getValue();

        Livro livro = new Livro(null, titulo, autor, isbnCodigo, idioma, editora, dataPublicacao, copia, idLocalizacao, genero);
        return livro;
    }

    private boolean validaDados(){
        if (txttitulo.getText() == null || txtautor.getText() == null || txtisbn.getText() == null || cmbIdioma.getValue() == null || txteditora.getText() == null || txtqntidade.getText() == null || txtlocalizacao.getText() == null || cmbGenero.getValue() == null || dtpDataPublicacao.getValue() == null) {
            return false;
        }
        return true;
    }

    @FXML
    private void onClickBtnVoltar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("acervo-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Acervo");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela de cadastro de cliente.");
        }

    }

    @FXML
    private void onClickBtnCancelar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("acervo-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Acervo");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela de cadastro de cliente.");
        }


    }

}