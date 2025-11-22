package br.com.codexcb.application.dao;

import br.com.codexcb.application.model.Emprestimo;
import br.com.codexcb.application.model.EmprestimoVisualizacao;
import br.com.codexcb.application.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO implements EmprestimoRepository{
    @Override
    public boolean registrarEmprestimo(Emprestimo emprestimo) {
        ConectaDatabase conectaDatabase = ConectaDatabase.getInstanceSingleton();
        String sqlScript = "insert into emprestimo values (?, ?, ?, ?, ?, ?)";

        try (Connection connection = conectaDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlScript)) {

            preparedStatement.setNull(1, Types.INTEGER);
            preparedStatement.setString(2, emprestimo.getIsbnCodigo());
            preparedStatement.setString(3, emprestimo.getCpfLeitor());
            preparedStatement.setDate(4, java.sql.Date.valueOf(emprestimo.getDataEmprestimo()));
            preparedStatement.setDate(5, java.sql.Date.valueOf(emprestimo.getDataDevolucao()));
            preparedStatement.setString(6, emprestimo.getStatus());

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
    public List<EmprestimoVisualizacao> consultarListaEmprestimo() {
        ConectaDatabase conectaDatabase = ConectaDatabase.getInstanceSingleton();
        String sqlScript = "select emprestimo.id, livro.titulo, leitor.nome, emprestimo.dataemprestimo, emprestimo.datadevolucao, emprestimo.status from emprestimo inner join leitor on emprestimo.cpffk = leitor.cpf inner join livro on emprestimo.isbncodigofk = livro.isbncodigo";
        List<EmprestimoVisualizacao> listEmprestimoVisualizacao = new ArrayList<>();

        try (Connection connection = conectaDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlScript);
             java.sql.ResultSet resultSet = preparedStatement.executeQuery()) {
            //ResultSet é uma interface, o qual mediante seu objeto se pode iterar sobre os dados da consulta

            //laço de repetição para  Iterar sobre cada linha retornada pelo banco
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String titulo  = resultSet.getString("titulo");
                String nome  = resultSet.getString("nome");
                java.sql.Date sqlDate = resultSet.getDate("dataemprestimo");
                LocalDate dataEmprestimo = sqlDate.toLocalDate();
                sqlDate = resultSet.getDate("dataDevolucao");
                LocalDate dataDevolucao = sqlDate.toLocalDate();
                String status = resultSet.getString("status");

                EmprestimoVisualizacao emprestimoVisualizacao = new EmprestimoVisualizacao(id, titulo, nome, dataEmprestimo, dataDevolucao, status);
                listEmprestimoVisualizacao.add(emprestimoVisualizacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro de banco de dados: " + e.getMessage());
        }
        return listEmprestimoVisualizacao;

    }

}
