package com.sisdist.cinema.controller;


import com.sisdist.cinema.model.Fidelidade;
import com.sisdist.cinema.rmi.Implementation.FidelidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fidelidades")
public class FidelidadeController {

    private final FidelidadeService fidelidadeService;

    @Autowired
    public FidelidadeController(FidelidadeService fidelidadeService){
        this.fidelidadeService = fidelidadeService;
    }

    // Listar todas as fidelidades
    @GetMapping
    public ResponseEntity<List<Fidelidade>> listFidelidades() {
        List<Fidelidade> fidelidades = fidelidadeService.listFidelidades();
        return ResponseEntity.ok(fidelidades);
    }

    // Buscar fidelidade por ID
    @GetMapping("/{id}")
    public ResponseEntity<Fidelidade> getFidelidadeById(@PathVariable int id) {
        Optional<Fidelidade> fidelidade = fidelidadeService.getFidelidadeById(id);
        return fidelidade.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Buscar fidelidades por usuário
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Fidelidade>> getFidelidadesByUsuario(@PathVariable int usuarioId) {
        List<Fidelidade> fidelidades = fidelidadeService.getFidelidadesByUsuario(usuarioId);
        if (fidelidades.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(fidelidades);
    }

    // Criar uma nova fidelidade
    @PostMapping
    public ResponseEntity<Fidelidade> createFidelidade(@RequestBody Fidelidade fidelidade) {
        Fidelidade novaFidelidade = fidelidadeService.createFidelidade(fidelidade);
        return ResponseEntity.status(201).body(novaFidelidade);
    }

    // Atualizar uma fidelidade existente
    @PutMapping("/{id}")
    public ResponseEntity<Fidelidade> updateFidelidade(@PathVariable int id, @RequestBody Fidelidade fidelidade) {
        try {
            Fidelidade updatedFidelidade = fidelidadeService.updateFidelidade(id, fidelidade);
            return ResponseEntity.ok(updatedFidelidade);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar uma fidelidade
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFidelidade(@PathVariable int id) {
        try {
            fidelidadeService.deleteFidelidade(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
