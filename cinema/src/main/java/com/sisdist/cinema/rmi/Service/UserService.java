package com.sisdist.cinema.rmi.Service;

import com.sisdist.cinema.controller.UserController;
import com.sisdist.cinema.model.Usuario;
import org.springframework.stereotype.Service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

public interface UserService extends Remote {
    Usuario createUser(Usuario user) throws RemoteException;
    List<Usuario> getAllUsers() throws RemoteException;

    Optional<Usuario> findUsuarioById(int id) throws RemoteException;

    Optional<Usuario> findUsuarioByCPF (String cpf) throws RemoteException;

    String authenticateUser (UserController.LoginRequest loginRequest) throws RemoteException;
}
