package com.sisdist.cinema.service;

import com.sisdist.cinema.api.model.Ingresso;
import com.sisdist.cinema.repository.IngressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngressoService {

    private final IngressoRepository ingressoRepository;

    @Autowired
    public IngressoService(IngressoRepository ingressoRepository){
        this.ingressoRepository=ingressoRepository;
    }

    public Optional<Ingresso> findIngressoById (int id){
        return ingressoRepository.findById(id);
    }

    public Ingresso saveIngresso (Ingresso ingresso){
        return ingressoRepository.save(ingresso);
    }

    public List<Ingresso> listIngressos (){
        return ingressoRepository.findAll();
    }

    public void deleteIngresso (int id){
        ingressoRepository.deleteById(id);
    }

    public Optional<Ingresso> updateIngresso (int id,Ingresso ingressoAtualizado){
        Optional<Ingresso> ingressoExistente = ingressoRepository.findById(id);
        if (ingressoExistente.isPresent()) {
            Ingresso ingresso = ingressoExistente.get();
            ingresso.setNumeroLugar(ingressoAtualizado.getNumeroLugar());
            ingresso.setTipo(ingressoAtualizado.getTipo());
            ingresso.setValor(ingressoAtualizado.getValor());
            ingresso.setStatus(ingressoAtualizado.getStatus());
            ingresso.setSessao(ingressoAtualizado.getSessao());
            ingressoRepository.save(ingresso);
            return Optional.of(ingresso);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Ingresso> updateStatus(int id, String novoStatus){
        Optional<Ingresso> ingressoExistente = ingressoRepository.findById(id);
        if (ingressoExistente.isPresent()) {
            Ingresso ingresso = ingressoExistente.get();
            ingresso.setStatus(novoStatus);
            ingressoRepository.save(ingresso);
            return Optional.of(ingresso);
        } else {
            return Optional.empty();
        }
    }
}
