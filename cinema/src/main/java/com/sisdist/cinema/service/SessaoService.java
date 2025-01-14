package com.sisdist.cinema.service;

import com.sisdist.cinema.api.model.Sessao;
import com.sisdist.cinema.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessaoService {

    private final SessaoRepository sessaoRepository;

    @Autowired
    public SessaoService(SessaoRepository sessaoRepository){
        this.sessaoRepository = sessaoRepository;
    }

    public List<Sessao> listSessoes() {
        return sessaoRepository.findAll();
    }

    public Optional<Sessao> getSessaoById(int id) {
        return sessaoRepository.findById(id);
    }

    public Sessao saveSessao(Sessao sessao) {
        return sessaoRepository.save(sessao);
    }
    public Sessao updateSessao(Sessao sessao, int id) {
        Optional<Sessao> sessaoAntiga = sessaoRepository.findById(id);
        if (sessaoAntiga.isPresent()) {
            Sessao sessaoAtualizada = sessaoAntiga.get();
            sessaoAtualizada.setFilme(sessao.getFilme());
            sessaoAtualizada.setSala(sessao.getSala());
            sessaoAtualizada.setValorIngresso(sessao.getValorIngresso());
            sessaoAtualizada.setDataHora(sessao.getDataHora());
            sessaoAtualizada.setIdioma(sessao.getIdioma());
            sessaoAtualizada.setLugaresDisponiveis(sessao.getLugaresDisponiveis());
            return sessaoRepository.save(sessaoAtualizada);
        }
        return null; // Caso não encontre a sessão
    }

    public void deleteSessao(int id) {
        sessaoRepository.deleteById(id);
    }

    public boolean selectLugar (int sessaoId, int lugar){
        Optional<Sessao> optionalSessao = sessaoRepository.findById(sessaoId);
        if (optionalSessao.isPresent()){
            Sessao sessao= optionalSessao.get();
            if (sessao.getLugaresDisponiveis().contains(lugar)){
                sessao.getLugaresDisponiveis().remove((Integer) lugar);
                sessaoRepository.save(sessao);
                return true;
            }
        }
        return false;
    }

}
