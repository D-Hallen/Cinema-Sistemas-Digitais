package com.sisdist.cinema.api.request;

public class IngressoRequest {
    private int ingressoId;
    private int quantidade;

    public int getIngressoId() {
        return ingressoId;
    }

    public void setIngressoId(int ingressoId) {
        this.ingressoId = ingressoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
