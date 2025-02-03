package com.sisdist.cinema.service;

import com.sisdist.cinema.api.model.Usuario;
import com.sisdist.cinema.api.request.LoginRequest;
import com.sisdist.cinema.config.BusinessException;
import com.sisdist.cinema.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listUsuarios (){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findUsuarioById (int id){
        return usuarioRepository.findById(id);
    }

    public Usuario saveUsuario (Usuario usuario){
        String token = UUID.randomUUID().toString();
        usuario.setToken(token);
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario (int id){
        usuarioRepository.deleteById(id);
    }

    public String authenticateUser(LoginRequest loginRequest){
        Optional<Usuario> usuarioOptional= usuarioRepository.findByEmail(loginRequest.getEmail());
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            // Verifica se a senha está correta
            if (usuario.getSenha().equals(loginRequest.getSenha())) {
                // Retorna o token do usuário
                return usuario.getToken();
            } else {
                throw new BusinessException("Senha Incorreta");
            }
        } else {
            throw new BusinessException("Usuário não encontrado");
        }
    }

    public Usuario updateUsuario (int id, Usuario usuario){
        Optional<Usuario> usuarioAntigo = usuarioRepository.findById(id);
        if (usuarioAntigo.isPresent()){
            Usuario newUsuario = usuarioAntigo.get();
            newUsuario.setCpf(usuario.getCpf());
            newUsuario.setEmail(usuario.getEmail());
            newUsuario.setEndereco(usuario.getEndereco());
            newUsuario.setNivelAcesso(usuario.getNivelAcesso());
            newUsuario.setStatus(usuario.getStatus());
            newUsuario.setSenha(usuario.getSenha());
            newUsuario.setTelefone(usuario.getTelefone());
            newUsuario.setNomeCompleto(usuario.getNomeCompleto());
            newUsuario.setDataNascimento(usuario.getDataNascimento());
            return usuarioRepository.save(newUsuario);
        } else{
            return null;
        }
    }

    public Optional<Usuario> findUsuarioByCPF (String cpf){
        return usuarioRepository.findByCpf(cpf);
    }
}
