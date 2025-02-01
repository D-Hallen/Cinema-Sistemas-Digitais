package com.sisdist.cinema.api.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("INGRESSO")
public class IngressoVenda extends ItemVenda{
    @ManyToOne
    @JoinColumn(name = "ingresso_id", nullable = false)
    private Ingresso ingresso;

    public Ingresso getIngresso() {
        return ingresso;
    }

    public void setIngresso(Ingresso ingresso) {
        this.ingresso = ingresso;
    }
}
