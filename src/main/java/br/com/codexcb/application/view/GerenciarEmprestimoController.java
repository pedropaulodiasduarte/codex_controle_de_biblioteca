package br.com.codexcb.application.view;

import br.com.codexcb.application.dao.EmprestimoDAO;
import br.com.codexcb.application.model.EmprestimoVisualizacao;
import br.com.codexcb.application.service.EmprestimoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class GerenciarEmprestimoController {
    @FXML private TableView<EmprestimoVisualizacao> tbEmprestimo;
    @FXML private TableColumn<EmprestimoVisualizacao, String> clTitulo;
    @FXML private TableColumn<EmprestimoVisualizacao, String> clNome;
    @FXML TableColumn<EmprestimoVisualizacao, String> clDataEmprestimo;
    @FXML TableColumn<EmprestimoVisualizacao, String> clStatus;
    @FXML TableColumn<EmprestimoVisualizacao, String> clDataDevolucao;

    @FXML
    private void initialize() {
        //vinculando (biding) as colunas da tabela com a classe que irá popular a tabela
        clTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        clNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clDataEmprestimo.setCellValueFactory(new PropertyValueFactory<>("dataEmprestimo"));
        clStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        clDataDevolucao.setCellValueFactory(new PropertyValueFactory<>("dataDevolucao"));
        carregaListaEmprestimo();
    }

    private void carregaListaEmprestimo() {
        try {
            EmprestimoService emprestimoService = new EmprestimoService(new EmprestimoDAO());
            List<EmprestimoVisualizacao> emprestimoVisualizacaosListaBanco = emprestimoService.consultarListaEmprestimo();
            ObservableList<EmprestimoVisualizacao> listaEmprestimo = FXCollections.observableArrayList(emprestimoVisualizacaosListaBanco);
            tbEmprestimo.setItems(listaEmprestimo);

        } catch (Exception e) {
            e.printStackTrace();
        }
            }
}
