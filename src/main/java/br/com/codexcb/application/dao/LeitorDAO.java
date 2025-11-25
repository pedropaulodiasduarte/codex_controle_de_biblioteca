package br.com.codexcb.application.dao;

import br.com.codexcb.application.dto.LeitoresStatusDTO;
import br.com.codexcb.application.model.EmprestimoVisualizacao;
import br.com.codexcb.application.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class LeitorDAO implements LeitorRepository {
    @Override
    public boolean cadastrarLeitor(Usuario leitor) {
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
    public Usuario consultarLeitorId(int id) {
        ConectaDatabase conectaDatabase = ConectaDatabase.getInstanceSingleton();
        String sqlScript = "select * from leitor where id = ?";

        try (Connection connection = conectaDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlScript)) {

            preparedStatement.setInt(1, id);

            try (java.sql.ResultSet resultSet = preparedStatement.executeQuery()) {
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

    @Override
    public Usuario consultarLeitorCPF(String cpf) {
        ConectaDatabase conectaDatabase = ConectaDatabase.getInstanceSingleton();
        String sqlScript = "select * from leitor where cpf = ?";

        try (Connection connection = conectaDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlScript)) {

            preparedStatement.setString(1, cpf);

            try (java.sql.ResultSet resultSet = preparedStatement.executeQuery()) {
                //ResultSet é uma interface, o qual mediante seu objeto se pode iterar sobre os dados da consulta
                if (resultSet.next()) {
                    int idRecuperado = resultSet.getInt("id");
                    String nome = resultSet.getString("nome");
                    String cpfRecuperado = resultSet.getString("cpf");
                    String endereco = resultSet.getString("endereco");
                    String telefone = resultSet.getString("telefone");
                    String email = resultSet.getString("email");
                    return new Usuario(idRecuperado, nome, cpfRecuperado, endereco, telefone, email);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro de banco de dados: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<LeitoresStatusDTO> consultarLeitoresUltimoStatus() {
        ConectaDatabase conectaDatabase = ConectaDatabase.getInstanceSingleton();
        List<LeitoresStatusDTO> leitoresStatus = new ArrayList<>();
        String sqlScript = "SELECT l.nome, l.cpf, l.id, l.telefone, COALESCE((SELECT e.status FROM emprestimo e WHERE e.cpffk = l.cpf ORDER BY e.dataEmprestimo DESC, e.id DESC LIMIT 1), 'Não há registro') AS statusultimoemprestimo FROM leitor l ORDER BY l.nome";

        try (Connection connection = conectaDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlScript);
             java.sql.ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String cpf = resultSet.getString("cpf");
                String telefone = resultSet.getString("telefone");
                String status = resultSet.getString("statusultimoemprestimo");

                LeitoresStatusDTO leitorStatus = new LeitoresStatusDTO(nome, telefone, cpf, id, status);
                leitoresStatus.add(leitorStatus);
            }
        } catch (SQLException e) {
            System.out.println("Erro de banco de dados na consulta de status: " + e.getMessage());
        }
        return leitoresStatus;
    }

    @Override
    public List<Usuario> consultarLeitorNome(String nome) {
        ConectaDatabase conectaDatabase = ConectaDatabase.getInstanceSingleton();
        String sqlScript = "select * from leitor where nome like ?";
        List<Usuario> leitores = new ArrayList<>();

        try (Connection connection = conectaDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlScript)) {

            nome += "%";
            preparedStatement.setString(1, nome);

            try (java.sql.ResultSet resultSet = preparedStatement.executeQuery()) {
                //ResultSet é uma interface, o qual mediante seu objeto se pode iterar sobre os dados da consulta
                while (resultSet.next()) {
                    int idRecuperado = resultSet.getInt("id");
                    String nomeRecuperado = resultSet.getString("nome");
                    String cpfRecuperado = resultSet.getString("cpf");
                    String endereco = resultSet.getString("endereco");
                    String telefone = resultSet.getString("telefone");
                    String email = resultSet.getString("email");
                    Usuario leitor = new Usuario(idRecuperado, nomeRecuperado, cpfRecuperado, endereco, telefone, email);
                    leitores.add(leitor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro de banco de dados: " + e.getMessage());
        }
        return leitores;

    }

    @Override
    public List<LeitoresStatusDTO> consultarLeitoresUltimoStatusNome(String nomePesquisa) {
        ConectaDatabase conectaDatabase = ConectaDatabase.getInstanceSingleton();
        List<LeitoresStatusDTO> leitoresStatus = new ArrayList<>();
        String sqlScript = "SELECT l.nome, l.cpf, l.id, l.telefone, COALESCE((SELECT e.status FROM emprestimo e WHERE e.cpffk = l.cpf ORDER BY e.dataEmprestimo DESC, e.id DESC LIMIT 1), 'Não há registro') AS statusultimoemprestimo FROM leitor l where nome like ? ORDER BY l.nome";

        try (Connection connection = conectaDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlScript)) {

            nomePesquisa+="%";
            preparedStatement.setString(1, nomePesquisa);
            try (java.sql.ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nome = resultSet.getString("nome");
                    String cpf = resultSet.getString("cpf");
                    String telefone = resultSet.getString("telefone");
                    String status = resultSet.getString("statusultimoemprestimo");

                    LeitoresStatusDTO leitorStatus = new LeitoresStatusDTO(nome, telefone, cpf, id, status);
                    leitoresStatus.add(leitorStatus);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro de banco de dados na consulta de status: " + e.getMessage());
        }
        return leitoresStatus;
    }

}
