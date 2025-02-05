package com.sisdist.cinema.controller;

import com.sisdist.cinema.model.Usuario;
import com.sisdist.cinema.rmi.Service.UserService;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    private final UserService userService;

    public UserController(@Qualifier("localUserService")UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Usuario createUser(@RequestBody Usuario user) throws RemoteException {
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        return userService.createUser(user);
    }

    @GetMapping
    public List<Usuario> getAllUsers() throws RemoteException {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario (@PathVariable int id) throws RemoteException {
        Optional<Usuario> usuario = userService.findUsuarioById(id);
        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cpf")
    public ResponseEntity<Usuario> getUsuarioByCpf(@RequestParam String cpf) throws RemoteException {
        Optional<Usuario> usuario = userService.findUsuarioByCPF(cpf);
        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginDTO) throws RemoteException {
        String token = userService.authenticateUser(loginDTO);

        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }

    // DTO para receber os dados
    public record LoginRequest(
            String email,
            String senha
    ) {    }
}
