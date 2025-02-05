package com.sisdist.cinema.rmi.Service;

import com.sisdist.cinema.model.Sala;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;


public interface SalaService extends Remote {
    List<Sala> getAllSalas() throws RemoteException;
    Optional<Sala> getSalaById(int id) throws RemoteException;
    Sala createSala(Sala sala) throws RemoteException;
    Sala updateSala(int id, Sala sala) throws RemoteException;
    void deleteSala(int id) throws RemoteException;
}
