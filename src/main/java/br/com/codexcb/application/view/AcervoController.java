package br.com.codexcb.application.view;

import br.com.codexcb.application.dao.LivroDAO;
import br.com.codexcb.application.model.EmprestimoVisualizacao;
import br.com.codexcb.application.model.Livro;
import br.com.codexcb.application.service.LivroService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AcervoController {
    private final LivroService livroService = new LivroService(new LivroDAO());
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
    private TableView<Livro> tbAcervo;
    @FXML
    private TableColumn<Livro, String> clTitulo;
    @FXML
    private TableColumn<Livro, String> clAutor;
    @FXML
    private TableColumn<Livro, String> clIsbnCodigo;
    @FXML
    private TableColumn<Livro, String> clEditora;
    @FXML
    private TableColumn<Livro, String> clGenero;

    @FXML
    private void initialize() {
        //vinculando (biding) as colunas da tabela com a classe que irá popular a tabela
        clTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        clAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        clIsbnCodigo.setCellValueFactory(new PropertyValueFactory<>("isbnCodigo"));
        clEditora.setCellValueFactory(new PropertyValueFactory<>("editora"));
        clGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        carregaListaEmprestimo(livroService.consultarListaLivro());
    }

    @FXML
    private void onClickBtnEditarLivro(ActionEvent event) {
        /*TableView.TableViewSelectionModel<Livro> selectionModel = tbAcervo.getSelectionModel();
        if (!selectionModel.isEmpty()) {
            Livro livro = selectionModel.getSelectedItem();
            Integer idAtual = emprestimoSelecionado.getId();
            String statusAtual = emprestimoSelecionado.getStatus();
            LocalDate dataDevolucao = LocalDate.now();
            emprestimoService.atualizarEmprestimo(idAtual, "Devolvido", statusAtual, dataDevolucao);
            carregaListaEmprestimo(emprestimoService.consultarListaEmprestimo());
        } else {
            MessageCreator creator = new AlertErrorCreator();
            creator.messageUser("Nenhum Empréstimo Selecionado", "Selecione um empréstimo para ser devolvido!");
        }*/
        alternaTela(event, "informacaolivro-view.fxml", "Cadastrar Empréstimo");
    }

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

    private void carregaListaEmprestimo(List<Livro> livroListaBanco) {
        try {
            ObservableList<Livro> livroObservableList = FXCollections.observableArrayList(livroListaBanco);
            tbAcervo.setItems(livroObservableList);
        } catch (Exception e) {
            e.printStackTrace();
        }
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