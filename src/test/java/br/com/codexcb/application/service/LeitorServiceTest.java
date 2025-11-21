package br.com.codexcb.application.service;

import br.com.codexcb.application.dao.LeitorDAO;
import br.com.codexcb.application.dao.LeitorRepository;
import br.com.codexcb.application.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LeitorServiceTest {
    private  LeitorService leitorService;
    private LeitorRepository leitorRepository;
    @BeforeEach
    void setup(){
        leitorService = new LeitorService(new LeitorDAO());
        leitorRepository = new LeitorDAO();
    }

    @Test
    @DisplayName("teste de consulta de usuário por id")
    void consultarLeitorId() {
        Usuario leitor = leitorRepository.consultarLeitorId(2);
        System.out.println("ID "+leitor.getId());
        System.out.println("Nome "+leitor.getNome());
        System.out.println("CPF "+leitor.getCpf());
        System.out.println("Endereço "+leitor.getEndereco());
        System.out.println("Telefone "+leitor.getTelefone());
        System.out.println("email "+leitor.getEmail());
    }

}