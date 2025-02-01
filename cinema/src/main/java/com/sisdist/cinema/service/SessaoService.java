package com.sisdist.cinema.service;

import com.sisdist.cinema.api.model.Reserva;
import com.sisdist.cinema.api.model.Sessao;
import com.sisdist.cinema.api.request.ReservaRequest;
import com.sisdist.cinema.repository.ReservaRepository;
import com.sisdist.cinema.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private ReservaRepository reservaRepository;

    @Autowired
    public SessaoService(SessaoRepository sessaoRepository, ReservaRepository reservaRepository){
        this.sessaoRepository = sessaoRepository;
        this.reservaRepository = reservaRepository;
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


    public Optional<Reserva> fazReserva(int id, ReservaRequest reservaRequest){
        Optional<Sessao> sessaoOpt = sessaoRepository.findById(id);
        if (sessaoOpt.isPresent()){ //Se a sessao existir
            Sessao sessao = sessaoOpt.get(); //Pega a sessao

            boolean lugarDisponivel = reservaRepository
                    .findBySessaoAndLugar(sessao, reservaRequest.getLugar()).isEmpty(); //Verifica se tem lugar disponivel
            if (!lugarDisponivel) {
                return Optional.empty(); // Lugar já reservado
            }

            Reserva reserva = new Reserva();
            reserva.setSessao(sessao);
            reserva.setLugar(reservaRequest.getLugar());
            reserva.setCpf(reservaRequest.getCpf());
            reserva.setDataExpiracao(sessao.getDataHora().toLocalDate());
            reserva.setStatus("ATIVA");

            reservaRepository.save(reserva);
            boolean selecionado = selectLugar(sessao.getId(), reserva.getLugar());
            // Salva a reserva
            if (selecionado){
                return Optional.of(reserva);
            } else {
                return Optional.empty();
            }

        }
            return Optional.empty();
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
