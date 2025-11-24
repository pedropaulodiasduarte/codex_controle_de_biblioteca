package br.com.codexcb.application.dto;

public record LeitoresStatusDTO(
        String nome,
        String cpf,
        Integer id,
        String telefone,
        String statusUltimoEmprestimo
) {
}
