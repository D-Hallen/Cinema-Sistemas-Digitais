package com.sisdist.cinema.api.controller;

import com.sisdist.cinema.api.model.Sessao;
import com.sisdist.cinema.service.SessaoService;
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
    public SessaoController (SessaoService sessaoService){
        this.sessaoService = sessaoService;
    }

    @GetMapping
    public List<Sessao> listSessoes() {
        return sessaoService.listSessoes();
    }

    // Obter uma sessão pelo ID
    @GetMapping("/{id}")
    public Optional<Sessao> getSessaoById(@PathVariable int id) {
        return sessaoService.getSessaoById(id);
    }

    // Adicionar uma nova sessão
    @PostMapping
    public Sessao saveSessao(@RequestBody Sessao sessao) {
        return sessaoService.saveSessao(sessao);
    }

    // Atualizar uma sessão existente
    @PutMapping("/{id}")
    public Sessao updateSessao(@RequestBody Sessao sessao, @PathVariable int id) {
        return sessaoService.updateSessao(sessao, id);
    }

    // Deletar uma sessão
    @DeleteMapping("/{id}")
    public void deleteSessao(@PathVariable int id) {
        sessaoService.deleteSessao(id);
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
