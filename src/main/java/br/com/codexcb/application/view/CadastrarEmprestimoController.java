package br.com.codexcb.application.view;

import br.com.codexcb.application.dao.EmprestimoDAO;
import br.com.codexcb.application.mensagens.AlertErrorCreator;
import br.com.codexcb.application.mensagens.AlertInformationCreator;
import br.com.codexcb.application.mensagens.MessageCreator;
import br.com.codexcb.application.model.Emprestimo;
import br.com.codexcb.application.model.Livro;
import br.com.codexcb.application.service.EmprestimoService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class CadastrarEmprestimoController {
    private final EmprestimoService emprestimoService = new EmprestimoService(new EmprestimoDAO());
    @FXML
    private TextField txtisbn;
    @FXML
    private TextField txtcpfleitor;
    @FXML
    private DatePicker txtdataemprestimo;
    @FXML
    private DatePicker txtdatadevolucao;
    @FXML
    private Button btncancelar;
    @FXML
    private Button btnadcemprestimo;
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
        txtdataemprestimo.setValue(LocalDate.now());
        calcularDataDevolucao();
        txtdataemprestimo.valueProperty().addListener((observable, oldValue, newValue) -> {
            calcularDataDevolucao();
        });
    }


    @FXML
    private void onClickBtnAdcEmprestimo(ActionEvent event) {
        if (validaDadosEmprestimo()) {
            if (insertDadosEmprestimo(extrairDadosView())) {
                MessageCreator creator = new AlertInformationCreator();
                creator.messageUser("Sucesso", "Empréstimo registrado com sucesso.");
                alternaTela(event, "gerenciaremprestimo-view.fxml", "Tela Inicial");
            }
        }
    }

    @FXML
    private void onClickBtnCancelar(ActionEvent event) {
        alternaTela(event, "gerenciaremprestimo-view.fxml", "Gerenciar Empréstimo");
    }

    @FXML
    private void onClickBtnVoltar(ActionEvent event) {
        alternaTela(event, "gerenciaremprestimo-view.fxml", "Gerenciar Empréstimo");
    }

    private Emprestimo extrairDadosView() {
        String isbnCodigoLivro = txtisbn.getText();
        String cpfLeitor = txtcpfleitor.getText();
        LocalDate dataEmprestimo = txtdataemprestimo.getValue();
        LocalDate dataDevolucao = txtdatadevolucao.getValue();
        String status = "Ativo";
        Emprestimo emprestimo = new Emprestimo(null, isbnCodigoLivro, cpfLeitor, dataEmprestimo, dataDevolucao, status);
        return emprestimo;
    }

    private boolean insertDadosEmprestimo(Emprestimo emprestimo) {
        {
            return emprestimoService.registrarEmprestimo(emprestimo);
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

    private boolean validaDadosEmprestimo() {
        MessageCreator creator = new AlertErrorCreator();
        String isbn = txtisbn.getText();
        String cpf = txtcpfleitor.getText();

        if (isbn == null || isbn.trim().isEmpty()) {
            creator.messageUser("Dado Inválido", "O campo do código ISBN é obrigatório.");
            return false;
        }

        if (!br.com.codexcb.application.util.ValidadorUtil.isIsbnValido(isbn)) {
            creator.messageUser("Dado Inválido", "O código ISBN não é um número válido (Verifique o formato ou dígito verificador).");
            return false;
        }

        if (cpf == null || cpf.trim().isEmpty()) {
            creator.messageUser("Dado Inválido", "O campo CPF do Leitor é obrigatório e deve ser preenchido.");
            return false;
        }

        if (!br.com.codexcb.application.util.ValidadorUtil.isCpfValido(cpf)) {
            creator.messageUser("Dado Inválido", "O CPF informado é inválido (Verifique o número e o dígito verificador).");
            return false;
        }

        if (txtdataemprestimo.getValue() == null) {
            creator.messageUser("Dado Inválido", "A Data de Empréstimo é obrigatória e deve ser selecionada.");
            return false;
        }

        if (txtdatadevolucao.getValue() == null) {
            creator.messageUser("Dado Inválido", "A Data de Devolução é obrigatória e deve ser selecionada.");
            return false;
        }

        LocalDate dataEmprestimo = txtdataemprestimo.getValue();
        LocalDate dataDevolucao = txtdatadevolucao.getValue();

        if (dataDevolucao.isBefore(dataEmprestimo)) {
            creator.messageUser("Dado Inválido", "A Data de Devolução não pode ser anterior à Data de Empréstimo.");
            return false;
        }

        return true;
    }

    private void calcularDataDevolucao() {
        LocalDate dataEmprestimo = txtdataemprestimo.getValue();
        if (dataEmprestimo != null) {
            LocalDate dataDevolucao = dataEmprestimo.plusDays(10);
            txtdatadevolucao.setValue(dataDevolucao);
        } else {
            txtdataemprestimo.setValue(LocalDate.now());
            LocalDate dataDevolucao = dataEmprestimo.plusDays(10);
            txtdatadevolucao.setValue(dataDevolucao);
        }
    }
}