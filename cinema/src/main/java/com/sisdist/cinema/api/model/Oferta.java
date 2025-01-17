package com.sisdist.cinema.api.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    private String descricao;

    private Double percentualDesconto;

    public Oferta() {
    }

    public Oferta(Produto produto, LocalDate dataInicio, LocalDate dataFim, String descricao, Double percentualDesconto) {
        this.produto = produto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.descricao = descricao;
        this.percentualDesconto = percentualDesconto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPercentualDesconto() {
        return percentualDesconto;
    }

    public void setPercentualDesconto(Double percentualDesconto) {
        this.percentualDesconto = percentualDesconto;
    }

}
