package com.sisdist.cinema.rmi.Implementation;

import com.sisdist.cinema.config.BusinessException;
import com.sisdist.cinema.controller.VendaController;
import com.sisdist.cinema.model.*;
import com.sisdist.cinema.repository.IngressoRepository;
import com.sisdist.cinema.repository.ProdutoRepository;
import com.sisdist.cinema.repository.UsuarioRepository;
import com.sisdist.cinema.repository.VendaRepository;
import com.sisdist.cinema.rmi.Service.VendaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//@Service
public class VendaServiceImpl extends UnicastRemoteObject implements VendaService {
    private final VendaRepository vendaRepository;
    private final IngressoRepository ingressoRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;


    @Autowired
    public VendaServiceImpl(
            VendaRepository vendaRepository,
            IngressoRepository ingressoRepository,
            ProdutoRepository produtoRepository,
            UsuarioRepository usuarioRepository
    ) throws RemoteException {
        super();
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
        // Valida a requisição
        validateVendaRequest(vendaRequest);

        // Busca o usuário e cria a venda
        Usuario usuario = findUsuario(vendaRequest.getUsuarioId());
        Venda venda = createVenda(usuario);

        // Processa os produtos e ingressos, acumulando o valor total
        double totalProdutos = processProdutos(venda, vendaRequest.getProdutos());
        double totalIngressos = processIngressos(venda, vendaRequest.getIngressos());

        venda.setValorTotal(totalProdutos + totalIngressos);

        // Salva a venda no repositório
        return vendaRepository.save(venda);
    }

    private void validateVendaRequest(VendaRequest vendaRequest) {
        if (vendaRequest.getUsuarioId() <= 0) {
            throw new BusinessException("ID do usuário é obrigatório");
        }
        if ((vendaRequest.getProdutos() == null || vendaRequest.getProdutos().isEmpty()) &&
                (vendaRequest.getIngressos() == null || vendaRequest.getIngressos().isEmpty())) {
            throw new BusinessException("A venda deve conter produtos ou ingressos");
        }
    }

    private Usuario findUsuario(int usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado: " + usuarioId));
    }

    private Venda createVenda(Usuario usuario) {
        Venda venda = new Venda();
        venda.setUsuario(usuario);
        venda.setDataVenda(LocalDateTime.now());
        return venda;
    }

    private double processProdutos(Venda venda, List<VendaRequest.ItemRequest> produtos) {
        double total = 0;
        if (produtos != null) {
            for (VendaRequest.ItemRequest prodDTO : produtos) {
                Produto produto = produtoRepository.findById(prodDTO.getId())
                        .orElseThrow(() -> new BusinessException("Produto não encontrado: " + prodDTO.getId()));
                // Adiciona o produto à venda conforme a quantidade informada
                for (int i = 0; i < prodDTO.getQuantidade(); i++) {
                    venda.getProdutos().add(produto);
                }
                total += produto.getValor() * prodDTO.getQuantidade();
            }
        }
        return total;
    }

    private double processIngressos(Venda venda, List<VendaRequest.ItemRequest> ingressos) {
        double total = 0;
        if (ingressos != null) {
            for (VendaRequest.ItemRequest ingDTO : ingressos) {
                Ingresso ingresso = ingressoRepository.findById(ingDTO.getId())
                        .orElseThrow(() -> new BusinessException("Ingresso não encontrado: " + ingDTO.getId()));
                // Adiciona o ingresso à venda conforme a quantidade informada
                for (int i = 0; i < ingDTO.getQuantidade(); i++) {
                    venda.getIngressos().add(ingresso);
                }
                total += ingresso.getValor() * ingDTO.getQuantidade();
            }
        }
        return total;
    }

    public void validarRequest(VendaRequest request) {
        if (request.getUsuarioId() <= 0) {
            throw new BusinessException("ID do usuário é obrigatório");
        }
        if ((request.getProdutos() == null || request.getProdutos().isEmpty()) &&
                (request.getIngressos() == null || request.getIngressos().isEmpty())) {
            throw new BusinessException("A venda deve conter produtos ou ingressos");
        }
    }

    public Usuario buscarUsuario(int usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado: " + usuarioId));
    }

    public Venda criarVenda(Usuario usuario) {
        Venda venda = new Venda();
        venda.setUsuario(usuario);
        venda.setDataVenda(LocalDateTime.now());
        return venda;
    }

    public ProdutoVenda criarProdutoVenda (String tipo, int itemId, int quantidade) {
        Produto produto = produtoRepository.findById(itemId)
                .orElseThrow(() -> new BusinessException("Produto não encontrado: " + itemId));
        return  new ProdutoVenda(produto, quantidade);
    }

    public IngressoVenda criarIngressoVenda (String tipo, int itemId, int quantidade){
        Ingresso ingresso = ingressoRepository.findById(itemId)
                .orElseThrow(() -> new BusinessException("Ingresso não encontrado: " + itemId));
        return new IngressoVenda(ingresso, quantidade);
    };

    public void validarQuantidade(int quantidade) {
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
//    public void calcularValorTotal(Venda venda) {
//        double total = venda.getProdutos().stream()
//                .mapToDouble(ItemVenda::getSubtotal)
//                .sum();
//        total += venda.getIngressos().stream()
//                .mapToDouble(ItemVenda::getSubtotal)
//                .sum();
//        venda.setValorTotal(total);
//    }
}
