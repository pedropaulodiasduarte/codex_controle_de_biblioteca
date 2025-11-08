package br.com.codexcb.application.dao;

import br.com.codexcb.application.model.Usuario;

public interface ClienteRepository {
    void cadastrarCliente(Usuario cliente);
}
