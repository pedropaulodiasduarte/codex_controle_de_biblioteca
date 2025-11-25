package br.com.codexcb.application.service;

import br.com.codexcb.application.dao.EmprestimoRepository;
import br.com.codexcb.application.dao.LeitorDAO;
import br.com.codexcb.application.model.Emprestimo;
import br.com.codexcb.application.model.EmprestimoVisualizacao;
import br.com.codexcb.application.model.Usuario;

import java.time.LocalDate;
import java.util.List;

public class EmprestimoService {
    private final EmprestimoRepository emprestimoRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }

    public boolean registrarEmprestimo(Emprestimo emprestimo) {
        return this.emprestimoRepository.registrarEmprestimo(emprestimo);
    }

    public List<EmprestimoVisualizacao> consultarListaEmprestimo() {
return         this.emprestimoRepository.consultarListaEmprestimo();
    }

    public EmprestimoVisualizacao consultarEmprestimoStatus(Integer idConsultar, String statusConsultar) {
        return this.emprestimoRepository.consultarEmprestimoStatus(idConsultar, statusConsultar);
    }

    public boolean atualizarEmprestimo(Integer idAtualizar, String statusAtualizar, String statusAtual, LocalDate dataDevolucaoAtualizar) {
        return this.emprestimoRepository.atualizarEmprestimo(idAtualizar, statusAtualizar, statusAtual, dataDevolucaoAtualizar);
    }

    public List<EmprestimoVisualizacao> consultarListaEmprestimoStatus(String statusConsultar) {
        return this.emprestimoRepository.consultarListaEmprestimoStatus(statusConsultar);
    }

    public List<EmprestimoVisualizacao> consultarListaEmprestimoNome(String nomePesquisa) {
        return emprestimoRepository.consultarListaEmprestimoNome(nomePesquisa);
    }
}
