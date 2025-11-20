package br.com.codexcb.application.service;

import br.com.codexcb.application.dao.LeitorRepository;
import br.com.codexcb.application.model.Usuario;

public class LeitorService {
    private final LeitorRepository leitorRepository;

    //construtor
    public LeitorService(LeitorRepository leitorRepository) {
        this.leitorRepository = leitorRepository;
    }

    public boolean cadastrarLeitor(Usuario leitor) {
        return this.leitorRepository.cadastrarLeitor(leitor);
    }
}
