package com.sisdist.cinema.repository;

import com.sisdist.cinema.api.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Integer> {

}
