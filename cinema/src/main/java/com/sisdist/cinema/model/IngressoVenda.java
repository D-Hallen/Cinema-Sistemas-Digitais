package com.sisdist.cinema.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("INGRESSO")
public class IngressoVenda extends ItemVenda{
    @ManyToOne
    @JoinColumn(name = "ingresso_id", nullable = false)
    private Ingresso ingresso = null;

    public IngressoVenda() {
    }

    public IngressoVenda(Ingresso ingresso, int quantidade) {
        this.ingresso = ingresso;
        this.setQuantidade(quantidade);
        this.setPrecoUnitario(this.ingresso.getValor());
        this.setSubtotal(ingresso.getValor() * quantidade); // Calcula o subtotal
    }

    public Ingresso getIngresso() {
        return ingresso;
    }

    public void setIngresso(Ingresso ingresso) {
        this.ingresso = ingresso;
    }
}
