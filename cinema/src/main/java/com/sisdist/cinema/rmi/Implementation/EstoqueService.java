package com.sisdist.cinema.rmi.Implementation;

import com.sisdist.cinema.model.Estoque;
import com.sisdist.cinema.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;

    @Autowired
    public EstoqueService(EstoqueRepository estoqueRepository){
        this.estoqueRepository = estoqueRepository;
    }

    public List<Estoque> listEstoques() {
        return estoqueRepository.findAll();
    }

    public Optional<Estoque> findEstoqueById(int id) {
        return estoqueRepository.findById(id);
    }

    public Estoque salveEstoque(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }

    public Estoque updateEstoque(int id, Estoque estoqueAtualizado) {
        Optional<Estoque> estoqueExistente = estoqueRepository.findById(id);

        if (estoqueExistente.isPresent()) {
            Estoque estoque = estoqueExistente.get();
            estoque.setData(estoqueAtualizado.getData());
            estoque.setStatus(estoqueAtualizado.getStatus());
            estoque.setQuantidade(estoqueAtualizado.getQuantidade());
            estoque.setPreco(estoqueAtualizado.getPreco());
            estoque.setProduto(estoqueAtualizado.getProduto());
            return estoqueRepository.save(estoque);
        }

        throw new RuntimeException("Estoque não encontrado com o ID: " + id);
    }

    public void deleteEstoque(int id) {
        estoqueRepository.deleteById(id);
    }

    public void reduzirQuantidade(int id, int quantidade) {
        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado com o ID: " + id));

        if (estoque.getQuantidade() >= quantidade) {
            estoque.setQuantidade(estoque.getQuantidade() - quantidade);
            if (estoque.getQuantidade() == 0) {
                estoque.setStatus("Esgotado");
            }
            estoqueRepository.save(estoque);
        } else {
            throw new RuntimeException("Quantidade insuficiente no estoque.");
        }
    }

}
