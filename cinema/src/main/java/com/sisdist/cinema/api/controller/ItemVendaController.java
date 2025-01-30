package com.sisdist.cinema.api.controller;

import com.sisdist.cinema.api.model.ItemVenda;
import com.sisdist.cinema.service.ItemVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendas/{vendaId}/itens")
public class ItemVendaController {
    @Autowired
    private ItemVendaService itemVendaService;

    // Lista todos os itens de uma venda
    @GetMapping
    public ResponseEntity<List<ItemVenda>> listarItensPorVenda(@PathVariable int vendaId) {
        List<ItemVenda> itens = itemVendaService.listarItensPorVenda(vendaId);
        return ResponseEntity.ok(itens);
    }

    // Busca um item por ID
    @GetMapping("/{id}")
    public ResponseEntity<ItemVenda> buscarItemPorId(@PathVariable int id) {
        Optional<ItemVenda> item = itemVendaService.buscarItemPorId(id);
        return item.map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Item não encontrado: ID " + id));
    }

    // Atualiza um item existente
    @PutMapping("/{id}")
    public ResponseEntity<ItemVenda> atualizarItem(
            @PathVariable int id,
            @RequestBody ItemVenda itemAtualizado
    ) {
        ItemVenda item = itemVendaService.atualizarItem(id, itemAtualizado);
        return ResponseEntity.ok(item);
    }

    // Exclui um item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirItem(@PathVariable int id) {
        itemVendaService.excluirItem(id);
        return ResponseEntity.noContent().build();
    }
}
