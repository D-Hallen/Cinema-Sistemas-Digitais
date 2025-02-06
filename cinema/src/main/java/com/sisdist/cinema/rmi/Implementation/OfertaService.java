package com.sisdist.cinema.rmi.Implementation;


import com.sisdist.cinema.model.Oferta;
import com.sisdist.cinema.repository.OfertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfertaService {
    private final OfertaRepository ofertaRepository;

    @Autowired
    public OfertaService(OfertaRepository ofertaRepository){
        this.ofertaRepository = ofertaRepository;
    }

    public List<Oferta> listOfertas() {
        return ofertaRepository.findAll();
    }

    public Optional<Oferta> findOfertaById(int id) {
        return ofertaRepository.findById(id);
    }

    public Oferta salveOferta(Oferta oferta) {
        return ofertaRepository.save(oferta);
    }

    public Oferta updateOferta(int id, Oferta oferta) {
        Optional<Oferta> ofertaExistente = ofertaRepository.findById(id);
        if (ofertaExistente.isPresent()) {
            Oferta ofertaAtualizada = ofertaExistente.get();
            ofertaAtualizada.setProduto(oferta.getProduto());
            ofertaAtualizada.setDataInicio(oferta.getDataInicio());
            ofertaAtualizada.setDataFim(oferta.getDataFim());
            ofertaAtualizada.setDescricao(oferta.getDescricao());
            ofertaAtualizada.setPercentualDesconto(oferta.getPercentualDesconto());
            return ofertaRepository.save(ofertaAtualizada);
        } else {
            throw new RuntimeException("Oferta com ID " + id + " não encontrada.");
        }
    }

    public void deleteOferta(int id) {
        ofertaRepository.deleteById(id);
    }

    public List<Oferta> findByProdutoId(int prodId){
        return ofertaRepository.findByProdutoId(prodId);
    }
}
