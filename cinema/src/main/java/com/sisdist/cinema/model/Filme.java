package com.sisdist.cinema.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Filme implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titulo;
    private String sinopse;
    private LocalDate lancamento;
    private double nota;
    private int duracao;
    private String classificacao; //L - PG-13 - PG-16 - M

    @ElementCollection
    @CollectionTable(name = "filme_generos", joinColumns = @JoinColumn(name = "filme_id"))
    @Column(name = "genero")
    private List<String> generos;

    public Filme(String titulo, String sinopse, LocalDate lancamento, double nota, int duracao, String classificacao) {
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.lancamento = lancamento;
        this.nota = nota;
        this.duracao = duracao;
        this.classificacao = classificacao;
    }

    public Filme(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public LocalDate getLancamento() {
        return lancamento;
    }

    public void setLancamento(LocalDate lancamento) {
        this.lancamento = lancamento;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public void updateDados(Filme filme) {
        this.titulo = filme.getTitulo();
        this.sinopse = filme.getSinopse();
        this.lancamento = filme.getLancamento();
        this.nota = filme.getNota();
        this.duracao = filme.getDuracao();
        this.classificacao = filme.getClassificacao();
        this.generos = filme.getGeneros();
    }
}
