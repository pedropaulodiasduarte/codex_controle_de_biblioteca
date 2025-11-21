package br.com.codexcb.application.dao;

import br.com.codexcb.application.model.Usuario;

import java.util.List;

public interface LeitorRepository {
    boolean cadastrarLeitor(Usuario cliente);
    Usuario consultarLeitorId(int id);
    Usuario consultarLeitorCPF(String cpf);
    List<Usuario> consultarListaUsuario();
}
