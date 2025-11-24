package br.com.codexcb.application.view;

import br.com.codexcb.application.dao.LeitorDAO;
import br.com.codexcb.application.dto.LeitoresStatusDTO;
import br.com.codexcb.application.model.EmprestimoVisualizacao;
import br.com.codexcb.application.model.Usuario;
import br.com.codexcb.application.service.LeitorService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class GerenciarClientesController {
    private final LeitorService leitorService =new LeitorService(new LeitorDAO());

    @FXML
    private TableView<LeitoresStatusDTO> tbLeitor;
    @FXML
    private TableColumn<LeitoresStatusDTO, String> clNome;
    @FXML
    private TableColumn<LeitoresStatusDTO, String> clTelefone;
    @FXML
    private TableColumn<LeitoresStatusDTO, String> clCpf;
    @FXML
    private TableColumn<LeitoresStatusDTO, String> clId;
    @FXML
    private TableColumn<LeitoresStatusDTO, String> clEmprestimo;

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
    private void initialize() {
        //vinculando (biding) as colunas da tabela com a classe que irá popular a tabela
        clNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        clId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        clEmprestimo.setCellValueFactory(new PropertyValueFactory<>("statusUltimoEmprestimo"));
        carregaListaLeitorUltimoStatus(leitorService.consultarLeitoresUltimoStatus());
    }

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

    private void carregaListaLeitorUltimoStatus(List<LeitoresStatusDTO> leitoresStatusDTOS) {
        try {
            ObservableList<LeitoresStatusDTO> lista = FXCollections.observableArrayList(leitoresStatusDTOS);
            tbLeitor.setItems(lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}