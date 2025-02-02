package com.sisdist.cinema.service;

import com.sisdist.cinema.api.model.*;
import com.sisdist.cinema.api.request.ItemRequest;
import com.sisdist.cinema.api.request.VendaRequest;
import com.sisdist.cinema.config.BusinessException;
import com.sisdist.cinema.repository.*;
import jakarta.transaction.Transactional;
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


    @Autowired
    public VendaService(
            VendaRepository vendaRepository,
            IngressoRepository ingressoRepository,
            ProdutoRepository produtoRepository,
            UsuarioRepository usuarioRepository
    ) {
        this.vendaRepository = vendaRepository;
        this.ingressoRepository = ingressoRepository;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Venda> listVendas() {
        return vendaRepository.findAll();
    }

    public Optional<Venda> getVendaById(int id) {
        return vendaRepository.findById(id);
    }

    @Transactional
    public Venda saveVenda(VendaRequest vendaRequest) {
        validarRequest(vendaRequest);

        Usuario usuario = buscarUsuario(vendaRequest.getUsuarioId());
        Venda venda = criarVenda(usuario);

        processarItens(venda, vendaRequest.getProdutos(), "PRODUTO");
        processarItens(venda, vendaRequest.getIngressos(), "INGRESSO");

        calcularValorTotal(venda);
        return vendaRepository.save(venda);
    }
    private void validarRequest(VendaRequest request) {
        if (request.getUsuarioId() <= 0) {
            throw new BusinessException("ID do usuário é obrigatório");
        }
        if ((request.getProdutos() == null || request.getProdutos().isEmpty()) &&
                (request.getIngressos() == null || request.getIngressos().isEmpty())) {
            throw new BusinessException("A venda deve conter produtos ou ingressos");
        }
    }

    private Usuario buscarUsuario(int usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado: " + usuarioId));
    }

    private Venda criarVenda(Usuario usuario) {
        Venda venda = new Venda();
        venda.setUsuario(usuario);
        venda.setDataVenda(LocalDateTime.now());
        return venda;
    }

    private void processarItens(Venda venda, List<ItemRequest> itensRequest, String tipo) {
        if (itensRequest == null) return;

        for (ItemRequest itemRequest : itensRequest) {
            validarQuantidade(itemRequest.getQuantidade());

            ItemVenda item = criarItemVenda(tipo, itemRequest.getId(), itemRequest.getQuantidade());
            venda.getItens().add(item);
            item.setVenda(venda); // Bidirecionalidade
        }
    }

    private ItemVenda criarItemVenda(String tipo, int itemId, int quantidade) {
        return switch (tipo) {
            case "PRODUTO" -> {
                Produto produto = produtoRepository.findById(itemId)
                        .orElseThrow(() -> new BusinessException("Produto não encontrado: " + itemId));
                yield new ProdutoVenda(produto, quantidade);
            }
            case "INGRESSO" -> {
                Ingresso ingresso = ingressoRepository.findById(itemId)
                        .orElseThrow(() -> new BusinessException("Ingresso não encontrado: " + itemId));
                yield new IngressoVenda(ingresso, quantidade);
            }
            default -> throw new IllegalStateException("Unexpected value: " + tipo);
        };
    }
    private void validarQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new BusinessException("Quantidade deve ser maior que zero");
        }
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
    private void calcularValorTotal(Venda venda) {
        double total = venda.getItens().stream()
                .mapToDouble(ItemVenda::getSubtotal)
                .sum();
        venda.setValorTotal(total);
    }
}