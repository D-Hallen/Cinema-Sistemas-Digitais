package com.sisdist.cinema.api.controller;

import com.sisdist.cinema.api.model.Ingresso;
import com.sisdist.cinema.service.IngressoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ingressos")
public class IngressoController {

    private IngressoService ingressoService;

    @Autowired
    public IngressoController(IngressoService ingressoService){
        this.ingressoService = ingressoService;
    }

    @GetMapping
    public ResponseEntity<List<Ingresso>> listIngressos() {
        return ResponseEntity.ok(ingressoService.listIngressos());
    }

    // Buscar ingresso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Ingresso> getIngressoById(@PathVariable int id) {
        Optional<Ingresso> ingresso = ingressoService.findIngressoById(id);
        return ingresso.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Adicionar um novo ingresso
    @PostMapping
    public ResponseEntity<Ingresso> addIngresso(@RequestBody Ingresso ingresso) {
        Ingresso novoIngresso = ingressoService.saveIngresso(ingresso);
        return ResponseEntity.ok(novoIngresso);
    }

    // Atualizar um ingresso existente
    @PutMapping("/{id}")
    public ResponseEntity<Ingresso> updateIngresso(@PathVariable int id, @RequestBody Ingresso ingresso) {
        Optional<Ingresso> ingressoAtualizado = ingressoService.updateIngresso(id, ingresso);
        return ingressoAtualizado.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar um ingresso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngresso(@PathVariable int id) {
        try {
            ingressoService.deleteIngresso(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Alterar o status de um ingresso (vendido, disponível, etc.)
    @PatchMapping("/{id}/status")
    public ResponseEntity<Ingresso> updateStatus(@PathVariable int id, @RequestParam String novoStatus) {
        Optional<Ingresso> ingressoAlterado = ingressoService.updateStatus(id, novoStatus);
        return ingressoAlterado.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
