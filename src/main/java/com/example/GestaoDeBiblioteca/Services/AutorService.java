package com.example.GestaoDeBiblioteca.Services;

import com.example.GestaoDeBiblioteca.DTOs.Autor.AutorCriarRequestDTO;
import com.example.GestaoDeBiblioteca.DTOs.GeralMessageDTO;
import com.example.GestaoDeBiblioteca.DTOs.TokenDTO;
import com.example.GestaoDeBiblioteca.Entities.Autor;
import com.example.GestaoDeBiblioteca.Entities.Clientes;
import com.example.GestaoDeBiblioteca.Exceptions.AutorNotFoundException;
import com.example.GestaoDeBiblioteca.Exceptions.ClienteNotFoundException;
import com.example.GestaoDeBiblioteca.Repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.UUID;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public ResponseEntity<?> CriarAutor(AutorCriarRequestDTO autor) {
        Autor novoAutor = new Autor(autor.nome(), autor.nacionalidade().toUpperCase());

        autorRepository.save(novoAutor);

        return ResponseEntity.ok("Autor criado com sucesso");
    }
    public GeralMessageDTO deletarAutor(AutorCriarRequestDTO autor)  throws AutorNotFoundException, SQLIntegrityConstraintViolationException {
        Autor autorencontrado = autorRepository.findByNome(autor.nome());
        if (autorencontrado == null) {
            throw new AutorNotFoundException("Autor não encontrado ou inexistente");
        }
        autorRepository.delete(autorencontrado);


        return new GeralMessageDTO("Autor deletado com sucesso!");
    }

}
