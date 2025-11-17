package br.com.codexcb.application.service;

import br.com.codexcb.application.dao.LivroDAO;
import br.com.codexcb.application.model.Livro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LivroServiceTest {
    private LivroService livroService;
    private LivroDAO livroDAO;

    @BeforeEach
    void setup(){
        livroDAO = new LivroDAO();
        livroService = new LivroService(livroDAO);
    }

    @Test
    @DisplayName("teste de persistência com insert de cadastro de livro novo")
    void cadastrarLivroTeste(){
        LocalDate dataPublicacao = LocalDate.parse("2024-12-12");
        String titulo = "Título teste";
        String autor = "Autor João Teste";
        String isbnCodigo = "ab3243";
        String idioma = "Português";
        String editora = "Editora SP";
        int copia = 10;
        int idLocalizacao = 1;
        String genero = "romance";

        Livro livro = new Livro(titulo, autor, isbnCodigo, idioma, editora, dataPublicacao, copia, idLocalizacao, genero);
        assertEquals(10, livro.getCopia());
 livroService.cadastrarLivro(livro);
    }

}