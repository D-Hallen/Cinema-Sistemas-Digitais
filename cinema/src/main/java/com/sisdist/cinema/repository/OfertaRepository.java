package com.sisdist.cinema.repository;

import com.sisdist.cinema.model.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Integer> {
    List<Oferta> findByProdutoId(int produtoId);
}
