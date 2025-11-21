package br.com.codexcb.application.dao;

import br.com.codexcb.application.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Usuario consultarLeitorId(int id){
        ConectaDatabase conectaDatabase = ConectaDatabase.getInstanceSingleton();
        String sqlScript = "select * from leitor where id = ?";

        try (Connection connection = conectaDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlScript)) {

preparedStatement.setInt(1, id);

            try (java.sql.ResultSet resultSet = preparedStatement.executeQuery()){
                //ResultSet é uma interface, o qual mediante seu objeto se pode iterar sobre os dados da consulta
                if (resultSet.next()) {
                    int idRecuperado = resultSet.getInt("id");
                    String nome = resultSet.getString("nome");
                    String cpf = resultSet.getString("cpf");
                    String endereco = resultSet.getString("endereco");
                    String telefone = resultSet.getString("telefone");
                    String email = resultSet.getString("email");
                    return new Usuario(idRecuperado, nome, cpf, endereco, telefone, email);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro de banco de dados: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Usuario> consultarListaUsuario() {
        ConectaDatabase conectaDatabase = ConectaDatabase.getInstanceSingleton();
        String sqlScript = "select * from leitor";
        List<Usuario> leitores = new ArrayList<>();

        try (Connection connection = conectaDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlScript);
             java.sql.ResultSet resultSet = preparedStatement.executeQuery()) {
            //ResultSet é uma interface, o qual mediante seu objeto se pode iterar sobre os dados da consulta

            //laço de repetição para  Iterar sobre cada linha retornada pelo banco
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String cpf = resultSet.getString("cpf");
                String endereco = resultSet.getString("endereco");
                String telefone = resultSet.getString("telefone");
                String email = resultSet.getString("email");
                Usuario leitor = new Usuario(id, nome, cpf, endereco, telefone, email);
                leitores.add(leitor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro de banco de dados: " + e.getMessage());
        }
        return leitores;
    }
}
