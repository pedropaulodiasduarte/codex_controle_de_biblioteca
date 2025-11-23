package br.com.codexcb.application.view;

import br.com.codexcb.application.dao.EmprestimoDAO;
import br.com.codexcb.application.mensagens.AlertErrorCreator;
import br.com.codexcb.application.mensagens.MessageCreator;
import br.com.codexcb.application.model.EmprestimoVisualizacao;
import br.com.codexcb.application.service.EmprestimoService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class GerenciarEmprestimoController {
    private final EmprestimoService emprestimoService = new EmprestimoService(new EmprestimoDAO());
    @FXML
    private TableView<EmprestimoVisualizacao> tbEmprestimo;
    @FXML
    private TableColumn<EmprestimoVisualizacao, Integer> clIdEmprestimo;
    @FXML
    private TableColumn<EmprestimoVisualizacao, String> clTitulo;
    @FXML
    private TableColumn<EmprestimoVisualizacao, String> clNome;
    @FXML
    TableColumn<EmprestimoVisualizacao, String> clDataEmprestimo;
    @FXML
    TableColumn<EmprestimoVisualizacao, String> clStatus;
    @FXML
    TableColumn<EmprestimoVisualizacao, String> clDataDevolucao;

    @FXML
    private void initialize() {
        //vinculando (biding) as colunas da tabela com a classe que irá popular a tabela
        clIdEmprestimo.setCellValueFactory(new PropertyValueFactory<>("id"));
        clTitulo.setCellValueFactory(new PropertyValueFactory<>("tituloLivro"));
        clNome.setCellValueFactory(new PropertyValueFactory<>("nomeLeitor"));
        clDataEmprestimo.setCellValueFactory(new PropertyValueFactory<>("dataEmprestimo"));
        clDataDevolucao.setCellValueFactory(new PropertyValueFactory<>("dataDevolucao"));
        clStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        carregaListaEmprestimo(emprestimoService.consultarListaEmprestimo());
    }

    @FXML
    private void onClickBtnMenuInicial(ActionEvent event) {
        alternaTela(event, "telaprincipal-view.fxml", "Tela Inicial");
    }

    @FXML
    private void onClickBtnCadastrarEmprestimo(ActionEvent event) {
        alternaTela(event, "cadastraremprestimo-view.fxml", "Cadastrar Empréstimo");
    }

    @FXML
    private void onClickBtnAprovarEntrega(ActionEvent event) {
        TableView.TableViewSelectionModel<EmprestimoVisualizacao> selectionModel = tbEmprestimo.getSelectionModel();
        if (!selectionModel.isEmpty()) {
            EmprestimoVisualizacao emprestimoSelecionado = selectionModel.getSelectedItem();
            Integer idAtual = emprestimoSelecionado.getId();
            String statusAtual = emprestimoSelecionado.getStatus();
            LocalDate dataDevolucao = LocalDate.now();
            emprestimoService.atualizarEmprestimo(idAtual, "Devolvido", statusAtual, dataDevolucao);
            carregaListaEmprestimo(emprestimoService.consultarListaEmprestimo());
        } else {
            MessageCreator creator = new AlertErrorCreator();
            creator.messageUser("Nenhum Empréstimo Selecionado", "Selecione um empréstimo para ser devolvido!");
        }
    }

    @FXML
    private void onClickBtnFiltroAtivo() {
        carregaListaEmprestimo(emprestimoService.consultarListaEmprestimoStatus("Ativo"));
    }

    @FXML
    private void onClickBtnFiltroAtrasado() {
        carregaListaEmprestimo(emprestimoService.consultarListaEmprestimoStatus("Atrasado"));
    }

    @FXML
    private void onClickBtnFiltroDevolvido() {
        carregaListaEmprestimo(emprestimoService.consultarListaEmprestimoStatus("Devolvido"));
    }

    private void carregaListaEmprestimo(List<EmprestimoVisualizacao> emprestimoVisualizacaosListaBanco) {
        try {
            ObservableList<EmprestimoVisualizacao> listaEmprestimo = FXCollections.observableArrayList(emprestimoVisualizacaosListaBanco);
            tbEmprestimo.setItems(listaEmprestimo);

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
