package com.sisdist.cinema.api.request;


import java.util.List;

public class VendaRequest {
    private int usuarioId; // ID do usuário que fez a venda
    private List<ItemRequest> produtos; // Lista de produtos
    private List<ItemRequest> ingressos; // Lista de ingressos

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<ItemRequest> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ItemRequest> produtos) {
        this.produtos = produtos;
    }

    public List<ItemRequest> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<ItemRequest> ingressos) {
        this.ingressos = ingressos;
    }

    public boolean isValid() {
        return (produtos != null && !produtos.isEmpty()) || (ingressos != null && !ingressos.isEmpty());
    }
}
