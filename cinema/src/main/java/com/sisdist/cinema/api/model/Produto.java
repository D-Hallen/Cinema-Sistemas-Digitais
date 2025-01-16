package com.sisdist.cinema.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String descricao;
    private Double valor;
    private int qtdDisp;

    public Produto() {
    }

    public Produto(String nome, String descricao, Double valor, int qtdDisp) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.qtdDisp = qtdDisp;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getQtdDisp() {
        return qtdDisp;
    }

    public void setQtdDisp(int qtdDisp) {
        this.qtdDisp = qtdDisp;
    }

    public boolean reduzirQtd(int qtd){
        if (qtdDisp >= qtd){
            qtdDisp -= qtd;
            return true;
        } else {
            return false;
        }
    }
}
