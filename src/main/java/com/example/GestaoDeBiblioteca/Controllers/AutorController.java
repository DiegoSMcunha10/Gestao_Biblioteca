package com.example.GestaoDeBiblioteca.Controllers;

import com.example.GestaoDeBiblioteca.DTOs.Autor.AutorCriarRequestDTO;
import com.example.GestaoDeBiblioteca.Services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping("/criar")
    public ResponseEntity<?> CriarAutor(@RequestBody AutorCriarRequestDTO autor) {
        return autorService.CriarAutor(autor);
    }
}
