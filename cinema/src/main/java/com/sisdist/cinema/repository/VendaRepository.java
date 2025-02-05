package com.sisdist.cinema.repository;

import com.sisdist.cinema.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Integer> {
    List<Venda> findByUsuarioId(int userId);
}
