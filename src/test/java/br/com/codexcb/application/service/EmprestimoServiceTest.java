package br.com.codexcb.application.service;

import br.com.codexcb.application.dao.EmprestimoDAO;
import br.com.codexcb.application.dao.EmprestimoRepository;
import br.com.codexcb.application.model.Emprestimo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmprestimoServiceTest {
    EmprestimoRepository emprestimoRepository;
    @BeforeEach
    void setup(){
        emprestimoRepository = new EmprestimoDAO();
    }

    @Test
    @DisplayName("teste de inserção de dados na tabela emprestimo")
    void registrarEmprestimo() {
        String isbnCodigo = "1";
        int idLeitor = 1;
        LocalDate dataemprestimo = LocalDate.parse("2025-10-10");
        LocalDate dataDevolucao = LocalDate.parse("2025-11-10");
        String status = "Pendente";
        Emprestimo emprestimo = new Emprestimo(isbnCodigo, idLeitor, dataemprestimo, dataDevolucao, status);
        assertEquals(true, emprestimoRepository.registrarEmprestimo(emprestimo));

    }

}