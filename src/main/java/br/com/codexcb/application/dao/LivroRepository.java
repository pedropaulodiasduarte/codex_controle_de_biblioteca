package br.com.codexcb.application.dao;

import br.com.codexcb.application.model.Livro;

public interface LivroRepository {
    boolean cadastrarLivro(Livro livro);
    Livro consultarLivroIsbnCodigo(String isbnCodigo);
}
