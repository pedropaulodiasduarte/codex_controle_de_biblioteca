package br.com.codexcb.application.dao;

import br.com.codexcb.application.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class ClienteDAO implements ClienteRepository{
    @Override
    public void cadastrarCliente(Usuario cliente){
        //Usuario cliente = new Usuario(nome, cpf, endereco, telefone, email);

        // por questões de segurança, ? serve para previnir o SQL injection. Também, melhora desempenho
        // se fosse feito via concatenção de Strings, a SQL injection poderia acontecer
        // Via PreparedStatement , os placeholders ?,  são preenchidos.
        String sql = "INSERT INTO cliente VALUES (?, ?, ?, ?, ?, ?)";

        ConectaDatabase conectaDatabase = ConectaDatabase.getInstanceSingleton();
        try (Connection connection = conectaDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setNull(1, Types.INTEGER);
            preparedStatement.setString(2, cliente.getNome());
            preparedStatement.setString(3, cliente.getCpf());
            preparedStatement.setString(4, cliente.getEndereco());
            preparedStatement.setString(5, cliente.getTelefone());
            preparedStatement.setString(6, cliente.getEmail());

            int linhasAfetadas = preparedStatement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("dados persistidos com sucesso");
            } else {
                System.out.println("falha na persistência");
            }

        } catch (SQLException e) {
            System.out.println("Erro de banco de dados: " + e.getMessage());
            //e.printStackTrace();
        }

    }
}
