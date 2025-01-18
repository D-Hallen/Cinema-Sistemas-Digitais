package com.sisdist.cinema.api.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Fidelidade {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    private String nome;
    private double valorMensalidade;
    private int pontuacao;
    private LocalDate dataAdesao;
    private LocalDate dataValidade;
    private String status;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Fidelidade() {
    }

    public Fidelidade(String nome, double valorMensalidade, int pontuacao, LocalDate dataAdesao, LocalDate dataValidade, String status, Usuario usuario) {
        this.nome = nome;
        this.valorMensalidade = valorMensalidade;
        this.pontuacao = pontuacao;
        this.dataAdesao = dataAdesao;
        this.dataValidade = dataValidade;
        this.status = status;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorMensalidade() {
        return valorMensalidade;
    }

    public void setValorMensalidade(double valorMensalidade) {
        this.valorMensalidade = valorMensalidade;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public LocalDate getDataAdesao() {
        return dataAdesao;
    }

    public void setDataAdesao(LocalDate dataAdesao) {
        this.dataAdesao = dataAdesao;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
