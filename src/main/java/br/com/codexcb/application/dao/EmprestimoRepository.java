package br.com.codexcb.application.dao;

import br.com.codexcb.application.model.Emprestimo;
import br.com.codexcb.application.model.EmprestimoVisualizacao;

import java.time.LocalDate;
import java.util.List;

public interface EmprestimoRepository {
    boolean registrarEmprestimo(Emprestimo emprestimo);
    boolean atualizarEmprestimo(Integer idAtualizar, String statusAtualizar, String statusAtual, LocalDate dataDevolucaoAtualizar);
    List<EmprestimoVisualizacao> consultarListaEmprestimo();
    EmprestimoVisualizacao consultarEmprestimoStatus(Integer idConsultar, String statusConsultar);
    List<EmprestimoVisualizacao> consultarListaEmprestimoStatus(String statusConsultar);
}
