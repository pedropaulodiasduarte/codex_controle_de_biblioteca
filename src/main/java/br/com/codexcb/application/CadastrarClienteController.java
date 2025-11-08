package br.com.codexcb.application;

import br.com.codexcb.application.dao.ClienteDAO;
import br.com.codexcb.application.dao.ClienteRepository;
import br.com.codexcb.application.dao.ConectaDatabase;
import br.com.codexcb.application.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class CadastrarClienteController {
    @FXML private Label documentoLabel;
    @FXML private TextField nomeField;
    @FXML private TextField cpfField;
    @FXML private TextField enderecoField;
    @FXML private TextField telefoneField;
    @FXML private Label mensagemLabel;

    //anotação abaixo, é para  informar a view que este método deve ser executado quando clicar no botão cadastrar cliente, pois no FXML, o botão tem o atributo onAction, que quando a FXMLLoader carrega o FXML da view, ela já procura o método para vincular ao botão
    @FXML
    protected void cadastrarCliente() {
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        String endereco = enderecoField.getText();
        String telefone = telefoneField.getText();
        String email = "email@dominio.com.br";

        if (nome.trim().isEmpty()) {
            mensagemLabel.setText("O campo Nome é obrigatório!");
            mensagemLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        ClienteRepository clienteRepository = new ClienteDAO();
        Usuario cliente = new Usuario(nome, cpf, endereco, telefone, email);
        clienteRepository.cadastrarCliente(cliente);
    }

    private void limparCampos() {
        nomeField.clear();
        enderecoField.clear();
        telefoneField.clear();
        nomeField.requestFocus();
    }
}