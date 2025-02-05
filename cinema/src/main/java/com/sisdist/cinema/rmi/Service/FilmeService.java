package com.sisdist.cinema.rmi.Service;

import com.sisdist.cinema.model.Filme;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface FilmeService extends Remote {
    List<Filme> getAllFilmes() throws RemoteException;
    Filme getFilmeById(int id) throws RemoteException;
    Filme createFilme(Filme filme) throws RemoteException;
    Filme updateFilme(int id, Filme filme) throws RemoteException;
    void deleteFilme(int id) throws RemoteException;

    List<Filme> buscarPorTitulo(String titulo) throws  RemoteException;

    List<Filme> buscarPorGenero(String genero) throws RemoteException;
}
