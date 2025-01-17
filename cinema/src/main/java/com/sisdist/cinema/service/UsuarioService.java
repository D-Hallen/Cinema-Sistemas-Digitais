package com.sisdist.cinema.service;

import com.sisdist.cinema.api.model.Usuario;
import com.sisdist.cinema.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario (int id){
        usuarioRepository.deleteById(id);
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
