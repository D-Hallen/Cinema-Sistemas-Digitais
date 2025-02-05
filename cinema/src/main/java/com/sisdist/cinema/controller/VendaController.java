package com.sisdist.cinema.controller;

import com.sisdist.cinema.model.ItemVenda;
import com.sisdist.cinema.model.Venda;
import com.sisdist.cinema.model.VendaRequest;
import com.sisdist.cinema.rmi.Service.VendaService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendas")
public class VendaController {
    private VendaService vendaService;

    @Autowired
    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping
    public ResponseEntity<Venda> saveVenda(@RequestBody VendaRequest vendaRequest) {
        try {
                Venda vendaRegistrada = vendaService.saveVenda(vendaRequest);
            return ResponseEntity.ok(vendaRegistrada);
        } catch (RemoteException e) {
            return ResponseEntity.internalServerError().body(null); // Ou inclua uma mensagem de erro
        }
    }

    // Busca uma venda por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> searchVendaById(@PathVariable int id) {
        try {
            Optional<Venda> vendaOptional = vendaService.getVendaById(id);
            if (vendaOptional.isPresent()) {
                return ResponseEntity.ok(vendaOptional.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venda não encontrada: ID " + id);
            }
        } catch (RemoteException e) {
            return ResponseEntity.internalServerError().body("Erro ao buscar venda: " + e.getMessage());
        }
    }

    // Lista todas as vendas
    @GetMapping
    public ResponseEntity<List<Venda>> listAllVendas() {
        List<Venda> vendas = null;
        try {
            vendas = vendaService.listVendas();
            return ResponseEntity.ok(vendas);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    // Atualiza uma venda existente
    @PutMapping("/{id}")
    public ResponseEntity<Venda> updateVenda(
            @PathVariable int id,
            @RequestBody Venda venda
    ) {
        Venda vendaAtualizada = null;
        try {
            vendaAtualizada = vendaService.updateVenda(venda, id);
            return ResponseEntity.ok(vendaAtualizada);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    // Exclui uma venda
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVenda(@PathVariable int id) {
        try {
            vendaService.deleteVenda(id);
            return ResponseEntity.noContent().build();
        } catch (RemoteException e) {
            return ResponseEntity.internalServerError().body("Erro ao excluir venda: " + e.getMessage());
        }
    }

}
