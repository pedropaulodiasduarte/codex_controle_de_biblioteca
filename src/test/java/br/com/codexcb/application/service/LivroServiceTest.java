package br.com.codexcb.application.service;

import br.com.codexcb.application.dao.LivroDAO;
import br.com.codexcb.application.dao.LivroRepository;
import br.com.codexcb.application.model.Livro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LivroServiceTest {
    private LivroService livroService;
    private LivroDAO livroDAO;
    private LivroRepository livroRepository;

    @BeforeEach
    void setup(){
        livroDAO = new LivroDAO();
        livroService = new LivroService(livroDAO);
        livroRepository = new LivroDAO();
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

        Livro livro = new Livro(null, titulo, autor, isbnCodigo, idioma, editora, dataPublicacao, copia, idLocalizacao, genero);
        assertEquals(10, livro.getCopia());
 livroService.cadastrarLivro(livro);
    }

    @Test
    @DisplayName("teste de consulta de livro com argumento de isbn")
    void consultarLivroIsbnCodigo() {
        Livro livro = livroService.consultarLivroIsbnCodigo("9788574480615");
        assertNotEquals(null, livro);
        System.out.println("ID | Título | Autor | ISBN | Idioma | Editora | Data Publicação | Cópia | Gênero | ID Localização");
        System.out.printf("%d | %s | %s | %s | %s | %s | %s | %d | %s | %d%n",
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getIsbnCodigo(),
                livro.getIdioma(),
                livro.getEditora(),
                livro.getDataPublicacao(),
                livro.getCopia(),
                livro.getGenero(),
                livro.getIdLocalizacao());

    }

    @Test
    @DisplayName("teste de recuperação de dados, onde retorna todos livros cadastrados no banco de dados")
    void consultarListaLivro() {
        List<Livro> livros = livroService.consultarListaLivro();
        assertNotEquals(0, livros.size());
        System.out.println("ID | Título | Autor | ISBN | Idioma | Editora | Data Publicação | Cópia | Gênero | ID Localização");
        for (int i = 0; i < livros.size(); i++) {
            System.out.printf("%d | %s | %s | %s | %s | %s | %s | %d | %s | %d%n",
                    livros.get(i).getId(),
                    livros.get(i).getTitulo(),
                    livros.get(i).getAutor(),
                    livros.get(i).getIsbnCodigo(),
                    livros.get(i).getIdioma(),
                    livros.get(i).getEditora(),
                    livros.get(i).getDataPublicacao(),
                    livros.get(i).getCopia(),
                    livros.get(i).getGenero(),
                    livros.get(i).getIdLocalizacao());
        }
    }

}