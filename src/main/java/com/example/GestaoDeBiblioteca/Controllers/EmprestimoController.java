package com.example.GestaoDeBiblioteca.Controllers;

import com.example.GestaoDeBiblioteca.DTOs.Emprestimo.FazerEmprestimoDTO;
import com.example.GestaoDeBiblioteca.Exceptions.ClienteNotFoundException;
import com.example.GestaoDeBiblioteca.Exceptions.LivroNotFoundException;
import com.example.GestaoDeBiblioteca.Services.EmprestimoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {
    private EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping("/fazer")
    public ResponseEntity<?> fazerEmprestimo(@RequestBody FazerEmprestimoDTO fazerEmprestimoDTO) throws ClienteNotFoundException, LivroNotFoundException {
        return ResponseEntity.ok(emprestimoService.fazerEmprestimo(fazerEmprestimoDTO));
    }

    @PutMapping("/devolver")
    public ResponseEntity<?> atualizarEmprestimo(@RequestBody FazerEmprestimoDTO fazerEmprestimoDTO) throws ClienteNotFoundException, LivroNotFoundException {
        return ResponseEntity.ok(emprestimoService.devolverLivro(fazerEmprestimoDTO));
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarEmprestimos() {
        return ResponseEntity.ok(emprestimoService.listarEmprestimos());
    }

}
