package com.sisdist.cinema.api.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public class ItemVendaRequest {
    @Positive(message = "O ID do produto deve ser positivo.")
    private Integer produtoId; // ID do produto (pode ser nulo se for um ingresso)
    @Positive(message = "O ID do ingresso deve ser positivo.")
    private Integer ingressoId; // ID do ingresso (pode ser nulo se for um produto)
    @Min(value = 1, message = "A quantidade deve ser pelo menos 1.")
    private int quantidade; // Quantidade do item

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getIngressoId() {
        return ingressoId;
    }

    public void setIngressoId(Integer ingressoId) {
        this.ingressoId = ingressoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public boolean isValid() {
        return (produtoId != null && ingressoId == null) || (produtoId == null && ingressoId != null);
    }
}
