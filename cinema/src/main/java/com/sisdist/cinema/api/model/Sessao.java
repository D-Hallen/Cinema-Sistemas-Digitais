package com.sisdist.cinema.api.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Sessao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "filme_id", nullable = false)
    private Filme filme;

    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;

    private double valorIngresso;
    private LocalDateTime dataHora;
    private String idioma;

    @ElementCollection
    private List<Integer> lugaresDisponiveis;

    public Sessao() {
    }

    public Sessao(Filme filme, Sala sala, double valorIngresso, LocalDateTime dataHora, String idioma, List<Integer> lugaresDisponiveis) {
        this.filme = filme;
        this.sala = sala;
        this.valorIngresso = valorIngresso;
        this.dataHora = dataHora;
        this.idioma = idioma;
        this.lugaresDisponiveis = lugaresDisponiveis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public double getValorIngresso() {
        return valorIngresso;
    }

    public void setValorIngresso(double valorIngresso) {
        this.valorIngresso = valorIngresso;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public List<Integer> getLugaresDisponiveis() {
        return lugaresDisponiveis;
    }

    public void setLugaresDisponiveis(List<Integer> lugaresDisponiveis) {
        this.lugaresDisponiveis = lugaresDisponiveis;
    }

}
