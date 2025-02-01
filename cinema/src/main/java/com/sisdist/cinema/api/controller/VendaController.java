package com.sisdist.cinema.api.controller;

import com.sisdist.cinema.api.model.ItemVenda;
import com.sisdist.cinema.api.model.Usuario;
import com.sisdist.cinema.api.model.Venda;
import com.sisdist.cinema.api.request.VendaRequest;
import com.sisdist.cinema.service.VendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    // Cria uma nova venda
    @PostMapping
    public ResponseEntity<Venda> criarVenda(@Valid @RequestBody VendaRequest vendaRequest) {
        //System.out.println(vendaRequest);
        Venda venda = vendaService.saveVenda(vendaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(venda);
    }

    // Busca uma venda por ID
    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarVendaPorId(@PathVariable int id) {
        Venda venda = vendaService.getVendaById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));
        return ResponseEntity.ok(venda);
    }

    // Lista todas as vendas
    @GetMapping
    public ResponseEntity<List<Venda>> listarTodasVendas() {
        List<Venda> vendas = vendaService.listVendas();
        return ResponseEntity.ok(vendas);
    }

    // Atualiza uma venda existente
    @PutMapping("/{id}")
    public ResponseEntity<Venda> atualizarVenda(
            @PathVariable int id,
            @RequestBody Venda venda
    ) {
        Venda vendaAtualizada = vendaService.updateVenda(venda, id);
        return ResponseEntity.ok(vendaAtualizada);
    }

    // Exclui uma venda
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirVenda(@PathVariable int id) {
        vendaService.deleteVenda(id);
        return ResponseEntity.noContent().build();
    }
}