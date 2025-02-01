package com.sisdist.cinema.service;

import com.sisdist.cinema.api.model.*;
import com.sisdist.cinema.api.request.IngressoRequest;
import com.sisdist.cinema.api.request.ItemVendaRequest;
import com.sisdist.cinema.api.request.ProdutoRequest;
import com.sisdist.cinema.api.request.VendaRequest;
import com.sisdist.cinema.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {
    private final VendaRepository vendaRepository;
    private final IngressoRepository ingressoRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ItemVendaRepository itemVendaRepository;

    @Autowired
    public VendaService(
            VendaRepository vendaRepository,
            IngressoRepository ingressoRepository,
            ProdutoRepository produtoRepository,
            UsuarioRepository usuarioRepository,
            ItemVendaRepository itemVendaRepository
    ) {
        this.vendaRepository = vendaRepository;
        this.ingressoRepository = ingressoRepository;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
        this.itemVendaRepository = itemVendaRepository;
    }

    public List<Venda> listVendas() {
        return vendaRepository.findAll();
    }

    public Optional<Venda> getVendaById(int id) {
        return vendaRepository.findById(id);
    }

    public Venda saveVenda(VendaRequest vendaRequest) {

        Usuario usuario = usuarioRepository.findById(vendaRequest.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Cria a venda
        Venda venda = new Venda();
        venda.setUsuario(usuario);
        venda.setDataVenda(LocalDateTime.now());
        venda.setItens(new ArrayList<>());





        // Processa produtos
        if (vendaRequest.getProdutos() != null) {
            for (ProdutoRequest produtoRequest : vendaRequest.getProdutos()) {
                Produto produto = produtoRepository.findById(produtoRequest.getProdutoId())
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado: ID " + produtoRequest.getProdutoId()));

                ItemVenda item = new ItemVenda();
                item.setVenda(venda);
                item.setProduto(produto);
                item.setQuantidade(produtoRequest.getQuantidade());
                item.setPrecoUnitario(produto.getValor());
                item.setSubtotal(produto.getValor() * produtoRequest.getQuantidade());
                System.out.println(item.toString());
                venda.getItens().add(item);
            }
        }

        // Processa ingressos
        if (vendaRequest.getIngressos() != null) {
            for (IngressoRequest ingressoRequest : vendaRequest.getIngressos()) {
                System.out.println("Ingresso ID "+ingressoRequest.getIngressoId());
                Ingresso ingresso = ingressoRepository.findById(ingressoRequest.getIngressoId())
                        .orElseThrow(() -> new RuntimeException("Ingresso não encontrado: ID " + ingressoRequest.getIngressoId()));

                ItemVenda item = new ItemVenda();
                item.setVenda(venda);
                item.setIngresso(ingresso);
                item.setQuantidade(ingressoRequest.getQuantidade());
                item.setPrecoUnitario(ingresso.getValor());
                item.setSubtotal(ingresso.getValor() * ingressoRequest.getQuantidade());

                venda.getItens().add(item);
            }
        }

        // Calcula valor total
        double valorTotal = venda.getItens().stream()
                .mapToDouble(ItemVenda::getSubtotal)
                .sum();
        venda.setValorTotal(valorTotal);

        // Salva a venda e os itens


        System.out.println("\n--- ITENS DEPOIS DO PROCESSAMENTO ---");
        for (ItemVenda item : venda.getItens()) {
            System.out.println("ItemVenda (Após processamento):");
            System.out.println("  ID: " + item.getId());
            System.out.println("  Produto ID: " + (item.getProduto() != null ? item.getProduto().getId() : "Nulo"));
            System.out.println("  Ingresso ID: " + (item.getIngresso() != null ? item.getIngresso().getId() : "Nulo"));
            System.out.println("  Quantidade: " + item.getQuantidade());
            System.out.println("  Preço Unitário: " + item.getPrecoUnitario());
            System.out.println("  Subtotal: " + item.getSubtotal());
            System.out.println("  Venda ID associada: " + (item.getVenda() != null ? item.getVenda().getId() : "Nulo"));
            System.out.println("----------------------------------");
        }

        vendaRepository.save(venda);

        itemVendaRepository.saveAll(venda.getItens());

        return venda;
    }

    public Venda updateVenda(Venda venda, int id) {
        Optional<Venda> vendaExistente = vendaRepository.findById(id);
        if (vendaExistente.isPresent()) {
            venda.setId(id);
            return vendaRepository.save(venda);
        }
        throw new IllegalArgumentException("Venda não encontrada: ID " + id);
    }

    public void deleteVenda(int id) {
        if (vendaRepository.existsById(id)) {
            vendaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Venda não encontrada: ID " + id);
        }
    }
}