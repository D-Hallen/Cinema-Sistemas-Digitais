package com.sisdist.cinema.api.controller;


import com.sisdist.cinema.api.model.Oferta;
import com.sisdist.cinema.service.OfertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oferta")
public class OfertaController {

    private final OfertaService ofertaService;

    @Autowired
    public OfertaController (OfertaService ofertaService){
        this.ofertaService = ofertaService;
    }

    // Listar todas as ofertas
    @GetMapping
    public ResponseEntity<List<Oferta>> listarOfertas() {
        List<Oferta> ofertas = ofertaService.listOfertas();
        return ResponseEntity.ok(ofertas);
    }

    // Buscar uma oferta específica pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Oferta> buscarOfertaPorId(@PathVariable int id) {
        return ofertaService.findOfertaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Adicionar uma nova oferta
    @PostMapping
    public ResponseEntity<Oferta> salvarOferta(@RequestBody Oferta oferta) {
        Oferta novaOferta = ofertaService.salveOferta(oferta);
        return ResponseEntity.ok(novaOferta);
    }

    // Atualizar uma oferta existente
    @PutMapping("/{id}")
    public ResponseEntity<Oferta> atualizarOferta(@PathVariable int id, @RequestBody Oferta oferta) {
        try {
            Oferta ofertaAtualizada = ofertaService.updateOferta(id, oferta);
            return ResponseEntity.ok(ofertaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar uma oferta pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOferta(@PathVariable int id) {
        ofertaService.deleteOferta(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar ofertas por ID do produto
    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<Oferta>> findOfertaByProdutoId(@PathVariable int produtoId) {
        List<Oferta> ofertas = ofertaService.findByProdutoId(produtoId);
        if (!ofertas.isEmpty()) {
            return ResponseEntity.ok(ofertas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
