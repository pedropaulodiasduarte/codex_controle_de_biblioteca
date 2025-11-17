package br.com.codexcb.application.service;

import br.com.codexcb.application.dao.LivroDAO;
import br.com.codexcb.application.dao.LivroRepository;
import br.com.codexcb.application.model.Livro;

public class LivroService {
    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public boolean cadastrarLivro(Livro livro){
        return this.livroRepository.cadastrarLivro(livro);
    }
}
