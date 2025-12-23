package com.example.GestaoDeBiblioteca.Controllers;

import com.example.GestaoDeBiblioteca.DTOs.Autor.AutorCriarRequestDTO;
import com.example.GestaoDeBiblioteca.Exceptions.AutorNotFoundException;
import com.example.GestaoDeBiblioteca.Exceptions.SQLException;
import com.example.GestaoDeBiblioteca.Services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

@RestController()
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping("/criar")
    public ResponseEntity<?> CriarAutor(@RequestBody AutorCriarRequestDTO autor) {
        return autorService.CriarAutor(autor);
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> DeletarAutor(@RequestBody AutorCriarRequestDTO autor) throws AutorNotFoundException, SQLException {
        try {
            return ResponseEntity.ok(autorService.deletarAutor(autor));
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLException("O Autor possui livros cadastrados.");
        }

    }
}
