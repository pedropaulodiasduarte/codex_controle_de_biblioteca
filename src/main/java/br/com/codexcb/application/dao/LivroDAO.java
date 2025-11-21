package br.com.codexcb.application.dao;

import br.com.codexcb.application.model.Livro;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

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

    @Override
    public Livro consultarLivroIsbnCodigo(String isbnCodigo) {
        ConectaDatabase conectaDatabase = ConectaDatabase.getInstanceSingleton();
        String sqlScript = "select * from livro where isbncodigo = ?";

        try (Connection connection = conectaDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlScript)) {

            preparedStatement.setString(1, isbnCodigo);

            try (java.sql.ResultSet resultSet = preparedStatement.executeQuery()){
                //ResultSet é uma interface, o qual mediante seu objeto se pode iterar sobre os dados da consulta
                if (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String titulo = resultSet.getString("titulo");
                    String autor = resultSet.getString("autor");
                    String isbnCodigoRecuperado = resultSet.getString("isbnCodigo");
                    String idioma = resultSet.getString("idioma");
                    String editora = resultSet.getString("editora");
                    LocalDate dataPublicacao = LocalDate.parse("2025-10-10");
                    int copia = resultSet.getInt("copia");
                    String genero = resultSet.getString("genero");
                    int idLocalizacao = resultSet.getInt("idLocalizacao");

                    return new Livro(id,titulo, autor, isbnCodigoRecuperado, idioma, editora, dataPublicacao, copia, idLocalizacao, genero);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro de banco de dados: " + e.getMessage());
        }
        return null;
    }

}
