package com.sisdist.cinema.api.rmi;

import com.sisdist.cinema.api.model.Venda;
import com.sisdist.cinema.api.request.VendaRequest;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface VendaRMI extends Remote {
    Venda registrarVenda (VendaRequest vendaRequest) throws RemoteException;
    List<Venda> listarVendasPorUsuario(int userId) throws RemoteException;
}
