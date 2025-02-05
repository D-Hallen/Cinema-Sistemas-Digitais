package com.sisdist.cinema.rmi.Implementation;

import com.sisdist.cinema.model.Produto;
import com.sisdist.cinema.repository.ProdutoRepository;
import com.sisdist.cinema.rmi.Service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;

@Service("produtoService")
public class ProdutoServiceImpl extends UnicastRemoteObject implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoServiceImpl(ProdutoRepository produtoRepository) throws RemoteException {
        super();
        this.produtoRepository = produtoRepository;
    }


    public Produto saveProduto (Produto produto) throws RemoteException{
        return produtoRepository.save(produto);
    }
    public List<Produto> listProdutos () throws RemoteException{
        return produtoRepository.findAll();
    }
    public Optional<Produto> findProdutoById (int id) throws RemoteException{
        return produtoRepository.findById(id);
    }
    public void deleteProduto (int id) throws RemoteException{
        produtoRepository.deleteById(id);
    }
    public Produto updateProduto(Produto produto, int id) throws RemoteException{
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
    public boolean reduzirQuantidade(int id, int quantidade) throws RemoteException{
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
