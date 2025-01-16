package com.sisdist.cinema.service;

import com.sisdist.cinema.api.model.Produto;
import com.sisdist.cinema.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public Produto saveProduto (Produto produto){
        return produtoRepository.save(produto);
    }

    public List<Produto> listProdutos (){
        return produtoRepository.findAll();
    }

    public Optional<Produto> findProdutoById (int id){
        return produtoRepository.findById(id);
    }

    public void deleteProduto (int id){
        produtoRepository.deleteById(id);
    }

    public Produto updateProduto(Produto produto, int id) {
        Optional<Produto> produtoAntigo = produtoRepository.findById(id);
        if (produtoAntigo.isPresent()) {
            Produto produtoAtualizado = produtoAntigo.get();
            produtoAtualizado.setNome(produto.getNome());
            produtoAtualizado.setValor(produto.getValor());
            produtoAtualizado.setDescricao(produto.getDescricao());
            produtoAtualizado.setQtdDisp(produto.getQtdDisp());
            return produtoRepository.save(produtoAtualizado);
        } else {
            return null; // Caso não encontre o produto
        }
    }

    public boolean reduzirQuantidade(int id, int quantidade) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);
        if (produtoOpt.isPresent()) {
            Produto produto = produtoOpt.get();
            try {
                produto.reduzirQtd(quantidade);
                produtoRepository.save(produto);
                return true;
            } catch (IllegalArgumentException e) {
                // Estoque insuficiente ou erro de lógica
                return false;
            }
        }
        return false;
    }

}
