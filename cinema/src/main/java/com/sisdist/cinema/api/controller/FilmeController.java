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
    public ResponseEntity<Filme> getFilme(@RequestParam int id){
        Optional<Filme> filme = filmeService.getFilmeByID(id);
        if (filme.isPresent()){
            return ResponseEntity.ok(filme.get());
        } else{
            return ResponseEntity.status(404).body(null);
        }
    }

    // Busca por título (parcial)
    @GetMapping("/buscar-titulo")
    public List<Filme> searchByTitulo(@RequestParam String titulo) {
        return filmeService.buscarPorTitulo(titulo);
    }

    // Busca por gênero
    @GetMapping("/buscar-genero")
    public List<Filme> searchByGenero(@RequestParam String genero) {
        return filmeService.buscarPorGenero(genero);
    }

    // Adiciona um novo filme
    @PostMapping
    public Filme addFilme(@RequestBody Filme filme) {
        return filmeService.saveFilme(filme);
    }

    // Atualiza um filme existente
    @PutMapping("/{id}")
    public Filme atualizarFilme(@PathVariable int id, @RequestBody Filme filme) {
        return filmeService.updateFilme(filme, id);
    }

    // Deleta um filme
    @DeleteMapping("/{id}")
    public void deleteFilme(@PathVariable int id) {
        filmeService.deleteFilme(id);
    }

    // Retorna todos os filmes
    @GetMapping
    public List<Filme> getAllFilmes() {
        return filmeService.listFilmes();
    }
}
