package br.com.codexcb.application.dao;

import br.com.codexcb.application.model.Emprestimo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

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

}
