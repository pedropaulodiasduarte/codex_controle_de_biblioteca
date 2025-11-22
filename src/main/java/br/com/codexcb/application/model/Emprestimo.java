package br.com.codexcb.application.model;

import java.time.LocalDate;

public class Emprestimo {
    private int id;
    private String isbnCodigo;
    private String cpfLeitor;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private String status;

    public Emprestimo(String isbnCodigo, String cpfLeitor, LocalDate dataEmprestimo, LocalDate dataDevolucao, String status) {
        this.isbnCodigo = isbnCodigo;
        this.cpfLeitor = cpfLeitor;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
    }

    public String getCpfLeitor() {
        return cpfLeitor;
    }

    public void setCpfLeitor(String cpfLeitor) {
        this.cpfLeitor = cpfLeitor;
    }

    public String getIsbnCodigo() {
        return isbnCodigo;
    }

    public void setIsbnCodigo(String isbnCodigo) {
        this.isbnCodigo = isbnCodigo;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }
}
