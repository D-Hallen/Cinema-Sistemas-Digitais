package com.sisdist.cinema;

import com.sisdist.cinema.model.Filme;
import com.sisdist.cinema.model.Produto;
import com.sisdist.cinema.model.Usuario;
import com.sisdist.cinema.rmi.Service.FilmeService;
import com.sisdist.cinema.rmi.Service.ProdutoService;
import com.sisdist.cinema.rmi.Service.UserService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClientTest {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        UserService service = (UserService) registry.lookup("UserService");
        FilmeService filmeService = (FilmeService) registry.lookup("FilmeService");
        ProdutoService produtoService = (ProdutoService) registry.lookup("ProdutoService");
        System.out.println("Usuarios:");
        for (Usuario usuario: service.getAllUsers()
             ) {
            System.out.println(usuario.getNomeCompleto());
        }
        System.out.println("Filmes:");
        for (Filme filme: filmeService.getAllFilmes()
        ) {
            System.out.println(filme.getTitulo() +" - "+ filme.getSinopse());
        }

        System.out.println("Produtos:");
        for (Produto produto: produtoService.listProdutos()
        ) {
            System.out.println(produto.getNome() + "- R$" + produto.getValor());
        }
    }
}
