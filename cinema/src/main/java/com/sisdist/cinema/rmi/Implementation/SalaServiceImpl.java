package com.sisdist.cinema.rmi.Implementation;

import com.sisdist.cinema.model.Sala;
import com.sisdist.cinema.repository.SalaRepository;
import com.sisdist.cinema.rmi.Service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;

@Service("salaService")
public class SalaServiceImpl extends UnicastRemoteObject implements SalaService {
    private final SalaRepository salaRepository;

    @Autowired
    public SalaServiceImpl(SalaRepository salaRepository) throws RemoteException {
        super();
        this.salaRepository = salaRepository;
    }

    @Override
    public List<Sala> getAllSalas()  throws RemoteException{
        return salaRepository.findAll();
    }

    @Override
    public Optional<Sala> getSalaById(int id) throws RemoteException{
        return salaRepository.findById(id);
    }

    @Override
    public Sala createSala(Sala sala) throws RemoteException{
        return salaRepository.save(sala);
    }

    @Override
    public Sala updateSala(int id, Sala sala) throws RemoteException {
        if (salaRepository.existsById(id)) {
            sala.setId(id); // Certifique-se de que exista um setter para id na entidade Sala, se necessário.
            return salaRepository.save(sala);
        }
        return null;
    }

    @Override
    public void deleteSala(int id) throws RemoteException {
        salaRepository.deleteById(id);
    }
}
