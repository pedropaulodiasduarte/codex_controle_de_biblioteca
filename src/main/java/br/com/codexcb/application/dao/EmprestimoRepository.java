package br.com.codexcb.application.dao;

import br.com.codexcb.application.model.Emprestimo;

public interface EmprestimoRepository {
    boolean registrarEmprestimo(Emprestimo emprestimo);
}
