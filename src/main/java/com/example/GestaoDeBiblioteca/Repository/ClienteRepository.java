package com.example.GestaoDeBiblioteca.Repository;

import com.example.GestaoDeBiblioteca.Entities.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Clientes, UUID> {
    Clientes findByEmail(String email);

    Clientes findByNomeAndEmail(String nome, String email);
}
