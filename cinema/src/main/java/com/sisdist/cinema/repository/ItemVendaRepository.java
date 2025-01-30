package com.sisdist.cinema.repository;

import com.sisdist.cinema.api.model.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda, Integer> {
    List<ItemVenda>findVendaById(int vendaId);
}
