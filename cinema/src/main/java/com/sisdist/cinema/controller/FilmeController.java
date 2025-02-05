package com.sisdist.cinema.controller;

import com.sisdist.cinema.model.Filme;
import com.sisdist.cinema.rmi.Service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.RemoteException;
import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping
    public ResponseEntity<List<Filme>> getAllFilmes() throws RemoteException {
        List<Filme> filmes = filmeService.getAllFilmes();
        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> getFilmeById(@PathVariable int id) throws RemoteException {
        Filme filme = filmeService.getFilmeById(id);
        if (filme != null) {
            return ResponseEntity.ok(filme);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Filme> createFilme(@RequestBody Filme filme) throws RemoteException {
        Filme createdFilme = filmeService.createFilme(filme);
        return ResponseEntity.ok(createdFilme);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filme> updateFilme(@PathVariable int id, @RequestBody Filme filme) throws RemoteException {
        Filme updatedFilme = filmeService.updateFilme(id, filme);
        if (updatedFilme != null) {
            return ResponseEntity.ok(updatedFilme);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilme(@PathVariable int id) throws RemoteException {
        filmeService.deleteFilme(id);
        return ResponseEntity.noContent().build();
    }
}
