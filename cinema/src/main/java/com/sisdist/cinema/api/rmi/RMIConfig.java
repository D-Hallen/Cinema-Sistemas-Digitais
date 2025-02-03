package com.sisdist.cinema.api.rmi;

import com.sisdist.cinema.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@Configuration
public class RMIConfig {
    @Autowired
    private VendaRepository vendaRepository;

    @Bean
    public Registry rmiRegistry() {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            VendaRMI vendaService = new VendaRMIImpl();
            registry.rebind("VendaService", vendaService);
            return registry;
        } catch (RemoteException e) {
            throw new RuntimeException("Falha ao iniciar o registro RMI", e);
        }
    }
}
