package com.sisdist.cinema.controller;


import com.sisdist.cinema.model.Estoque;
import com.sisdist.cinema.rmi.Implementation.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estoques")
public class EstoqueController {
    private final EstoqueService estoqueService;

    @Autowired
    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @GetMapping
    public ResponseEntity<List<Estoque>> listarEstoques() {
        return ResponseEntity.ok(estoqueService.listEstoques());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estoque> buscarEstoquePorId(@PathVariable int id) {
        Optional<Estoque> estoque = estoqueService.findEstoqueById(id);
        return estoque.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Estoque> salvarEstoque(@RequestBody Estoque estoque) {
        Estoque novoEstoque = estoqueService.salveEstoque(estoque);
        return ResponseEntity.ok(novoEstoque);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estoque> atualizarEstoque(@PathVariable int id, @RequestBody Estoque estoque) {
        try {
            Estoque estoqueAtualizado = estoqueService.updateEstoque(id, estoque);
            return ResponseEntity.ok(estoqueAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEstoque(@PathVariable int id) {
        try {
            estoqueService.deleteEstoque(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/reduzir")
    public ResponseEntity<String> reduzirQuantidade(@PathVariable int id, @RequestParam int quantidade) {
        try {
            estoqueService.reduzirQuantidade(id, quantidade);
            return ResponseEntity.ok("Quantidade reduzida com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
