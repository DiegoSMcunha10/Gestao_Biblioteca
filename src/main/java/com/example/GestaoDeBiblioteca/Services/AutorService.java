package com.example.GestaoDeBiblioteca.Services;

import com.example.GestaoDeBiblioteca.DTOs.Autor.AutorCriarRequestDTO;
import com.example.GestaoDeBiblioteca.Entities.Autor;
import com.example.GestaoDeBiblioteca.Repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public ResponseEntity<?> CriarAutor(AutorCriarRequestDTO autor) {
        Autor novoAutor = new Autor(autor.nome(), autor.nacionalidade().toUpperCase());

        autorRepository.save(novoAutor);

        return ResponseEntity.ok("Autor criado com sucesso");
    }
}
