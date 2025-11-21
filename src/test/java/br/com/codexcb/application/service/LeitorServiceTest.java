package br.com.codexcb.application.service;

import br.com.codexcb.application.dao.LeitorDAO;
import br.com.codexcb.application.dao.LeitorRepository;
import br.com.codexcb.application.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    @DisplayName("teste de recuperação de todos usuários cadastrados do banco em uma arraylist")
    void consultarListaLeitor(){
        List<Usuario> leitores = leitorRepository.consultarListaUsuario();
        assertNotEquals(0, leitores.size());
        for (int i = 0; i < leitores.size(); i++) {
            System.out.println("ID "+leitores.get(i).getId());
            System.out.println("Nome "+leitores.get(i).getNome());
            System.out.println("CPF "+leitores.get(i).getCpf());
            System.out.println("Endereço "+leitores.get(i).getEndereco());
            System.out.println("Telefone "+leitores.get(i).getTelefone());
            System.out.println("email "+leitores.get(i).getEmail());
        }

    }

}