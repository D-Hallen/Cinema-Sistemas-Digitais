package com.sisdist.cinema;

import com.sisdist.cinema.api.rmi.VendaRMI;
import com.sisdist.cinema.api.rmi.VendaRMIImpl;
import com.sisdist.cinema.repository.VendaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@Component
public class RMIServer {

    @Autowired
    private VendaRepository vendaRepository;

    @PostConstruct
    public void iniciarServidorRMI() {
        try {
            // Cria o serviço RMI com o repositório injetado
            VendaRMI vendaService = new VendaRMIImpl();

            // Cria ou obtém o registro RMI na porta 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // Registra o serviço com o nome "VendaService"
            registry.rebind("VendaService", vendaService);

            System.out.println("Servidor RMI iniciado na porta 1099!");
        } catch (Exception e) {
            System.err.println("Erro ao iniciar o servidor RMI:");
            e.printStackTrace();
        }
    }
}