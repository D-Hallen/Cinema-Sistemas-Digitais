package com.sisdist.cinema;

import com.sisdist.cinema.repository.*;
import com.sisdist.cinema.rmi.Implementation.*;
import com.sisdist.cinema.rmi.Service.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@SpringBootApplication
public class CinemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

	// Bean do UserService
	@Bean("localUserService")
	public UserService userService(UsuarioRepository userRepository) throws RemoteException {
		return new UserServiceImpl(userRepository);
	}

	// Bean do IngressoService
	@Bean("localIngressoService")
	public IngressoService ingressoService(IngressoRepository ingressoRepository) throws RemoteException {
		return new IngressoServiceImpl(ingressoRepository);
	}

	@Bean("localFilmeService")
	public FilmeService filmeService(FilmeRepository filmeRepository) throws RemoteException {
		return new FilmeServiceImpl(filmeRepository);
	}

	@Bean("localSalaService")
	public SalaService salaService(SalaRepository salaRepository) throws RemoteException {
		return new SalaServiceImpl(salaRepository);
	}

	@Bean("localProdutoService")
	public ProdutoService produtoService(ProdutoRepository produtoRepository) throws RemoteException {
		return new ProdutoServiceImpl(produtoRepository);
	}

	@Bean("localVendaService")
	public VendaService vendaService(
			VendaRepository vendaRepository,
			IngressoRepository ingressoRepository,
			ProdutoRepository produtoRepository,
			UsuarioRepository usuarioRepository) throws RemoteException {
		return new VendaServiceImpl(vendaRepository, ingressoRepository, produtoRepository, usuarioRepository);
	}
}