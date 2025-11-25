package br.com.codexcb.application.service;

import br.com.codexcb.application.dao.LivroDAO;
import br.com.codexcb.application.dao.LivroRepository;
import br.com.codexcb.application.model.Livro;

import java.util.List;

public class LivroService {
    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public boolean cadastrarLivro(Livro livro){
        return this.livroRepository.cadastrarLivro(livro);
    }

    public Livro consultarLivroIsbnCodigo(String isbnCodigo) {
        return this.livroRepository.consultarLivroIsbnCodigo(isbnCodigo);
    }

    public List<Livro> consultarListaLivro() {
        return this.livroRepository.consultarListaLivro();
    }

    public boolean atualizarLivro(Livro livro) {
        return this.livroRepository.atualizarLivro(livro);
    }
}
