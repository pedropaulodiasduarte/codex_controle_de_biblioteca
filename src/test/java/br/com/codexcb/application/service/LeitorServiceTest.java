package br.com.codexcb.application.service;

import br.com.codexcb.application.dao.LeitorDAO;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class LeitorServiceTest {
    private  LeitorService leitorService;
    @BeforeEach
    void setup(){
        leitorService = new LeitorService(new LeitorDAO());
    }


}