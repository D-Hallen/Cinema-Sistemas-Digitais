package com.sisdist.cinema.api.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    private List<Produto> produtos;
    
    private List<Ingresso> ingressos;

    private String formaPagamento;
    private LocalDateTime dataVenda;
    private Double valorTotal;
    private String status;
}
