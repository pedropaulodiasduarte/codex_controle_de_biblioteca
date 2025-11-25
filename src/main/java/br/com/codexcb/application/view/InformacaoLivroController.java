package br.com.codexcb.application.view;

import br.com.codexcb.application.dao.LivroDAO;
import br.com.codexcb.application.mensagens.AlertInformationCreator;
import br.com.codexcb.application.mensagens.MessageCreator;
import br.com.codexcb.application.model.Livro;
import br.com.codexcb.application.service.LivroService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class InformacaoLivroController {
    private LivroService livroService = new LivroService(new LivroDAO());
    private Livro livro;
    private MessageCreator creator;

    @FXML
    private Button btnsalvar;
    @FXML
    private Button btncancelar;
    @FXML
    private Button btneditar;

    @FXML
    private TextField txtnomelivro;
    @FXML
    private TextField txtAutor;
    @FXML
    private DatePicker seletordata;
    @FXML
    private TextField txtgeneros;
    @FXML
    private TextField txtlocalizacao;
    @FXML
    private TextField txtidioma;
    @FXML
    private TextField txteditora;
    @FXML
    private TextField txtquantidadeadicional;

    @FXML
    private Text txtcodigoisbn;
    @FXML
    private Text txtquantidadedisponivel;

    @FXML
    public void initialize() {
    }

    @FXML
    public void btnsalvar(ActionEvent event) {
        if (livroService.atualizarLivro(extrairDadosView())) {
            creator = new AlertInformationCreator();
            creator.messageUser("Sucesso", "Livro atualizado com sucesso.");
            alternaTela(event, "acervo-view.fxml", "Acervo");
        }
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
 txtAutor.setText(livro.getAutor());
 txtnomelivro.setText(livro.getTitulo());
 txtcodigoisbn.setText(livro.getIsbnCodigo());
 txtidioma.setText(livro.getIdioma());
 txteditora.setText(livro.getEditora());
 seletordata.setValue(livro.getDataPublicacao());
 txtquantidadeadicional.setText(String.valueOf(livro.getCopia()));
 txtgeneros.setText(livro.getGenero());
 txtlocalizacao.setText(String.valueOf(livro.getIdLocalizacao()));
    }

    private Livro extrairDadosView() {
        Integer id = this.livro.getId();
        String isbnCodigo = this.livro.getIsbnCodigo();

        String titulo = txtnomelivro.getText();
        String autor = txtAutor.getText();
        String idioma = txtidioma.getText();
        String editora = txteditora.getText();
        String genero = txtgeneros.getText();
        LocalDate dataPublicacao = seletordata.getValue();

        int copia = Integer.parseInt(txtquantidadeadicional.getText());
        int idLocalizacao = Integer.parseInt(txtlocalizacao.getText());

        Livro livroAtualizado = new Livro(
                id,
                titulo,
                autor,
                isbnCodigo,
                idioma,
                editora,
                dataPublicacao,
                copia,
                idLocalizacao,
                genero
        );

        return livroAtualizado;
    }

}