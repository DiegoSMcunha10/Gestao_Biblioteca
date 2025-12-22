package com.example.GestaoDeBiblioteca.DTOs.Livro;

public record LivroResponseDTO(
        String titulo,
        String editora,
        String dataLancamento,
        String nomeAutor,
        String ISBN
) {
}
