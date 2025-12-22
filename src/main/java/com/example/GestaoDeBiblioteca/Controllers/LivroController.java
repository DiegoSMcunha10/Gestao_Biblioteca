package com.example.GestaoDeBiblioteca.Controllers;

import com.example.GestaoDeBiblioteca.DTOs.ConsultaTextoDTO;
import com.example.GestaoDeBiblioteca.DTOs.GeralMessageDTO;
import com.example.GestaoDeBiblioteca.Exceptions.AutorNotFoundException;
import com.example.GestaoDeBiblioteca.DTOs.Livro.LivroRequestDTO;
import com.example.GestaoDeBiblioteca.DTOs.Livro.LivroResponseDTO;
import com.example.GestaoDeBiblioteca.Exceptions.LivroNotFoundException;
import com.example.GestaoDeBiblioteca.Services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping("/listar")
    public ResponseEntity<?> listarLivros(){
        return ResponseEntity.ok(livroService.listarLivros());
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criarLivro(@RequestBody LivroRequestDTO livro){
        try {
            LivroResponseDTO retornoLivro = livroService.criarLivro(livro);
            return ResponseEntity.status(HttpStatus.OK).body(retornoLivro);
        } catch (AutorNotFoundException e) { // Pega nossa excessão personalizada e faz o tratamento
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/apagar")
    public ResponseEntity<?> apagarLivro(@RequestBody ConsultaTextoDTO consulta){
        try {
            GeralMessageDTO retornoDelete = livroService.deletarLivro(consulta);
            return  ResponseEntity.status(HttpStatus.OK).body(retornoDelete);
        } catch (LivroNotFoundException e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarLivro(@RequestBody LivroRequestDTO livro){
        try {
            livroService.atualizarLivro(livro);
            return ResponseEntity.status(HttpStatus.OK).body(livro);
        } catch (LivroNotFoundException | AutorNotFoundException e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
