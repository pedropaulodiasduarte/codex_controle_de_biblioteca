package br.com.codexcb.application.model;

import java.time.LocalDate;

public class Emprestimo {
    private int id;
    private String isbnCodigo;
    private int idLeitor;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private String status;

    public Emprestimo(String isbnCodigo, int idLeitor, LocalDate dataEmprestimo, LocalDate dataDevolucao, String status) {
        this.isbnCodigo = isbnCodigo;
        this.idLeitor = idLeitor;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
    }
}
