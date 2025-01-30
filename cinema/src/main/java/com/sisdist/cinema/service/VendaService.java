package com.sisdist.cinema.service;

import com.sisdist.cinema.api.model.*;
import com.sisdist.cinema.api.request.ItemVendaRequest;
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
        // Validação básica do DTO
        if (vendaRequest.getItens() == null || vendaRequest.getItens().isEmpty()) {
            throw new IllegalArgumentException("A venda deve conter pelo menos um item.");
        }

        // Busca o usuário
        Usuario usuario = usuarioRepository.findById(vendaRequest.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Cria a venda
        Venda venda = new Venda();
        venda.setUsuario(usuario);
        venda.setDataVenda(LocalDateTime.now());
        venda.setItens(new ArrayList<>()); // Garante que a lista não seja nula

        // Processa os itens
        List<ItemVenda> itens = new ArrayList<>();
        double valorTotal = 0;

        for (ItemVendaRequest itemRequest : vendaRequest.getItens()) {
            // Validação do item
            if (!itemRequest.isValid()) {
                throw new IllegalArgumentException("Item inválido: deve ter produtoId OU ingressoId.");
            }

            ItemVenda item = new ItemVenda();
            item.setVenda(venda);

            // Processa produto ou ingresso
            if (itemRequest.getProdutoId() != null) {
                Produto produto = produtoRepository.findById(itemRequest.getProdutoId())
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado: ID " + itemRequest.getProdutoId()));
                item.setProduto(produto);
                item.setPrecoUnitario(produto.getValor());
            } else {
                Ingresso ingresso = ingressoRepository.findById(itemRequest.getIngressoId())
                        .orElseThrow(() -> new RuntimeException("Ingresso não encontrado: ID " + itemRequest.getIngressoId()));
                item.setIngresso(ingresso);
                item.setPrecoUnitario(ingresso.getValor());
            }

            // Define quantidade e subtotal
            item.setQuantidade(itemRequest.getQuantidade());
            item.setSubtotal(item.getPrecoUnitario() * item.getQuantidade());
            valorTotal += item.getSubtotal();

            //itens.add(item);
            venda.getItens().add(item); // Agora a lista não é mais nula!
        }

        // Define o valor total da venda
        venda.setValorTotal(valorTotal);

        // Salva a venda e os itens
        vendaRepository.save(venda);
        itemVendaRepository.saveAll(itens);

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