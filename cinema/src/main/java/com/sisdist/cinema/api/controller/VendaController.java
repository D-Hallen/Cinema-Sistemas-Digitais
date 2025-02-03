package com.sisdist.cinema.api.controller;

import com.sisdist.cinema.api.model.ItemVenda;
import com.sisdist.cinema.api.model.Usuario;
import com.sisdist.cinema.api.model.Venda;
import com.sisdist.cinema.api.request.VendaRequest;
import com.sisdist.cinema.api.rmi.VendaRMI;
import com.sisdist.cinema.service.VendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;
    private VendaRMI vendaServiceRMI;

    public VendaController() {
        try {
            // Conecta ao registro RMI (mesma JVM, localhost)
            //WIP WIP
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            vendaServiceRMI = (VendaRMI) registry.lookup("VendaService");
        } catch (Exception e) {
            System.err.println("Erro ao conectar ao serviço RMI:");
            e.printStackTrace();
        }
    }

    // Cria uma nova venda
    @PostMapping
    public ResponseEntity<Venda> criarVenda(@Valid @RequestBody VendaRequest vendaRequest) {
        try {
            Venda vendaRegistrada = vendaServiceRMI.registrarVenda(vendaRequest);
            return ResponseEntity.ok(vendaRegistrada);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
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