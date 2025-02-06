package com.sisdist.cinema.repository;

import com.sisdist.cinema.model.Fidelidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FidelidadeRepository extends JpaRepository<Fidelidade, Integer> {
    public List<Fidelidade> findByUsuarioId(int id);
}
