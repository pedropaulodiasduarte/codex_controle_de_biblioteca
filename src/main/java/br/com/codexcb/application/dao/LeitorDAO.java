package br.com.codexcb.application.dao;

import br.com.codexcb.application.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class LeitorDAO implements LeitorRepository {
    @Override
    public boolean cadastrarLeitor(Usuario leitor){
        // por questões de segurança, ? serve para previnir o SQL injection. Também, melhora desempenho
        // se fosse feito via concatenção de Strings, a SQL injection poderia acontecer
        // Via PreparedStatement , os placeholders ?,  são preenchidos.
        String sql = "INSERT INTO leitor VALUES (?, ?, ?, ?, ?, ?)";

        ConectaDatabase conectaDatabase = ConectaDatabase.getInstanceSingleton();
        try (Connection connection = conectaDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setNull(1, Types.INTEGER);
            preparedStatement.setString(2, leitor.getNome());
            preparedStatement.setString(3, leitor.getCpf());
            preparedStatement.setString(4, leitor.getEndereco());
            preparedStatement.setString(5, leitor.getTelefone());
            preparedStatement.setString(6, leitor.getEmail());

            int linhasAfetadas = preparedStatement.executeUpdate();

            if (linhasAfetadas > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erro de banco de dados: " + e.getMessage());
            //e.printStackTrace();
        }
return false;
    }
}
