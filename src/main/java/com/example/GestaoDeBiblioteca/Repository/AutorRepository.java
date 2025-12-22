package com.example.GestaoDeBiblioteca.Repository;

import com.example.GestaoDeBiblioteca.Entities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {
    Autor findByNome(String nome);
}
