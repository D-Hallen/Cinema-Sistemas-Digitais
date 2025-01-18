package com.sisdist.cinema.service;

import com.sisdist.cinema.api.model.Fidelidade;
import com.sisdist.cinema.repository.FidelidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FidelidadeService {

    private final FidelidadeRepository fidelidadeRepository;

    @Autowired
    public FidelidadeService(FidelidadeRepository fidelidadeRepository){
        this.fidelidadeRepository = fidelidadeRepository;
    }

    // Listar todas as fidelidades
    public List<Fidelidade> listFidelidades() {
        return fidelidadeRepository.findAll();
    }

    // Buscar fidelidade por ID
    public Optional<Fidelidade> getFidelidadeById(int id) {
        return fidelidadeRepository.findById(id);
    }

    // Buscar fidelidades por usuário
    public List<Fidelidade> getFidelidadesByUsuario(int usuarioId) {
        return fidelidadeRepository.findByUsuarioId(usuarioId);
    }

    // Criar uma nova fidelidade
    public Fidelidade createFidelidade(Fidelidade fidelidade) {
        fidelidade.setDataAdesao(LocalDate.now()); // Define a data de adesão atual
        fidelidade.setDataValidade(fidelidade.getDataAdesao().plusMonths(1)); // Exemplo de validade padrão: 1 mês
        fidelidade.setStatus("Ativo"); // Define o status padrão
        return fidelidadeRepository.save(fidelidade);
    }

    // Atualizar uma fidelidade existente
    public Fidelidade updateFidelidade(int id, Fidelidade fidelidadeAtualizada) {
        return fidelidadeRepository.findById(id).map(fidelidade -> {
            fidelidade.setNome(fidelidadeAtualizada.getNome());
            fidelidade.setValorMensalidade(fidelidadeAtualizada.getValorMensalidade());
            fidelidade.setPontuacao(fidelidadeAtualizada.getPontuacao());
            fidelidade.setDataAdesao(fidelidadeAtualizada.getDataAdesao());
            fidelidade.setDataValidade(fidelidadeAtualizada.getDataValidade());
            fidelidade.setStatus(fidelidadeAtualizada.getStatus());
            fidelidade.setUsuario(fidelidadeAtualizada.getUsuario());
            return fidelidadeRepository.save(fidelidade);
        }).orElseThrow(() -> new RuntimeException("Fidelidade não encontrada com o ID: " + id));
    }

    // Deletar uma fidelidade
    public void deleteFidelidade(int id) {
        fidelidadeRepository.deleteById(id);
    }
}
