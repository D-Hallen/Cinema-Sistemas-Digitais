package com.sisdist.cinema.repository;

import com.sisdist.cinema.model.Reserva;
import com.sisdist.cinema.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    Optional<Reserva> findBySessaoAndLugar (Sessao sessao, Integer lugar);
}
