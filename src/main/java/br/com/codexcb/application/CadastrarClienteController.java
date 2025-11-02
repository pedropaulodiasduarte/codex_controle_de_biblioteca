package br.com.codexcb.application;

import br.com.codexcb.application.dao.ConectaDatabase;
import br.com.codexcb.application.model.Pessoa;
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


        if (nome.trim().isEmpty()) {
            mensagemLabel.setText("O campo Nome é obrigatório!");
            mensagemLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        Pessoa pessoa = new Pessoa(nome, cpf, endereco, telefone);

        // por questões de segurança, ? serve para previnir o SQL injection. Também, melhora desempenho
        // se fosse feito via concatenção de Strings, a SQL injection poderia acontecer
        // Via PreparedStatement , os placeholders ?,  são preenchidos.
        String sql = "INSERT INTO cliente VALUES (?, ?, ?, ?, ?)";

        ConectaDatabase conectaDatabase = ConectaDatabase.getInstanceSingleton();
        try (Connection connection = conectaDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setNull(1, Types.INTEGER);
            preparedStatement.setString(2, pessoa.getNome());
            preparedStatement.setString(3, pessoa.getCpf());
            preparedStatement.setString(4, pessoa.getEndereco());
            preparedStatement.setString(5, pessoa.getTelefone());

            int linhasAfetadas = preparedStatement.executeUpdate();

            if (linhasAfetadas > 0) {
                mensagemLabel.setText("Cliente '" + nome + "' cadastrado com sucesso!");
                mensagemLabel.setStyle("-fx-text-fill: green;");
                limparCampos();
            } else {
                mensagemLabel.setText("Erro ao cadastrar cliente. Nenhuma linha afetada.");
                mensagemLabel.setStyle("-fx-text-fill: red;");
            }

        } catch (SQLException e) {
            mensagemLabel.setText("Erro de banco de dados: " + e.getMessage());
            mensagemLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }

    private void limparCampos() {
        nomeField.clear();
        enderecoField.clear();
        telefoneField.clear();
        nomeField.requestFocus();
    }
}