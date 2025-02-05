package com.sisdist.cinema.rmi.Implementation;

import com.sisdist.cinema.model.Ingresso;
import com.sisdist.cinema.repository.IngressoRepository;
import com.sisdist.cinema.rmi.Service.IngressoService;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;

@Service
public class IngressoServiceImpl extends UnicastRemoteObject implements IngressoService {

    private final IngressoRepository ingressoRepository;

    public IngressoServiceImpl(IngressoRepository ingressoRepository) throws RemoteException {
        super();
        this.ingressoRepository = ingressoRepository;
    }

    @Override
    public Ingresso createIngresso (Ingresso ingresso) throws RemoteException{
        Ingresso novoIngresso = ingressoRepository.save(ingresso);
        return novoIngresso;
    }
    @Override
    public List<Ingresso> listIngressos () throws  RemoteException{
        return ingressoRepository.findAll();
    }
    @Override
    public Optional<Ingresso> getIngressoById (int id) throws RemoteException{
        return ingressoRepository.findById(id);
    }

    @Override
    public void deleteIngresso(int id) throws RemoteException{
        ingressoRepository.deleteById(id);
    }
}
