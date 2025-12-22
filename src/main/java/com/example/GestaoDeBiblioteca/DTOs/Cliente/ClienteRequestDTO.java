package com.example.GestaoDeBiblioteca.DTOs.Cliente;

import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDTO(
        @NotBlank(message = "Digite um nome") String nome,
        @NotBlank(message = "Digite uma senha") String senha,
        @NotBlank(message = "Digite um email") String email
) {
}
