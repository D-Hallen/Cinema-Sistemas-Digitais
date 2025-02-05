package com.sisdist.cinema.controller;

import com.sisdist.cinema.model.Sala;
import com.sisdist.cinema.rmi.Service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @GetMapping
    public ResponseEntity<List<Sala>> getAllSalas() throws RemoteException {
        List<Sala> salas = salaService.getAllSalas();
        return ResponseEntity.ok(salas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> getSalaById(@PathVariable int id) throws RemoteException {
        Optional<Sala> sala = salaService.getSalaById(id);
        return sala.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).build());
    }

    @PostMapping
    public ResponseEntity<Sala> createSala(@RequestBody Sala sala) throws RemoteException {
        Sala createdSala = salaService.createSala(sala);
        return ResponseEntity.ok(createdSala);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sala> updateSala(@PathVariable int id, @RequestBody Sala sala) throws RemoteException {
        Sala updatedSala = salaService.updateSala(id, sala);
        if (updatedSala != null) {
            return ResponseEntity.ok(updatedSala);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSala(@PathVariable int id) throws RemoteException {
        salaService.deleteSala(id);
        return ResponseEntity.noContent().build();
    }
}
