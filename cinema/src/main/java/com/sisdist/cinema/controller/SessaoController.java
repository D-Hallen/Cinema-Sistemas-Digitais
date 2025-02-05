package com.sisdist.cinema.controller;


import com.sisdist.cinema.model.Reserva;
import com.sisdist.cinema.model.ReservaRequest;
import com.sisdist.cinema.model.Sessao;
import com.sisdist.cinema.rmi.Implementation.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sessoes")
public class SessaoController {
    private final SessaoService sessaoService;

    @Autowired
    public SessaoController(SessaoService sessaoService){
        this.sessaoService = sessaoService;
    }

    @GetMapping
    public ResponseEntity<List<Sessao>> listSessoes() {
        List<Sessao> sessoes = sessaoService.listSessoes();
        if (!sessoes.isEmpty()) {
            return ResponseEntity.ok(sessoes);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}/disponiveis")
    public ResponseEntity<List<Integer>> getDisponiveis(@PathVariable int id){
        Optional<Sessao> sessao = sessaoService.getSessaoById(id);
        if (sessao!= null){
            return ResponseEntity.ok((sessao.get().getLugaresDisponiveis()));
        }
        else {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/{id}/reservar")
    public ResponseEntity<Reserva> reservarLugar (@PathVariable int id, @RequestBody ReservaRequest reservaRequest){
        Optional<Reserva> reserva = sessaoService.fazReserva(id, reservaRequest);
        return reserva.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sessao> getSessaoById(@PathVariable int id) {
        Optional<Sessao> sessao = sessaoService.getSessaoById(id);
        return sessao.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).build());
    }

    @PostMapping
    public ResponseEntity<Sessao> saveSessao(@RequestBody Sessao sessao) {
        Sessao novaSessao = sessaoService.saveSessao(sessao);
        return ResponseEntity.status(201).body(novaSessao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sessao> updateSessao(@RequestBody Sessao sessao, @PathVariable int id) {
        Sessao sessaoAtualizada = sessaoService.updateSessao(sessao, id);
        if (sessaoAtualizada != null) {
            return ResponseEntity.ok(sessaoAtualizada);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSessao(@PathVariable int id) {
        Optional<Sessao> sessao = sessaoService.getSessaoById(id);
        if (sessao.isPresent()) {
            sessaoService.deleteSessao(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/{id}/selecionar-lugar/{lugar}")
    public ResponseEntity<String> selecionarLugar(@PathVariable int id, @PathVariable int lugar) {
        boolean sucesso = sessaoService.selectLugar(id, lugar);
        if (sucesso) {
            return ResponseEntity.ok("Lugar selecionado com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Erro: Lugar não disponível ou sessão inexistente.");
        }
    }
}
