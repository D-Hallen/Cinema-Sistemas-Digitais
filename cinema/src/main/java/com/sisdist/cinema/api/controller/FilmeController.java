package com.sisdist.cinema.api.controller;

import com.sisdist.cinema.api.model.Filme;
import com.sisdist.cinema.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private FilmeService filmeService;

    @Autowired
    public FilmeController(FilmeService filmeService){
        this.filmeService = filmeService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Filme> getFilmeById(@PathVariable int id) {
        Optional<Filme> filme = filmeService.getFilmeByID(id);
        if (filme.isPresent()){
            return ResponseEntity.ok(filme.get());
        }else {
        return ResponseEntity.status(404).body(null);
        }
    }
    // Busca por título (parcial)
    @GetMapping("/buscar-titulo")
    public ResponseEntity<List<Filme>> searchByTitulo(@RequestParam String titulo) {
        List<Filme> filmes = filmeService.buscarPorTitulo(titulo);
        if (!filmes.isEmpty()) {
            return ResponseEntity.ok(filmes);
        }
        return ResponseEntity.status(404).body(filmes); // Lista vazia, mas semanticamente indicamos não encontrado
    }

    // Busca por gênero
    @GetMapping("/buscar-genero")
    public ResponseEntity<List<Filme>> searchByGenero(@RequestParam String genero) {
        List<Filme> filmes = filmeService.buscarPorGenero(genero);
        if (!filmes.isEmpty()) {
            return ResponseEntity.ok(filmes);
        }
        return ResponseEntity.status(404).body(filmes);
    }

    // Adiciona um novo filme
    @PostMapping
    public ResponseEntity<Filme> addFilme(@RequestBody Filme filme) {
        Filme novoFilme = filmeService.saveFilme(filme);
        return ResponseEntity.status(201).body(novoFilme); // HTTP 201 Created
    }

    // Atualiza um filme existente
    @PutMapping("/{id}")
    public ResponseEntity<Filme> atualizarFilme(@PathVariable int id, @RequestBody Filme filme) {
        Filme atualizado = filmeService.updateFilme(filme, id);
        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        }
        return ResponseEntity.status(404).body(null); // Filme não encontrado
    }

    // Deleta um filme
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilme(@PathVariable int id) {
        Optional<Filme> filme = filmeService.getFilmeByID(id);
        if (filme.isPresent()) {
            filmeService.deleteFilme(id);
            return ResponseEntity.noContent().build(); // HTTP 204 No Content
        }
        return ResponseEntity.status(404).build(); // Filme não encontrado
    }

    // Retorna todos os filmes
    @GetMapping
    public ResponseEntity<List<Filme>> getAllFilmes() {
        List<Filme> filmes = filmeService.listFilmes();
        return ResponseEntity.ok(filmes);
    }
}
