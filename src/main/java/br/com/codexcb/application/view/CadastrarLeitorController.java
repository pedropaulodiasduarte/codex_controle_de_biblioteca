package br.com.codexcb.application.view;

import br.com.codexcb.application.dao.LeitorDAO;
import br.com.codexcb.application.mensagens.AlertErrorCreator;
import br.com.codexcb.application.mensagens.AlertInformationCreator;
import br.com.codexcb.application.mensagens.MessageCreator;
import br.com.codexcb.application.model.Usuario;
import br.com.codexcb.application.service.LeitorService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastrarLeitorController {
    @FXML
    private TextField nomeField;
    @FXML
    private TextField cpfField;
    @FXML
    private TextField enderecoField;
    @FXML
    private TextField telefoneField;
    @FXML
    private TextField emailfield;

    @FXML
    private Button btnadcleitor;
    @FXML
    private Button btnvoltar;
    @FXML
    private Button btncancelar;

    private MessageCreator messageCreator;

    //anotação abaixo, é para  informar a view que este método deve ser executado quando clicar no botão cadastrar cliente, pois no FXML, o botão tem o atributo onAction, que quando a FXMLLoader carrega o FXML da view, ela já procura o método para vincular ao botão
    @FXML
    private void onClickCadastrarLeitor(ActionEvent event) {
        if (insertLeitor(extrairDadosView()) || encontrarCampoVazio() == false) {
            messageCreator = new AlertInformationCreator();
            messageCreator.messageUser("Sucesso!", "Leitor cadastrado com sucesso.");
            abrirTelaPrincipal(event);
        }
    }

    @FXML
    private void onClickBtnCancelar(ActionEvent event) {
        abrirTelaPrincipal(event);
    }

    @FXML
    private void onClickBtnVoltar(ActionEvent event) {
        abrirTelaPrincipal(event);
    }

    private Usuario extrairDadosView() {
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        String endereco = enderecoField.getText();
        String telefone = telefoneField.getText();
        String email = emailfield.getText();

        Usuario leitor = new Usuario(null, nome, cpf, endereco, telefone, email);
        return leitor;
    }

    private boolean insertLeitor(Usuario leitor) {
        LeitorService leitorService = new LeitorService(new LeitorDAO());
        return leitorService.cadastrarLeitor(leitor);
    }

    private boolean encontrarCampoVazio() {
        String mensagem = "Por favor, preencha os campos obrigatórios! (";
        boolean campoVazio = false;
        if (nomeField.getText().trim().isEmpty()) {
            mensagem+="nome";
            campoVazio = true;
        }
        if (cpfField.getText().trim().isEmpty()){
            mensagem += ", CPF";
            campoVazio = true;
        }
        if (enderecoField.getText().trim().isEmpty()) {
            mensagem += ", endereço";
            campoVazio = true;
        }
        if (telefoneField.getText().trim().isEmpty()){
            mensagem += ", telefone";
            campoVazio = true;
        }
        if (emailfield.getText().trim().isEmpty()) {
            mensagem+=", e-mail";
            campoVazio = true;
        }

        if (campoVazio == true) {
            mensagem+=")";
            messageCreator = new AlertErrorCreator();
            messageCreator.messageUser("Atenção", mensagem);
            return campoVazio;
        }
        return campoVazio;
    }

    private void abrirTelaPrincipal(ActionEvent event) {
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
}