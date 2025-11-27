package br.com.codexcb.application.dao;

import br.com.codexcb.application.model.Livro;
import br.com.codexcb.application.model.Usuario;

import java.util.List;

public interface LivroRepository {
    boolean cadastrarLivro(Livro livro);
    Livro consultarLivroIsbnCodigo(String isbnCodigo);
    List<Livro> consultarListaLivro();
    boolean atualizarLivro(Livro livro);
    List<Livro> consultarListaLivroNome(String tituloPesquisa);
}
