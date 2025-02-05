package com.sisdist.cinema.rmi.Implementation;

import com.sisdist.cinema.config.BusinessException;
import com.sisdist.cinema.controller.UserController;
import com.sisdist.cinema.model.Usuario;
import com.sisdist.cinema.repository.UsuarioRepository;
import com.sisdist.cinema.rmi.Service.UserService;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends UnicastRemoteObject implements UserService {
    private final UsuarioRepository usuarioRepository;

    public UserServiceImpl(UsuarioRepository userRepository) throws RemoteException {
        super();
        this.usuarioRepository = userRepository;
    }

    @Override
    public Usuario createUser(Usuario usuario) throws RemoteException {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> getAllUsers() throws RemoteException{
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findUsuarioById(int id) throws RemoteException{
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuario> findUsuarioByCPF (String cpf) throws RemoteException{
        return usuarioRepository.findByCpf(cpf);
    }

    @Override
    public String authenticateUser (UserController.LoginRequest loginRequest) throws RemoteException{
        Optional<Usuario> usuarioOptional= usuarioRepository.findByEmail(loginRequest.email());
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            // Verifica se a senha está correta
            if (usuario.getSenha().equals(loginRequest.senha())) {
                // Retorna o token do usuário
                return usuario.getToken();
            } else {
                throw new BusinessException("Senha Incorreta");
            }
        } else {
            throw new BusinessException("Usuário não encontrado");
        }
    }

}
