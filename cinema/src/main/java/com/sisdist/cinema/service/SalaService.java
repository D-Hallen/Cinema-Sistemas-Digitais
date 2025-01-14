package com.sisdist.cinema.service;

import com.sisdist.cinema.api.model.Sala;
import com.sisdist.cinema.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {
    private final SalaRepository salaRepository;

    @Autowired
    public SalaService(SalaRepository salaRepository){
        this.salaRepository = salaRepository;
    }

    public List<Sala> listSalas(){
        return salaRepository.findAll();
    }

    public Optional<Sala> getSalaById(int id){
        return salaRepository.findById(id);
    }

    public Sala saveSala(Sala sala){
        return salaRepository.save(sala);
    }

    public Sala updateSala(Sala sala, int id){
        Optional<Sala> salaAntiga = salaRepository.findById(id);
        if (salaAntiga.isPresent()){
            Sala newSala = salaAntiga.get();
            newSala.updateDados(sala);
            return salaRepository.save(newSala);
        }
        return null;
    }

    public void deleteSala(int id){
        salaRepository.deleteById(id);
    }
}
