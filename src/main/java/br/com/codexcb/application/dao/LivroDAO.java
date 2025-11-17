package br.com.codexcb.application.dao;

import br.com.codexcb.application.model.Livro;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class LivroDAO implements LivroRepository{
    @Override
    public boolean cadastrarLivro(Livro livro){
        String sqlScript = "INSERT INTO livro VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ConectaDatabase conectaDatabase = ConectaDatabase.getInstanceSingleton();
        try (Connection connection = conectaDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlScript)) {
            preparedStatement.setNull(1, Types.INTEGER);
            preparedStatement.setString(2, livro.getTitulo());
            preparedStatement.setString(3, livro.getAutor());
            preparedStatement.setString(4, livro.getIsbnCodigo());
            preparedStatement.setString(5, livro.getIdioma());
            preparedStatement.setString(6, livro.getEditora());
            preparedStatement.setDate(7, java.sql.Date.valueOf(livro.getDataPublicacao()));
            preparedStatement.setInt(8, livro.getCopia());
            preparedStatement.setString(9, livro.getGenero());
            preparedStatement.setInt(10, livro.getIdLocalizacao());

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
