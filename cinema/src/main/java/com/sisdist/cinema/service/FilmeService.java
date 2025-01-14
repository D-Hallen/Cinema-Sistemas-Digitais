package com.sisdist.cinema.service;

import com.sisdist.cinema.api.model.Filme;
import com.sisdist.cinema.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;

    @Autowired
    public FilmeService(FilmeRepository filmeRepository){
        this.filmeRepository = filmeRepository;
    }
    public Optional<Filme> getFilmeByID(int id){
        return filmeRepository.findById(id);
    }

    public Filme saveFilme (Filme filme){
        return filmeRepository.save(filme);
    }

    public List<Filme> listFilmes (){
        return filmeRepository.findAll();
    }

    public void deleteFilme (int id){
        filmeRepository.deleteById(id);
    }

    //Busca por Titulo
    public List<Filme> buscarPorTitulo(String titulo) {
        return filmeRepository.findByTituloContainingIgnoreCase(titulo);
    }

    // Busca por gênero
    public List<Filme> buscarPorGenero(String genero) {
        return filmeRepository.findByGenerosContainingIgnoreCase(genero);
    }

    public Filme updateFilme (Filme filme, int id){
        Optional<Filme> filmeAntigo = filmeRepository.findById(id);
        if (filmeAntigo.isPresent()) {
            Filme filmeNew = filmeAntigo.get();
            filmeNew.updateDados(filme);
            return filmeRepository.save(filmeNew);
        } else {
            return null; // Caso não encontre o filme
        }
    }

}
