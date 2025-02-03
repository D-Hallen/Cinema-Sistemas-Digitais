package com.sisdist.cinema.api.rmi;

import com.sisdist.cinema.api.model.Venda;
import com.sisdist.cinema.api.request.VendaRequest;
import com.sisdist.cinema.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class VendaRMIImpl extends UnicastRemoteObject implements VendaRMI {
    private final VendaRepository vendaRepository;

    @Autowired
    public VendaRMIImpl(VendaRepository vendaRepository) throws RemoteException {
        this.vendaRepository = vendaRepository;
    }

    @Override
    public Venda registrarVenda(VendaRequest vendaRequest) throws RemoteException {
        return vendaRepository.save(venda);
    }

    @Override
    public List<Venda> listarVendasPorUsuario(int usuarioId) throws RemoteException {
        return vendaRepository.findByUsuarioId(usuarioId);
    }
}
