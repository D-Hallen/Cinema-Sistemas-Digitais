package com.sisdist.cinema.api.controller;

import com.sisdist.cinema.api.model.Usuario;
import com.sisdist.cinema.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController (UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario (@PathVariable int id){
        Optional<Usuario> usuario = usuarioService.findUsuarioById(id);
        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cpf")
    public ResponseEntity<Usuario> getUsuarioByCpf(@RequestParam String cpf){
        Optional<Usuario> usuario = usuarioService.findUsuarioByCPF(cpf);
        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> addUsuario (@RequestBody Usuario usuario){
        Usuario novoUsuario = usuarioService.saveUsuario(usuario);
        return ResponseEntity.status(201).body(novoUsuario); // HTTP 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario (@PathVariable int id,@RequestBody Usuario usuario){
        Usuario novoUsuario = usuarioService.updateUsuario(id, usuario);
        if (novoUsuario != null){
            return ResponseEntity.ok(novoUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listUsuarios (){
        List<Usuario> usuarios = usuarioService.listUsuarios();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content se não houver produtos
        }
        return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario (@PathVariable int id){
        if (usuarioService.findUsuarioById(id).isPresent()){
            usuarioService.deleteUsuario(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
