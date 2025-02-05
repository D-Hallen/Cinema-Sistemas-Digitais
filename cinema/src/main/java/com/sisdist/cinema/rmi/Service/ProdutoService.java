package com.sisdist.cinema.rmi.Service;

import com.sisdist.cinema.model.Produto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

public interface ProdutoService extends Remote {
    Produto saveProduto (Produto produto) throws RemoteException;
    List<Produto> listProdutos () throws RemoteException;
    Optional<Produto> findProdutoById (int id) throws RemoteException;
    void deleteProduto (int id) throws RemoteException;
    Produto updateProduto(Produto produto, int id) throws RemoteException;
    boolean reduzirQuantidade(int id, int quantidade) throws RemoteException;
}
