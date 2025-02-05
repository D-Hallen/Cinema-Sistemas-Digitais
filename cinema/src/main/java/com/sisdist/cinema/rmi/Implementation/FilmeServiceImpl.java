package com.sisdist.cinema.rmi.Implementation;

import com.sisdist.cinema.model.Filme;
import com.sisdist.cinema.repository.FilmeRepository;
import com.sisdist.cinema.rmi.Service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;

@Service("filmeService")
public class FilmeServiceImpl extends UnicastRemoteObject implements FilmeService {
    private final FilmeRepository filmeRepository;

    @Autowired
    public FilmeServiceImpl(FilmeRepository filmeRepository) throws RemoteException {
        super();
        this.filmeRepository = filmeRepository;
    }

    @Override
    public List<Filme> getAllFilmes() throws RemoteException {
        return filmeRepository.findAll();
    }

    @Override
    public Filme getFilmeById(int id) throws RemoteException{
        Optional<Filme> filme = filmeRepository.findById(id);
        return filme.orElse(null);
    }

    @Override
    public Filme createFilme(Filme filme) throws RemoteException{
        return filmeRepository.save(filme);
    }

    @Override
    public Filme updateFilme(int id, Filme filme) throws RemoteException{
        if (filmeRepository.existsById(id)) {
            // Caso a entidade Filme não tenha setter para id, você pode precisar criar um novo objeto
            filme.setId(id); // Certifique-se de que existe um setter para o id na entidade Filme, se necessário.
            return filmeRepository.save(filme);
        }
        return null;
    }

    @Override
    public void deleteFilme(int id) throws RemoteException{
        filmeRepository.deleteById(id);
    }

    //Busca por Titulo
    @Override
    public List<Filme> buscarPorTitulo(String titulo)  throws RemoteException{
        return filmeRepository.findByTituloContainingIgnoreCase(titulo);
    }

    // Busca por gênero
    @Override
    public List<Filme> buscarPorGenero(String genero) throws RemoteException{
        return filmeRepository.findByGenerosContainingIgnoreCase(genero);
    }
}
