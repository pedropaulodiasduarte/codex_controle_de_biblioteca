package br.com.codexcb.application.service;

import br.com.codexcb.application.dao.EmprestimoRepository;
import br.com.codexcb.application.dao.LeitorDAO;
import br.com.codexcb.application.model.Emprestimo;
import br.com.codexcb.application.model.Usuario;

public class EmprestimoService {
    private final EmprestimoRepository emprestimoRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }

    public boolean registrarEmprestimo(Emprestimo emprestimo) {
        return this.emprestimoRepository.registrarEmprestimo(emprestimo);
    }
}
