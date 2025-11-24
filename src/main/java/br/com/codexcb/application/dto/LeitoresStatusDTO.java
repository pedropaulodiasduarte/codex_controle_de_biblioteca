package br.com.codexcb.application.dto;

public class LeitoresStatusDTO {
    private String nome;
    private String telefone;
    private String cpf;
    private Integer id;
    private String statusUltimoEmprestimo;

    public LeitoresStatusDTO(String nome, String telefone, String cpf, Integer id, String statusUltimoEmprestimo) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.id = id;
        this.statusUltimoEmprestimo = statusUltimoEmprestimo;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public Integer getId() {
        return id;
    }

    public String getStatusUltimoEmprestimo() {
        return statusUltimoEmprestimo;
    }
}
