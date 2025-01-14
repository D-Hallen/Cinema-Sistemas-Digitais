package com.sisdist.cinema.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int numero;
    private int capacidade;
    private String tipo;
    private String status;

    public Sala() {
    }

    public Sala(int numero, int capacidade, String tipo, String status) {
        this.numero = numero;
        this.capacidade = capacidade;
        this.tipo = tipo;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void updateDados(Sala novaSala){
        this.numero = novaSala.getNumero();
        this.capacidade = novaSala.getCapacidade();
        this.tipo = novaSala.getTipo();
        this.status = novaSala.getStatus();
    }
}
