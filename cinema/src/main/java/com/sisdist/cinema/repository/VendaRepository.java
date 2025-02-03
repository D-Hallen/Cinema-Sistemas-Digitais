package com.sisdist.cinema.repository;

import com.sisdist.cinema.api.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer> {
    List<Venda> findByUsuarioId(int userId);
}
