package com.sisdist.cinema.api.rmi;

import com.sisdist.cinema.api.model.Venda;
import com.sisdist.cinema.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class VendaRepositoryImpl {
    private final VendaRepository vendaRepository;

    @Autowired
    public VendaRepositoryImpl(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    public Venda save(Venda venda) {
        return vendaRepository.save(venda);
    }

    public List<Venda> findByUsuarioId(int usuarioId) {
        return vendaRepository.findByUsuarioId(usuarioId);
    }
}
