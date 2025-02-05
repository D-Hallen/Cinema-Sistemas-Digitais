package com.sisdist.cinema.controller;

import com.sisdist.cinema.model.Ingresso;
import com.sisdist.cinema.rmi.Service.IngressoService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.RemoteException;
import java.util.List;


@RestController
@RequestMapping("/ingressos")
public class IngressoController {

    private final IngressoService ingressoService;

    public IngressoController(@Qualifier("localIngressoService") IngressoService ingressoService) {
        this.ingressoService = ingressoService;
    }

    @PostMapping
    public ResponseEntity<Ingresso> createIngresso(@RequestBody Ingresso ingresso) throws RemoteException {
        Ingresso novoIngresso = ingressoService.createIngresso(ingresso);
        return ResponseEntity.ok(novoIngresso);
    }

    @GetMapping
    public ResponseEntity<List<Ingresso>> getAllIngressos() throws RemoteException {
        return ResponseEntity.ok(ingressoService.listIngressos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngresso(@PathVariable int id) {
        try {
            ingressoService.deleteIngresso(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
