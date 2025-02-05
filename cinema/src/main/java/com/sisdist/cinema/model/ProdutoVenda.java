package com.sisdist.cinema.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("PRODUTO")
public class ProdutoVenda extends ItemVenda {
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = true)
    private Produto produto;

    public ProdutoVenda() {
    }

    public ProdutoVenda(Produto produto, int quantidade) {
        this.produto = produto;
        this.setQuantidade(quantidade);
        this.setPrecoUnitario(this.produto.getValor());
        this.setSubtotal(produto.getValor() * quantidade); // Calcula o subtotal
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
