package com.sisdist.cinema.repository;

import com.sisdist.cinema.api.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Integer> {

    List<Filme> findByTituloContainingIgnoreCase(String titulo);

    // Busca por gênero (usando o @ElementCollection no atributo 'generos')
    List<Filme> findByGenerosContainingIgnoreCase(String genero);

}
