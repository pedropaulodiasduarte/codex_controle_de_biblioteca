package br.com.codexcb.application.dao;

import br.com.codexcb.application.model.Usuario;

public interface LeitorRepository {
    boolean cadastrarLeitor(Usuario cliente);
}
