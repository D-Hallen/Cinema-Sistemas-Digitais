package com.sisdist.cinema.api.controller;

import com.sisdist.cinema.api.model.Sala;
import com.sisdist.cinema.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Sala>> listSalas() {
        List<Sala> salas = salaService.listSalas();
        if (!salas.isEmpty()) {
            return ResponseEntity.ok(salas);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> getSalaById(@PathVariable int id) {
        Optional<Sala> sala = salaService.getSalaById(id);
        return sala.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).build());
    }

    @PostMapping
    public ResponseEntity<Sala> saveSala(@RequestBody Sala sala) {
        Sala novaSala = salaService.saveSala(sala);
        return ResponseEntity.status(201).body(novaSala);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sala> updateSala(@RequestBody Sala sala, @PathVariable int id) {
        Sala salaAtualizada = salaService.updateSala(sala, id);
        if (salaAtualizada != null) {
            return ResponseEntity.ok(salaAtualizada);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSala(@PathVariable int id) {
        Optional<Sala> sala = salaService.getSalaById(id);
        if (sala.isPresent()) {
            salaService.deleteSala(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

}
