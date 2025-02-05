package com.sisdist.cinema;

import com.sisdist.cinema.rmi.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@Component
public class RmiInitializer implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        try {
            // Cria ou obtém o registry na porta 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // Registra os serviços do RMI utilizando os beans do contexto
            registry.rebind("UserService", applicationContext.getBean("localUserService", UserService.class));
            registry.rebind("IngressoService", applicationContext.getBean("localIngressoService", IngressoService.class));
            registry.rebind("FilmeService", applicationContext.getBean("localFilmeService", FilmeService.class));
            registry.rebind("SalaService", applicationContext.getBean("localSalaService", SalaService.class));
            registry.rebind("ProdutoService", applicationContext.getBean("localProdutoService", ProdutoService.class));
            registry.rebind("VendaService", applicationContext.getBean("localVendaService", VendaService.class));

            System.out.println("Servidor RMI iniciado na porta 1099");
        } catch (Exception e) {
            System.err.println("\n\nErro ao iniciar servidor RMI: " + e.getMessage() + "\n\n");
        }
    }
}
