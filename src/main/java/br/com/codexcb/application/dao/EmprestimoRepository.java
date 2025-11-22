package br.com.codexcb.application.dao;

import br.com.codexcb.application.model.Emprestimo;
import br.com.codexcb.application.model.EmprestimoVisualizacao;
import br.com.codexcb.application.service.LeitorService;

import java.util.List;

public interface EmprestimoRepository {
    boolean registrarEmprestimo(Emprestimo emprestimo);
    List<EmprestimoVisualizacao> consultarListaEmprestimo();
}
