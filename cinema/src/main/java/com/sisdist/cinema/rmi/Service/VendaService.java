package com.sisdist.cinema.rmi.Service;

import com.sisdist.cinema.controller.VendaController;
import com.sisdist.cinema.model.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

public interface VendaService extends Remote {
    List<Venda> listVendas() throws RemoteException;
    Optional<Venda> getVendaById(int id) throws RemoteException;
    Venda saveVenda(VendaRequest vendaRequest) throws RemoteException;

    Venda updateVenda(Venda venda, int id) throws RemoteException;
    void deleteVenda(int id) throws RemoteException;
}
