package br.com.codexcb.application.model;

import java.time.LocalDate;

public class EmprestimoVisualizacao {
    private int id;
    private String tituloLivro;
    private String nomeLeitor;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private String status;

    public EmprestimoVisualizacao(int id, String tituloLivro, String nomeLeitor, LocalDate dataEmprestimo, LocalDate dataDevolucao, String status) {
        this.id = id;
        this.tituloLivro = tituloLivro;
        this.nomeLeitor = nomeLeitor;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public String getNomeLeitor() {
        return nomeLeitor;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public String getStatus() {
        return status;
    }
}
