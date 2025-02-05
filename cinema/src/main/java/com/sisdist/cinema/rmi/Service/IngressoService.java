package com.sisdist.cinema.rmi.Service;

import com.sisdist.cinema.model.Ingresso;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

public interface IngressoService extends Remote {
    Ingresso createIngresso (Ingresso ingresso) throws RemoteException;
    List<Ingresso> listIngressos () throws  RemoteException;
    Optional<Ingresso> getIngressoById (int id) throws RemoteException;

    void deleteIngresso (int id) throws  RemoteException;
}
