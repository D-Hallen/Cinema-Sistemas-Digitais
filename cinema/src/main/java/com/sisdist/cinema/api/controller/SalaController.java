package com.sisdist.cinema.api.controller;

import com.sisdist.cinema.api.model.Sala;
import com.sisdist.cinema.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/salas")
public class SalaController {
    private final SalaService salaService;

    @Autowired
    public SalaController(SalaService salaService){
        this.salaService = salaService;
    }

    @GetMapping
    public List<Sala> listSalas(){
        return salaService.listSalas();
    }

    @GetMapping("/{id}")
    public Optional<Sala> getSalaById(@PathVariable int id){
        return salaService.getSalaById(id);
    }

    @PostMapping
    public Sala saveSala(@RequestBody Sala sala){
        return salaService.saveSala(sala);
    }

    @PutMapping("/{id}")
    public Sala updateSala(@RequestBody Sala sala, @PathVariable int id){
        return salaService.updateSala(sala, id);
    }
    @DeleteMapping("/{id}")
    public void deleteSala(@PathVariable int id){
        salaService.deleteSala(id);
    }

}
