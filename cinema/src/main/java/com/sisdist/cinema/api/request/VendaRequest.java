package com.sisdist.cinema.api.request;


import java.util.List;

public class VendaRequest {
    private int usuarioId; // ID do usuário que fez a venda
    private List<ProdutoRequest> produtos; // Lista de produtos
    private List<IngressoRequest> ingressos; // Lista de ingressos

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<ProdutoRequest> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoRequest> produtos) {
        this.produtos = produtos;
    }

    public List<IngressoRequest> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<IngressoRequest> ingressos) {
        this.ingressos = ingressos;
    }

    public boolean isValid() {
        return (produtos != null && !produtos.isEmpty()) || (ingressos != null && !ingressos.isEmpty());
    }
}
