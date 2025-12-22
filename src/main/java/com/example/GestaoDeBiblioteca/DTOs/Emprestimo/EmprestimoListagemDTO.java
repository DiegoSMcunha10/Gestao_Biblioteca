package com.example.GestaoDeBiblioteca.DTOs.Emprestimo;

public record EmprestimoListagemDTO(
        String nomeCliente,
        String nomeLivro,
        String statusEmprestimo
) {}
