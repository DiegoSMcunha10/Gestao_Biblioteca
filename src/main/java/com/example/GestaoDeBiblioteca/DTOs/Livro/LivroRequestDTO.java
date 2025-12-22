package com.example.GestaoDeBiblioteca.DTOs.Livro;



public record LivroRequestDTO(String titulo, String nomeAutor, String ISBN, String editora, String dataLancamento) {
}
