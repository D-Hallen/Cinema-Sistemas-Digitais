package com.sisdist.cinema.repository;

import com.sisdist.cinema.api.model.Ingresso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Integer> {
}
