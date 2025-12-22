package com.example.GestaoDeBiblioteca.Repository;

import com.example.GestaoDeBiblioteca.Entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
    Livro findByTitulo(String titulo);

    Livro findByISBN(String isbn);
}
