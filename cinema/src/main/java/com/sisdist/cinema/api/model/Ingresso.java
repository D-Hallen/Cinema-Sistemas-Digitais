package com.sisdist.cinema.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Ingresso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "sessao_id", nullable = false)
    @JsonIgnore // Evita loop se Sessao tiver lista de ingressos
    private Sessao sessao;

    private int numeroLugar;
    private String tipo;
    private double valor;
    private String status;

    public Ingresso() {
    }

    public Ingresso(Sessao sessao, int numeroLugar, String tipo, double valor, String status) {
        this.sessao = sessao;
        this.numeroLugar = numeroLugar;
        this.tipo = tipo; //Inteira - Meia
        this.valor = valor;
        this.status = status; // Disponivel - Utilizado
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public int getNumeroLugar() {
        return numeroLugar;
    }

    public void setNumeroLugar(int numeroLugar) {
        this.numeroLugar = numeroLugar;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
