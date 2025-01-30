package com.sisdist.cinema.api.request;


import java.util.List;

public class VendaRequest {
    private int usuarioId; // ID do usuário que fez a venda
    private List<ItemVendaRequest> itens; // Lista de itens da venda

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<ItemVendaRequest> getItens() {
        return itens;
    }

    public void setItens(List<ItemVendaRequest> itens) {
        this.itens = itens;
    }
}
