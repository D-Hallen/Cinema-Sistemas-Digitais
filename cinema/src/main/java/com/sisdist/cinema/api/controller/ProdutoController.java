package com.sisdist.cinema.api.controller;

import com.sisdist.cinema.api.model.Produto;
import com.sisdist.cinema.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController (ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> salveProduto(@RequestBody Produto produto) {
        Produto produtoSalvo = produtoService.saveProduto(produto);
        return ResponseEntity.ok(produtoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoService.listProdutos();
        if (produtos.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content se não houver produtos
        }
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable int id) {
        Optional<Produto> produto = produtoService.findProdutoById(id);
        if (produto.isPresent()){
            return ResponseEntity.ok(produto.get());
        } else{
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable int id) {
        if (produtoService.findProdutoById(id).isPresent()) {
            produtoService.deleteProduto(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@RequestBody Produto produto, @PathVariable int id) {
        Produto produtoAtualizado = produtoService.updateProduto(produto, id);
        if (produtoAtualizado != null) {
            return ResponseEntity.ok(produtoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/reduzir")
    public ResponseEntity<String> reduzirQuantidade(@PathVariable int id, @RequestParam int quantidade) {
        boolean sucesso = produtoService.reduzirQuantidade(id, quantidade);
        if (sucesso) {
            return ResponseEntity.ok("Quantidade reduzida com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Falha ao reduzir quantidade. Produto não encontrado ou estoque insuficiente.");
        }
    }
}
