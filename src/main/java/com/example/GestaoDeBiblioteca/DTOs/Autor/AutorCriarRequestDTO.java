package com.example.GestaoDeBiblioteca.DTOs.Autor;

import jakarta.validation.constraints.NotBlank;

public record AutorCriarRequestDTO(
        @NotBlank String nome,
        @NotBlank String nacionalidade) {

    public AutorCriarRequestDTO {
        nome = nome.toUpperCase();
    }
}
