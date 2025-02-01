package com.sisdist.cinema.service;

import com.sisdist.cinema.api.model.ItemVenda;
import com.sisdist.cinema.repository.ItemVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemVendaService {

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    // Lista todos os itens de uma venda
    public List<ItemVenda> listarItensPorVenda(int vendaId) {
        return itemVendaRepository.findVendaById(vendaId);
    }

    // Busca um item por ID
    public Optional<ItemVenda> buscarItemPorId(int id) {
        return itemVendaRepository.findById(id);
    }

    // Atualiza um item existente
    public ItemVenda atualizarItem(int id, ItemVenda itemAtualizado) {
        ItemVenda itemExistente = itemVendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));
        // Recalcula o subtotal
        itemExistente.setSubtotal(itemExistente.getPrecoUnitario() * itemExistente.getQuantidade());

        return itemVendaRepository.save(itemExistente);
    }

    // Exclui um item
    public void excluirItem(int id) {
        if (itemVendaRepository.existsById(id)) {
            itemVendaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Item não encontrado: ID " + id);
        }
    }
}
