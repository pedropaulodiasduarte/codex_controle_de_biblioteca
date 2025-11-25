package br.com.codexcb.application.service;

import br.com.codexcb.application.dao.LeitorRepository;
import br.com.codexcb.application.dto.LeitoresStatusDTO;
import br.com.codexcb.application.model.Usuario;

import java.util.List;

public class LeitorService {
    private final LeitorRepository leitorRepository;

    //construtor
    public LeitorService(LeitorRepository leitorRepository) {
        this.leitorRepository = leitorRepository;
    }

    public boolean cadastrarLeitor(Usuario leitor) {
        return this.leitorRepository.cadastrarLeitor(leitor);
    }
    public Usuario consultarLeitorId(int id) {
        return this.leitorRepository.consultarLeitorId(id);
    }

    public Usuario consultarLeitorCPF(String cpf) {
        return this.leitorRepository.consultarLeitorCPF(cpf);
    }

    public List<Usuario> consultarListaUsuario() {
        return this.leitorRepository.consultarListaUsuario();
    }

    public List<LeitoresStatusDTO> consultarLeitoresUltimoStatus() {
        return leitorRepository.consultarLeitoresUltimoStatus();
    }

    public List<Usuario> consultarLeitorNome(String nome) {
        return this.leitorRepository.consultarLeitorNome(nome);
    }

    public List<LeitoresStatusDTO> consultarLeitoresUltimoStatusNome(String nomePesquisa){
        return this.leitorRepository.consultarLeitoresUltimoStatusNome(nomePesquisa);
    }
}
