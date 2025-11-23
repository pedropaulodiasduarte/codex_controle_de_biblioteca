package br.com.codexcb.application.service;

import br.com.codexcb.application.dao.EmprestimoDAO;
import br.com.codexcb.application.dao.EmprestimoRepository;
import br.com.codexcb.application.model.Emprestimo;
import br.com.codexcb.application.model.EmprestimoVisualizacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmprestimoServiceTest {
    private EmprestimoService emprestimoService;
    @BeforeEach
    void setup(){
        emprestimoService = new EmprestimoService(new EmprestimoDAO());
    }

    @Test
    @DisplayName("teste de inserção de dados na tabela emprestimo")
    void registrarEmprestimo() {
        String isbnCodigo = "1";
        String cpfLeitor = "07062032751";
        LocalDate dataemprestimo = LocalDate.parse("2025-10-10");
        LocalDate dataDevolucao = LocalDate.parse("2025-11-10");
        String status = "Pendente";
        Emprestimo emprestimo = new Emprestimo(isbnCodigo, cpfLeitor, dataemprestimo, dataDevolucao, status);
        assertEquals(true, emprestimoService.registrarEmprestimo(emprestimo));

    }

    @Test
    @DisplayName("teste de consulta de lista de emprestimo")
    void consultarListaEmprestimo() {
        List<EmprestimoVisualizacao> emprestimoVisualizacaos = emprestimoService.consultarListaEmprestimo();
        assertNotEquals(0, emprestimoVisualizacaos.size());
        for (int i = 0; i < emprestimoVisualizacaos.size(); i++) {
            System.out.printf("ID: %d | Nome %s | Título %s%n",
                    emprestimoVisualizacaos.get(i).getId(),
                    emprestimoVisualizacaos.get(i).getNomeLeitor(),
                    emprestimoVisualizacaos.get(i).getTituloLivro());
        }
    }

}